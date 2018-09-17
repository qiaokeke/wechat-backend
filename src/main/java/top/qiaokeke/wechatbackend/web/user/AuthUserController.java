package top.qiaokeke.wechatbackend.web.user;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.qiaokeke.wechatbackend.authorize.util.JWTUtil;
import top.qiaokeke.wechatbackend.common.bean.ResponseBean;
import top.qiaokeke.wechatbackend.common.bean.ResponseConstants;
import top.qiaokeke.wechatbackend.dataaccess.entity.views.AuthUserView;
import top.qiaokeke.wechatbackend.dataaccess.service.impl.AuthUserService;

import java.util.Map;

@RestController
@RequestMapping("/authUser")
public class AuthUserController {

    Logger logger = LoggerFactory.getLogger(AuthUserController.class);

    @Autowired
    AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseBean login(@RequestBody Map<String,String> loginMap){
        logger.info("Login Request Map:{}",loginMap);

        boolean isAccess = authUserService.checkAdminUserByAuidAndPassword(loginMap.get("username"),loginMap.get("password"));
        if(!isAccess){
            return new ResponseBean(ResponseConstants.RespCode.ILL_TOKEN,ResponseConstants.RespMsg.ILL_TOKEN,null);
        }
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK, JWTUtil.sign(loginMap.get("username"),loginMap.get("password")));
    }

    @GetMapping("/info")
    @RequiresRoles("ADMIN")
    public ResponseBean info(@RequestParam String token){
        String auid = JWTUtil.getUsername(token);
        boolean isExists = authUserService.isExistsByAuid(auid);
        AuthUserView authUserView = authUserService.getAuthUserViewByAuid(auid);
        if(!isExists || authUserView==null)
            return new ResponseBean(ResponseConstants.RespCode.USER_NOT_FOUND,ResponseConstants.RespMsg.USER_NOT_FOUND,"");

        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,authUserView);

    }


}
