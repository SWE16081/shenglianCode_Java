package swe;

import com.example.springbootquickstart2.util.ApiCode;

public class ApiReturnInfo {
    private static final String CODE_SUCCESS = "200";

    private static final String CODE_FAIL = "404";

    private static final String STATUS_SUCCESS="success";

    private static final String STATUS_FAIL="failed";
    private String status;
    private  Object data;
    private  String code;
    private String message;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ApiReturnInfo() {
    }

    public ApiReturnInfo(String status){
        this.status = status;
    }
    public ApiReturnInfo(String status, String message){
        this.status = status;
        this.message = message;
    }
    public ApiReturnInfo(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public ApiReturnInfo(String status, String code, String message, Object data) {
        this.status = status;
        this.code=code;
        this.message = message;
        this.data = data;
    }
    public static ApiReturnInfo success(){
        return new ApiReturnInfo(CODE_SUCCESS,STATUS_SUCCESS);
    }
    public static ApiReturnInfo success(Object data){
        return new ApiReturnInfo(CODE_SUCCESS,STATUS_SUCCESS,data);
    }

    public static ApiReturnInfo fail(){
        return new ApiReturnInfo(CODE_FAIL,STATUS_FAIL);
    }
    public static ApiReturnInfo fail(Object data){
        return new ApiReturnInfo(CODE_FAIL,STATUS_FAIL,data);
    }
    public static ApiReturnInfo fail(ApiCode apiCode, Object data){
       return new ApiReturnInfo(CODE_FAIL,apiCode.getMessage(),data);
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
