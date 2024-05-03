package com.ruoyi.web.controller.framework.exception;

import com.ruoyi.common.exception.GlobalException;

public class CustomException  extends GlobalException {
    private static final long serialVersionUID = 1L;
    private String code;

    public CustomException(String code, String message) {
        super(message);
        this.code = code;
    }

}
