package com.liangbo.xing.flexibletranscation.exception;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/19 下午11:18 xingliangbo Exp $
 */
public class FtmErrorCode {

    private int    code;
    private int    httpCode;
    private String msg;

    public FtmErrorCode(int code,int httpCode, String msg) {
        this.code = code;
        this.msg = msg;
        this.httpCode = httpCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
