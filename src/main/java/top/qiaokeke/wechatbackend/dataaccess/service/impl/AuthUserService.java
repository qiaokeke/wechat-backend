package top.qiaokeke.wechatbackend.dataaccess.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.qiaokeke.wechatbackend.common.bean.ResponseConstants;
import top.qiaokeke.wechatbackend.dataaccess.entity.*;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.ActiveType;
import top.qiaokeke.wechatbackend.dataaccess.entity.views.AuthUserView;
import top.qiaokeke.wechatbackend.dataaccess.repository.AdminerRepository;
import top.qiaokeke.wechatbackend.dataaccess.repository.AuthUserRepository;
import top.qiaokeke.wechatbackend.dataaccess.repository.AuthUserRoleRepository;
import top.qiaokeke.wechatbackend.dataaccess.service.IAuthUserService;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthUserService implements IAuthUserService {

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    AuthUserRoleRepository authUserRoleRepository;

    @Autowired
    AdminerRepository adminerRepository;

    Logger logger = LoggerFactory.getLogger(AuthUserService.class);

    @Override
    public boolean checkAdminUserByAuidAndPassword(String auid, String password) {

        AuthUser authUser = authUserRepository.getAuthUserByAuid(auid);
        if(authUser==null) return false;
        if(!authUser.getPassword().equals(password))return false;
        if(!authUser.getRoleType().equals(RoleType.ADMIN))return false;

        return true;
    }

    @Override
    public boolean checkUserByAuidAndPassword(String auid, String password) {
        AuthUser authUser = authUserRepository.getAuthUserByAuid(auid);
        if(authUser==null) return false;
        if(!authUser.getPassword().equals(password))return false;
        return true;
    }

    @Override
    public AuthUser getAuthUserByAuid(String auid) {
        return authUserRepository.getAuthUserByAuid(auid);
    }

    @Override
    public boolean isExistsByAuid(String auid) {
        return authUserRepository.existsByAuid(auid);
    }

    @Override
    public AuthUserView getAuthUserViewByAuid(String auid) {
        if(!authUserRepository.existsByAuid(auid))
            return null;
        Adminer adminer = adminerRepository.getAdminerByAuid(auid);
        AuthUserView authUserView = new AuthUserView();
        authUserView.setName(adminer.getAdminName());
        authUserView.setIntroduction(adminer.getAdminDesp());
        authUserView.setAvatar(adminer.getAdminPicUrl());

        List<AuthUserRole> authUserRoles = authUserRoleRepository.getAuthUserRoleByAuid(auid);
        List<String> authUserRolesString = new LinkedList<>();
        for (AuthUserRole role: authUserRoles){
            authUserRolesString.add(role.getRoleType().name());
        }
        authUserView.setRoles(authUserRolesString);

        return authUserView;
    }

    @Override
    public String addAuthUserByAuidAndPassword(String auid, String password,RoleType roleType) {
        if(authUserRepository.existsByAuid(auid))
            return ResponseConstants.RespData.USER_EXIST;
        AuthUser authUser = new AuthUser();
        authUser.setAuid(auid);
        authUser.setPassword(password);
        authUser.setRoleType(roleType);
        authUser.setIsActive(ActiveType.TRUE);
        authUser.setCreateTime(new Date());
        authUser.setUpdateTime(new Date());
        try {
            authUserRepository.save(authUser);
        }catch (Exception e){
            logger.error("authuer add error:{}",e);
            return ResponseConstants.RespData.ADD_FAIL;
        }
        return ResponseConstants.RespData.ADD_SUCCESS;
    }
}
