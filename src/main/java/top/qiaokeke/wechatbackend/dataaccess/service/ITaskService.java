package top.qiaokeke.wechatbackend.dataaccess.service;

import top.qiaokeke.wechatbackend.dataaccess.entity.views.TaskView;

import java.util.Date;
import java.util.List;

public interface ITaskService {
    public List<TaskView> getPreheatTasks(Date date);
    public List<TaskView> getPublishTasks(Date date);
}
