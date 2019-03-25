package com.bateng.guestroom.config.interceptor;

import org.springframework.util.DigestUtils;

import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;

public class MD5PropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            String md5= DigestUtils.md5DigestAsHex(text.getBytes("utf-8"));
            setValue(md5);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
