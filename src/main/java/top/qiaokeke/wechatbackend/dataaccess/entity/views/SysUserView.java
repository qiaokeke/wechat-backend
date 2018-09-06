package top.qiaokeke.wechatbackend.dataaccess.entity.views;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class SysUserView {

    private String name;
    private String introduction;
    private String avatar;
    private String token;

    private List roles;

}
