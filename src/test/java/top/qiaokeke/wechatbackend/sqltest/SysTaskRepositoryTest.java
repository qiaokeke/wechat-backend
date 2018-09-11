package top.qiaokeke.wechatbackend.sqltest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import top.qiaokeke.wechatbackend.dataaccess.entity.Task;
import top.qiaokeke.wechatbackend.dataaccess.repository.TaskRepository;
import top.qiaokeke.wechatbackend.utils.strategy.IdStrategy;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysTaskRepositoryTest {

    Logger logger = LoggerFactory.getLogger(SysTaskRepositoryTest.class);

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void testSaveTashs(){
        taskRepository.deleteAll();

        for (int i=0;i<1000;i++){
            Task task = new Task();
            task.setTId(String.valueOf(i+10000));
            task.setTSellerId(IdStrategy.buildTaskId());
            task.setUpdateTime(new Date());
            task.setTGiftPicUrl("https://render.bitstrips.com/v2/cpanel/2063b3b3-2054-4a07-83b6-0a10a04de771-4d9bdedc-ec6a-413d-bb8d-96d4eee1dc22-v1.png?transparent=1&palette=1");
            task.setTTaobaoUrl("https://detail.tmall.com/item.htm?spm=a230r.1.14.13.b9b7171dh1rFJJ&id=565259219719&cm_id=140105335569ed55e27b&abbucket=16");
            task.setTTaobaoId("565259219719");
            task.setTUploadTime(new Date());
            task.setTChargeAmout(String.valueOf(100+i));
            task.setTGift("现金5元");
            task.setTReward("现金5元");
            if(i%3==0){
                task.setTGift("赠品");
                task.setTReward("赠品");
            }
            if(i%3==2) {
                task.setTGift("现金6元");
                task.setTReward("现金6元");
            }
            double random = Math.random();
            long t = new Date().getTime();
            t += (random*20-10)*60*60*1000;
            task.setTPreheatTime(new Date(t+1000*60*60*2));
            task.setTPublishTime(new Date(t+1000*60*60*4));
            task.setTFinishTime(new Date(t+1000*60*60*6));
            task.setCreateTime(new Date());
            task.setUpdateTime(new Date());
            task.setTTotal(10);
            task.setTProgress(i%10);
            taskRepository.save(task);
        }
    }

    @Test
    public void testPreTasks(){
        List<Task> tasks = taskRepository.getTasksByTPreheatTimeBeforeAndTPublishTimeAfter(new Date(),new Date());
        for (Task task: tasks)
            logger.info("pre task:{}",task);
        List<Task> atasks = taskRepository.getTasksByTPublishTimeBeforeAndTFinishTimeAfter(new Date(),new Date());
        for (Task task: atasks)
            logger.info("pub task:{}",task);
    }

    @Test
    public void testPageTasks(){
        Pageable pageable = new PageRequest(0,30);
        Page<Task> tasks = taskRepository.getAllByTPublishTimeBeforeAndTFinishTimeAfter(new Date(),new Date(),pageable);
        logger.info("pages:{}",tasks);
        logger.info("total pages:{}",tasks.getTotalPages());
        logger.info("total elements:{}",tasks.getTotalElements());
        for (Task task : tasks){
            logger.info("task:{}",task);
        }
    }
}
