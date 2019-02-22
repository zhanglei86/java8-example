package win.leizhang.java8example.bo;

import java.io.Serializable;
import java.util.UUID;

public class MainOutputDTO<T> implements Serializable {

    public MainOutputDTO() {
        this.code = "00000000";
        this.msg = "成功";
        transactionUuid = UUID.randomUUID().toString();
    }

    public MainOutputDTO(String code, String msg) {
        this.code = code;
        this.msg = msg;
        transactionUuid = UUID.randomUUID().toString();
    }

    // uuid
    private String transactionUuid;

    // 返回结果编码
    protected String code;

    // 错误描述
    protected String msg;

    // 出参通用对象
    private T outputParam;

    // @Setter, @Getter
    public String getTransactionUuid() {
        return transactionUuid;
    }

    public void setTransactionUuid(String transactionUuid) {
        this.transactionUuid = transactionUuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getOutputParam() {
        return outputParam;
    }

    public void setOutputParam(T outputParam) {
        this.outputParam = outputParam;
    }
}
