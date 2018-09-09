package top.qiaokeke.wechatbackend.dataaccess.service;

import top.qiaokeke.wechatbackend.dataaccess.entity.AuthUser;
import top.qiaokeke.wechatbackend.dataaccess.entity.RoleType;
import top.qiaokeke.wechatbackend.dataaccess.entity.views.AuthUserView;

public interface IAuthUserService {
    public boolean checkAdminUserByAuidAndPassword(String auid, String password);
    public boolean checkUserByAuidAndPassword(String auid,String password);
    public AuthUser getAuthUserByAuid(String auid);
    public boolean isExistsByAuid(String auid);
    public AuthUserView getAuthUserViewByAuid(String auid);

    public String addAuthUserByAuidAndPassword(String auid, String password, RoleType roleType);
}
