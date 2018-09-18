package top.qiaokeke.wechatbackend.dataaccess.entity.types;

public enum StepStatus {
    NWE_STEP("NWE_STEP"),
    SEARCH_STEP("SEARCH_STEP"),
    CHECK_SHOP("CHECK_SHOP"),
    CHECK_TAOCMD("CHECK_TAOCMD"),
    READ_DETAIL("READ_DETAIL"),
    COLLECT("COLLECT"),
    PAY("PAY"),
    UPLOAD_WECHATPAY("UPLOAD_WECHATPAY"),
    CANCEL("CANCEL");

    private String status;

    StepStatus(String status){this.status=status;};
}
