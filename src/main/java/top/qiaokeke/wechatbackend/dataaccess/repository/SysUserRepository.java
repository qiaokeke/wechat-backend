package top.qiaokeke.wechatbackend.dataaccess.repository;


import org.springframework.data.repository.CrudRepository;
import top.qiaokeke.wechatbackend.dataaccess.entity.SysUser;


public interface SysUserRepository extends CrudRepository<SysUser,String> {
    public SysUser getSysUserByUid(String uid);
}
