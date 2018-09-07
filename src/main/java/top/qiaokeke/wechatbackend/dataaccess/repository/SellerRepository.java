package top.qiaokeke.wechatbackend.dataaccess.repository;

import org.springframework.data.repository.CrudRepository;
import top.qiaokeke.wechatbackend.dataaccess.entity.Seller;

import java.util.List;

public interface SellerRepository extends CrudRepository<Seller,String> {
    List<Seller> getAllByActive(boolean isActive);
    
}
