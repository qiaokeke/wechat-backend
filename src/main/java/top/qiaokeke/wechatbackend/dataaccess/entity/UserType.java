package top.qiaokeke.wechatbackend.dataaccess.entity;

public enum UserType {

    /*

     */
    WECHAT_USER("WECHAR_USER"),
    SELLER("SELLER"),
    ADMIN("ADMIN");


    private String type;

    UserType(String type){this.type=type;};
}
