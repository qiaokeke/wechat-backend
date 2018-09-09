package top.qiaokeke.wechatbackend.dataaccess.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.OrderStatus;

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

    private String orderId;
    private String tId;
    private String wechaterId;
    private OrderStatus orderStatus;

    private Date createTime;
    private Date updateTime;
}
