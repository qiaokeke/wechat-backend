package top.qiaokeke.wechatbackend.dataaccess.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import top.qiaokeke.wechatbackend.dataaccess.entity.Seller;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.ActiveType;

import java.util.List;

public interface SellerRepository extends CrudRepository<Seller,String> {
    List<Seller> getAllByIsActive(ActiveType isActive);
    boolean existsByAuid(String auid);
    public Page<Seller> getAllBySellerId(String sellerId, Pageable pageable);
    public Page<Seller> getAllBySellerName(String sellerName,Pageable pageable);
    public Page<Seller> getAllByIsActive(ActiveType activeType,Pageable pageable);
    public Seller getSellerBySellerId(String sellerId);
    public Page<Seller> getAllBySellerIdLike(String sellerId, Pageable pageable);
    public Page<Seller> getAllBySellerNameLike(String sellerName,Pageable pageable);

}
