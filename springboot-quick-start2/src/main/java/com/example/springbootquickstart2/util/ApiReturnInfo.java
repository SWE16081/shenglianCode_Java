package com.example.springbootquickstart2.util;

public class ApiReturnInfo {


    private static final String STATUS_SUCCESS="success";

    private static final String STATUS_FAIL="failed";
    private static final String CODE_SUCCESS = "200";

    private static final String CODE_FAIL = "404";
    private String status;//success fail 提示
    private  String code;//状态码
    private String message;//接口描述信息
    private  Object data;

    public ApiReturnInfo(){}
    public ApiReturnInfo(String status, String code){
        this.status = status;
        this.code = code;
    }
    public ApiReturnInfo(String status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
    public ApiReturnInfo(String status, String code, Object data){
        this.status = status;
        this.code = code;
        this.data = data;
    }
    public ApiReturnInfo(String status, String code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiReturnInfo success(){
        return new ApiReturnInfo(STATUS_SUCCESS,CODE_SUCCESS);
    }
    /*
    * 没有回传数据
    * */
    public static ApiReturnInfo success(ApiCode apiCode){
        return new ApiReturnInfo(STATUS_SUCCESS,CODE_SUCCESS,apiCode.getMessage());
    }
    /*
    * 没有接口描述信息
    * */
    public static ApiReturnInfo success(Object data){
        return new ApiReturnInfo(STATUS_SUCCESS,CODE_SUCCESS,data);
    }
    /*
    *
    * */
    public static ApiReturnInfo success(ApiCode apiCode,Object data){
        return new ApiReturnInfo(STATUS_SUCCESS,CODE_SUCCESS,apiCode.getMessage(),data);
    }
    public static ApiReturnInfo fail(){
        return new ApiReturnInfo(STATUS_FAIL,CODE_FAIL);
    }
    /*
     * 没有回传数据
     * */
    public static ApiReturnInfo fail(ApiCode apiCode){
        return new ApiReturnInfo(STATUS_FAIL,CODE_FAIL,apiCode.getMessage());
    }
    /*
     * 没有接口描述信息
     * */
    public static ApiReturnInfo fail(Object data){
        return new ApiReturnInfo(STATUS_FAIL,CODE_FAIL,data);
    }
    /*
     *
     * */
    public static ApiReturnInfo fail(ApiCode apiCode,Object data){
        return new ApiReturnInfo(STATUS_FAIL,CODE_FAIL,apiCode.getMessage(),data);
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
