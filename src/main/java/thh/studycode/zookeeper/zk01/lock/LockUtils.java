package thh.studycode.zookeeper.zk01.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LockUtils implements Watcher, AsyncCallback.StringCallback, AsyncCallback.ChildrenCallback {

    private final static String ZK_CLUSTER = "localhost:2181/java";

    private CountDownLatch lockLatch;
    private static CountDownLatch connectLatch;

    private static ZooKeeper zooKeeper;

    private ThreadLocal<Integer> threadLock = new ThreadLocal<Integer>();
    private String lockFilename;

    public ZooKeeper getZooKeeper() {
        if (zooKeeper == null) {
            synchronized (LockUtils.class) {
                if (zooKeeper == null) {
                    connectLatch = new CountDownLatch(1);
                    try {
                        zooKeeper = new ZooKeeper(ZK_CLUSTER, 3000, new Watcher() {
                            @Override
                            public void process(WatchedEvent event) {
                                switch (event.getState()) {
                                    case SyncConnected:
                                        connectLatch.countDown();
                                        break;
                                }
                            }
                        });
                        connectLatch.await();
                    } catch (IOException e) {
                        log.error("error occured,", e);
                    } catch (InterruptedException e) {
                        log.error("error occured,", e);
                    }
                }
            }
        }
        return zooKeeper;
    }

    /**
     * 监控删除前一个锁的 ephemeral 节点的事件
     *
     * @param event
     */
    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case NodeDeleted:
                this.getZooKeeper().getChildren("/", null, this, "lock");
                break;
        }
    }

    /**
     * 创建序列节点成功回调
     * StringCallback
     * <p>On success, rc is {@link KeeperException.Code#OK}.
     *
     * <p>On failure, rc is set to the corresponding failure code in {@link KeeperException}.
     * <ul>
     * <li>{@link KeeperException.Code#NODEEXISTS}
     * - The node on give path already exists for some API calls.</li>
     * <li>{@link KeeperException.Code#NONODE}
     * - The node on given path doesn't exist for some API calls.</li>
     * <li>{@link KeeperException.Code#NOCHILDRENFOREPHEMERALS}
     * - An ephemeral node cannot have children. There is discussion in
     * community. It might be changed in the future.</li>
     * </ul>
     *
     * @param rc   The return code or the result of the call.
     * @param path The path that we passed to asynchronous calls.
     * @param ctx  Whatever context object that we passed to asynchronous calls.
     * @param name The name of the znode that was created. On success, <i>name</i>
     */
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        if (rc == KeeperException.Code.OK.intValue()) {
            // 创建序列节点成功，在回调方法中进行取锁
            this.getZooKeeper().getChildren("/", null, this, "lock");
            lockFilename = name;
        }
    }

    /**
     * 查询子所有ephemeral 节点的回调
     * ChilrenCallback
     *
     * @param rc
     * @param path
     * @param ctx
     * @param children
     */
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children) {
        Collections.sort(children);
        int index = children.indexOf(lockFilename.substring(1));
        if (index == 0) {
            //当前序列节点已经是第一个，获得锁。
            lockLatch.countDown();
        }
        if (index > 0) {
            try {
                Stat stat = this.getZooKeeper().exists("/" + children.get(index - 1), this);
                if (stat == null) {
                    // 这一步很重要
                    //在高并发情况下，如果查询的时候还在，但在exists 绑定watcher 的时候已经删除了，会导至 watcher无效。
                    //重新发起子节点的查询，并添加watcher 事件。
                    this.getZooKeeper().getChildren("/", null, this, "lock");
                    return;
                }
            } catch (InterruptedException e) {
                log.error("error occured.", e);
            } catch (KeeperException e) {
                log.error("error occured.", e);
            }
        }
    }

    /**
     * 获取锁的方法
     * 1. 创建 ephemeral and sequential 节点，使用响应式编程，在 StringCallback 中获取结果。
     * 2. 收到创建的 StringCallback，读取所有锁节点
     * 3. 判断当前节点是不是第一个，如果是第一个，获得锁成功
     * 4. 如果不是第一个，监控当前队列中的前一个节点的删除事件
     * 5. 如果前一个节点删除或退出
     * @param timeout 在指定时间内如果还没排到锁的话，做过期处理。
     * @return
     * @throws InterruptedException
     * @throws KeeperException
     */
    public boolean lock(int timeout) throws InterruptedException, KeeperException {
        Integer times = threadLock.get();
        if (times != null) {
            //重入锁， 如果当前线程已经取得锁+1；
            threadLock.set(++times);
            return true;
        }
        lockLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = this.getZooKeeper();
        zooKeeper.create("/lock", "lock".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, "lock");

        //超时返回。
        lockLatch.await(timeout, TimeUnit.SECONDS);
//        if (locked) {
        threadLock.set(1);
//        }
        return true;
    }

    public void unlock() throws InterruptedException, KeeperException {
        Integer times = threadLock.get();
        if (times <= 1) {
            //重入锁， 如果当前线程锁完全移除，删除节点。
            threadLock.remove();
            this.getZooKeeper().delete(lockFilename, 0);
        } else {
            //重入锁， 如果当前线程多次取得锁，unlock 一次减1.
            times--;
        }
        threadLock.set(times);
    }

}
