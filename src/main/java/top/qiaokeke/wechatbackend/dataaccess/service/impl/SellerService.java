package top.qiaokeke.wechatbackend.dataaccess.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.qiaokeke.wechatbackend.dataaccess.entity.Seller;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.ActiveType;
import top.qiaokeke.wechatbackend.dataaccess.entity.views.SellerView;
import top.qiaokeke.wechatbackend.dataaccess.repository.SellerRepository;
import top.qiaokeke.wechatbackend.dataaccess.service.ISellerService;

import java.util.LinkedList;
import java.util.List;

@Service
public class SellerService implements ISellerService {

    Logger logger = LoggerFactory.getLogger(SellerService.class);

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public boolean isExist(Seller seller) {
        return sellerRepository.existsByAuid(seller.getAuid());
    }

    @Override
    public boolean save(Seller seller) {
        try {
            sellerRepository.save(seller);
        }catch (Exception e){
            logger.error("save seller error:{}",e);
            return false;
        }
        return true;
    }

    @Override
    public List<SellerView> getAllSellerViewsByActive(ActiveType activeType) {
        List<Seller> sellers = sellerRepository.getAllByIsActive(activeType);
        List<SellerView> sellerViews = new LinkedList<>();

        for(Seller seller: sellers){
            SellerView view = new SellerView();
            view.setAuid(seller.getAuid());
            view.setUsername(seller.getSellerName());
            view.setShopName(seller.getSellerShopName());
            view.setShopUrl(seller.getSellerShopUrl());
            view.setWechatId(seller.getSellerWechatId());
            view.setWechatName(seller.getSellerWechatName());
            view.setQqId(seller.getSellerQQId());
            view.setQqName(seller.getSellerQQName());
            view.setPhoneNumber(seller.getSellerPhoneNumber());
            sellerViews.add(view);
        }
        return sellerViews;
    }


}
