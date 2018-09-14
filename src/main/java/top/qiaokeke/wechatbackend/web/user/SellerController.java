package top.qiaokeke.wechatbackend.web.user;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import top.qiaokeke.wechatbackend.common.bean.ResponseBean;
import top.qiaokeke.wechatbackend.common.bean.ResponseConstants;
import top.qiaokeke.wechatbackend.common.bean.ResponsePage;
import top.qiaokeke.wechatbackend.dataaccess.entity.RoleType;
import top.qiaokeke.wechatbackend.dataaccess.entity.Seller;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.ActiveType;
import top.qiaokeke.wechatbackend.dataaccess.service.IAuthUserService;
import top.qiaokeke.wechatbackend.dataaccess.service.ISellerService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    IAuthUserService authUserService;

    @Autowired
    ISellerService sellerService;


    @PostMapping("/account/add")
    @RequiresRoles("ADMIN")
    public ResponseBean add(@RequestBody Map<String,String> addMap){

        String auid = addMap.get("username");
        String password = addMap.get("password");

        String respData = authUserService.addAuthUserByAuidAndPassword(auid,password,RoleType.SELLER);

        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,respData);
    }

    @PostMapping("/info/add")
    public ResponseBean addInfo(@RequestBody Map<String,String > infoMap){

        String auid = infoMap.get("userid");
        String username = infoMap.get("sellerName");
        String shopName = infoMap.get("sellerShopName");
        String shopUrl = infoMap.get("sellerShopUrl");
        String wechatId = infoMap.get("sellerWechatId");
        String wechatName = infoMap.get("sellerWechatName");
        String qqId = infoMap.get("sellerQQId");
        String qqName = infoMap.get("sellerQQName");
        String phoneNumber = infoMap.get("sellerPhoneNumber");

        Seller seller = new Seller();
        seller.setAuid(auid);
        seller.setSellerId(username);
        seller.setSellerName(username);
        seller.setSellerWechatId(wechatId);
        seller.setSellerWechatName(wechatName);
        seller.setSellerQQId(qqId);
        seller.setSellerQQName(qqName);
        seller.setSellerPhoneNumber(phoneNumber);
        seller.setSellerShopUrl(shopUrl);
        seller.setSellerShopName(shopName);

        seller.setIsActive(ActiveType.TRUE);
        seller.setCreateTime(new Date());
        seller.setUpdateTime(new Date());

        if(sellerService.isExist(seller))
            return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,"用户已存在,或网络错误");
        if(!sellerService.save(seller))
            return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,"网络错误");
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,"添加成功");
    }

    @GetMapping("/views")
    public ResponseBean getSellerViews(){
        List views = sellerService.getAllSellerViewsByActive(ActiveType.TRUE);
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,views);
    }

    @GetMapping("/pageSellers")
    public ResponseBean getPageSellers(@RequestParam int page,@RequestParam int size){
        Pageable pageable = new PageRequest(page-1,size);
        ResponsePage responsePage = null;



        responsePage = sellerService.getAllPageSellerViewsByActive(ActiveType.TRUE,pageable);
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,responsePage);
    }


}
