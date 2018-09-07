package top.qiaokeke.wechatbackend.dataaccess.service.impl;

import org.springframework.stereotype.Service;
import top.qiaokeke.wechatbackend.dataaccess.entity.SysUser;
import top.qiaokeke.wechatbackend.dataaccess.service.IAuthUserService;

@Service
public class AuthUserService implements IAuthUserService {

    @Override
    public boolean checkUserByAuidAndPassword(String uid, String upassword) {
        return false;
    }

    @Override
    public SysUser getSysUserByUid(String uid) {
        return null;
    }
}
