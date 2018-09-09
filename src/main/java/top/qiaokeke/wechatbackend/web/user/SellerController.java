package top.qiaokeke.wechatbackend.web.user;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.qiaokeke.wechatbackend.common.bean.ResponseBean;
import top.qiaokeke.wechatbackend.common.bean.ResponseConstants;
import top.qiaokeke.wechatbackend.dataaccess.entity.RoleType;
import top.qiaokeke.wechatbackend.dataaccess.service.impl.AuthUserService;

import java.util.Map;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    AuthUserService authUserService;

    @PostMapping("/account/add")
    @RequiresRoles("ADMIN")
    public ResponseBean add(@RequestBody Map<String,String> addMap){

        String auid = addMap.get("username");
        String password = addMap.get("password");

        String respData = authUserService.addAuthUserByAuidAndPassword(auid,password,RoleType.SELLER);

        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,respData);

    }



}
