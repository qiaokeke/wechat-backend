package top.qiaokeke.wechatbackend.dataaccess.service;

import top.qiaokeke.wechatbackend.dataaccess.entity.SysUser;

public interface ISysUserService {
    public boolean checkUserByUidAndUpassword(String uid,String upassword);
    public SysUser getSysUserByUid(String uid);
}
