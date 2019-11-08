package com.bateng.guestroom.controller.reoport;

import com.bateng.guestroom.biz.DeclarationFormBiz;
import com.bateng.guestroom.biz.RoomBiz;
import com.bateng.guestroom.biz.RoomOptionBiz;
import com.bateng.guestroom.config.controller.BaseController;
import com.bateng.guestroom.config.interceptor.DatePropertyEditor;
import com.bateng.guestroom.config.interceptor.DatePropertyEditor2;
import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Room;
import com.bateng.guestroom.entity.vo.RoomOptionVo;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private RoomOptionBiz roomOptionBiz;

    /**
     * 查看每个类别的报修次数
     *
     * @param pageVo
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/report/room/findByRoomOptionCount", method = {RequestMethod.GET, RequestMethod.POST})
    public String findByOptionNameCount(PageVo<DeclarationForm> pageVo, Model model, HttpSession session, RoomOptionVo roomOptionVo) {
        pageVo = declarationFormBiz.findByRoomOptionCountByPage(pageVo, roomOptionVo);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("roomOptionVo", roomOptionVo);
        return "report/room/report_room_index_count";
    }

    @RequestMapping(value = "/report/room/index", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(PageVo<DeclarationForm> pageVo, Model model, DeclarationForm declarationForm, HttpSession session) {
        pageVo = declarationFormBiz.findDeclarationFormByPage(pageVo, declarationForm);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("declarationForm", declarationForm);
        return "report/room/report_room_index";
    }

    /**
     * 两次维修记录以上的房间
     *
     * @param pageVo
     * @param model
     * @param declarationForm
     * @param session
     * @return
     */
    @RequestMapping(value = "/report/room/index1", method = {RequestMethod.GET, RequestMethod.POST})
    public String index1(PageVo<DeclarationForm> pageVo, Model model, DeclarationForm declarationForm, HttpSession session) {
        pageVo = declarationFormBiz.findDeclarationFormByPage1(pageVo, declarationForm);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("declarationForm", declarationForm);
        return "report/room/report_room_index1";
    }

    @RequestMapping(value = "report/room/download", method = {RequestMethod.GET, RequestMethod.POST})
    public void detail(DeclarationForm declarationForm, HttpServletResponse response) throws IOException {
        List<DeclarationForm> list = declarationFormBiz.findDeclarationForms(declarationForm);
        int rnum = 0;
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sh = wb.createSheet("sheet1");
        HSSFRow row = sh.createRow(rnum++);
        row.createCell(0).setCellValue("房号");
        row.createCell(1).setCellValue("报修位置");
        row.createCell(2).setCellValue("报修内容");
        row.createCell(3).setCellValue("报修描述");
        row.createCell(4).setCellValue("报修人(真实姓名)");
        row.createCell(5).setCellValue("创建时间");
        row.createCell(6).setCellValue("状态");
        for (DeclarationForm df : list) {
            row = sh.createRow(rnum++);
            int cnum = 0;
            row.createCell(cnum++).setCellValue(df.getRoom().getName());
            row.createCell(cnum++).setCellValue(df.getRoomOption().getName());
            row.createCell(cnum++).setCellValue(df.getForNameOption().getName());
            row.createCell(cnum++).setCellValue(df.getDescription());
            row.createCell(cnum++).setCellValue(df.getUser().getUsername() + "(" + df.getUser().getRealName() + ")");
            row.createCell(cnum++).setCellValue(df.getCreateDate());
            row.createCell(cnum++).setCellValue(df.getDeclarationFormStatus().getStatus());
        }

        response.setHeader("Content-Disposition", "attachment;filename=" + new Date().getTime() + ".xls");
        response.setContentType("application/x-download");
        response.setCharacterEncoding("utf-8");
        OutputStream out = response.getOutputStream();
        wb.write(out);
//        out.write("你好".getBytes());
        out.close();
    }

    /**
     * 维修两次记录以上的房间
     *
     * @param declarationForm
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "report/room/download1", method = {RequestMethod.GET, RequestMethod.POST})
    public void detail1(DeclarationForm declarationForm, HttpServletResponse response) throws IOException {
        List<DeclarationForm> list = declarationFormBiz.findDeclarationForms1(declarationForm);
        int rnum = 0;
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sh = wb.createSheet("sheet1");
        HSSFRow row = sh.createRow(rnum++);
        row.createCell(0).setCellValue("房号");
        row.createCell(1).setCellValue("报修位置");
        row.createCell(2).setCellValue("报修内容");
        row.createCell(3).setCellValue("报修描述");
        row.createCell(4).setCellValue("报修人(真实姓名)");
        row.createCell(5).setCellValue("创建时间");
        row.createCell(6).setCellValue("状态");
        for (DeclarationForm df : list) {
            row = sh.createRow(rnum++);
            int cnum = 0;
            row.createCell(cnum++).setCellValue(df.getRoom().getName());
            row.createCell(cnum++).setCellValue(df.getRoomOption().getName());
            row.createCell(cnum++).setCellValue(df.getForNameOption().getName());
            row.createCell(cnum++).setCellValue(df.getDescription());
            row.createCell(cnum++).setCellValue(df.getUser().getUsername() + "(" + df.getUser().getRealName() + ")");
            row.createCell(cnum++).setCellValue(df.getCreateDate());
            row.createCell(cnum++).setCellValue(df.getDeclarationFormStatus().getStatus());
        }

        response.setHeader("Content-Disposition", "attachment;filename=" + new Date().getTime() + ".xls");
        response.setContentType("application/x-download");
        response.setCharacterEncoding("utf-8");
        OutputStream out = response.getOutputStream();
        wb.write(out);
//        out.write("你好".getBytes());
        out.close();
    }

    /**
     * 统计维修记录
     *
     * @param declarationForm
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "report/room/download4", method = {RequestMethod.GET, RequestMethod.POST})
    public void download4(DeclarationForm declarationForm, HttpServletResponse response) throws IOException {
        List<DeclarationForm> list = declarationFormBiz.findDeclarationForms4(declarationForm);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int rnum = 0;
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sh = wb.createSheet("sheet1");
        HSSFRow row = sh.createRow(rnum++);
        row.createCell(0).setCellValue("房号");
        row.createCell(1).setCellValue("报修内容");
        row.createCell(2).setCellValue("报修次数");
        row.createCell(3).setCellValue("起始时间");
        row.createCell(4).setCellValue("最后时间");
        for (DeclarationForm df : list) {
            row = sh.createRow(rnum++);
            int cnum = 0;
            row.createCell(cnum++).setCellValue(df.getRoom().getName());
            row.createCell(cnum++).setCellValue(df.getForNameOption().getName());
            row.createCell(cnum++).setCellValue(df.getCount());
            row.createCell(cnum++).setCellValue(format.format(df.getTime01()));
            row.createCell(cnum++).setCellValue(format.format(df.getTime02()));
        }

        response.setHeader("Content-Disposition", "attachment;filename=" + new Date().getTime() + ".xls");
        response.setContentType("application/x-download");
        response.setCharacterEncoding("utf-8");
        OutputStream out = response.getOutputStream();
        wb.write(out);
//        out.write("你好".getBytes());
        out.close();
    }

    @RequestMapping(value = "report/room/download/count", method = {RequestMethod.GET, RequestMethod.POST})
    public void downloadCount(RoomOptionVo roomOptionVo, HttpServletResponse response) throws IOException {
        List<RoomOptionVo> roomOptionVos = roomOptionBiz.findAllRoomOptionVo(roomOptionVo);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int rnum = 0;
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sh = wb.createSheet("sheet1");
        HSSFRow row = sh.createRow(rnum++);
        row.createCell(0).setCellValue("报修类别");
        row.createCell(1).setCellValue("报修次数");
        row.createCell(2).setCellValue("起始时间");
        row.createCell(3).setCellValue("最后时间");

        for (RoomOptionVo rv : roomOptionVos) {
            row = sh.createRow(rnum++);
            int cnum = 0;
            row.createCell(cnum++).setCellValue(rv.getName());
            row.createCell(cnum++).setCellValue(rv.getCount());
            if (rv.getTime01() != null)
                row.createCell(cnum++).setCellValue(rv.getTime01());
            else
                row.createCell(cnum++).setCellValue("");
            if (rv.getTime02() != null)
                row.createCell(cnum++).setCellValue(rv.getTime02());
            else
                row.createCell(cnum++).setCellValue("");
        }

        response.setHeader("Content-Disposition", "attachment;filename=" + new Date().getTime() + ".xls");
        response.setContentType("application/x-download");
        response.setCharacterEncoding("utf-8");
        OutputStream out = response.getOutputStream();
        wb.write(out);
//        out.write("你好".getBytes());
        out.close();

    }


    /**
     * 根据房号时间内容查询
     *
     * @return
     */
    @RequestMapping(value = "report/room/findByRoomOrTimeOrContent", method = {RequestMethod.GET, RequestMethod.POST})
    public String findByRoomOrTimeOrContent(PageVo<DeclarationForm> pageVo, Model model, DeclarationForm declarationForm, HttpSession session) {
        pageVo = declarationFormBiz.findDeclarationFormByPage(pageVo, declarationForm);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("declarationForm", declarationForm);
        return "report/room/report_room_index3";
    }

    /**
     * 根据次数搜索
     *
     * @return
     */
    @RequestMapping(value = "report/room/findByCount", method = {RequestMethod.GET, RequestMethod.POST})
    public String findByCount(PageVo<DeclarationForm> pageVo, Model model, DeclarationForm declarationForm, HttpSession session) {
        pageVo = declarationFormBiz.findDeclarationFormByPage4(pageVo, declarationForm);
        model.addAttribute("pageVo", pageVo);
        model.addAttribute("declarationForm", declarationForm);
        return "report/room/report_room_index4";
    }

    public DeclarationFormBiz getDeclarationFormBiz() {
        return declarationFormBiz;
    }

    public void setDeclarationFormBiz(DeclarationFormBiz declarationFormBiz) {
        this.declarationFormBiz = declarationFormBiz;
    }

    public RoomOptionBiz getRoomOptionBiz() {
        return roomOptionBiz;
    }

    public void setRoomOptionBiz(RoomOptionBiz roomOptionBiz) {
        this.roomOptionBiz = roomOptionBiz;
    }

    public RoomBiz getRoomBiz() {
        return roomBiz;
    }

    public void setRoomBiz(RoomBiz roomBiz) {
        this.roomBiz = roomBiz;
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, "time01", new DatePropertyEditor2());
        binder.registerCustomEditor(Date.class, "time02", new DatePropertyEditor2());
    }
}
