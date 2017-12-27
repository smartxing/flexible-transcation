package com.liangbo.xing.flexibletranscation.assembly;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/24 下午7:56 xingliangbo Exp $
 */
public class ChainInfo {

    private String method;

    private Object[] params;

    private Object info;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
