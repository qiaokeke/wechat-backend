package top.qiaokeke.wechatbackend.sqltest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import top.qiaokeke.wechatbackend.dataaccess.entity.Seller;
import top.qiaokeke.wechatbackend.dataaccess.entity.SysUser;
import top.qiaokeke.wechatbackend.dataaccess.entity.UserType;
import top.qiaokeke.wechatbackend.dataaccess.repository.SellerRepository;
import top.qiaokeke.wechatbackend.dataaccess.repository.SysUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserRepositoryTest {

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    SellerRepository sellerRepository;

    //生成数据
    @Test
    public void SysUserSave(){
        for (int i=1;i<=100;i++) {
            SysUser sysUser = new SysUser();
            sysUser.setUid("wechat"+String.valueOf(i));
            sysUser.setUpassword("wechat"+String.valueOf(i));
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

    @Test
    public void SellerSelectTest(){
        Seller seller = sellerRepository.getSellerBySellerId("auth@10001");
        System.out.println(seller);

        Pageable pageable = new PageRequest(0,20);
        Page<Seller> sellerPage = sellerRepository.getAllBySellerIdLike("%seller100%",pageable);

        for(Seller seller1: sellerPage){
            System.out.println(seller1);
        }

    }


}
