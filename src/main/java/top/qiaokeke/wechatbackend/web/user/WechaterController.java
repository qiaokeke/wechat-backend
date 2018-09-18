package top.qiaokeke.wechatbackend.web.user;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.qiaokeke.wechatbackend.authorize.util.JWTUtil;
import top.qiaokeke.wechatbackend.wechat.config.WxMpConfiguration;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import top.qiaokeke.wechatbackend.authorize.util.JWTUtil;
import top.qiaokeke.wechatbackend.common.bean.ResponseBean;
import top.qiaokeke.wechatbackend.common.bean.ResponseConstants;
import top.qiaokeke.wechatbackend.wechat.config.WxMpConfiguration;

import java.util.Map;

@RestController
@RequestMapping("/wechater")
public class WechaterController {


    Logger logger = LoggerFactory.getLogger(WechaterController.class);

    @PostMapping("/login")
    public ResponseBean login(@RequestBody Map<String,String> code) {
        logger.info("login request:{}",code);
        logger.info(code.get("code"));
        //根据code获取token和用户信息
        /*WxMpUser wxMpUser = null;
        try {
            final WxMpService wxService = WxMpConfiguration.getMpServices().get("wxbd140862c4f2afc6");
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code.get("code"));
            logger.info("wxMpOAuth2AccessToken:{}", wxMpOAuth2AccessToken);
            wxMpUser = wxService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
            logger.info("wxMpUser:{}", wxMpUser);
        }catch (Exception e){
            logger.error("oauth2getAccessToken error:{}",e.getMessage());
            return new ResponseBean(ResponseConstants.RespCode.ILL_TOKEN,ResponseConstants.RespMsg.ILL_TOKEN,null);
        }
        String token = JWTUtil.sign(wxMpUser.getOpenId(),wxMpUser.getOpenId());*/
        String token = JWTUtil.sign("aa","aa");
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,token);
    }

    @PostMapping("/code")
    public String echoCode(@RequestBody Map<String,String> code){
        logger.info("login code request:{}",code);
        return code.get(code);
    }
}
