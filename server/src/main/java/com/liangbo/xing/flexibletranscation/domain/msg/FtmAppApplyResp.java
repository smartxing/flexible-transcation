package com.liangbo.xing.flexibletranscation.domain.msg;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/23 下午10:41 xingliangbo Exp $
 */
public class FtmAppApplyResp {

    private String appName;
    private String appId;
    private String appKey;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
