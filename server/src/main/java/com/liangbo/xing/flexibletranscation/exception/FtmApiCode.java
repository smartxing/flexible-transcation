package com.liangbo.xing.flexibletranscation.exception;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 下午3:20 xingliangbo Exp $
 */
public class FtmApiCode {

    private int code;
    private String message;

    public FtmApiCode() {
    }

    public FtmApiCode(int code, String error) {
        this.code = code;
        this.message = error;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String messge) {
        this.message = messge;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
