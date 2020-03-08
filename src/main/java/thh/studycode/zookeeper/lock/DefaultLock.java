package thh.studycode.zookeeper.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

@Slf4j
public class DefaultLock implements Watcher {
    private final static String NODE_NAME = "/node1/lock";
    private final static String PARENT_NODE = "/node1";

    private ZooKeeper zk = null;
    private String lockFilename = null;
    CountDownLatch latch = null;

    public boolean lock() {
        latch = new CountDownLatch(1);
        try {
            if (StringUtils.isBlank(lockFilename)) {
                //先创建文件
                ZookeeperUtil util = new ZookeeperUtil();
                zk = util.getZk();
                lockFilename = zk.create(NODE_NAME, "lock".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            }
            //判断当前创建的文件是否是第一个
            this.fetchLock(lockFilename);
            latch.await();
        } catch (KeeperException e) {
            log.error("error occured.", e);
        } catch (InterruptedException e) {
            log.error("error occured.", e);
        }
        return true;
    }

    public void unlock() {
        try {
            //工作完成后，删除锁
            log.info("unlock: {}", lockFilename);
            zk.delete(lockFilename, 0);
            lockFilename = null;
        } catch (InterruptedException e) {
            log.error("error occured.", e);
        } catch (KeeperException e) {
            log.error("error occured.", e);
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        Event.EventType type = watchedEvent.getType();
        switch (type) {
            case NodeDeleted:
                //删除节点，触发所有后续watched事件
                this.fetchLock(lockFilename);
                break;
            case NodeDataChanged:
                break;
            case NodeChildrenChanged:
                break;

        }
    }

    /**
     * @param nodeName
     * @return
     */
    private void fetchLock(String nodeName) {
        List<String> nodes = null;
        try {
            nodes = zk.getChildren(PARENT_NODE, false);
            nodes = nodes.parallelStream().sorted(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.substring(NODE_NAME.length()).compareTo(o2.substring(NODE_NAME.length()));
                }
            }).collect(Collectors.toList());

            log.info("nodeName: {}   ---- nodelist: {}", nodeName, StringUtils.join(nodes, ","));
            if (nodeName.endsWith(nodes.get(0))) {
                //当前文件是第一个，成功持有锁
                latch.countDown();
            } else {
                //添加对当前持有锁的文件的监控
                Stat stat = new Stat();
                zk.getData(PARENT_NODE + "/" + nodes.get(0), this, stat);
            }
        } catch (KeeperException e) {
            log.error("error occured.", e);
        } catch (InterruptedException e) {
            log.error("error occured.", e);
        }
    }
}
