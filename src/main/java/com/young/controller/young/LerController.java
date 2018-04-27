package com.young.controller.young;

import com.framework.util.ExcelUtil;
import com.framework.util.FileUtil;
import com.young.controller.base.BaseController;
import com.young.entity.*;
import com.young.json.BaseJson;
import com.young.services.le_r.impl.LerServiceImpl;
import com.young.vo.TableExtendVo;
import com.young.vo.YearVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;




/*汉办*/
@Controller
@RequestMapping(value = "/r")
public class LerController extends BaseController {
    @Resource
    private LerServiceImpl lerService;

    private BaseJson queryJson = new BaseJson();


    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public ModelAndView management() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        ArrayList<User> users = lerService.get_users_by_pre_code(user.getuCodeE());
        modelAndView.getModel().put("user",user);
        modelAndView.getModel().put("users",users);
        modelAndView.setViewName("ler/management");
        return modelAndView;
    }


    @RequestMapping(value = "/{year}/{code}/table-management", method = RequestMethod.GET)
    public ModelAndView table_management_detail(
            @PathVariable("year") String year,
            @PathVariable("code") int code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        ArrayList<TableExtendVo>reList = lerService.get_tables(year,code,user);
        ArrayList<User>users =lerService.get_users_by_pre_code(user.getuCodeE());
        ArrayList<YearVo>years = lerService.get_years();
        modelAndView.getModel().put("userself",user);
        modelAndView.getModel().put("tables",reList);
        modelAndView.getModel().put("users",users);
        modelAndView.getModel().put("years",years);
        modelAndView.getModel().put("user",code);
        modelAndView.getModel().put("year",year);
        modelAndView.setViewName("/ler/table-management");
        return modelAndView;
    }


    @RequestMapping(value = "/{year}/table-list", method = RequestMethod.GET)
    public ModelAndView table_list(
            @PathVariable("year") String year
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        modelAndView.getModel().put("user",user);
        ArrayList<TableCopy> tables=null;
        if (year.equals(-1)){
        }else{
            tables = lerService.get_table_by_year_user(year,user);
        }
        modelAndView.getModel().put("tables",  (ArrayList<TableCopy> )tables);
        ArrayList<YearVo>years = lerService.get_years();
        modelAndView.getModel().put("years",years);
        modelAndView.getModel().put("year",year);
        modelAndView.setViewName("/ler/table-list");
        return modelAndView;
    }



    @RequestMapping(value = "/excel_upload", method = RequestMethod.POST)
    public ModelAndView excel_upload(
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        queryJson = new BaseJson();
        List<Map<String , Object>> reList = null ;
        if (file.getOriginalFilename().endsWith(".xlsx")){
            reList= ExcelUtil.deal_excel_xlsx(file.getInputStream());
        }else if (file.getOriginalFilename().endsWith(".xls")){
            reList=ExcelUtil.deal_excel_xls(file.getInputStream());
        }else{
            modelAndView.getModel().put("msg","文件格式不正确，请检查后上传");
            modelAndView.getModel().put("url","/r/management");
            modelAndView.setViewName("return");
            return modelAndView;
        }
        int readNum = reList.size();
        int successNum;
        successNum = lerService.total_register(reList);
        modelAndView.getModel().put("msg","读取公司"+readNum+"家 ， 成功注册"+successNum+"家");
        modelAndView.getModel().put("url","/r/management");
        modelAndView.setViewName("return");
        return modelAndView;
    }




    @RequestMapping(value = "/{code}/recode", method = RequestMethod.GET)
    public ModelAndView login(
            @PathVariable("code") long code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int flag =lerService.reset_pw(code);
        if(flag==1){
            modelAndView.getModel().put("msg","重置密码成功");
            modelAndView.getModel().put("url","/r/management");
        }
        else{
            modelAndView.getModel().put("msg","重置密码失败，请稍后再试");
            modelAndView.getModel().put("url","/r/management");
        }
        modelAndView.setViewName("return");
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.POST)
    public ModelAndView cancel(
            @PathVariable("id") long id,
            @RequestParam(value ="form_cancel_text", defaultValue = "") String form_cancel_text
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int recode = lerService.cancel(id,form_cancel_text);
        if (recode == 1){
            modelAndView.getModel().put("msg","驳回操作成功");
            modelAndView.getModel().put("url","/r/-1/-1/table-management");
            modelAndView.setViewName("return");
        }else{
            modelAndView.getModel().put("msg","驳回操作失败");
            modelAndView.getModel().put("url","/r/-1/-1/table-management");
            modelAndView.setViewName("return");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/{id}/up", method = RequestMethod.POST)
    public ModelAndView up(
            @PathVariable("id") long id,
            @RequestParam(value ="form_cancel_text", defaultValue = "") String form_cancel_text,
            @RequestParam(value ="tNumber", defaultValue = "") String tNumber,
            @RequestParam(value ="tDateY", defaultValue = "") String tDateY,
            @RequestParam(value ="tDateM", defaultValue = "") String tDateM,
            @RequestParam(value ="tDateD", defaultValue = "") String tDateD
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int recode = lerService.up(id,form_cancel_text,tNumber,tDateY,tDateM,tDateD);
        if (recode == 1){
            modelAndView.getModel().put("msg","审核通过操作成功");
            modelAndView.getModel().put("url","/r/-1/-1/table-management");
            modelAndView.setViewName("return");
        }else{
            modelAndView.getModel().put("msg","审核通过操作失败");
            modelAndView.getModel().put("url","/r/-1/-1/table-management");
            modelAndView.setViewName("return");
        }
        return modelAndView;
    }



    @ResponseBody
    @RequestMapping(value = "/{year}/downloadFile", method = RequestMethod.GET)
    private void  downloadFile3(
            @PathVariable("year") String year
    ){
        try {
            User user = getUserSession();
            ArrayList<TableCopy> tables=null;
            if (year.equals("-1")){
            }else{
                tables = lerService.get_table_by_year_user(year,user);
            }
            //文件所在目录路径
            String filePath = getHttpRequest().getSession().getServletContext().getRealPath(File.separator)+File.separator+
                    "web"+File.separator+"file"+File.separator;
            String fileName = year+"年"+user.getuName()+"汉办汇总表.xls";
            String path =  FileUtil.creat_file_static3(year,tables,filePath,fileName);
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            //设置Http响应头告诉浏览器下载这个附件
            getHttpResponse().setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(fileName, "UTF-8"));
            OutputStream outputStream = getHttpResponse().getOutputStream();
            byte[] bytes = new byte[2048];
            int len = 0;
            while ((len = fileInputStream.read(bytes))>0){
                outputStream.write(bytes,0,len);
            }
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @RequestMapping(value = "/psd", method = RequestMethod.GET)
    public ModelAndView psd(
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        modelAndView.setViewName("/ler/psd");
        return modelAndView;
    }


    @RequestMapping(value = "/change_pw", method = RequestMethod.POST)
    public ModelAndView change_pw(
            @RequestParam("old_pw") String old_pw,
            @RequestParam("new_pw1") String new_pw1,
            @RequestParam("new_pw") String new_pw
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if (!new_pw1.equals(new_pw)){
            modelAndView.getModel().put("msg","两次新密码必须相同");
            modelAndView.getModel().put("url","/ler/psd");
            modelAndView.setViewName("return");
            return modelAndView;
        }
        User user =getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int flag = lerService.change_pw(old_pw,new_pw,user.getuId());
        if (flag==1){
            modelAndView.getModel().put("msg","修改密码成功");
            modelAndView.getModel().put("url","/common/login");
        }else if (flag==-2){
            modelAndView.getModel().put("msg","旧密码不正确");
            modelAndView.getModel().put("url","/ler/psd");
        }
        else if (flag==-3){
            modelAndView.getModel().put("msg","新旧密码不能相同");
            modelAndView.getModel().put("url","/ler/psd");
        }else{
            modelAndView.getModel().put("msg","修改密码失败，请联系管理员修改密码");
            modelAndView.getModel().put("url","/ler/psd");
        }
        modelAndView.setViewName("return");
        return modelAndView;
    }






    @RequestMapping(value = "/{year}/{code}/table-management2", method = RequestMethod.GET)
    public ModelAndView management2(
            @PathVariable("year") String year,
            @PathVariable("code") int code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }


        ArrayList<TableExtendVo>reList = lerService.get_tables2(year,code,user);
        ArrayList<User1>users =lerService.get_users_by_pre_code2();
        ArrayList<YearVo>years = lerService.get_years2();


        modelAndView.getModel().put("userself",user);
        modelAndView.getModel().put("tables",reList);
        modelAndView.getModel().put("users",users);
        modelAndView.getModel().put("years",years);
        modelAndView.getModel().put("user",code);
        modelAndView.getModel().put("year",year);
        modelAndView.setViewName("/ler/table-management2");
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/cancel2", method = RequestMethod.POST)
    public ModelAndView cancel2(
            @PathVariable("id") long id,
            @RequestParam(value ="form_cancel_text", defaultValue = "") String form_cancel_text
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int recode = lerService.cancel2(id,form_cancel_text);
        if (recode == 1){
            modelAndView.getModel().put("msg","驳回操作成功");
            modelAndView.getModel().put("url","/r/-1/-1/table-management2");
            modelAndView.setViewName("return");
        }else{
            modelAndView.getModel().put("msg","驳回操作失败");
            modelAndView.getModel().put("url","/r/-1/-1/table-management2");
            modelAndView.setViewName("return");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/up2", method = RequestMethod.POST)
    public ModelAndView up2(
            @PathVariable("id") long id,
            @RequestParam(value ="form_cancel_text", defaultValue = "") String form_cancel_text,
            @RequestParam(value ="tNumber", defaultValue = "") String tNumber,
            @RequestParam(value ="tDateY", defaultValue = "") String tDateY,
            @RequestParam(value ="tDateM", defaultValue = "") String tDateM,
            @RequestParam(value ="tDateD", defaultValue = "") String tDateD
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int recode = lerService.up2(id,form_cancel_text,tNumber,tDateY,tDateM,tDateD);
        if (recode == 1){
            modelAndView.getModel().put("msg","审核通过操作成功");
            modelAndView.getModel().put("url","/r/-1/-1/table-management2");
            modelAndView.setViewName("return");
        }else{
            modelAndView.getModel().put("msg","审核通过操作失败");
            modelAndView.getModel().put("url","/r/-1/-1/table-management2");
            modelAndView.setViewName("return");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/{year}/table-list2", method = RequestMethod.GET)
    public ModelAndView table_list2(
            @PathVariable("year") String year
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        modelAndView.getModel().put("user",user);
        ArrayList<TableCopyCopy> tables=null;
        if (year.equals(-1)){
        }else{
            tables = lerService.get_table_by_year_user2(year,user);
        }
        modelAndView.getModel().put("tables",  (ArrayList<TableCopyCopy> )tables);
        ArrayList<YearVo>years = lerService.get_years2();
        modelAndView.getModel().put("years",years);
        modelAndView.getModel().put("year",year);
        modelAndView.setViewName("/ler/table-list2");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/{year}/downloadFile2", method = RequestMethod.GET)
    private void  downloadFile4(
            @PathVariable("year") String year
    ){
        try {
            User user = getUserSession();
            ArrayList<TableCopyCopy> tables=null;
            if (year.equals("-1")){
            }else{
                tables = lerService.get_table_by_year_user2(year,user);
            }
            //文件所在目录路径
            String filePath = getHttpRequest().getSession().getServletContext().getRealPath(File.separator)+File.separator+
                    "web"+File.separator+"file"+File.separator;
            String fileName = year+"年"+user.getuName()+"汉办汇总表.xls";
            String path =  FileUtil.creat_file_static4(year,tables,filePath,fileName);
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            //设置Http响应头告诉浏览器下载这个附件
            getHttpResponse().setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(fileName, "UTF-8"));
            OutputStream outputStream = getHttpResponse().getOutputStream();
            byte[] bytes = new byte[2048];
            int len = 0;
            while ((len = fileInputStream.read(bytes))>0){
                outputStream.write(bytes,0,len);
            }
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/management2", method = RequestMethod.GET)
    public ModelAndView management2() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        ArrayList<User1> users = lerService.get_users_by_pre_code2();
        modelAndView.getModel().put("user",user);
        modelAndView.getModel().put("users",users);
        modelAndView.setViewName("ler/management2");
        return modelAndView;
    }

    @RequestMapping(value = "/{code}/recode2", method = RequestMethod.GET)
    public ModelAndView recode2(
            @PathVariable("code") long code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int flag =lerService.reset_pw2(code);
        if(flag==1){
            modelAndView.getModel().put("msg","重置密码成功");
            modelAndView.getModel().put("url","/r/management2");
        }
        else{
            modelAndView.getModel().put("msg","重置密码失败，请稍后再试");
            modelAndView.getModel().put("url","/r/management2");
        }
        modelAndView.setViewName("return");
        return modelAndView;
    }


    @RequestMapping(value = "/req2", method = RequestMethod.GET)
    public ModelAndView req2(
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        ArrayList<User1> users = lerService.req2();
        modelAndView.getModel().put("user",user);
        modelAndView.getModel().put("users",users);
        modelAndView.setViewName("ler/req2");
        return modelAndView;
    }


    @RequestMapping(value = "/{code}/ok2", method = RequestMethod.GET)
    public ModelAndView ok2(
            @PathVariable("code") long code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();

        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int flag =lerService.reset_ok2(code);
        User1 user1 = lerService.getEntity(code+"",User1.class);
        if(flag==1){
            modelAndView.getModel().put("msg","账号审核通过，初始账号: sc" +user1.getU1Phone()+ "  初始密码: 123456，请电话联系通知对方");

            modelAndView.getModel().put("url","/r/req2");
        }
        else{
            modelAndView.getModel().put("msg","系统错误");
            modelAndView.getModel().put("url","/r/req2");
        }
        modelAndView.setViewName("return");
        return modelAndView;
    }

    @RequestMapping(value = "/{code}/nook2", method = RequestMethod.GET)
    public ModelAndView nook2(
            @PathVariable("code") long code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int flag =lerService.reset_nook2(code);
        if(flag==1){
            modelAndView.getModel().put("msg","账号审核未通过，请电话联系通知对方重新申请");
            modelAndView.getModel().put("url","/r/req2");
        }
        else{
            modelAndView.getModel().put("msg","系统错误");
            modelAndView.getModel().put("url","/r/req2");
        }
        modelAndView.setViewName("return");
        return modelAndView;
    }




    @RequestMapping(value = "/{year}/{code}/table-management3", method = RequestMethod.GET)
    public ModelAndView management3(
            @PathVariable("year") String year,
            @PathVariable("code") int code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }


        ArrayList<TableExtendVo>reList = lerService.get_tables3(year,code,user);
        ArrayList<User2>users =lerService.get_users_by_pre_code3();
        ArrayList<YearVo>years = lerService.get_years3();


        modelAndView.getModel().put("userself",user);
        modelAndView.getModel().put("tables",reList);
        modelAndView.getModel().put("users",users);
        modelAndView.getModel().put("years",years);
        modelAndView.getModel().put("user",code);
        modelAndView.getModel().put("year",year);
        modelAndView.setViewName("/ler/table-management3");
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/cancel3", method = RequestMethod.POST)
    public ModelAndView cancel3(
            @PathVariable("id") long id,
            @RequestParam(value ="form_cancel_text", defaultValue = "") String form_cancel_text
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int recode = lerService.cancel3(id,form_cancel_text);
        if (recode == 1){
            modelAndView.getModel().put("msg","驳回操作成功");
            modelAndView.getModel().put("url","/r/-1/-1/table-management3");
            modelAndView.setViewName("return");
        }else{
            modelAndView.getModel().put("msg","驳回操作失败");
            modelAndView.getModel().put("url","/r/-1/-1/table-management3");
            modelAndView.setViewName("return");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/up3", method = RequestMethod.POST)
    public ModelAndView up3(
            @PathVariable("id") long id,
            @RequestParam(value ="form_cancel_text", defaultValue = "") String form_cancel_text,
            @RequestParam(value ="tNumber", defaultValue = "") String tNumber,
            @RequestParam(value ="tDateY", defaultValue = "") String tDateY,
            @RequestParam(value ="tDateM", defaultValue = "") String tDateM,
            @RequestParam(value ="tDateD", defaultValue = "") String tDateD
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int recode = lerService.up3(id,form_cancel_text,tNumber,tDateY,tDateM,tDateD);
        if (recode == 1){
            modelAndView.getModel().put("msg","审核通过操作成功");
            modelAndView.getModel().put("url","/r/-1/-1/table-management3");
            modelAndView.setViewName("return");
        }else{
            modelAndView.getModel().put("msg","审核通过操作失败");
            modelAndView.getModel().put("url","/r/-1/-1/table-management3");
            modelAndView.setViewName("return");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/{year}/table-list3", method = RequestMethod.GET)
    public ModelAndView table_list3(
            @PathVariable("year") String year
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        modelAndView.getModel().put("user",user);
        ArrayList<TableCopyCopyCopy> tables=null;
        if (year.equals(-1)){
        }else{
            tables = lerService.get_table_by_year_user3(year,user);
        }
        modelAndView.getModel().put("tables",  (ArrayList<TableCopyCopyCopy> )tables);
        ArrayList<YearVo>years = lerService.get_years3();
        modelAndView.getModel().put("years",years);
        modelAndView.getModel().put("year",year);
        modelAndView.setViewName("/ler/table-list3");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/{year}/downloadFile3", method = RequestMethod.GET)
    private void  downloadFile5(
            @PathVariable("year") String year
    ){
        try {
            User user = getUserSession();
            ArrayList<TableCopyCopyCopy> tables=null;
            if (year.equals("-1")){
            }else{
                tables = lerService.get_table_by_year_user3(year,user);
            }
            //文件所在目录路径
            String filePath = getHttpRequest().getSession().getServletContext().getRealPath(File.separator)+File.separator+
                    "web"+File.separator+"file"+File.separator;
            String fileName = year+"年"+user.getuName()+"汉办汇总表.xls";
            String path =  FileUtil.creat_file_static5(year,tables,filePath,fileName);
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            //设置Http响应头告诉浏览器下载这个附件
            getHttpResponse().setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(fileName, "UTF-8"));
            OutputStream outputStream = getHttpResponse().getOutputStream();
            byte[] bytes = new byte[2048];
            int len = 0;
            while ((len = fileInputStream.read(bytes))>0){
                outputStream.write(bytes,0,len);
            }
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/management3", method = RequestMethod.GET)
    public ModelAndView management3() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        ArrayList<User2> users = lerService.get_users_by_pre_code3();
        modelAndView.getModel().put("user",user);
        modelAndView.getModel().put("users",users);
        modelAndView.setViewName("ler/management3");
        return modelAndView;
    }

    @RequestMapping(value = "/{code}/recode3", method = RequestMethod.GET)
    public ModelAndView recode3(
            @PathVariable("code") long code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int flag =lerService.reset_pw3(code);
        if(flag==1){
            modelAndView.getModel().put("msg","重置密码成功");
            modelAndView.getModel().put("url","/r/management3");
        }
        else{
            modelAndView.getModel().put("msg","重置密码失败，请稍后再试");
            modelAndView.getModel().put("url","/r/management3");
        }
        modelAndView.setViewName("return");
        return modelAndView;
    }


    @RequestMapping(value = "/req3", method = RequestMethod.GET)
    public ModelAndView req3(
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        ArrayList<User2> users = lerService.req3();
        modelAndView.getModel().put("user",user);
        modelAndView.getModel().put("users",users);
        modelAndView.setViewName("ler/req3");
        return modelAndView;
    }


    @RequestMapping(value = "/{code}/ok3", method = RequestMethod.GET)
    public ModelAndView ok3(
            @PathVariable("code") long code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int flag =lerService.reset_ok3(code);
        User2 user2 = lerService.getEntity(code+"",User2.class);
        if(flag==1){
            modelAndView.getModel().put("msg","账号审核通过，初始账号: te" +user2.getU2Phone()+ "  初始密码: 123456，请电话联系通知对方");
            modelAndView.getModel().put("url","/r/req3");
        }
        else{
            modelAndView.getModel().put("msg","系统错误");
            modelAndView.getModel().put("url","/r/req3");
        }
        modelAndView.setViewName("return");
        return modelAndView;
    }

    @RequestMapping(value = "/{code}/nook3", method = RequestMethod.GET)
    public ModelAndView nook3(
            @PathVariable("code") long code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=4){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        int flag =lerService.reset_nook3(code);
        if(flag==1){
            modelAndView.getModel().put("msg","账号审核未通过，请电话联系通知对方重新申请");
            modelAndView.getModel().put("url","/r/req3");
        }
        else{
            modelAndView.getModel().put("msg","系统错误");
            modelAndView.getModel().put("url","/r/req3");
        }
        modelAndView.setViewName("return");
        return modelAndView;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public void users() throws Exception {
        try {
            User user = getUserSession();

            ArrayList<User> users = lerService.get_users_by_pre_code(user.getuCodeE());
            //文件所在目录路径

            String filePath = getHttpRequest().getSession().getServletContext().getRealPath(File.separator)+File.separator+
                    "web"+File.separator+"file"+File.separator;
            String fileName = user.getuName() + "单位通讯录汇总表.xls";

            String path =  FileUtil.creat_usersr(user,users,filePath,fileName);
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            //设置Http响应头告诉浏览器下载这个附件
            getHttpResponse().setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(fileName, "UTF-8"));
            OutputStream outputStream = getHttpResponse().getOutputStream();
            byte[] bytes = new byte[2048];
            int len = 0;
            while ((len = fileInputStream.read(bytes))>0){
                outputStream.write(bytes,0,len);
            }
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/users1", method = RequestMethod.GET)
    public void users1() throws Exception {
        try {
            User user = getUserSession();

            ArrayList<User1> users = lerService.get_users_by_pre_code2();
            //文件所在目录路径

            String filePath = getHttpRequest().getSession().getServletContext().getRealPath(File.separator)+File.separator+
                    "web"+File.separator+"file"+File.separator;
            String fileName = user.getuName() + "单位通讯录汇总表.xls";

            String path =  FileUtil.creat_usersr1(user,users,filePath,fileName);
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            //设置Http响应头告诉浏览器下载这个附件
            getHttpResponse().setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(fileName, "UTF-8"));
            OutputStream outputStream = getHttpResponse().getOutputStream();
            byte[] bytes = new byte[2048];
            int len = 0;
            while ((len = fileInputStream.read(bytes))>0){
                outputStream.write(bytes,0,len);
            }
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/users2", method = RequestMethod.GET)
    public void users2() throws Exception {
        try {
            User user = getUserSession();

            ArrayList<User2> users = lerService.get_users_by_pre_code3();
            //文件所在目录路径

            String filePath = getHttpRequest().getSession().getServletContext().getRealPath(File.separator)+File.separator+
                    "web"+File.separator+"file"+File.separator;
            String fileName = user.getuName() + "单位通讯录汇总表.xls";

            String path =  FileUtil.creat_usersr2(user,users,filePath,fileName);
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            //设置Http响应头告诉浏览器下载这个附件
            getHttpResponse().setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(fileName, "UTF-8"));
            OutputStream outputStream = getHttpResponse().getOutputStream();
            byte[] bytes = new byte[2048];
            int len = 0;
            while ((len = fileInputStream.read(bytes))>0){
                outputStream.write(bytes,0,len);
            }
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
