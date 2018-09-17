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

@RestController
@RequestMapping("/wechater")
public class WechaterController {

    @GetMapping("/login")
    public String login(@RequestParam String code) throws WxErrorException {

        //根据code
        WxMpService wxMpService = WxMpConfiguration.getMpServices().get("wxbd140862c4f2afc6");
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);

        String token = JWTUtil.sign(wxMpOAuth2AccessToken.getOpenId(),wxMpOAuth2AccessToken.getOpenId());

        return token;
    }
}
