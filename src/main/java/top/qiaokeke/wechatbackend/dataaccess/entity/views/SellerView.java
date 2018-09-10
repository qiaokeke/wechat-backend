package top.qiaokeke.wechatbackend.dataaccess.entity.views;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SellerView {

    String auid;
    String username;
    String shopName;
    String shopUrl;
    String wechatId;
    String wechatName;
    String qqId;
    String qqName;
    String phoneNumber;

}
