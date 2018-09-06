package top.qiaokeke.wechatbackend.sqltest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.qiaokeke.wechatbackend.dataaccess.entity.Task;
import top.qiaokeke.wechatbackend.dataaccess.repository.TaskRepository;

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


        for (int i=0;i<1000;i++){
            Task task = new Task();
            task.setTId(String.valueOf(i+10000));
            task.setTSysUserId("wechat"+String.valueOf(i));
            task.setTUpdateTime(new Date());
            task.setTGiftPicUrl("https://render.bitstrips.com/v2/cpanel/07f4abbf-69d7-4d6c-bc11-dca3e458875d-4d9bdedc-ec6a-413d-bb8d-96d4eee1dc22-v1.png?transparent=1&palette=1");
            task.setTTaobaoUrl("https://detail.tmall.com/item.htm?spm=a230r.1.14.13.b9b7171dh1rFJJ&id=565259219719&cm_id=140105335569ed55e27b&abbucket=16");
            task.setTTaobaoId("565259219719");
            task.setTUploadTime(new Date());
            task.setTChargeAmout(String.valueOf(100+i));
            task.setTGift("现金5元");
            if(i%3==0)
                task.setTGift("赠品");

            double random = Math.random();
            long t = new Date().getTime();
            t += (random*20-10)*60*60*1000;
            task.setTPreheatTime(new Date(t+1000*60*60*2));
            task.setTPublishTime(new Date(t+1000*60*60*4));
            task.setTFinishTime(new Date(t+1000*60*60*6));
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
}
