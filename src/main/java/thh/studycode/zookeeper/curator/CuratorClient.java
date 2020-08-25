package thh.studycode.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class CuratorClient {

    public final static String ZK_CLUSTER = "localhost:2181";

    public static CuratorFramework newClient() {
        // 重试策略: 第一次重试等待1秒，第二次重试等待2秒，第三次重试等待4秒
        // 第一个参数：等待时间的基础单位，单位为毫秒
        // 第二个参数：最大重试次数
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient(ZK_CLUSTER, policy);
    }

    public static CuratorFramework buildClient() {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.builder()
                .connectString(ZK_CLUSTER)
                .retryPolicy(policy)
                .connectionTimeoutMs(5000)
                .sessionTimeoutMs(2000)
                .build();
    }

    public static void createNode(String path, String contents) {
        CuratorFramework client = CuratorClient.newClient();
        client.start();
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, contents.getBytes());
        } catch (Exception e) {
            log.error("error occured,", e);
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    public static String getNode(String path) {
        CuratorFramework client = CuratorClient.newClient();
        client.start();
        String value = null;
        try {
            byte[] data = client.getData().forPath(path);
            value = new String(data);
        } catch (Exception e) {
            log.error("error occured,", e);
        } finally {
            CloseableUtils.closeQuietly(client);
        }
        return value;
    }


    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorClient.newClient();
        String path = "/node/node1/node2/node3";

        // 开启链接
        client.start();

        // 创建节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, "test".getBytes());

        // 更新节点数据
        client.setData().forPath(path, "update contents".getBytes());

        // 读取节点数据
        byte[] data = client.getData().forPath(path);
        String contents = new String(data);
        System.out.println(contents);

        CountDownLatch latch = new CountDownLatch(2);

        client.getData().usingWatcher(new Watcher() {
            // 使用 Watcher 监听节点
            @Override
            public void process(WatchedEvent event) {
                log.info("Type: {};  stat: {}", event.getType(), event.getState());
                switch (event.getType()) {
                    case None:
                        break;
                    case NodeCreated:
                        break;
                    case NodeDeleted:
                        log.info("the node[{}] has been deleted!", event.getPath());
                        latch.countDown();
                        break;
                    case NodeDataChanged:
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
            }
        }).inBackground(new BackgroundCallback() {
            // 使用 inBackground 设置回调方法读取节点信息
            @Override
            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                log.info("Type: {};  stat: {}", event.getType(), event.getStat());
                byte[] data = event.getData();
                log.info("get data: {}", new String(data));
                switch (event.getType()) {
                    case CREATE:
                        break;
                    case DELETE:
                        break;
                    case EXISTS:
                        break;
                    case GET_DATA:
                        latch.countDown();
                        break;
                    case SET_DATA:
                        break;
                    case CHILDREN:
                        break;
                    case SYNC:
                        break;
                    case GET_ACL:
                        break;
                    case SET_ACL:
                        break;
                    case TRANSACTION:
                        break;
                    case GET_CONFIG:
                        break;
                    case RECONFIG:
                        break;
                    case WATCHED:
                        break;
                    case REMOVE_WATCHES:
                        break;
                    case CLOSING:
                        break;
                    case ADD_WATCH:
                        break;
                }
            }
        }).forPath(path);

        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .inBackground((curator, event) -> {
                    log.info("Type: {};  stat: {}", event.getType(), event.getStat());
                    byte[] bytes = event.getData();
                    log.info("path: {}", event.getPath());
                }).forPath(path + "/abcd");

//        client.delete().forPath(path);
        latch.await();
    }
}
