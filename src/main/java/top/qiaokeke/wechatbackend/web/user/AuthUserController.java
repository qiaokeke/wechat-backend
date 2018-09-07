package top.qiaokeke.wechatbackend.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.qiaokeke.wechatbackend.authorize.util.JWTUtil;
import top.qiaokeke.wechatbackend.common.bean.ResponseBean;
import top.qiaokeke.wechatbackend.common.bean.ResponseConstants;
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

        boolean isAccess = authUserService.checkUserByAuidAndPassword(loginMap.get("username"),loginMap.get("password"));
        if(!isAccess){
            return new ResponseBean(ResponseConstants.RespCode.ILL_TOKEN,ResponseConstants.RespMsg.ILL_TOKEN,null);
        }
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK, JWTUtil.sign(loginMap.get("username"),loginMap.get("password")));
    }

}
