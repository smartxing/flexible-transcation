package com.liangbo.xing.flexibletranscation.enums;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/19 下午11:03 xingliangbo Exp $
 */
public enum PublishMsgStatusEnum {

    WAITING_CONFIRM(0),//待确认

    SENDING(1),

    UNKOWN(2),//未知

    DIE(3),

    WAITING_INTERVENTION(4);//等待人工干预

    private int val;

    private PublishMsgStatusEnum(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
