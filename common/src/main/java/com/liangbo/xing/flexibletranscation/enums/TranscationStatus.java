package com.liangbo.xing.flexibletranscation.enums;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午7:48 xingliangbo Exp $
 */
public enum TranscationStatus {

    OK("1"),//待确认

    UNKNOW("2"),

    FAIL("3");

    private String val;

    private TranscationStatus(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String  val) {
        this.val = val;
    }
}
