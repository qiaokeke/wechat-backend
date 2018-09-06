package top.qiaokeke.wechatbackend.web.user;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.qiaokeke.wechatbackend.authorize.util.JWTUtil;
import top.qiaokeke.wechatbackend.common.bean.ResponseBean;
import top.qiaokeke.wechatbackend.common.bean.ResponseConstants;
import top.qiaokeke.wechatbackend.dataaccess.entity.SysUser;
import top.qiaokeke.wechatbackend.dataaccess.entity.UserType;
import top.qiaokeke.wechatbackend.dataaccess.entity.views.SysUserView;
import top.qiaokeke.wechatbackend.dataaccess.service.impl.SysUserService;

import javax.websocket.server.ServerEndpoint;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class SysUserController {

    Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    SysUserService sysUserService;

    @PostMapping("/login")
    public ResponseBean login(@RequestBody Map<String,String> loginMap){
        logger.info("Login Request Map:{}",loginMap);

        boolean isAccess = sysUserService.checkUserByUidAndUpassword(loginMap.get("username"),loginMap.get("password"));
        if(!isAccess){
            return new ResponseBean(ResponseConstants.RES_CODE_ILL_TOKEN,ResponseConstants.RES_MSG_ILL_TOKEN,null);
        }
        return new ResponseBean(ResponseConstants.RES_CODE_OK,ResponseConstants.RES_MSG_OK, JWTUtil.sign(loginMap.get("username"),loginMap.get("password")));
    }


    @GetMapping("/info")
    @RequiresAuthentication
    public ResponseBean info(@RequestParam String token){
        String uid = JWTUtil.getUsername(token);
        SysUser sysUser = sysUserService.getSysUserByUid(uid);
        if (sysUser==null){
            return new ResponseBean(ResponseConstants.RES_CODE_USER_NOT_FOUND,ResponseConstants.RES_MSG_USER_NOT_FOUND,null);
        }

        // 转换用户视图
        List<UserType> roleList = new LinkedList<>();
        roleList.add(sysUser.getUserType());
        SysUserView sysUserView = new SysUserView();
        sysUserView.setName(sysUser.getUname());
        sysUserView.setRoles(roleList);
        sysUserView.setAvatar(sysUser.getAvatar());
        sysUserView.setToken("token");

        return new ResponseBean(ResponseConstants.RES_CODE_OK,ResponseConstants.RES_MSG_OK,sysUserView);

    }

}
