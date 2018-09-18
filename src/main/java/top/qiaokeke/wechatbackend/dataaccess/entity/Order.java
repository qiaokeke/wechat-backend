package top.qiaokeke.wechatbackend.dataaccess.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.OrderStatus;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.StepStatus;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String oId;
    private String tId;
    private String oWechaterId;
    private OrderStatus oStatus;
    private StepStatus oStepStatus;

    private String oSearchPicUrl;
    private String oTaoCmd;
    private String oTaobaoOrderId;
    private String oTaobaoOrderPicUrl;
    private String oWechatPayPicUrl;

    private Date createTime;
    private Date updateTime;
}
