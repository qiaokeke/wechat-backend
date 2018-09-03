package top.qiaokeke.wechatbackend.sqltest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.qiaokeke.wechatbackend.dataaccess.entity.SysUser;
import top.qiaokeke.wechatbackend.dataaccess.entity.UserType;
import top.qiaokeke.wechatbackend.dataaccess.repository.SysUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserRepositoryTest {

    @Autowired
    SysUserRepository sysUserRepository;

    //生成数据
    @Test
    public void SysUserSave(){
        for (int i=1;i<=100;i++) {
            SysUser sysUser = new SysUser();
            sysUser.setUid(String.valueOf(i));
            sysUser.setUpassword(String.valueOf(i));
            if(i<40)
                sysUser.setUserType(UserType.WECHAT_USER);
            else if(i>80)
                sysUser.setUserType(UserType.SELLER);
            else
                sysUser.setUserType(UserType.ADMIN);

            sysUser.setUage(String.valueOf(i));
            sysUserRepository.save(sysUser);
        }

    }


}
