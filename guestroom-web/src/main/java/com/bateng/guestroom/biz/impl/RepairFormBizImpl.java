package com.bateng.guestroom.biz.impl;

import com.bateng.guestroom.biz.RepairFormBiz;
import com.bateng.guestroom.dao.RepairFormDao;
import com.bateng.guestroom.entity.RepairForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("repairFormBiz")
public class RepairFormBizImpl implements RepairFormBiz {
    @Autowired
    private RepairFormDao repairFormDao;
    @Override
    @Transactional
    public void saveRepairForm(RepairForm repairForm) {
        repairFormDao.save(repairForm);
    }

    public RepairFormDao getRepairFormDao() {
        return repairFormDao;
    }

    public void setRepairFormDao(RepairFormDao repairFormDao) {
        this.repairFormDao = repairFormDao;
    }
}
