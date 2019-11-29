package com.bateng.guestroom.biz.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bateng.guestroom.biz.DeclarationFormStatusBiz;
import com.bateng.guestroom.dao.DeclarationFormStatusDao;
import com.bateng.guestroom.entity.DeclarationFormStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("declarationFormStatusBiz")
public class DeclarationFormStatusBizImpl implements DeclarationFormStatusBiz {

    @Autowired
    private DeclarationFormStatusDao declarationFormStatusDao;
    @Override
    public String findAllAjax() {
        List<DeclarationFormStatus> list = declarationFormStatusDao.findAll();
        return JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
    }

    public DeclarationFormStatusDao getDeclarationFormStatusDao() {
        return declarationFormStatusDao;
    }

    public void setDeclarationFormStatusDao(DeclarationFormStatusDao declarationFormStatusDao) {
        this.declarationFormStatusDao = declarationFormStatusDao;
    }
}
