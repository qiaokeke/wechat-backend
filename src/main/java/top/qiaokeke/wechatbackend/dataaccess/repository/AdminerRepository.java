package top.qiaokeke.wechatbackend.dataaccess.repository;

import org.springframework.data.repository.CrudRepository;
import top.qiaokeke.wechatbackend.dataaccess.entity.Adminer;

public interface AdminerRepository extends CrudRepository<Adminer,String> {
    public Adminer getAdminerByAuid(String auid);
}
