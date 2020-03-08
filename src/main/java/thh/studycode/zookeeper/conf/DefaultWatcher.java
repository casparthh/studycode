package thh.studycode.zookeeper.conf;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Date;

@Slf4j
public class DefaultWatcher implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

    private final static String FILEPATH = "/node1/conf";

    private ZooKeeper zk =null;
    public Configuration conf = null;

    public Configuration readConfigration() {
        if (zk == null) {
            zk =  ZookeeperUtil.getZk();;
            zk.exists(FILEPATH, this, this, "Caspar");
        }
        return conf;
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("watch event: {}", watchedEvent.toString());
        Event.EventType type = watchedEvent.getType();

        switch (type) {
            case NodeCreated:
                zk.getData(FILEPATH, this, this, "Caspar");
                break;
            case NodeDeleted:
                conf= null;
                zk.exists(FILEPATH, this, this, "Caspar");
                break;
            case NodeDataChanged:
                zk.getData(FILEPATH, this, this, "Caspar");
                break;
        }
    }

    @Override
    public void processResult(int i, String s, Object o, Stat stat) {
        log.info("StatCallback event: i={}, s={}, o:{}, stat={}", i, s, o, stat);
        if (stat != null) {
            zk.getData(FILEPATH, false, this, "Caspar");
        }
    }

    @Override
    public void processResult(int i, String node, Object ctx, byte[] bytes, Stat stat) {
        log.info("DataCallback event: i={}, s={}, o={}, byte[]={}, stat={}", i, node, ctx, new String(bytes), stat);
        conf = new Configuration(bytes, new Date(stat.getCtime()), new Date(stat.getMtime()));
    }
}
