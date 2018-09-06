package top.qiaokeke.wechatbackend.sqltest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.qiaokeke.wechatbackend.dataaccess.entity.Task;
import top.qiaokeke.wechatbackend.dataaccess.repository.TaskRepository;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysTaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void testSaveTashs(){

        Task task = new Task();
        for (int i=0;i<100;i++){
            task.setId(String.valueOf(i+10000));
            task.setTUpdateTime(new Date());
            task.setTGiftPicUrl("https://render.bitstrips.com/v2/cpanel/07f4abbf-69d7-4d6c-bc11-dca3e458875d-4d9bdedc-ec6a-413d-bb8d-96d4eee1dc22-v1.png?transparent=1&palette=1");
            task.setTTaobaoUrl("https://detail.tmall.com/item.htm?spm=a230r.1.14.13.b9b7171dh1rFJJ&id=565259219719&cm_id=140105335569ed55e27b&abbucket=16");
            task.setTTaobaoId("565259219719");
            task.setTUploadTime(new Date());
            task.setTChargeAmout(String.valueOf(100+i));
            task.setTGift("现金5元");
            if(i%3==0)
                task.setTGift("赠品");

            task.setTPreheatStartTime(new Date(new Date().getTime()+1000*60*60*2));
            task.setTPublishTime(new Date(new Date().getTime()+1000*60*60*4));
            task.setTFinishTime(new Date(new Date().getTime()+1000*60*60*6));
            taskRepository.save(task);
        }

    }
}
