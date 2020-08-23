package thh.studycode.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class App {

    public final static String zkCluster = "192.168.79.101:2181,192.168.79.102:2181,192.168.79.103:2181,192.168.79.104:2181";

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            /*
                watch 观察，回调
                第一类：在new zk 的时候传入的watch，这个是session级别的，跟node，path没有关系，所以在节点变化的时候是收不到回调的。
                第二类：在节点上添加的watch, 是一次性的。
                watch 的事件只发生在读(get|exists)方法之上的
             */
            ZooKeeper zk = new ZooKeeper(zkCluster, 1000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    log.info("default watch: {}", watchedEvent.toString());

                    Event.EventType type = watchedEvent.getType();
                    Event.KeeperState state = watchedEvent.getState();
                    String path = watchedEvent.getPath();

                    switch (state) {
                        case Unknown:
                            log.info("State = Unknown: {}", state);
                            break;
                        case Disconnected:
                            log.info("State = Disconnected: {}", state);
                            break;
                        case NoSyncConnected:
                            log.info("State = NoSyncConnected: {}", state);
                            break;
                        case SyncConnected:
                            log.info("State = SyncConnected: {}", state);
                            latch.countDown();
                            break;
                        case AuthFailed:
                            log.info("State = AuthFailed: {}", state);
                            break;
                        case ConnectedReadOnly:
                            log.info("State = ConnectedReadOnly: {}", state);
                            break;
                        case SaslAuthenticated:
                            log.info("State = SaslAuthenticated: {}", state);
                            break;
                        case Expired:
                            log.info("State = Expired: {}", state);
                            break;
                        case Closed:
                            log.info("State = Closed: {}", state);
                            break;
                    }
                    switch (type) {
                        case None:
                            log.info("Type = None: {}", state);
                            break;
                        case NodeCreated:
                            log.info("Type = NodeCreated: {}", state);
                            break;
                        case NodeDeleted:
                            log.info("Type = NodeDeleted: {}", state);
                            break;
                        case NodeDataChanged:
                            log.info("Type = NodeDataChanged: {}", state);
                            break;
                        case NodeChildrenChanged:
                            log.info("Type = NodeChildrenChanged: {}", state);
                            break;
                        case DataWatchRemoved:
                            log.info("Type = DataWatchRemoved: {}", state);
                            break;
                        case ChildWatchRemoved:
                            log.info("Type = ChildWatchRemoved: {}", state);
                            break;
                        case PersistentWatchRemoved:
                            log.info("Type = PersistentWatchRemoved: {}", state);
                            break;
                    }

                }
            });

            latch.await();
            ZooKeeper.States state = zk.getState();
            switch (state) {
                case CONNECTING:
                    log.info("ZooKeeper State = CONNECTING: {}", state);
                    break;
                case ASSOCIATING:
                    log.info("ZooKeeper State = ASSOCIATING: {}", state);
                    break;
                case CONNECTED:
                    log.info("ZooKeeper State = CONNECTED: {}", state);
                    break;
                case CONNECTEDREADONLY:
                    log.info("ZooKeeper State = CONNECTEDREADONLY: {}", state);
                    break;
                case CLOSED:
                    log.info("ZooKeeper State = CLOSED: {}", state);
                    break;
                case AUTH_FAILED:
                    log.info("ZooKeeper State = AUTH_FAILED: {}", state);
                    break;
                case NOT_CONNECTED:
                    log.info("ZooKeeper State = NOT_CONNECTED: {}", state);
                    break;
            }

            String name = zk.create("/node1/node2", "hello node2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            log.info("created node: {}", name);
            Stat stat = new Stat();
            byte[] data = zk.getData("/node1/node2", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    log.info("Watch callback :{}", watchedEvent.toString());
                    try {
                        zk.getData("/node1/node2", this, stat);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }, stat);
            log.info("getData: {}", new String(data));
            log.info("getData stat: {}", stat.toString());


            Stat stat1 = zk.setData("/node1/node2", "reset node data".getBytes(), 0);
            log.info("setData stat: {}", stat1.toString());

            Thread.sleep(5000);
            Stat stat2 = zk.setData("/node1/node2", "reset node data again".getBytes(), 1);
            log.info("setData stat: {}", stat2.toString());


            Thread.sleep(5000);
        } catch (IOException e) {
            log.error("error occured.", e);
        } catch (InterruptedException e) {
            log.error("error occured.", e);
        } catch (KeeperException e) {
            log.error("error occured.", e);
        }
    }
}
