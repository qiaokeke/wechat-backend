package top.qiaokeke.wechatbackend.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Service;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.ActiveType;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"auid"})})
public class AuthUser {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String auid;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private ActiveType isActive;
    private Date createTime;
    private Date updateTime;

}
