package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.DeclarationFormPhoto;

public interface DeclarationFormPhotoBiz {

    public void deleteById(int id);

    public void saveDeclaratrionFormPhoto(DeclarationFormPhoto declarationFormPhoto);
}
