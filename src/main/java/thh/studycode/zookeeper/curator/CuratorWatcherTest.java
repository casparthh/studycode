package thh.studycode.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class CuratorWatcherTest {

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


public static void main(String[] args) throws Exception {
    CuratorFramework client = CuratorWatcherTest.newClient();
    CountDownLatch latch = new CountDownLatch(1);
    String path = "/node";

    // 开启链接
    client.start();

    NodeCache nodeCache = new NodeCache(client, path);
    NodeCacheListener nodeCacheListener = new NodeCacheListener() {
        @Override
        public void nodeChanged() throws Exception {
            ChildData childData = nodeCache.getCurrentData();
            log.info("NodeCache the node[{}] changed..............", childData.getPath());
            log.info("NodeCache the new data: {}..............", new String(childData.getData()));
        }
    };

    nodeCache.getListenable().addListener(nodeCacheListener);
    nodeCache.start(true);

    PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
    PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
        @Override
        public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
            ChildData childData = event.getData();
            log.info("PathChildrenCache the node[{}] changed..............", childData.getPath());
            log.info("PathChildrenCache the new data: {}..............", new String(childData.getData()));
        }
    };

    pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
    pathChildrenCache.start(true);

    TreeCache treeCache = new TreeCache(client, path);;
    TreeCacheListener treeCacheListener = new TreeCacheListener() {
        @Override
        public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
            ChildData childData = event.getData();
            log.info("TreeCache the node[{}] changed..............", childData.getPath());
            log.info("TreeCache the new data: {}..............", new String(childData.getData()));
        }
    };
    treeCache.getListenable().addListener(treeCacheListener);
    treeCache.start();

    latch.await();
}
}
