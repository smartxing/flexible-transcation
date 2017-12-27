package com.liangbo.xing.flexibletranscation.domain;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/26 上午12:01 xingliangbo Exp $
 */
public class FtmProducerCheckResp {

    private String transcationStatus;

    public FtmProducerCheckResp(String transcationStatus) {
        this.transcationStatus = transcationStatus;
    }

    public String getTranscationStatus() {
        return transcationStatus;
    }

    public void setTranscationStatus(String transcationStatus) {
        this.transcationStatus = transcationStatus;
    }
}
