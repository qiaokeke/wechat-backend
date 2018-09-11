package top.qiaokeke.wechatbackend.dataaccess.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.qiaokeke.wechatbackend.common.bean.ResponsePage;
import top.qiaokeke.wechatbackend.dataaccess.entity.Task;
import top.qiaokeke.wechatbackend.dataaccess.entity.views.TaskView;

import java.util.Date;
import java.util.List;

public interface ITaskService {
    public List<TaskView> getPreheatTasks(Date date);
    public List<TaskView> getPublishTasks(Date date);
    public boolean isExistByTaskId(String taskId);
    public boolean saveTask(Task task);
    public ResponsePage getPublishPageTasks(Date date, Pageable pageable);
}
