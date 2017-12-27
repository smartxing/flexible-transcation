package com.liangbo.xing.flexibletranscation.exception;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/19 下午11:12 xingliangbo Exp $
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({FtmClientException.class})
    @ResponseBody
    public FtmApiExceptionResp handleException(FtmApiException e, HttpServletResponse response) {
        FtmApiExceptionResp exceptionResponse = new FtmApiExceptionResp();
        response.setStatus(e.getCode());
        exceptionResponse.addError(e.getErrorCodes());
        return exceptionResponse;
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public FtmApiExceptionResp handleException(IllegalArgumentException e, HttpServletResponse response) {
        FtmApiExceptionResp exceptionResponse = new FtmApiExceptionResp();
        response.setStatus(400);
        exceptionResponse.addError(400, e.getMessage());
        return exceptionResponse;
    }


    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public FtmApiExceptionResp unknownException(Exception e, HttpServletResponse response) {
        LOGGER.error(ExceptionUtils.getStackTrace(e));
        return default500Exception(e, response);
    }

    private FtmApiExceptionResp default500Exception(Exception e, HttpServletResponse response) {
        FtmApiExceptionResp exceptionResponse = new FtmApiExceptionResp();
        response.setStatus(500);
        exceptionResponse.addError(500, "未知异常");
        return exceptionResponse;
    }


    private FtmApiExceptionResp default400Exception(Exception e, HttpServletResponse response, String msg) {
        FtmApiExceptionResp exceptionResponse = new FtmApiExceptionResp();
        response.setStatus(400);
        exceptionResponse.addError(400, msg);
        return exceptionResponse;
    }
}
