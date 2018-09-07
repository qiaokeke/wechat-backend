package top.qiaokeke.wechatbackend.dataaccess.repository;


import org.springframework.data.repository.CrudRepository;
import top.qiaokeke.wechatbackend.dataaccess.entity.AuthUser;

public interface AuthUserRepository extends CrudRepository<AuthUser,String> {
    public AuthUser getAuthUserByAuid(String auid);
}
