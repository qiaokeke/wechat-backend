package top.qiaokeke.wechatbackend.dataaccess.repository;

import org.springframework.data.repository.CrudRepository;
import top.qiaokeke.wechatbackend.dataaccess.entity.Task;

public interface TaskRepository extends CrudRepository<Task,String> {

}
