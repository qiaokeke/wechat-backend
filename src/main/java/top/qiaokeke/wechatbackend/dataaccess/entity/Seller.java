package top.qiaokeke.wechatbackend.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity
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
    private boolean isActive;

}
