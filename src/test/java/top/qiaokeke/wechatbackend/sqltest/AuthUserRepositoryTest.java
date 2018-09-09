package top.qiaokeke.wechatbackend.sqltest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.qiaokeke.wechatbackend.dataaccess.entity.*;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.ActiveType;
import top.qiaokeke.wechatbackend.dataaccess.repository.*;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthUserRepositoryTest {

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    AdminerRepository adminerRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    WechaterRepository wechaterRepository;

    @Autowired
    AuthUserRoleRepository authUserRoleRepository;


    @Test
    public void save(){
        authUserRepository.deleteAll();
        authUserRoleRepository.deleteAll();
        sellerRepository.deleteAll();
        wechaterRepository.deleteAll();
        adminerRepository.deleteAll();

        for (int i=1 ;i<1000;i++){
            AuthUser authUser = new AuthUser();
            authUser.setIsActive(ActiveType.TRUE);
            authUser.setAuid("auth@"+String.valueOf(10000+i));
            authUser.setPassword("auth@"+String.valueOf(10000+i));

            switch (i%3){
                case 0:adminerRepository.save(buildAdminer(i));authUser.setRoleType(RoleType.ADMIN);break;
                case 1:sellerRepository.save(buildSeller(i));authUser.setRoleType(RoleType.SELLER);break;
                case 2:wechaterRepository.save(buildWechater(i));authUser.setRoleType(RoleType.WECHAT_USER);break;
            }
            authUserRepository.save(authUser);

            //save to role table

            AuthUserRole authUserRole = new AuthUserRole();
            authUserRole.setAuid(authUser.getAuid());
            authUserRole.setRoleType(authUser.getRoleType());

            authUserRoleRepository.save(authUserRole);
        }
    }

    static Adminer buildAdminer(int i){
        Adminer adminer = new Adminer();
        adminer.setAuid("auth@"+String.valueOf(10000+i));
        adminer.setAdminName("adminer"+String.valueOf(10000+i));
        adminer.setAdminId("adminer"+String.valueOf(10000+i));
        adminer.setAdminPicUrl("https://render.bitstrips.com/v2/cpanel/88952da0-e609-495a-8106-8b796bafb451-4d9bdedc-ec6a-413d-bb8d-96d4eee1dc22-v1.png?transparent=1&palette=1");
        adminer.setAdminDesp("admin desp");
        return adminer;
    }

    static Seller buildSeller(int i){
        Seller seller = new Seller();
        seller.setAuid("auth@"+String.valueOf(10000+i));
        seller.setSellerId("seller"+String.valueOf(10000+i));
        seller.setSellerName("seller"+String.valueOf(10000+i));
        seller.setIsActive(ActiveType.TRUE);
        seller.setSellerShopName("taobao");
        seller.setSellerShopUrl("taobaourl");
        seller.setSellerWechatId("wechat id ");
        seller.setSellerWechatName("wechatname");
        seller.setSellerQQId("qqid");
        seller.setSellerQQName("qqname");
        seller.setSellerPhoneNumber("phone number");
        seller.setCreateTime(new Date());
        seller.setUpdateTime(new Date());
        return seller;
    }
    static Wechater buildWechater(int i){
        Wechater wechater = new Wechater();
        wechater.setAuid("auth@"+String.valueOf(10000+i));
        wechater.setWId("wechater"+String.valueOf(10000+i));
        wechater.setCreateTime(new Date());
        wechater.setUpdateTime(new Date());
        wechater.setIsActive(ActiveType.TRUE);
        wechater.setWName("wechat name");
        wechater.setWOpenid("wechat openid");
        wechater.setWTaobaoId("taobao id");
        wechater.setWTaobaoName("tabao name");
        wechater.setWPhoneNumber("phone number");
        wechater.setWPicUrl("https://render.bitstrips.com/v2/cpanel/4d373c62-acb2-4215-9f0c-d74b92e3536c-4d9bdedc-ec6a-413d-bb8d-96d4eee1dc22-v1.png?transparent=1&palette=1");
        return wechater;
    }
    @Test
    public void saveSeller(){
        sellerRepository.deleteAll();
        for(int i=1;i<1000;i++){
            Seller seller = new Seller();
            seller.setAuid("auth@"+String.valueOf(10000+i));
            seller.setSellerId("seller"+String.valueOf(10000+i));
            seller.setSellerName("seller"+String.valueOf(10000+i));
            seller.setIsActive(ActiveType.TRUE);
            seller.setSellerShopName("taobao");
            seller.setSellerShopUrl("taobaourl");
            seller.setSellerWechatId("wechat id ");
            seller.setSellerWechatName("wechatname");
            seller.setSellerQQId("qqid");
            seller.setSellerQQName("qqname");
            seller.setSellerPhoneNumber("phone number");
            seller.setCreateTime(new Date());
            seller.setUpdateTime(new Date());
            sellerRepository.save(seller);
        }
    }

    @Test
    public void saveWechater(){
        wechaterRepository.deleteAll();
        for(int i=1;i<1000;i++){
            Wechater wechater = new Wechater();
            wechater.setAuid("auth@"+String.valueOf(10000+i));
            wechater.setWId("wechater"+String.valueOf(10000+i));
            wechater.setCreateTime(new Date());
            wechater.setUpdateTime(new Date());
            wechater.setIsActive(ActiveType.TRUE);
            wechater.setWName("wechat name");
            wechater.setWOpenid("wechat openid");
            wechater.setWTaobaoId("taobao id");
            wechater.setWTaobaoName("tabao name");
            wechater.setWPhoneNumber("phone number");
            wechater.setWPicUrl("https://render.bitstrips.com/v2/cpanel/4d373c62-acb2-4215-9f0c-d74b92e3536c-4d9bdedc-ec6a-413d-bb8d-96d4eee1dc22-v1.png?transparent=1&palette=1");
            wechaterRepository.save(wechater);
        }
    }


    @Test
    public void saveAuthUerRole(){



    }




}
