package thh.studycode.zookeeper.conf;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class ZookeeperUtil {

    public final static String zkCluster = "192.168.79.101:2181,192.168.79.102:2181,192.168.79.103:2181,192.168.79.104:2181";
    private static ZooKeeper zk = null;
    static CountDownLatch latch = new CountDownLatch(1);

    static {
        try {
            zk = new ZooKeeper(zkCluster, 1000, new Watcher() {
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
        } catch (IOException e) {
            log.error("error occure.", e);
        }
    }

    public static ZooKeeper getZk() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error("error occured.", e);
        }
        return zk;
    }


}
