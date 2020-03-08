package thh.studycode.zookeeper.conf;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestRunner {

    @Test
    public void test() {
        DefaultWatcher watcher = new DefaultWatcher();
        Configuration conf = null;
        try {
            while (true) {
                conf = watcher.readConfigration();
                System.out.println((conf== null || StringUtils.isBlank(conf.toString())) ? "the config is empty!!!!" : conf);
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (InterruptedException e) {
            log.error("error occured.", e);
        }
    }


}
