package top.qiaokeke.wechatbackend.web.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechater")
public class WechaterController {

    @GetMapping("/login")
    public String login(@RequestParam String code){

        //根据code

        return null;
    }
}
