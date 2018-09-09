package top.qiaokeke.wechatbackend.dataaccess.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.ActiveType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"auid","adminId"})})
public class Adminer {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String auid;
    private String adminId;
    private String adminName;
    private String adminPicUrl;
    private String adminDesp;

    private ActiveType activeType;
    private Date creteTime;
    private Date updateTime;
}
