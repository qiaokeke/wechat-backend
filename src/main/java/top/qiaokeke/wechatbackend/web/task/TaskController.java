package top.qiaokeke.wechatbackend.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.qiaokeke.wechatbackend.common.bean.ResponseBean;
import top.qiaokeke.wechatbackend.common.bean.ResponseConstants;
import top.qiaokeke.wechatbackend.dataaccess.entity.Task;
import top.qiaokeke.wechatbackend.dataaccess.entity.views.TaskView;
import top.qiaokeke.wechatbackend.dataaccess.service.ITaskService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    ITaskService taskService;

    @GetMapping("/preheatTasks")
    public ResponseBean getPreheatTasks(){
        List<TaskView> preheatTasks = taskService.getPreheatTasks(new Date());
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,preheatTasks);
    }

    @GetMapping("/publishTasks")
    public ResponseBean getPublishTasks(){
        List<TaskView> publishTasks = taskService.getPublishTasks(new Date());
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,publishTasks);
    }



}
