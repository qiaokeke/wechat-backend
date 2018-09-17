package top.qiaokeke.wechatbackend.dataaccess.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.qiaokeke.wechatbackend.common.bean.ResponsePage;
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
    public boolean isExistByAuid(String auid) {
        return sellerRepository.existsByAuid(auid);
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
        List<SellerView> sellerViews = sellerList2SellerViews(sellers);

        return sellerViews;
    }

    @Override
    public ResponsePage getAllPageSellerViewsByActive(ActiveType activeType, Pageable pageable) {
        Page<Seller> sellers = null;
        try {
            sellers = sellerRepository.getAllByIsActive(ActiveType.TRUE, pageable);
        }catch (Exception e){
            logger.error("select all sellers error:{}",e.getMessage());
        }
        List<SellerView> sellerViews = sellerPages2SellerViews(sellers);

        return new ResponsePage(sellers.getTotalElements(),sellerViews);
    }

    @Override
    public ResponsePage getAllPageSellerViewsBySellerId(String sellerId, Pageable pageable) {
        Page<Seller> sellers = null;
        try {
            sellers = sellerRepository.getAllBySellerId(sellerId, pageable);
        }catch (Exception e){
            logger.error("select seller sellers error:{}",e.getMessage());
        }
        List<SellerView> sellerViews = sellerPages2SellerViews(sellers);

        return new ResponsePage(sellers.getTotalElements(),sellerViews);
    }

    @Override
    public ResponsePage getAllPageSellerViewsBySellerName(String sellerName, Pageable pageable) {
        Page<Seller> sellers = null;
        try {
            sellers = sellerRepository.getAllBySellerName(sellerName, pageable);
        }catch (Exception e){
            logger.error("select seller sellers error:{}",e.getMessage());
        }
        List<SellerView> sellerViews = sellerPages2SellerViews(sellers);

        return new ResponsePage(sellers.getTotalElements(),sellerViews);
    }

    @Override
    public Seller getSellerBySellerId(String sellerId) {
        Seller seller=null;
        try {
            seller = sellerRepository.getSellerBySellerId(sellerId);
        }catch (Exception e){
            logger.error("getSellerBySellerId error:{}",e.getMessage());
        }
        return seller;
    }

    @Override
    public ResponsePage getAllPageSellerViewsBySellerIdLike(String sellerId, Pageable pageable) {
        Page<Seller> sellers = null;
        try {
            sellers = sellerRepository.getAllBySellerIdLike(sellerId, pageable);
        }catch (Exception e){
            logger.error("select seller sellers error:{}",e.getMessage());
        }
        List<SellerView> sellerViews = sellerPages2SellerViews(sellers);

        return new ResponsePage(sellers.getTotalElements(),sellerViews);
    }

    @Override
    public ResponsePage getAllPageSellerViewsBySellerNameLike(String sellerName, Pageable pageable) {
        Page<Seller> sellers = null;
        try {
            sellers = sellerRepository.getAllBySellerNameLike(sellerName, pageable);
        }catch (Exception e){
            logger.error("select seller sellers error:{}",e.getMessage());
        }
        List<SellerView> sellerViews = sellerPages2SellerViews(sellers);

        return new ResponsePage(sellers.getTotalElements(),sellerViews);
    }

    @Override
    public boolean deleteSeller(Seller seller) {

        try {
            sellerRepository.delete(seller);
        }catch (Exception e){
            logger.error("deleteSeller error:{}",e.getMessage());
            return false;
        }
        return true;
    }


    private List<SellerView> sellerPages2SellerViews(Page<Seller> sellers){
        List<SellerView> sellerViews = new LinkedList<>();

        for(Seller seller: sellers){
            SellerView view = new SellerView();
            view.setSellerId(seller.getSellerId());
            view.setSellerName(seller.getSellerName());
            view.setSellerShopName(seller.getSellerShopName());
            view.setSellerShopUrl(seller.getSellerShopUrl());
            view.setSellerWechatId(seller.getSellerWechatId());
            view.setSellerWechatName(seller.getSellerWechatName());
            view.setSellerQQId(seller.getSellerQQId());
            view.setSellerQQName(seller.getSellerQQName());
            view.setSellerPhoneNumber(seller.getSellerPhoneNumber());
            sellerViews.add(view);
        }
        return sellerViews;
    }

    private List<SellerView> sellerList2SellerViews(List<Seller> sellers){
        List<SellerView> sellerViews = new LinkedList<>();

        for(Seller seller: sellers){
            SellerView view = new SellerView();
            view.setSellerId(seller.getSellerId());
            view.setSellerName(seller.getSellerName());
            view.setSellerShopName(seller.getSellerShopName());
            view.setSellerShopUrl(seller.getSellerShopUrl());
            view.setSellerWechatId(seller.getSellerWechatId());
            view.setSellerWechatName(seller.getSellerWechatName());
            view.setSellerQQId(seller.getSellerQQId());
            view.setSellerQQName(seller.getSellerQQName());
            view.setSellerPhoneNumber(seller.getSellerPhoneNumber());
            sellerViews.add(view);
        }
        return sellerViews;
    }
}
