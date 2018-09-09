package top.qiaokeke.wechatbackend.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {
    public static String yyyyMMddHHmmssDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }
}
