package top.qiaokeke.wechatbackend.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.ActiveType;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"auid","sellerId"})})
public class Seller {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String auid;
    private String sellerId;
    private String sellerName;
    private String sellerShopName;
    private String sellerShopUrl;
    private String sellerWechatId;
    private String sellerWechatName;
    private String sellerQQId;
    private String sellerQQName;
    private String sellerPhoneNumber;
    private ActiveType isActive;
    private Date createTime;
    private Date updateTime;

}
