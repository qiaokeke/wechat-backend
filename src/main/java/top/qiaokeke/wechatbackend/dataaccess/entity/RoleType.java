package top.qiaokeke.wechatbackend.dataaccess.entity;

public enum RoleType {

    WECHAT_USER("WECHAR_USER"),
    SELLER("SELLER"),
    ADMIN("ADMIN");

    private String type;

    RoleType(String type){this.type=type;};
}
