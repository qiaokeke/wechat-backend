package top.qiaokeke.wechatbackend.dataaccess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.qiaokeke.wechatbackend.dataaccess.entity.SysUser;
import top.qiaokeke.wechatbackend.dataaccess.repository.SysUserRepository;
import top.qiaokeke.wechatbackend.dataaccess.service.ISysUserService;

@Service
public class SysUserService implements ISysUserService {

    @Autowired
    SysUserRepository sysUserRepository;

    public boolean checkUserByUidAndUpassword(String uid, String upassword) {

        SysUser sysUser = sysUserRepository.getSysUserByUid(uid);
        if(sysUser==null) return false;
        if(sysUser.getUpassword().equals(upassword)) return true;
        return false;
    }

    @Override
    public SysUser getSysUserByUid(String uid) {
        return sysUserRepository.getSysUserByUid(uid);
    }

}
