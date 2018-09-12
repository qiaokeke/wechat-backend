package top.qiaokeke.wechatbackend.dataaccess.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.qiaokeke.wechatbackend.common.bean.ResponsePage;
import top.qiaokeke.wechatbackend.dataaccess.entity.Task;
import top.qiaokeke.wechatbackend.dataaccess.entity.views.TaskView;
import top.qiaokeke.wechatbackend.dataaccess.repository.TaskRepository;
import top.qiaokeke.wechatbackend.dataaccess.service.ITaskService;
import top.qiaokeke.wechatbackend.utils.date.Format;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Service
public class TaskService implements ITaskService {


    Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Task getTaskByTId(String tId) {
        return taskRepository.getTaskByTId(tId);
    }

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

    @Override
    public boolean isExistByTaskId(String taskId) {
        return taskRepository.existsByTId(taskId);
    }

    @Override
    public boolean saveTask(Task task) {
        try {
            taskRepository.save(task);
        }catch (Exception e){
            logger.error("save task error:{}",e);
            return false;
        }
        return true;
    }

    @Override
    public ResponsePage getPublishPageTasks(Date date, Pageable pageable) {
        Page<Task> tasks;
        try {
            tasks = taskRepository.getAllByTPublishTimeBeforeAndTFinishTimeAfter(date, date, pageable);
        }catch (Exception e){
            logger.error("get publish page tasks error:{}",e);
            return null;
        }
        List<TaskView> taskViews = taskPage2Views(tasks);
        return new ResponsePage(tasks.getTotalElements(),taskViews);
    }

    @Override
    public ResponsePage getPreheatPageTasks(Date date, Pageable pageable) {
        Page<Task> tasks;
        try {
            tasks = taskRepository.getAllByTPreheatTimeBeforeAndTPublishTimeAfter(date, date, pageable);
        }catch (Exception e){
            logger.error("get preheat page tasks error:{}",e);
            return null;
        }
        List<TaskView> taskViews = taskPage2Views(tasks);
        return new ResponsePage(tasks.getTotalElements(),taskViews);
    }

    @Override
    public ResponsePage getPreStartPageTasks(Date date, Pageable pageable) {
        Page<Task> tasks;
        try {
            tasks = taskRepository.getAllByTPreheatTimeAfter(date, pageable);
        }catch (Exception e){
            logger.error("get pre start page tasks error:{}",e);
            return null;
        }
        List<TaskView> taskViews = taskPage2Views(tasks);
        return new ResponsePage(tasks.getTotalElements(),taskViews);
    }

    @Override
    public ResponsePage getAfterFinishPageTasks(Date date, Pageable pageable) {
        Page<Task> tasks;
        try {
            tasks = taskRepository.getAllByTFinishTimeBefore(date, pageable);
        }catch (Exception e){
            logger.error("get after finish page tasks error:{}",e);
            return null;
        }
        List<TaskView> taskViews = taskPage2Views(tasks);
        return new ResponsePage(tasks.getTotalElements(),taskViews);
    }

    @Override
    public ResponsePage getPageTasksByTaskId(String taskId, Pageable pageable) {
        Page<Task> tasks;
        try {
            tasks = taskRepository.getAllByTId(taskId, pageable);
        }catch (Exception e){
            logger.error("get after taskid page tasks error:{}",e);
            return null;
        }
        List<TaskView> taskViews = taskPage2Views(tasks);
        return new ResponsePage(tasks.getTotalElements(),taskViews);
    }


    @Override
    public ResponsePage getPageTasksBySellerId(String sellerId, Pageable pageable) {
        Page<Task> tasks;
        try {
            tasks = taskRepository.getAllByTSellerId(sellerId, pageable);
        }catch (Exception e){
            logger.error("get after sellerid page tasks error:{}",e);
            return null;
        }
        List<TaskView> taskViews = taskPage2Views(tasks);
        return new ResponsePage(tasks.getTotalElements(),taskViews);
    }

    private List<TaskView> taskPage2Views(Page<Task> tasks){
        List<TaskView> taskViews = new LinkedList<>();
        for(Task task: tasks){
            TaskView taskView = new TaskView();
            taskView.setTId(task.getTId());
            taskView.setTName(task.getTName());
            taskView.setTSellerId(task.getTSellerId());
            taskView.setTChargeAmout(task.getTChargeAmout());
            taskView.setTTotal(String.valueOf(task.getTTotal()));
            taskView.setTProgress(String.valueOf(task.getTProgress()));
            taskView.setTPreheatTime(Format.yyyy_MM_dd_HH_mm_ssDateString(task.getTPreheatTime()));
            taskView.setTPublishTime(Format.yyyy_MM_dd_HH_mm_ssDateString(task.getTPublishTime()));
            taskView.setTFinishTime(Format.yyyy_MM_dd_HH_mm_ssDateString(task.getTFinishTime()));
            taskView.setTGift(task.getTGift());
            taskView.setTGiftPicUrl(task.getTGiftPicUrl());
            taskView.setTReward(task.getTReward());
            taskViews.add(taskView);
        }

        return taskViews;
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
