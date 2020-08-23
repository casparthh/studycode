package thh.studycode.zookeeper.zk01.conf;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Config {

    //配置文件内容
    private byte[] data;
    // 创建时间
    private long cdate;
    // 修改时间
    private long mdate;
}
