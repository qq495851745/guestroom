package com.bateng.guestroom.controller;

import com.alibaba.fastjson.JSONObject;
import com.bateng.guestroom.biz.DeclarationFormBiz;
import com.bateng.guestroom.biz.RepairFormBiz;
import com.bateng.guestroom.config.constant.StatusCodeDWZ;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.config.util.FastDFSClient;
import com.bateng.guestroom.entity.*;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/guestroom")
public class RepairFormController extends BaseController {

    @Autowired
    private DeclarationFormBiz declarationFormBiz;
    @Autowired
    private RepairFormBiz repairFormBiz;

    //跳转添加维修单
    @RequestMapping(value = "/repairForm/declarationForm/{id}",method = {RequestMethod.GET})
    public String toAdd(@PathVariable(required = false,value = "id") int id, DeclarationForm declarationForm, Model model){
       declarationForm = declarationFormBiz.getDeclarationFormById(declarationForm.getId());
       model.addAttribute("declarationForm",declarationForm);
       addurl(model);
        //修改状态为已读
        DeclarationFormStatus declarationFormStatus=new DeclarationFormStatus();
        declarationFormStatus.setId(2);
        declarationForm.setDeclarationFormStatus(declarationFormStatus);
        declarationFormBiz.updateStatus(declarationForm);
        return  "repairForm/project/repairForm_add";
    }

    //做添加维修单
    @RequestMapping(value = "/repairForm",method = RequestMethod.POST)
    public String add(RepairForm repairForm, @RequestParam("photo") MultipartFile[] photos, HttpSession session) throws  Exception{
        List<RepairFormPhoto> repairFormPhotos=new ArrayList<RepairFormPhoto>();
        for(MultipartFile photo : photos){
            if(photo.getSize() == 0)
                continue;
            String path=FastDFSClient.uploadFile(photo.getInputStream(),photo.getOriginalFilename());
            RepairFormPhoto repairFormPhoto=new RepairFormPhoto();
            repairFormPhoto.setExt(FastDFSClient.getFileExt(photo.getOriginalFilename()));
            repairFormPhoto.setCreateDate(new Date());
            repairFormPhoto.setOrigName(photo.getOriginalFilename());
            repairFormPhoto.setPath(path);
            repairFormPhotos.add(repairFormPhoto);
        }
        repairForm.setRepairFormPhotos(repairFormPhotos);
        repairForm.setCreateDate(new Date());
        repairForm.setUser1((User) session.getAttribute("user"));
        repairForm.setDeclarationFormStatus(new DeclarationFormStatus(3));
        repairFormBiz.saveRepairForm(repairForm);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("statusCode", StatusCodeDWZ.OK);
        jsonObject.put("callbackType","closeCurrent");
        jsonObject.put("message","处理成功！");
        jsonObject.put("navTabId","w_15");
        return jsonObject.toJSONString();
    }

    public DeclarationFormBiz getDeclarationFormBiz() {
        return declarationFormBiz;
    }

    public void setDeclarationFormBiz(DeclarationFormBiz declarationFormBiz) {
        this.declarationFormBiz = declarationFormBiz;
    }

    public RepairFormBiz getRepairFormBiz() {
        return repairFormBiz;
    }

    public void setRepairFormBiz(RepairFormBiz repairFormBiz) {
        this.repairFormBiz = repairFormBiz;
    }
}
