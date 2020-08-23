package thh.studycode.zookeeper.zk01.conf;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Getter
public class ConfigUtil implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    private static volatile ZooKeeper zookeeper;
    private CountDownLatch connectLatch = new CountDownLatch(1);

    private volatile Config conf = null;

    //本机测试只用了单机
    private final static String ZK_ClUSTER = "localhost:2181/java";

    /**
     * 单例模式实现 Zookeeper 客户端连接
     *
     * @return
     */
    public ZooKeeper getZooKeeper() {
        if (zookeeper != null) {
            return zookeeper;
        }

        synchronized (ConfigUtil.class) {
            if (zookeeper != null) {
                return zookeeper;
            }
            try {
                //在new zk 的时候传入的watch，这个是session级别的，跟node，path没有关系，所以在节点变化的时候是收不到回调的。
                zookeeper = new ZooKeeper(ZK_ClUSTER, 1000, this);
                connectLatch.await();
            } catch (IOException e) {
                log.error("get connection error!", e);
            } catch (InterruptedException e) {
                log.error("get connection error!", e);
            }
            return zookeeper;
        }
    }

    @Override
    public void process(WatchedEvent event) {
        log.info(event.toString());
        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                // 监控到节点创建后，拉取最新信息
                System.out.println("node created !!!!!!!!!!!!!!!!!!!!!!!");
                this.getZooKeeper().getData(event.getPath(), this, this, null);
                break;
            case NodeDeleted:
                conf = null;
                // 监控到节点删除后，清空数据，并从新watcher 节点
                readConfig(event.getPath());
                break;
            case NodeDataChanged:
                // 监控到节点修改后，拉取最新信息
                this.getZooKeeper().getData(event.getPath(), this, this, null);
                break;
            case NodeChildrenChanged:
                break;
            case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
            case PersistentWatchRemoved:
                break;
        }

        switch (event.getState()) {
            case Disconnected:
                break;
            case SyncConnected:
                //通过 CountDownLatch 控制 zookeeper 客户端的初使链接
                connectLatch.countDown();
                break;
            case AuthFailed:
                break;
            case ConnectedReadOnly:
                break;
            case SaslAuthenticated:
                break;
            case Expired:
                break;
            case Closed:
                break;
        }
    }

    /**
     * This callback is used to retrieve the stat of the node
     *
     * @param rc   The return code or the result of the call.
     * @param path The path that we passed to asynchronous calls.
     * @param ctx  Whatever context object that we passed to asynchronous calls.
     * @param stat {@link Stat} object of the node on given path.
     * @see ZooKeeper#exists(String, boolean, StatCallback, Object)
     * @see ZooKeeper#exists(String, Watcher, StatCallback, Object)
     * @see ZooKeeper#setData(String, byte[], int, StatCallback, Object)
     * @see ZooKeeper#setACL(String, List, int, StatCallback, Object)
     */
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        log.info("DataCallback event: rc={}, path={}, ctx={}, stat={}", rc, path, ctx, stat);
        if (KeeperException.Code.OK.intValue() == rc) {
            // 节点存在，拉取数据
            this.getZooKeeper().getData(path, this, this, null);
        }
    }

    /**
     * This callback is used to retrieve the data and stat of the node
     *
     * @param rc   The return code or the result of the call.
     * @param path The path that we passed to asynchronous calls.
     * @param ctx  Whatever context object that we passed to asynchronous calls.
     * @param data The data of the node.
     * @param stat {@link Stat} object of the node on given path.
     * @see ZooKeeper#getData(String, boolean, DataCallback, Object)
     * @see ZooKeeper#getData(String, Watcher, DataCallback, Object)
     * @see ZooKeeper#getConfig(boolean, DataCallback, Object)
     * @see ZooKeeper#getConfig(Watcher, DataCallback, Object)
     */
    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        log.info("StatCallback event: rc={}, path={}, ctx={}, data={}, stat={}", rc, path, ctx, new String(data), stat);
        if (rc == KeeperException.Code.OK.intValue()) {
            conf = new Config(data, stat.getCtime(), stat.getMtime());
        }
    }

    /**
     * 先判断是否存在并注入watcher事件和callback
     * 这里要用exists, 因为当节不存在的时候，getData 的watcher监控不到创建节点的事件。
     */
    public void readConfig(String filepath) {
        this.getZooKeeper().exists(filepath, this, this, null);
        this.getZooKeeper().getData(filepath, this, this, null);
    }
}
