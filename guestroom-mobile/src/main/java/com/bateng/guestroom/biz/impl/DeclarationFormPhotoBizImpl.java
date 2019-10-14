package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.DeclarationFormPhotoBiz;
import com.bateng.guestroom.config.util.FastDFSClient;
import com.bateng.guestroom.dao.DeclarationFormDao;
import com.bateng.guestroom.dao.DeclarationFormPhotoDao;
import com.bateng.guestroom.entity.DeclarationFormPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("declarationFormPhotoBiz")
public class DeclarationFormPhotoBizImpl implements DeclarationFormPhotoBiz {
    @Autowired
   private DeclarationFormPhotoDao declarationFormPhotoDao;
    @Override
    @Transactional
    public void deleteById(int id) {
        DeclarationFormPhoto declarationFormPhoto=declarationFormPhotoDao.getOne(id);
        FastDFSClient.deleteFile(declarationFormPhoto.getPath());//删除图片
        declarationFormPhotoDao.deleteById(id);
    }

    @Override
    @Transactional
    public void saveDeclaratrionFormPhoto(DeclarationFormPhoto declarationFormPhoto) {
        declarationFormPhotoDao.save(declarationFormPhoto);
    }

    public DeclarationFormPhotoDao getDeclarationFormPhotoDao() {
        return declarationFormPhotoDao;
    }

    public void setDeclarationFormPhotoDao(DeclarationFormPhotoDao declarationFormPhotoDao) {
        this.declarationFormPhotoDao = declarationFormPhotoDao;
    }
}
