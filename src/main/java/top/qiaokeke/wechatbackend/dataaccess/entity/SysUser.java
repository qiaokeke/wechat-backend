package top.qiaokeke.wechatbackend.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"uid"})})
public class SysUser {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    public SysUser(){

    }
    public SysUser(String uid){
        this.uid = uid;
    }

    private String uid;
    private String uname;
    private String upassword;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Enumerated(EnumType.STRING)
    private SexualType usexual;
    private String uage;
    private String openId;
    private String wechatId;
    private String wechatName;
    private String wechatPicUrl;
    private String utaobaoId;
    private String utaobaoName;
    private int utaoqiValue;
    private String uqqId;
    private String uqqName;
    private String shopId;
    private String shopName;
    private String shopUrl;

    private String umark;
    private String mark;


}
