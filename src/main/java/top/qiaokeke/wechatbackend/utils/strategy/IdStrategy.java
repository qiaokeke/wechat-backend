package top.qiaokeke.wechatbackend.utils.strategy;

import top.qiaokeke.wechatbackend.utils.constants.IdConstants;
import top.qiaokeke.wechatbackend.utils.date.Format;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdStrategy {


    public static String buildTaskId(){
        return buildIdbyPrefix(IdConstants.TASK_ID_PREFIX);
    }

    public static String buildOrderId(){
        return buildIdbyPrefix(IdConstants.ORDER_ID_PREFIX);
    }

    public static String buildOssImgId(){return buildIdbyPrefix(IdConstants.OSS_IMG_ID_PREFIX);}

    private static String buildIdbyPrefix(String prefix){
        int tail = (int) (Math.random()*9000+1000);
        return prefix+Format.yyyyMMddHHmmssDate(new Date())+String.format("%4d",tail);
    }
}
