package com.liangbo.xing.flexibletranscation.domain;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/24 下午7:19 xingliangbo Exp $
 */
public class FtmVoidResponse {

    private int value = 200;

    public FtmVoidResponse() {
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}