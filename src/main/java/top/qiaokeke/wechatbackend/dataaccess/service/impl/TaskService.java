package top.qiaokeke.wechatbackend.dataaccess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.qiaokeke.wechatbackend.dataaccess.entity.Task;
import top.qiaokeke.wechatbackend.dataaccess.repository.TaskRepository;
import top.qiaokeke.wechatbackend.dataaccess.service.ITaskService;


@Service
public class TaskService implements ITaskService {

    @Autowired
    TaskRepository taskRepository;

}
