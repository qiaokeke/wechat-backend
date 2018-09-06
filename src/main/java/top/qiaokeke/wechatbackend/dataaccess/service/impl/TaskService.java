package top.qiaokeke.wechatbackend.dataaccess.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.qiaokeke.wechatbackend.dataaccess.entity.Task;
import top.qiaokeke.wechatbackend.dataaccess.entity.views.TaskView;
import top.qiaokeke.wechatbackend.dataaccess.repository.TaskRepository;
import top.qiaokeke.wechatbackend.dataaccess.service.ITaskService;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Service
public class TaskService implements ITaskService {

    Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<TaskView> getPreheatTasks(Date date) {
        List<Task> tasks;
        try {
            tasks = taskRepository.getTasksByTPreheatTimeBeforeAndTPublishTimeAfter(date,date);
        }catch (Exception e){
            logger.info("get preheat tasks error:{}",e);
            return null;
        }
        return tasks2TaskViews(tasks);
    }

    @Override
    public List<TaskView> getPublishTasks(Date date) {
        List<Task> tasks;
        try {
            tasks =  taskRepository.getTasksByTPublishTimeBeforeAndTFinishTimeAfter(date,date);
        }catch (Exception e){
            logger.info("get publish tasks error:{}",e);
            return null;
        }
        return tasks2TaskViews(tasks);
    }

    private List<TaskView> tasks2TaskViews(List<Task> tasks){
        List<TaskView> taskViews = new LinkedList<>();
        for (Task task : tasks){
            TaskView taskView = new TaskView();
            taskView.setTId(task.getTId());
            taskView.setTGift(task.getTGift());
            taskView.setTGiftPicUrl(task.getTGiftPicUrl());
            taskView.setTReward(task.getTReward());
            taskView.setTChargeAmout(task.getTChargeAmout());
            taskViews.add(taskView);
        }
        return taskViews;
    }
}
