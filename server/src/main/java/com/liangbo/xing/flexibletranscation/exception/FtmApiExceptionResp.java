package com.liangbo.xing.flexibletranscation.exception;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/19 下午11:15 xingliangbo Exp $
 */
public class FtmApiExceptionResp implements Serializable {

    private List<FtmApiCode> errorCodes = Lists.newArrayList();

    public void addError(int code, String message) {
        errorCodes.add(new FtmApiCode(code, message));
    }

    public void addError(List<FtmApiCode> ftmApiCodes) {
        if (!CollectionUtils.isEmpty(ftmApiCodes)) {
            errorCodes.addAll(ftmApiCodes);
        }
    }


}
