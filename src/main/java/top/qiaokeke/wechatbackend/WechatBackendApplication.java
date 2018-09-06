package top.qiaokeke.wechatbackend;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.qiaokeke.wechatbackend.dataaccess.entity.Customer;
import top.qiaokeke.wechatbackend.dataaccess.entity.SysUser;
import top.qiaokeke.wechatbackend.dataaccess.repository.CustomerRepository;
import top.qiaokeke.wechatbackend.dataaccess.repository.SysUserRepository;

@SpringBootApplication
public class WechatBackendApplication {

    private static final Logger log = LoggerFactory.getLogger(Application.class);



    public static void main(String[] args) {
        SpringApplication.run(WechatBackendApplication.class, args);
    }



}
