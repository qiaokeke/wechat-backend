package top.qiaokeke.wechatbackend.dataaccess.service;

import top.qiaokeke.wechatbackend.dataaccess.entity.Seller;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.ActiveType;
import top.qiaokeke.wechatbackend.dataaccess.entity.views.SellerView;

import java.util.List;

public interface ISellerService {
    public boolean isExist(Seller seller);
    public boolean isExistByAuid(String auid);
    public boolean save(Seller seller);
    public List<SellerView> getAllSellerViewsByActive(ActiveType activeType);

}
