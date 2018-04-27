/*
package com.young.controller.young;

import com.framework.util.ExcelUtil;
import com.framework.util.FileUtil;
import com.young.controller.base.BaseController;
import com.young.json.BaseJson;
import com.young.services.young.IYoungService;
import com.young.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

*/
/**
 * Created by young on 2017/9/7.
 *//*

@Controller
@RequestMapping(value = "/young")
public class YoungController extends BaseController{
    @Resource
    private IYoungService youngService;

    private BaseJson queryJson = new BaseJson();



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
    ) throws Exception {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        setUserSession(null);
        setUserTypeSession(null);
        setSessionAttribute("per_tables",null);
        modelAndView.setViewName("redirect:/young/login");
        return modelAndView;
    }

    @RequestMapping(value = "/company-management", method = RequestMethod.GET)
    public ModelAndView company_management() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 1){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        List<UserVo>reList=youngService.get_userList_pro(user_id);
        modelAndView.getModel().put("users",reList);
        modelAndView.getModel().put("usercode",user_id);
        modelAndView.setViewName("company-management");
        return modelAndView;
    }


    @RequestMapping(value = "/table", method = RequestMethod.GET)
    public ModelAndView table(
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 2){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        ArrayList<PerTable>  perTables;
        if (getSessionAttribute("per_tables")==null){
            perTables = new ArrayList<>();
        }else {
            perTables = (ArrayList<PerTable>) getSessionAttribute("per_tables");
        }

        int dis = perTables.size();
        for (int i = 0; i < 10 - dis; i++) {
            perTables.add(new PerTable());
        }
        modelAndView.getModel().put("usercode",user_id);
        modelAndView.getModel().put("year", getSessionAttribute("year"));
        modelAndView.getModel().put("main_table",getSessionAttribute("main_table"));
        modelAndView.getModel().put("per_tables",perTables);
        modelAndView.setViewName("table");
        return modelAndView;
    }


    @RequestMapping(value = "/history-table", method = RequestMethod.GET)
    public ModelAndView history_table(
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 2){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }

        modelAndView.getModel().put("usercode",user_id);
        ArrayList<TableShortVo>reList = youngService.get_tables(user_id);
        modelAndView.getModel().put("list",(ArrayList<TableShortVo>)reList);
        modelAndView.setViewName("history-table");
        return modelAndView;
    }

    @RequestMapping(value = "/psd", method = RequestMethod.GET)
    public ModelAndView psd(
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 2){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        modelAndView.getModel().put("usercode",user_id);
        modelAndView.setViewName("psd");
        return modelAndView;
    }

    @RequestMapping(value = "/psd-for-manager", method = RequestMethod.GET)
    public ModelAndView psd_for_manager(
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 1){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        modelAndView.getModel().put("usercode",user_id);
        modelAndView.setViewName("psd-for-manager");
        return modelAndView;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public ModelAndView statistics(
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 1){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        modelAndView.getModel().put("usercode",user_id);
        modelAndView.getModel().put("perTables",null);
        modelAndView.getModel().put("totalTable",null);
        modelAndView.getModel().put("years",youngService.get_years());
        modelAndView.getModel().put("year",-1);
        modelAndView.setViewName("statistics");
        return modelAndView;
    }


    @RequestMapping(value = "/{year}/statistics", method = RequestMethod.GET)
    public ModelAndView statistics_year(
            @PathVariable("year") int year
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 1){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        modelAndView.getModel().put("usercode",user_id);
        Map<String , Object>reMap = youngService.init_table_statistics_pro(year,user_id);
        modelAndView.getModel().put("perTables",(ArrayList<MainTableVo>)reMap.get("perTables"));
        modelAndView.getModel().put("totalTable",(MainTable)reMap.get("totalTable"));
        modelAndView.getModel().put("years",(ArrayList<YearVo>)reMap.get("years"));
        modelAndView.getModel().put("year",year);
        modelAndView.setViewName("statistics");
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.POST)
    public ModelAndView cancel(
            @PathVariable("id") long id,
            @RequestParam(value ="form_cancel_text", defaultValue = "") String form_cancel_text
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 1){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        form_cancel_text=new String(form_cancel_text.getBytes("iso8859-1"),"utf-8");
        int recode = youngService.cancel(id,form_cancel_text);
        if (recode == 1){
            modelAndView.getModel().put("msg","驳回操作成功");
            if (user_type==3) modelAndView.getModel().put("url","/young/top-table-management");
            else  modelAndView.getModel().put("url","/young/table-management");
            modelAndView.setViewName("return");
        }else{
            modelAndView.getModel().put("msg","驳回操作失败");
            if (user_type==3) modelAndView.getModel().put("url","/young/top-table-management");
            else  modelAndView.getModel().put("url","/young/table-management");
            modelAndView.setViewName("return");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/table-management", method = RequestMethod.GET)
    public ModelAndView table_management(
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 1){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        modelAndView.getModel().put("usercode",user_id);
        ArrayList<TableShortVo>reList = youngService.get_table_by_code_a_year_for_pro(-1,-1,user_id);
        ArrayList<CodeVo>codes = youngService.get_codes_for_pro(user_id);
        ArrayList<YearVo>years = youngService.get_years();
        modelAndView.getModel().put("list",(ArrayList<TableShortVo>)reList);
        modelAndView.getModel().put("years",(ArrayList<YearVo>)years);
        modelAndView.getModel().put("codes",(ArrayList<CodeVo>)codes);
        modelAndView.getModel().put("year",-1);
        modelAndView.getModel().put("code",-1);
        modelAndView.setViewName("table-management");
        return modelAndView;
    }

    @RequestMapping(value = "/{year}/{code}/table-management", method = RequestMethod.GET)
    public ModelAndView table_management_detail(
            @PathVariable("year") int year,
            @PathVariable("code") int code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 1){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        modelAndView.getModel().put("usercode",user_id);
        ArrayList<TableShortVo>reList = youngService.get_table_by_code_a_year_for_pro(code,year,user_id);
        ArrayList<CodeVo>codes = youngService.get_codes_for_pro(user_id);
        ArrayList<YearVo>years = youngService.get_years();
        modelAndView.getModel().put("list",(ArrayList<TableShortVo>)reList);
        modelAndView.getModel().put("years",(ArrayList<YearVo>)years);
        modelAndView.getModel().put("codes",(ArrayList<CodeVo>)codes);
        modelAndView.getModel().put("year",year);
        modelAndView.getModel().put("code",code);
        modelAndView.setViewName("table-management");
        return modelAndView;
    }


    @RequestMapping(value = "/{id}/table-detail/1", method = RequestMethod.GET)
    public ModelAndView table_detail(
            @PathVariable("id") long id
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        Map<String, Object>remap= youngService.get_table_by_id(id);
        modelAndView.getModel().put("main_table",(MainTable)remap.get("main"));
        modelAndView.getModel().put("per_tables",(ArrayList<PerTable>)remap.get("perList"));
        modelAndView.setViewName("table-detail");
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/table-detail/2", method = RequestMethod.GET)
    public ModelAndView table_detail2(
            @PathVariable("id") long id
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        Map<String, Object>remap= youngService.get_table_by_id(id);
        modelAndView.getModel().put("main_table",(MainTable)remap.get("main"));
        modelAndView.getModel().put("per_tables",(ArrayList<PerTable>)remap.get("perList"));
        if (((MainTable) remap.get("main")).getState()==2){
            modelAndView.setViewName("table-detail");
        }else{
            modelAndView.setViewName("tables-datail-c");
        }
        return modelAndView;
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView verifycode(
            @RequestParam(value = "login_num", defaultValue = "123") long login_num,
            @RequestParam(value ="pw", defaultValue = "!!!") String pw
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        queryJson = new BaseJson();
        long reCode = youngService.check_login(login_num , pw);
        if (reCode<0){
            modelAndView.getModel().put("msg","登陆失败，请检查用户名密码");
            modelAndView.getModel().put("url","/young/login");
            modelAndView.setViewName("return");
        }else{
            int type = youngService.get_user_type(reCode);
            if(type==1){
                //普通用户
                setUserSession(reCode);
                setUserTypeSession(type);
                modelAndView.setViewName("redirect:/young/table");
            }else if(type==2){
                //管理员
                setUserSession(reCode);
                setUserTypeSession(type);
                modelAndView.setViewName("redirect:/young/company-management");
            }else if(type==3){
                //汉办
                setUserSession(reCode);
                setUserTypeSession(type);
                modelAndView.setViewName("redirect:/young/top-company-management");
            }else{
                modelAndView.getModel().put("msg","登陆失败，请检查用户名密码");
                modelAndView.getModel().put("url","/young/login");
                modelAndView.setViewName("return");
            }
        }
        return modelAndView;
    }


    @RequestMapping(value = "/excel_upload", method = RequestMethod.POST)
    public ModelAndView excel_upload(
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        queryJson = new BaseJson();

        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 1){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }

        List<Map<String , Object>> reList = null ;
        if (file.getOriginalFilename().endsWith(".xlsx")){
            reList=ExcelUtil.deal_excel_xlsx(file.getInputStream());
        }else if (file.getOriginalFilename().endsWith(".xls")){
            reList=ExcelUtil.deal_excel_xls(file.getInputStream());
        }else{
            modelAndView.getModel().put("msg","文件格式不正确，请检查后上传");
            modelAndView.getModel().put("url","/young/company-management");
            modelAndView.setViewName("return");
            return modelAndView;
        }
        int readNum = reList.size();
        int successNum;
        if(user_type==2){
            successNum = youngService.total_register(reList,user_id);
            modelAndView.getModel().put("msg","读取公司"+readNum+"家 ， 成功注册"+successNum+"家");
            modelAndView.getModel().put("url","/young/company-management");
            modelAndView.setViewName("return");
        }else {
            successNum = youngService.total_register_top(reList,user_id);
            modelAndView.getModel().put("msg","读取公司"+readNum+"家 ， 成功注册"+successNum+"家");
            modelAndView.getModel().put("url","/young/top-company-management");
            modelAndView.setViewName("return");
        }


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
            modelAndView.getModel().put("url","/young/psd");
            modelAndView.setViewName("return");
            return modelAndView;
        }
        long user_id=getUserSession();f
        int flag = youngService.change_pw(old_pw,new_pw,user_id);
        if (flag==1){
            modelAndView.getModel().put("msg","修改密码成功");
            modelAndView.getModel().put("url","/young/login");
        }else if (flag==-2){
            modelAndView.getModel().put("msg","旧密码不正确");
            modelAndView.getModel().put("url","/young/psd");
        }
        else if (flag==-3){
            modelAndView.getModel().put("msg","新旧密码不能相同");
            modelAndView.getModel().put("url","/young/psd");
        }else{
            modelAndView.getModel().put("msg","修改密码失败，请联系管理员修改密码");
            modelAndView.getModel().put("url","/young/psd");
        }
        modelAndView.setViewName("return");
        return modelAndView;
    }

    @RequestMapping(value = "/{code}/recode", method = RequestMethod.GET)
    public ModelAndView login(
            @PathVariable("code") long code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 1){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        modelAndView.getModel().put("usercode",user_id);
        int flag = youngService.reset_pw(code);
        if(flag==1){
            modelAndView.getModel().put("msg","重置密码成功");
            modelAndView.getModel().put("url","/young/company-management");
        }
        else{
            modelAndView.getModel().put("msg","重置密码失败，请稍后再试");
            modelAndView.getModel().put("url","/young/company-management");
        }
        modelAndView.setViewName("return");
        return modelAndView;
    }


    @RequestMapping(value = "/up_table_temp", method = RequestMethod.POST)
    public ModelAndView up_table(
            @RequestParam("per_table") String [] per_tables,
            @RequestParam(value ="province",defaultValue = "") String province,
            @RequestParam(value = "school",defaultValue = "") String school,
            @RequestParam(value ="name",defaultValue = "") String name,
            @RequestParam(value ="phone",defaultValue = "") String phone,
            @RequestParam(value ="fax",defaultValue = "") String fax,
            @RequestParam(value ="type",defaultValue = "1") int type,
            @RequestParam(value ="year",defaultValue = "1970") int year
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        province=new String(province.getBytes("iso8859-1"),"utf-8");
        school=new String(school.getBytes("iso8859-1"),"utf-8");
        name=new String(name.getBytes("iso8859-1"),"utf-8");
        phone=new String(phone.getBytes("iso8859-1"),"utf-8");
        fax=new String(fax.getBytes("iso8859-1"),"utf-8");
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 2){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        MainTable mt = new MainTable();
        mt.settPhone(phone);
        mt.settName(name);
        mt.settFax(fax);
        mt.settSchool(school);
        mt.settProvince(province);
        mt.settId(0);
        mt.setYear(year);
        int flag;
        if (type==1){
            flag = youngService.table_up_temp(mt,per_tables,user_id);
        }else{
            flag = youngService.table_up(mt,per_tables,user_id);
        }
        if(flag==1){
            modelAndView.getModel().put("msg","录入表格成功");
        }else if(flag==-1){
            modelAndView.getModel().put("msg","此表格已经提交 ， 无法修改");
        }else {
            modelAndView.getModel().put("msg","录入表格失败");
        }
        modelAndView.getModel().put("url","/young/history-table");
        modelAndView.setViewName("return");
        return modelAndView;
    }



    @RequestMapping(value = "/up_table", method = RequestMethod.POST)
    public ModelAndView up_table2(
            @RequestParam("per_table") String [] per_tables,
            @RequestParam(value ="province",defaultValue = "") String province,
            @RequestParam(value = "school",defaultValue = "") String school,
            @RequestParam(value ="name",defaultValue = "") String name,
            @RequestParam(value ="phone",defaultValue = "") String phone,
            @RequestParam(value ="fax",defaultValue = "") String fax,
            @RequestParam(value ="type",defaultValue = "1") int type,
            @RequestParam(value ="year",defaultValue = "1970") int year
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        province=new String(province.getBytes("iso8859-1"),"utf-8");
        school=new String(school.getBytes("iso8859-1"),"utf-8");
        name=new String(name.getBytes("iso8859-1"),"utf-8");
        phone=new String(phone.getBytes("iso8859-1"),"utf-8");
        fax=new String(fax.getBytes("iso8859-1"),"utf-8");
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 2){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        MainTable mt = new MainTable();
        mt.settPhone(phone);
        mt.settName(name);
        mt.settFax(fax);
        mt.settSchool(school);
        mt.settProvince(province);
        mt.settId(0);
        mt.setYear(year);
        int flag;
        if (type==1){
            flag = youngService.table_up_temp2(mt,per_tables,user_id);
            if(flag==1){
                modelAndView.getModel().put("msg","录入暂存成功");
            }else if(flag==-1){
                modelAndView.getModel().put("msg","这一年的表格已上传过，请在历史表格中查看");
            }
        }else{
            flag = youngService.table_up2(mt,per_tables,user_id);
            if(flag==1){
                modelAndView.getModel().put("msg","录入提交成功");
            }else if(flag==-1){
                modelAndView.getModel().put("msg","这一年的表格已上传过，请在历史表格中查看");
            }
        }
        setSessionAttribute("usercode",user_id);
        setSessionAttribute("year",year);
        setSessionAttribute("main_table",mt);
        setSessionAttribute("per_tables",youngService.deal_Per_String(per_tables));
        modelAndView.getModel().put("url","/young/table");
        modelAndView.setViewName("return");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/downloadFile/1/{code}", method = RequestMethod.GET)
    private void  downloadFile(@PathVariable("code") long code){
        try {
            Map<String, Object>remap= youngService.get_table_by_id(code);
            Table table = new Table();
            table.setMainTable((MainTable)remap.get("main"));
            table.setPerTables((ArrayList<PerTable>)remap.get("perList"));
            //文件所在目录路径
            String filePath = getHttpRequest().getSession().getServletContext().getRealPath(File.separator)+File.separator+
                    "web"+File.separator+"file"+File.separator;
            String fileName = code+".xls";
            String path =  FileUtil.creat_file(table,filePath,fileName);
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

    @ResponseBody
    @RequestMapping(value = "/downloadFile/2/{year}/{code}", method = RequestMethod.GET)
    private void  downloadFile2(
            @PathVariable("year") int year,
            @PathVariable("code") long code
    ){
        try {
            Map<String, Object>remap= youngService.init_table_statistics_top(year,code);
            remap.put("names",youngService.u_names((ArrayList<MainTable>)remap.get("perTables")));
            //文件所在目录路径
            String filePath = getHttpRequest().getSession().getServletContext().getRealPath(File.separator)+File.separator+
                    "web"+File.separator+"file"+File.separator;
            String fileName;
            if (code<0){
                fileName = year+"年汇总表.xls";
            }else{
                fileName = year+"年"+code+"省厅汇总表.xls";
            }

            String path =  FileUtil.creat_file_static(remap,filePath,fileName);
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

    @ResponseBody
    @RequestMapping(value = "/downloadFile/3/{year}", method = RequestMethod.GET)
    private void  downloadFile3(
            @PathVariable("year") int year
    ){
                long user_id = getUserSession();
        try {
            Map<String, Object>remap= youngService.init_table_statistics_pro(year,user_id);
            remap.put("names",youngService.u_names((ArrayList<MainTable>)remap.get("perTables")));
            //文件所在目录路径
            String filePath = getHttpRequest().getSession().getServletContext().getRealPath(File.separator)+File.separator+
                    "web"+File.separator+"file"+File.separator;
            String fileName = year+"年"+user_id+"省厅汇总表.xls";
            String path =  FileUtil.creat_file_static(remap,filePath,fileName);
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

    @RequestMapping(value = "/top-table-management", method = RequestMethod.GET)
    public ModelAndView table_management_top(
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type != 3){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        modelAndView.getModel().put("usercode",user_id);
        ArrayList<TableShortVo>reList = youngService.get_table_by_code_a_year_for_top(-1,-1);
        ArrayList<CodeVo>codes = youngService.get_codes_for_top();
        ArrayList<YearVo>years = youngService.get_years();
        modelAndView.getModel().put("list",(ArrayList<TableShortVo>)reList);
        modelAndView.getModel().put("years",(ArrayList<YearVo>)years);
        modelAndView.getModel().put("codes",(ArrayList<CodeVo>)codes);
        modelAndView.getModel().put("year",-1);
        modelAndView.getModel().put("code",-1);
        modelAndView.setViewName("top/table-management");
        return modelAndView;
    }
    @RequestMapping(value = "/{year}/{code}/top-table-management", method = RequestMethod.GET)
    public ModelAndView table_management_detail_top(
            @PathVariable("year") int year,
            @PathVariable("code") int code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 1){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        modelAndView.getModel().put("usercode",user_id);
        ArrayList<TableShortVo>reList = youngService.get_table_by_code_a_year_for_top(code,year);
        ArrayList<CodeVo>codes = youngService.get_codes_for_top();
        ArrayList<YearVo>years = youngService.get_years();
        modelAndView.getModel().put("list",(ArrayList<TableShortVo>)reList);
        modelAndView.getModel().put("years",(ArrayList<YearVo>)years);
        modelAndView.getModel().put("codes",(ArrayList<CodeVo>)codes);
        modelAndView.getModel().put("year",year);
        modelAndView.getModel().put("code",code);
        modelAndView.setViewName("top/table-management");
        return modelAndView;
    }

    @RequestMapping(value = "/top-company-management", method = RequestMethod.GET)
    public ModelAndView company_management_top() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type != 3){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        List<UserVo>reList=youngService.get_userList_pro(user_id);
        modelAndView.getModel().put("users",reList);
        modelAndView.getModel().put("usercode",user_id);
        modelAndView.setViewName("top/company-management");
        return modelAndView;
    }

    @RequestMapping(value = "/top-psd-for-manager", method = RequestMethod.GET)
    public ModelAndView psd_for_top(
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type != 3){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        modelAndView.getModel().put("usercode",user_id);
        modelAndView.setViewName("top/psd-for-manager");
        return modelAndView;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/top-statistics", method = RequestMethod.GET)
    public ModelAndView statistics_top(
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 1){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        ArrayList<YearVo>lists = youngService.get_years();
        modelAndView.getModel().put("usercode",user_id);
        modelAndView.getModel().put("perTables",null);
        modelAndView.getModel().put("totalTable",null);
        modelAndView.getModel().put("years",lists);

        modelAndView.getModel().put("year",-1);
        modelAndView.getModel().put("codes",youngService.get_codes_for_top());
        modelAndView.getModel().put("code",-1);
        modelAndView.setViewName("top/statistics");
        return modelAndView;
    }


    @RequestMapping(value = "/{year}/{code}/top-statistics", method = RequestMethod.GET)
    public ModelAndView statistics_year_top(
            @PathVariable("year") int year,
            @PathVariable("code") int code
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=getUserSession();
        int user_type=getUserTypeSession();
        if(user_type == 1){
            modelAndView.setViewName("redirect:/young/login");
            return modelAndView;
        }
        modelAndView.getModel().put("usercode",user_id);
        Map<String , Object>reMap = youngService.init_table_statistics_top(year,code);
        modelAndView.getModel().put("perTables",(ArrayList<MainTableVo>)reMap.get("perTables"));
        modelAndView.getModel().put("totalTable",(MainTable)reMap.get("totalTable"));
        modelAndView.getModel().put("years",youngService.get_years());
        modelAndView.getModel().put("year",year);
        modelAndView.getModel().put("codes",youngService.get_codes_for_top());
        modelAndView.getModel().put("code",code);
        modelAndView.setViewName("top/statistics");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/downloadFile/normal", method = RequestMethod.GET)
    private void  downloadFile(){
        try {

            //文件所在目录路径
            String filePath = getHttpRequest().getSession().getServletContext().getRealPath(File.separator)+File.separator+
                    "web"+File.separator+"file"+File.separator+"normal"+File.separator+"范例.xlsx";
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            //设置Http响应头告诉浏览器下载这个附件
            getHttpResponse().setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode("范例.xlsx", "UTF-8"));
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
*/
