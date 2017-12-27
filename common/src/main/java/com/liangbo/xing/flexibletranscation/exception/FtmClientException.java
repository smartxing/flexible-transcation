package com.liangbo.xing.flexibletranscation.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/19 下午11:14 xingliangbo Exp $
 */
public class FtmClientException extends RuntimeException {
    public FtmClientException(String s, Throwable e) {
        super(s, e);
    }
}

