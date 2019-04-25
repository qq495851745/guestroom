package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.RepairFormBiz;
import com.bateng.guestroom.dao.DeclarationFormDao;
import com.bateng.guestroom.dao.RepairFormDao;
import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.RepairForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("repairFormBiz")
public class RepairFormBizImpl implements RepairFormBiz {
    @Autowired
    private RepairFormDao repairFormDao;
    @Autowired
    private DeclarationFormDao declarationFormDao;
    @Override
    @Transactional
    public void saveRepairForm(RepairForm repairForm) {
        repairFormDao.save(repairForm);
        //修改报修单状态
        declarationFormDao.updateDeclarationForm2(3,repairForm.getDeclarationForm().getId());
    }

    @Override
    public List<RepairForm> findRepairFormByDeclarationFormId(int id) {
        return repairFormDao.findAllByDeclarationFormId(id);
    }

    public RepairFormDao getRepairFormDao() {
        return repairFormDao;
    }

    public void setRepairFormDao(RepairFormDao repairFormDao) {
        this.repairFormDao = repairFormDao;
    }

    public DeclarationFormDao getDeclarationFormDao() {
        return declarationFormDao;
    }

    public void setDeclarationFormDao(DeclarationFormDao declarationFormDao) {
        this.declarationFormDao = declarationFormDao;
    }
}
