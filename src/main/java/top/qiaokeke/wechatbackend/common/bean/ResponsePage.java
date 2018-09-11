package top.qiaokeke.wechatbackend.common.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponsePage {
    private long total;
    private Object items;

    public ResponsePage(long total,Object items){
        this.total = total;
        this.items = items;
    }

}
