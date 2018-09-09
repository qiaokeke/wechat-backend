package top.qiaokeke.wechatbackend.sqltest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.qiaokeke.wechatbackend.dataaccess.entity.Order;
import top.qiaokeke.wechatbackend.dataaccess.entity.types.OrderStatus;
import top.qiaokeke.wechatbackend.dataaccess.repository.OrderRepository;
import top.qiaokeke.wechatbackend.utils.strategy.IdStrategy;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {


    @Test
    public void save(){

        for(int i =1 ;i<10000;i++){
            Order order = new Order();

            switch (i%6){
                case 0:order.setOrderStatus(OrderStatus.WAIT_COMMIT);break;
                case 1:order.setOrderStatus(OrderStatus.WAIT_CONFIRM);break;
                case 2:order.setOrderStatus(OrderStatus.WAIT_COMPIT_PAY);break;
                case 3:order.setOrderStatus(OrderStatus.WAIT_COMFIRM_PAY);break;
                case 4:order.setOrderStatus(OrderStatus.FINISH);break;
                case 5:order.setOrderStatus(OrderStatus.CANCEL);break;
            }
            order.setOrderId(IdStrategy.buildOrderId());
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
        }
    }
}
