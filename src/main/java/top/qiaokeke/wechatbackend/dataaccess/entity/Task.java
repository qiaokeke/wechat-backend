package top.qiaokeke.wechatbackend.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"tId"})})
public class Task {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String tId;
    private String tName;
    private String tGiftPicUrl;
    private String tTaobaoUrl;
    private String tTaobaoId;
    private String tTaobaoPid;
    private String tTaobaoCmd;
    private String tDescription;
    private String tChargeAmout;
    private String tGift;
    private String tReward;
    private String tMark;
    private String tReserve;
    private Date tUploadTime;
    private Date tPreheatStartTime;
    private Date tPublishTime;
    private Date tFinishTime;
    private Date tUpdateTime;

}
