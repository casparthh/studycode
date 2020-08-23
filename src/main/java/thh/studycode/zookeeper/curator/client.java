package thh.studycode.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;

@Slf4j
public class client {

    public final static String ZK_CLUSTER = "localhost:2181";


    public static void main(String[] args) throws Exception{
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curator = CuratorFrameworkFactory.newClient(ZK_CLUSTER, policy);
        try{
            //启动客户端实例，连接服务器
            curator.start();

            String node = "/node/node11";
            String data = "node-02";
            curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(node,data.getBytes());
            curator.setData().forPath(node, (data+data).getBytes());
            byte[] bytes = curator.getData().forPath(node);
            System.out.println(new String(bytes));
        } finally {
            CloseableUtils.closeQuietly(curator);
            log.info("end of test");
        }
    }


}
