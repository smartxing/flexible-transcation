package com.liangbo.xing.flexibletranscation.exception;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 下午3:14 xingliangbo Exp $
 */
public class FtmBizException extends RuntimeException {

    private FtmErrorCode ftmErrorCode;
    private String msg;

    public FtmBizException(String message) {
        super(message);
    }

    public FtmBizException(FtmErrorCode ftmErrorCode, Throwable cause) {
        super(ftmErrorCode.getMsg(), cause);
        this.ftmErrorCode = ftmErrorCode;
    }

    public FtmBizException(FtmErrorCode ftmErrorCode, String msg) {
        super(ftmErrorCode.getMsg());
        this.ftmErrorCode = ftmErrorCode;
        this.msg = msg;
    }

    public FtmBizException(int code, int httpCode, String msg) {
        super(msg);
        this.ftmErrorCode = new FtmErrorCode(code, httpCode, msg);
    }

    public FtmBizException(int code, int httpCode, String msg, Throwable cause) {
        super(msg, cause);
        this.ftmErrorCode = new FtmErrorCode(code, httpCode, msg);
    }

    public FtmBizException(FtmErrorCode ftmErrorCode) {
        super(ftmErrorCode.getMsg());
        this.ftmErrorCode = ftmErrorCode;
    }

    public FtmErrorCode getFtmErrorCode() {
        return ftmErrorCode;
    }

    public void setFtmErrorCode(FtmErrorCode ftmErrorCode) {
        this.ftmErrorCode = ftmErrorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
