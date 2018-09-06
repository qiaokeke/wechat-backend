package top.qiaokeke.wechatbackend.common.bean;

public class ResponseConstants {

    public static final int RES_CODE_OK=20000;
    public static final int RES_CODE_ILL_TOKEN=50008;
    public static final int RES_CODE_OTHER_LOGIN=50012;
    public static final int RES_CODE_EXPIRE_TOKEN=50014;

    public static final int RES_CODE_USER_NOT_FOUND=50021;


    public static final String RES_MSG_OK="[20000]OK";
    public static final String RES_MSG_ILL_TOKEN="[50008]token不合法";
    public static final String RES_MSG_OTHER_LOGIN="[50012]其他用户已登录";
    public static final String RES_MSG_EXPIRE_TOKEN="[50014]token已过期";

    public static final String RES_MSG_USER_NOT_FOUND="[50021]user not found";

}
