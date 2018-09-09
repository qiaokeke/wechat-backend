package top.qiaokeke.wechatbackend.dataaccess.entity.types;

public enum OrderStatus {

    NEW_ORDER("NEW_ORDER"),
    WAIT_COMMIT("WAIT_COMMIT"),
    WAIT_CONFIRM("WAIT_CONFIRM"),
    WAIT_COMPIT_PAY("WAIT_COMPIT_PAY"),
    WAIT_COMFIRM_PAY("WAIT_CONFIRM_PAY"),
    FINISH("FINISH"),
    CANCEL("CANCEL");

    private String status;

    OrderStatus(String status){this.status=status;};
}
