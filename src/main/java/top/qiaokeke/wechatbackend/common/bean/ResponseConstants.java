package top.qiaokeke.wechatbackend.common.bean;

public interface ResponseConstants {


    public static class RespCode {
        public static final int Ok = 20000;
        public static final int ILL_TOKEN = 50008;
        public static final int OTHER_LOGIN = 50012;
        public static final int EXPIRE_TOKEN = 50014;
        public static final int USER_NOT_FOUND = 50021;
    }

    public static class RespMsg{
        public static final String OK ="[20000]OK";
        public static final String ILL_TOKEN="[50008]token不合法";
        public static final String OTHER_LOGIN="[50012]其他用户已登录";
        public static final String EXPIRE_TOKEN="[50014]token已过期";

        public static final String USER_NOT_FOUND="[50021]user not found";
    }

    public static class RespData{
        public static final String ADD_SUCCESS="添加成功";
        public static final String USER_EXIST="用户已存在";
        public static final String ADD_FAIL="添加失败";


    }

}
