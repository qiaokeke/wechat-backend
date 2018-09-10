package top.qiaokeke.wechatbackend.dataaccess.repository;

import org.springframework.data.repository.CrudRepository;
import top.qiaokeke.wechatbackend.dataaccess.entity.Seller;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.ActiveType;

import java.util.List;

public interface SellerRepository extends CrudRepository<Seller,String> {
    List<Seller> getAllByIsActive(ActiveType isActive);
    boolean existsByAuid(String auid);
}
