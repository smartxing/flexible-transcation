package com.liangbo.xing.flexibletranscation.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/19 下午11:14 xingliangbo Exp $
 */
public class FtmApiException extends RuntimeException {

    private Integer code;
    private List<FtmApiCode> errorCodes = new ArrayList();

    public FtmApiException() {
    }


    public FtmApiException(String message, Throwable e) {
        super(message, e);
    }

    public FtmApiException(Integer httpCode, FtmApiCode... codes) {
        this.code = httpCode;
        if (codes != null && codes.length > 0) {
            this.errorCodes.addAll(Arrays.asList(codes));
        }
    }

    public FtmApiException(FtmErrorCode ftmErrorCode) {
        this.code = ftmErrorCode.getHttpCode();
        addErrorCode(ftmErrorCode.getCode(), ftmErrorCode.getMsg());
    }


    public FtmApiException(FtmBizException ftmBizException) {
        super(ftmBizException.getMessage(), ftmBizException);
        this.code = ftmBizException.getFtmErrorCode().getHttpCode();
        addErrorCode(ftmBizException.getFtmErrorCode().getCode(), ftmBizException.getFtmErrorCode().getMsg());
    }


    public void addErrorCode(int code, String msg) {
        this.errorCodes.add(new FtmApiCode(code, msg));
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public List<FtmApiCode> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<FtmApiCode> errorCodes) {
        this.errorCodes = errorCodes;
    }
}

