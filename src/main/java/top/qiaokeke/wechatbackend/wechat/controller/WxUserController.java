package top.qiaokeke.wechatbackend.wechat.controller;

import top.qiaokeke.wechatbackend.wechat.config.WxMpConfiguration;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wx/user/{appid}")
public class WxUserController {

    @RequestMapping("/userList")
    public WxMpUserList userList(@PathVariable String appid) throws WxErrorException {

        return WxMpConfiguration.getMpServices().get(appid).getUserService().userList(null);
    }
    @GetMapping("/userList/{nextOpenid}")
    public WxMpUserList userList(@PathVariable String appid, @PathVariable String nextOpenid) throws WxErrorException {

        return WxMpConfiguration.getMpServices().get(appid).getUserService().userList(nextOpenid);
    }

    @GetMapping("/userInfo/{openid}")
    public WxMpUser userInfo(@PathVariable String appid, @PathVariable String openid) throws WxErrorException {
        return WxMpConfiguration.getMpServices().get(appid).getUserService().userInfo(openid);
    }

    @PostMapping("/userListInfo")
    public List<WxMpUser> userListInfo(@PathVariable String appid, @RequestParam List<String> openids) throws WxErrorException {
        return WxMpConfiguration.getMpServices().get(appid).getUserService().userInfoList(openids);
    }


}
