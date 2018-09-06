package top.qiaokeke.wechatbackend.dataaccess.entity.views;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskView {
    private String tId;
    private String tChargeAmout;
    private String tGiftPicUrl;
    private String tGift;
    private String tReward;
}
