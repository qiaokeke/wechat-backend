package top.qiaokeke.wechatbackend.web.api;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.qiaokeke.wechatbackend.common.bean.ResponseBean;
import top.qiaokeke.wechatbackend.common.bean.ResponseConstants;
import top.qiaokeke.wechatbackend.utils.strategy.IdStrategy;

@RestController
@RequestMapping("/api/idStrategy")
public class IdStrategyController {

    @GetMapping("/build/taskId")
    public ResponseBean buildTaskId(){
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,IdStrategy.buildTaskId());
    }
    @GetMapping("/build/orderId")
    public ResponseBean buildOrderId(){
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,IdStrategy.buildOrderId());
    }

}
