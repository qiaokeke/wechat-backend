package top.qiaokeke.wechatbackend.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import top.qiaokeke.wechatbackend.common.bean.ResponseBean;
import top.qiaokeke.wechatbackend.common.bean.ResponseConstants;
import top.qiaokeke.wechatbackend.common.bean.ResponsePage;
import top.qiaokeke.wechatbackend.dataaccess.entity.Task;
import top.qiaokeke.wechatbackend.dataaccess.entity.views.TaskView;
import top.qiaokeke.wechatbackend.dataaccess.service.ISellerService;
import top.qiaokeke.wechatbackend.dataaccess.service.ITaskService;
import top.qiaokeke.wechatbackend.utils.date.Format;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    ITaskService taskService;
    @Autowired
    ISellerService sellerService;

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

    @GetMapping("/pageTasks")
    public ResponseBean getPublishTasksByPage(@RequestParam int page,@RequestParam int size,@RequestParam(required = false) String taskId,@RequestParam(required = false) String sellerId,@RequestParam(required = false) String taskStatus){
        Pageable pageable = new PageRequest(page-1,size);
        ResponsePage responsePage = null;
        if(taskId!=null && !taskId.equals("")){
            responsePage = taskService.getPageTasksByTaskId(taskId,pageable);
            return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,responsePage);
        }
        if(sellerId!=null && !sellerId.equals("")){
            responsePage = taskService.getPageTasksBySellerId(sellerId,pageable);
            return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,responsePage);
        }

        if(taskStatus.equals("未开始"))
            responsePage = taskService.getPreStartPageTasks(new Date(),pageable);
        if(taskStatus.equals("预热中"))
            responsePage = taskService.getPreheatPageTasks(new Date(),pageable);
        if(taskStatus.equals("已发布"))
            responsePage = taskService.getPublishPageTasks(new Date(),pageable);
        if(taskStatus.equals("已结束"))
            responsePage = taskService.getAfterFinishPageTasks(new Date(),pageable);

        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,responsePage);
    }

    @PostMapping("/add")
    public ResponseBean addTask(@RequestBody Map<String,String> map){

        String taskId = map.get("taskId");
        String taskName = map.get("taskName");
        String sellerId = map.get("sellerId");
        String chargeAmount = map.get("chargeAmount");
        String taskAmount = map.get("taskAmount");
        String gift = map.get("gift");
        String giftPicUrl = map.get("giftPicUrl ");
        String preheatTime = map.get("preheatTime");
        String publishTime = map.get("publishTime");
        String finishTime = map.get("finishTime");

        Task task = new Task();
        task.setTId(taskId);
        task.setTName(taskName);
        task.setTSellerId(sellerId);
        task.setTChargeAmout(chargeAmount);
        task.setTTotal(Integer.valueOf(taskAmount));
        task.setTGift(gift);
        task.setTGiftPicUrl(giftPicUrl);
        try {
            task.setTPreheatTime(Format.datetimeString2Date(preheatTime));
            task.setTPublishTime(Format.datetimeString2Date(publishTime));
            task.setTFinishTime(Format.datetimeString2Date(finishTime));
        }catch (Exception e){
            logger.error("parse date:{}",e);
            return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,"日期格式错误");
        }

        task.setCreateTime(new Date());
        task.setTPublishTime(new Date());
        if(taskService.isExistByTaskId(taskId))
            return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,"任务已存在");
        if(!sellerService.isExistByAuid(sellerId))
            return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,"卖家不存在");
        if(!taskService.saveTask(task))
            return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,"网络错误");
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,"添加成功");
    }

    @PostMapping("/update")
    public ResponseBean updateTask(@RequestBody  Map<String,String> map){
        String tid = map.get("tid");
        String tname = map.get("tname");
        String tsellerId = map.get("tsellerId");
        String tgift = map.get("tgift");
        String tgiftPicUrl = map.get("tgiftPicUrl");
        String tpreheatTime = map.get("tpreheatTime");
        String tpublishTime = map.get("tpublishTime");
        String tfinishTime = map.get("tfinishTime ");
        String tprogress = map.get("tprogress");
        String ttotal = map.get("ttotal");
        String tchargeAmout = map.get("tchargeAmout");

        Task task = taskService.getTaskByTId(tid);

        if(task==null)
            return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,"该任务不存在，更新失败");

        task.setTName(tname);
        task.setTSellerId(tsellerId);
        task.setTGift(tgift);
        task.setTGiftPicUrl(tgiftPicUrl);
        task.setTChargeAmout(tchargeAmout);
        task.setTTotal(Integer.parseInt(ttotal));
        task.setTProgress(Integer.parseInt(tprogress));
        try {
            task.setTPreheatTime(Format.datetimeString2Date(tpreheatTime));
            task.setTPublishTime(Format.datetimeString2Date(tpublishTime));
            task.setTFinishTime(Format.datetimeString2Date(tfinishTime));
        }catch (Exception e){
            logger.error("update time error:{}",e);
            return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,"日期格式错误");
        }
        task.setTUploadTime(new Date());

        if(!taskService.saveTask(task))
            return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,"网络错误");
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,"添加成功");

    }

}
