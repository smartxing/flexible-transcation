package com.liangbo.xing.flexibletranscation.domain.msg;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/23 下午10:38 xingliangbo Exp $
 */
public class FtmAppDto {

    @NotEmpty(message = "appName can not be empty")
    private String appName;
    private String appDesc;
    private String callBackUrl;


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }
}
