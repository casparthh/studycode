package thh.studycode.zookeeper.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class ZookeeperUtil {

    public final static String zkCluster = "192.168.79.101:2181,192.168.79.102:2181,192.168.79.103:2181,192.168.79.104:2181";
    private ZooKeeper zk = null;
    private CountDownLatch latch = new CountDownLatch(1);

    public ZooKeeper getZk() {
        if (zk != null) {
            return zk;
        }
        try {
            zk = new ZooKeeper(zkCluster, 2000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    Event.KeeperState state = watchedEvent.getState();
                    log.info("Connect Watch Event: {}", watchedEvent.toString());
                    switch (state) {
                        case SyncConnected:
                            latch.countDown();
                            break;
                    }

                }
            });
            latch.await();
        } catch (InterruptedException e) {
            log.error("error occured.", e);
        } catch (IOException e) {
            log.error("error occured.", e);
        }
        return zk;
    }
}
