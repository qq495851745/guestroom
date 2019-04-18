package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.DeclarationFormBiz;
import com.bateng.guestroom.config.util.FastDFSClient;
import com.bateng.guestroom.dao.DeclarationFormDao;
import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.DeclarationFormPhoto;
import com.bateng.guestroom.entity.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("declarationFormBiz")
public class DeclarationFormBizImpl implements DeclarationFormBiz {

    @Autowired
    private DeclarationFormDao declarationFormDao;
    @Override
    public PageVo<DeclarationForm> findDeclarationFormByPage(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm) {

        return declarationFormDao.findDeclarationFormByPage(pageVo,declarationForm);
    }

    @Override
    @Transactional
    public void saveDeclarationForm(DeclarationForm declarationForm) {
        declarationFormDao.save(declarationForm);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        /*//删除图片
        DeclarationForm declarationForm=declarationFormDao.getOne(id);
        List<DeclarationFormPhoto> photos=declarationForm.getDeclarationFormPhotos();
        for(DeclarationFormPhoto photo:photos){
            FastDFSClient.deleteFile(photo.getPath());
        }
        declarationFormDao.deleteById(id);*/
        //假删除
        declarationFormDao.updateByFlag(0,id);

    }

    public DeclarationFormDao getDeclarationFormDao() {
        return declarationFormDao;
    }

    public void setDeclarationFormDao(DeclarationFormDao declarationFormDao) {
        this.declarationFormDao = declarationFormDao;
    }
}
