package system.common;

import lombok.Data;

/**
 * 前后端统一数据交互格式
 */
@Data
public class Result {

    private Integer code;

    private String message;

    private Object data;

    private Result() {
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("Success");
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setCode(400);
        result.setMessage("Fail");
        return result;
    }

    public Result data(Object data) {
        this.setData(data);
        return this;
    }

    public Result message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }
}

