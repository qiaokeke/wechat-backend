package top.qiaokeke.wechatbackend.dataaccess.repository;

import org.springframework.data.repository.CrudRepository;
import top.qiaokeke.wechatbackend.dataaccess.entity.AuthUserRole;

import java.util.List;

public interface AuthUserRoleRepository extends CrudRepository<AuthUserRole,String> {
    public List<AuthUserRole> getAuthUserRoleByAuid(String auid);
}
