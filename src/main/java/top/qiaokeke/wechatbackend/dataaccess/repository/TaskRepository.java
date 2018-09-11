package top.qiaokeke.wechatbackend.dataaccess.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import top.qiaokeke.wechatbackend.dataaccess.entity.Task;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends CrudRepository<Task,String> {
    public List<Task> getTasksByTPreheatTimeBeforeAndTPublishTimeAfter(Date date1,Date date2);
    public List<Task> getTasksByTPublishTimeBeforeAndTFinishTimeAfter(Date date1,Date date2);
    public boolean existsByTId(String tId);
    public Page<Task> getAllByTPublishTimeBeforeAndTFinishTimeAfter(Date date1,Date date2,Pageable pageable);
}
