package top.qiaokeke.wechatbackend.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class Commodity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String cid;
    private String cname;
    private String cpicUrl;
    private String chargeAmount;
    private String gift;
    private String reward;
    private String rewardPicUrl;
    private Date uploadTime;
    private Date preheatStartTime;
    private Date preheatEndTime;
    private Date publishStartTime;
    private Date publishEndTime;
    private Date updateTime;


}
