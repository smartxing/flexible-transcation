package com.liangbo.xing.flexibletranscation.enums;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/19 下午11:05 xingliangbo Exp $
 */
public enum SubscribeMsgStatusEnum {

    WAITINT_ASK(0),//未确认

    ASKED(1),

    UNKOWN(2),

    DIE(3),
    WAITING_INTERVENTION(4);

    private int val;

    private SubscribeMsgStatusEnum(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
