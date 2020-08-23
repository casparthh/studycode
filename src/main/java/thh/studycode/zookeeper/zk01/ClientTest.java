package thh.studycode.zookeeper.zk01;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ClientTest {

    public final static String ZK_CLUSTER = "localhost:2181/java";

    private static volatile ZooKeeper zooKeeper = null;

    public static ZooKeeper getZookeeer() throws IOException, InterruptedException {
        if (zooKeeper != null) {
            return zooKeeper;
        }

        synchronized (ClientTest.class) {
            if (zooKeeper != null) {
                return zooKeeper;
            }
            CountDownLatch conectLatch = new CountDownLatch(1);
            // 在new zk 的时候传入的watch，这个是session级别的，跟node，path没有关系，所以在节点变化的时候是收不到回调的。
            zooKeeper = new ZooKeeper(ZK_CLUSTER, 100000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    switch (watchedEvent.getType()) {
                        case None:
                            System.out.println("Type : Node");
                            break;
                        case NodeCreated:
                            System.out.println("Type : NodeCreated");
                            break;
                        case NodeDeleted:
                            System.out.println("Type : NodeDeleted");
                            break;
                        case NodeDataChanged:
                            System.out.println("Type : NodeDataChanged");
                            break;
                        case NodeChildrenChanged:
                            System.out.println("Type : NodeChildrenChanged");
                            break;
                        case DataWatchRemoved:
                            System.out.println("Type : DataWatchRemoved");
                            break;
                        case ChildWatchRemoved:
                            System.out.println("Type : ChildWatchRemoved");
                            break;
                        case PersistentWatchRemoved:
                            System.out.println("Type : PersistentWatchRemoved");
                            break;
                    }

                    switch (watchedEvent.getState()) {
                        case Unknown:
                            System.out.println("State : Unknown");
                            break;
                        case Disconnected:
                            System.out.println("State : Disconnected");
                            break;
                        case NoSyncConnected:
                            System.out.println("State : NoSyncConnected");
                            break;
                        case SyncConnected:
                            conectLatch.countDown();
                            System.out.println("State : SyncConnected");
                            break;
                        case AuthFailed:
                            System.out.println("State : AuthFailed");
                            break;
                        case ConnectedReadOnly:
                            System.out.println("State : ConnectedReadOnly");
                            break;
                        case SaslAuthenticated:
                            System.out.println("State : SaslAuthenticated");
                            break;
                        case Expired:
                            System.out.println("State : Expired");
                            break;
                        case Closed:
                            System.out.println("State : Closed");
                            break;
                    }
                    log.info("Type : {}", watchedEvent.getType());
                    log.info("State : {}", watchedEvent.getState());
                }
            });
            conectLatch.await();
            return zooKeeper;
        }

    }

    public static boolean addNode(String path, String data) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = ClientTest.getZookeeer();
        Stat stat = zk.exists(path, false);
        if (stat != null) {
            zk.delete(path, stat.getVersion());
        } else {
            zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        log.info("created node: ", path);
        return true;
    }

    public static boolean saveOrUpdateNode(String path, String data) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = ClientTest.getZookeeer();
        Stat stat = zk.exists(path, false);
        if (stat != null) {
            zk.setData(path, data.getBytes(), stat.getVersion());
        } else {
            addNode(path, data);
        }
        return true;
    }

    public static boolean deleteNode(String path) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = ClientTest.getZookeeer();
        Stat stat = zk.exists(path, false);
        if (stat != null) {
            zk.delete(path, stat.getVersion());
        }
        return true;
    }

    public static String getNode(String path) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = ClientTest.getZookeeer();

        //这里的watcher 事件是一次性的。
        byte[] data = zk.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                switch (event.getType()) {
                    case None:
                        break;
                    case NodeCreated:
                        log.info("节点[{}]被创建了", path);
                        break;
                    case NodeDeleted:
                        log.info("节点[{}]被删除了", path);
                        break;
                    case NodeDataChanged:
                        log.info("节点[{}]被修改了", path);
                        break;
                    case NodeChildrenChanged:
                        log.info("节点[{}]的子节点被修改了", path);
                        break;
                    case DataWatchRemoved:
                        log.info("节点[{}] DataWatchRemoved", path);
                        break;
                    case ChildWatchRemoved:
                        log.info("节点[{}] ChildWatchRemoved", path);
                        break;
                    case PersistentWatchRemoved:
                        log.info("节点[{}] PersistentWatchRemoved", path);
                        break;
                }
            }
        }, null);
        return new String(data);
    }

    public static void main(String[] args) {
        try {
            ClientTest.addNode("/caspar", "hello caspar !!!");
            ClientTest.saveOrUpdateNode("/caspar", "Hello Caspar.");
            TimeUnit.SECONDS.sleep(5);
            ClientTest.getNode("/caspar");
            ClientTest.deleteNode("/caspar");
            boolean a = ClientTest.deleteNode("/caspar01");
            System.out.println("done");
        } catch (Exception e) {
            log.error("error occured", e);
        } finally {
            try {
                if (zooKeeper != null) {
                    zooKeeper.close();
                }
            } catch (InterruptedException e) {
                log.error("close zookeeper error.", e);
            }
        }
    }

}
