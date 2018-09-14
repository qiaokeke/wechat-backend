package top.qiaokeke.wechatbackend.dataaccess.service;

import org.springframework.data.domain.Pageable;
import top.qiaokeke.wechatbackend.common.bean.ResponsePage;
import top.qiaokeke.wechatbackend.dataaccess.entity.Seller;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.ActiveType;
import top.qiaokeke.wechatbackend.dataaccess.entity.views.SellerView;

import java.util.List;

public interface ISellerService {
    public boolean isExist(Seller seller);
    public boolean isExistByAuid(String auid);
    public boolean save(Seller seller);
    public List<SellerView> getAllSellerViewsByActive(ActiveType activeType);
    public ResponsePage getAllPageSellerViewsByActive(ActiveType activeType, Pageable pageable);
    public ResponsePage getAllPageSellerViewsBySellerId(String sellerId,Pageable pageable);
    public ResponsePage getAllPageSellerViewsBySellerName(String sellerName,Pageable pageable);

}
