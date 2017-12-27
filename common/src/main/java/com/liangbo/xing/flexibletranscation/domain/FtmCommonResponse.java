package com.liangbo.xing.flexibletranscation.domain;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 下午8:54 xingliangbo Exp $
 */
public class FtmCommonResponse {

    public FtmCommonResponse(){

    }

    public FtmCommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

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
}
