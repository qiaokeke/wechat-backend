package top.qiaokeke.wechatbackend.dataaccess.entity.views;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskView {
    private String tId;
    private String tName;
    private String tSellerId;
    private String tChargeAmout;
    private String tProgress;
    private String tTotal;
    private int tPrecent;
    private String tGiftPicUrl;
    private String tGift;
    private String tReward;
    private String tPreheatTime;
    private String tPublishTime;
    private String tFinishTime;
}
