package top.qiaokeke.wechatbackend.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Service;

import javax.persistence.*;

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
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
