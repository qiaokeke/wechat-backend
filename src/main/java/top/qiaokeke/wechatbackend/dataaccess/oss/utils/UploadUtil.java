package top.qiaokeke.wechatbackend.dataaccess.oss.utils;

import com.aliyun.oss.OSSClient;

import java.io.InputStream;

public class UploadUtil {

    public static String uploadPicByStream(String bucketName,String objectName,InputStream stream){
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAIaOAyClNAQ9BP";
        String accessKeySecret = "vtBui4bGdtBbm5mPJHvORxxM6prHZT";
        //String bucketName = "wechat-ruanshili";
        //String objectName = "<yourObjectName>";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, objectName, stream);
        ossClient.shutdown();
        return "http://"+bucketName+".oss-cn-hangzhou.aliyuncs.com/"+objectName;
    }
}
