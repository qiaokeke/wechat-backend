package top.qiaokeke.wechatbackend.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {
    public static String yyyyMMddHHmmssDateString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }
    public static String yyyy_MM_dd_HH_mm_ssDateString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    public static Date datetime2Date(String date,String time) throws ParseException {
        String datetimeString = date+" "+time;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(datetimeString);
    }

    public static Date datetimeString2Date(String datetime) throws ParseException {
        System.out.println(datetime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(datetime);
    }
}
