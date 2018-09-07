package top.qiaokeke.wechatbackend.dataaccess.service;

import top.qiaokeke.wechatbackend.dataaccess.entity.SysUser;

public interface IAuthUserService {
    public boolean checkUserByAuidAndPassword(String uid,String upassword);
    public SysUser getSysUserByUid(String uid);
}
