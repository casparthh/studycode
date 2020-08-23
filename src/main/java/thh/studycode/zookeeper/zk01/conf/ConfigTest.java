package thh.studycode.zookeeper.zk01.conf;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ConfigTest {


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                String thread = Thread.currentThread().getName();
                ConfigUtil configUtil = new ConfigUtil();
                // 通过 AutoCloseable 自动关闭资源
                try (ZooKeeper zooKeeper = configUtil.getZooKeeper()) {
                    configUtil.readConfig("/app/conf");
                    for (; ; ) {
                        if (configUtil.getConf() != null) {
                            String data = new String(configUtil.getConf().getData());
                            log.info("thread:{} read conf: {}", thread, data);
                            if (data.equalsIgnoreCase("stop")) {
                                log.info("thread:{} exiting....", thread);
                                break;
                            }
                        } else {
                            log.info("thread:{} has no conf....", thread);
                        }
                        TimeUnit.SECONDS.sleep(3);
                    }
                    log.info("done !!!");
                } catch (InterruptedException e) {
                    log.error("auto close error !!!", e);
                } finally {
                    log.info("thread:{} finish test!!!", thread);
                }
            },"Thread - "+ i).start();
        }

    }
}
