package thh.studycode.zookeeper.conf;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

@Setter
@Getter
public class Configuration {

    private byte[] contents;
    private Date ctime;
    private Date mtime;

    public Configuration(byte[] contents) {
        this.contents = contents;
    }

    public Configuration(byte[] contents, Date ctime, Date mtime) {
        this.contents = contents;
        this.ctime = ctime;
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        String c = DateFormatUtils.format(ctime,"yyyy-MM-dd HH:mm:ss");
        String m = DateFormatUtils.format(mtime,"yyyy-MM-dd HH:mm:ss");
        return "ctime="+c+"\tmtime="+m+"\t"+new String(contents);
    }
}
