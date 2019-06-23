package com.bateng.guestroom.controller.reoport;

import com.bateng.guestroom.biz.DeclarationFormBiz;
import com.bateng.guestroom.biz.RoomBiz;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Room;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 报表功能
 */
@Controller
@RequestMapping("/guestroom")
public class RoomReportController extends BaseController {

    @Autowired
    private RoomBiz roomBiz;
    @Autowired
    private DeclarationFormBiz declarationFormBiz;

    @RequestMapping(value = "/report/room/index",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(PageVo<DeclarationForm> pageVo, Model model, DeclarationForm declarationForm, HttpSession session){
        pageVo=declarationFormBiz.findDeclarationFormByPage(pageVo,declarationForm);
        model.addAttribute("pageVo",pageVo);
        model.addAttribute("declarationForm",declarationForm);
        return "report/room/report_room_index";
    }

    @RequestMapping(value = "report/room/download",method = RequestMethod.POST)
    public void detail(DeclarationForm declarationForm, HttpServletResponse response) throws IOException {
       List<DeclarationForm> list=declarationFormBiz.findDeclarationForms(declarationForm);
        int rnum = 0;
        HSSFWorkbook wb = new HSSFWorkbook();
//        HSSFSheet sh = wb.createSheet("sheet1");
//        HSSFRow row = sh.createRow(rnum++);
//        row.createCell(0).setCellValue("房号");
        /*row.createCell(1).setCellValue("报修位置");
        row.createCell(2).setCellValue("报修内容");
        row.createCell(3).setCellValue("报修描述");
        row.createCell(4).setCellValue("报修人(真实姓名)");
        row.createCell(5).setCellValue("创建时间");
        row.createCell(6).setCellValue("状态");*/
        /*for(DeclarationForm df : list){
            row = sh.createRow(rnum++);
            int cnum=0;
            row.createCell(cnum++).setCellValue(df.getRoom().getName());
            row.createCell(cnum++).setCellValue(df.getRoomOption().getName());
            row.createCell(cnum++).setCellValue(df.getForNameOption().getName());
            row.createCell(cnum++).setCellValue(df.getDescription());
            row.createCell(cnum++).setCellValue(df.getUser().getUsername()+"("+df.getUser().getRealName()+")");
            row.createCell(cnum++).setCellValue(df.getCreateDate());
            row.createCell(cnum++).setCellValue(df.getDeclarationFormStatus().getStatus());
        }*/

        response.setHeader("Content-Disposition","attachment;filename=" + "abc.xls");
        response.setContentType("application/x-download");
        response.setCharacterEncoding("utf-8");
        OutputStream out = response.getOutputStream();
//        wb.write(out);
        out.write("你好".getBytes());
        out.close();
    }

    public DeclarationFormBiz getDeclarationFormBiz() {
        return declarationFormBiz;
    }

    public void setDeclarationFormBiz(DeclarationFormBiz declarationFormBiz) {
        this.declarationFormBiz = declarationFormBiz;
    }

    public RoomBiz getRoomBiz() {
        return roomBiz;
    }

    public void setRoomBiz(RoomBiz roomBiz) {
        this.roomBiz = roomBiz;
    }
}
