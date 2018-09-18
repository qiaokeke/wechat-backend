package top.qiaokeke.wechatbackend.dataaccess.entity.views;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class WechatOrderView {
    private String oId;
    private String tId;
    private String oWechaterId;
    private String oStatus;
    private String oStepStatus;

    private String oGoodsPicUrl;

    private String oSearchPicUrl;
    private String oTaoCmd;
    private String oTaobaoOrderId;
    private String oTaobaoOrderPicUrl;
    private String oWechatPayPicUrl;
    private Date finishTime;

}
