package top.qiaokeke.wechatbackend.web.oss;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.qiaokeke.wechatbackend.common.bean.ResponseBean;
import top.qiaokeke.wechatbackend.common.bean.ResponseConstants;
import top.qiaokeke.wechatbackend.dataaccess.oss.utils.UploadUtil;
import top.qiaokeke.wechatbackend.utils.constants.GlobalConstants;
import top.qiaokeke.wechatbackend.utils.strategy.IdStrategy;

import java.io.IOException;

@RestController
@RequestMapping("/oss/upload")
public class UploadController {

    @PostMapping("/img")
    public ResponseBean uploadImg(@RequestParam("file")MultipartFile file) throws IOException {
        String url;
        String tail = file.getOriginalFilename();
        int lastIndex = tail.length();
        int fistIndex = tail.lastIndexOf(".");
        tail = tail.substring(fistIndex,lastIndex);
        if (!tail.equals(".jpg") && !tail.equals(".png") &!tail.equals("jpeg"))
            return new ResponseBean(ResponseConstants.RespCode.UPLOAD_ERROR,ResponseConstants.RespMsg.UPLOAD_ERROR,null);
        try {
            url = UploadUtil.uploadPicByStream(GlobalConstants.OssConstants.WECHAT_RUANSHILI_BUCKET_NAME, IdStrategy.buildOssImgId()+tail,file.getInputStream());
        }catch (Exception e){
            return new ResponseBean(ResponseConstants.RespCode.UPLOAD_ERROR,ResponseConstants.RespMsg.UPLOAD_ERROR,"");
        }
        return new ResponseBean(ResponseConstants.RespCode.Ok,ResponseConstants.RespMsg.OK,url);
    }
}
