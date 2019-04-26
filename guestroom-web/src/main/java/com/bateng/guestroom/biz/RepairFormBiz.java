package com.bateng.guestroom.biz;

import com.bateng.guestroom.entity.RepairForm;

import java.util.List;

public interface RepairFormBiz {

    public void saveRepairForm(RepairForm repairForm);

    public void saveRepairForm2(RepairForm repairForm);

    public List<RepairForm> findRepairFormByDeclarationFormId(int id);
}
