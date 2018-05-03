package com.young.controller.young;


import com.young.controller.base.BaseController;
import com.young.entity.*;
import com.young.json.BaseJson;
import com.young.services.common.impl.CommServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController {

    @Resource
    private CommServiceImpl commService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("comm/login_teacher");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login_post(
            @RequestParam(value = "login_num", defaultValue = "123") String login_num,
            @RequestParam(value ="pw", defaultValue = "!!!") String pw,
            @RequestParam(value ="login_type", defaultValue = "!!!") int login_type
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        String from = "";
        if (login_type==1){
            from = "teacher";
        }else if(login_type==2){
            from = "b";
        }else if(login_type==5){
            from = "r";
        }
        BaseJson queryJson = new BaseJson();
        User user = commService.check_login(login_num , pw,login_type);
        if (user==null){
            modelAndView.getModel().put("msg","登陆失败，请检查用户名密码");
            modelAndView.getModel().put("url","/"+from+"/login");
            modelAndView.setViewName("return");
        }else{
            setUserSession(user);
            if(user.getuType()==1){
                //教师
                modelAndView.setViewName("redirect:/teacher/table");
            }else if(user.getuType()==2){
                //单位
                modelAndView.setViewName("redirect:/b/management");
            }else if(user.getuType()==3){
                //省厅
                modelAndView.setViewName("redirect:/c/management");
            }else if(user.getuType()==4){
                //汉办
                modelAndView.setViewName("redirect:/d/management");
            }else if(user.getuType()==5){
                //部署院校
                modelAndView.setViewName("redirect:/r/management");
            }else if(user.getuType()==6){
                //部署院校下属教师
                modelAndView.setViewName("redirect:/y/table");
            }else if(user.getuType()==7){
                //无单位教师
                modelAndView.setViewName("redirect:/u/table");
            }else{
                modelAndView.getModel().put("msg","登陆失败，请检查用户名密码");
                modelAndView.getModel().put("url","/teacher/login");
                modelAndView.setViewName("return");
            }
        }
        return modelAndView;
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        setUserSession(null);
        modelAndView.setViewName("redirect:/teacher/login");
        return modelAndView;
    }



    @RequestMapping(value = "{t_id}/statics", method = RequestMethod.GET)
    public ModelAndView statics(
            @PathVariable("t_id") long t_id
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=1;
        TableCopy tableCopy = commService.get_table_by_id(t_id);
        String reason = "";
        modelAndView.getModel().put("table",tableCopy);
        modelAndView.setViewName("comm/static");
        return modelAndView;
    }

    @RequestMapping(value = "{t_id}/statics3", method = RequestMethod.GET)
    public ModelAndView statics3(
            @PathVariable("t_id") long t_id
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=1;
        TableCopyCopyCopy tableCopy = commService.get_table_by_id5(t_id);
        String reason = "";
        modelAndView.getModel().put("table",tableCopy);
        modelAndView.setViewName("comm/static");
        return modelAndView;
    }

    @RequestMapping(value = "{t_id}/statics2", method = RequestMethod.GET)
    public ModelAndView statics2(
            @PathVariable("t_id") long t_id
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        long user_id=1;
        TableCopyCopy tableCopy = commService.get_table_by_id2(t_id);
        String reason = "";
        modelAndView.getModel().put("table",tableCopy);
        modelAndView.setViewName("comm/static");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/downloadFile/normal", method = RequestMethod.GET)
    private void  downloadFile(){
        try {
            //文件所在目录路径
            String filePath = getHttpRequest().getSession().getServletContext().getRealPath(File.separator)+File.separator+
                    "web"+ File.separator+"file"+File.separator+"normal"+File.separator+"范例.xlsx";
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

    @ResponseBody
    @RequestMapping(value = "/downloadFile/normal2", method = RequestMethod.GET)
    private void  downloadFile2(){
        try {
            //文件所在目录路径
            String filePath = getHttpRequest().getSession().getServletContext().getRealPath(File.separator)+File.separator+
                    "web"+ File.separator+"file"+File.separator+"normal"+File.separator+"范例2.xlsx";
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
    @RequestMapping(value = "/{type}/register", method = RequestMethod.POST)
    public ModelAndView registerSc(
            @PathVariable(value = "type")  int type,
            @RequestParam(value = "u1UName")  String u1UName,
            @RequestParam(value = "u1Phone")  String u1Phone,
            @RequestParam(value = "u1Email")  String u1Email,
            @RequestParam(value = "idcode",defaultValue = "-")  String idcode,
            @RequestParam(value = "q")  String q
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        if (!"-".equals(idcode)){
            user.setuCodeW(idcode);
        }
        user.setuV2Phone(u1Phone);
        user.setuName(u1UName);
        user.setuV2Name(u1UName);
        user.setuV2Email(u1Email);
        int  re_code = commService.register(user,type,q);
        if (re_code==1){
            modelAndView.getModel().put("msg","注册成功,使用手机号登录,初始密码123456");
            modelAndView.getModel().put("url","/teacher/login");
            modelAndView.setViewName("return");
        }else if(re_code==-1){
            modelAndView.getModel().put("msg","此手机号已被注册");
            modelAndView.getModel().put("url","/teacher/login");
            modelAndView.setViewName("return");
        }else{
            modelAndView.getModel().put("msg","系统错误");
            modelAndView.getModel().put("url","/teacher/login");
            modelAndView.setViewName("return");
        }
        return modelAndView;
    }



    @RequestMapping(value = "/register1", method = RequestMethod.GET)
    public ModelAndView register1() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        ArrayList<User>users = commService.get_users_by_type(2);
        modelAndView.getModel().put("users",users);
        modelAndView.setViewName("/comm/register1");
        return modelAndView;
    }

    @RequestMapping(value = "/register2", method = RequestMethod.GET)
    public ModelAndView register2() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        ArrayList<User>users = commService.get_users_by_type(3);
        modelAndView.getModel().put("users",users);
        modelAndView.setViewName("/comm/register2");
        return modelAndView;
    }

    @RequestMapping(value = "/register3", method = RequestMethod.GET)
    public ModelAndView register3() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/comm/register3");
        return modelAndView;
    }

}
