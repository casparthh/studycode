package thh.studycode.zookeeper.zk01.conf;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ConfigTest {


    public static void main(String[] args) {
        ConfigUtil configUtil = new ConfigUtil();
        try (ZooKeeper zooKeeper = configUtil.getZooKeeper()) {
            configUtil.readConfig("/app/conf");
            for (; ; ) {
                if (configUtil.getConf() != null) {
                    String data = new String(configUtil.getConf().getData());
                    log.info("conf: {}", data);
                    if (data.equalsIgnoreCase("stop")) {
                        log.info("exiting....");
                        break;
                    }
                } else {
                    log.info("has no conf....");
                }
                TimeUnit.SECONDS.sleep(3);
            }
            log.info("done !!!");
        } catch (InterruptedException e) {
            log.error("auto close error !!!", e);
        } finally {
            log.info("finish test!!!");
        }

    }
}
