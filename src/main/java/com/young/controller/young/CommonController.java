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

@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController {

    @Resource
    private CommServiceImpl commService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/comm/login");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login_post(
            @RequestParam(value = "login_num", defaultValue = "123") String login_num,
            @RequestParam(value ="pw", defaultValue = "!!!") String pw
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        BaseJson queryJson = new BaseJson();
        User user = commService.check_login(login_num , pw);
        if (user==null){
            modelAndView.getModel().put("msg","登陆失败，请检查用户名密码");
            modelAndView.getModel().put("url","/common/login");
            modelAndView.setViewName("return");
        }else{
            setUserSession(user);
            if(user.getuType()==1){
                //教师
                modelAndView.setViewName("redirect:/teacher/table");
            }else if(user.getuType()==2){
                //单位
                modelAndView.setViewName("redirect:/q/management");
            }else if(user.getuType()==3){
                //省厅
                modelAndView.setViewName("redirect:/w/management");
            }else if(user.getuType()==4){
                //汉办
                modelAndView.setViewName("redirect:/r/management");
            }else if(user.getuType()==5){
                //部署院校
                modelAndView.setViewName("redirect:/t/management");
            }else if(user.getuType()==6){
                //部署院校下属教师
                modelAndView.setViewName("redirect:/y/table");
            }else if(user.getuType()==7){
                //无单位教师
                modelAndView.setViewName("redirect:/u/table");
            }else{
                modelAndView.getModel().put("msg","登陆失败，请检查用户名密码");
                modelAndView.getModel().put("url","/common/login");
                modelAndView.setViewName("return");
            }
        }
        return modelAndView;
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        setUserSession(null);
        modelAndView.setViewName("redirect:/common/login");
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

    @RequestMapping(value = "/registerSc", method = RequestMethod.POST)
    public ModelAndView registerSc(
            @RequestParam(value = "u1ScName")  String u1ScName,
            @RequestParam(value = "u1UName")  String u1UName,
            @RequestParam(value = "u1Phone")  String u1Phone,
            @RequestParam(value = "u1Email")  String u1Email,
            @RequestParam(value = "u1Address")  String u1Address
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User1 user1 = new User1();
        user1.setU1ScName(u1ScName);
        user1.setU1Address(u1Address);
        user1.setU1UName(u1UName);
        user1.setU1Phone(u1Phone);
        user1.setU1Email(u1Email);
        int  re_code = commService.register(user1);
        if (re_code==1){
            modelAndView.getModel().put("msg","申请已提交，请保持联系方式畅通");
            modelAndView.getModel().put("url","/common/login");
            modelAndView.setViewName("return");
        }else if(re_code==-1){
            modelAndView.getModel().put("msg","此手机号已被注册");
            modelAndView.getModel().put("url","/common/login");
            modelAndView.setViewName("return");
        }else{
            modelAndView.getModel().put("msg","系统错误");
            modelAndView.getModel().put("url","/common/login");
            modelAndView.setViewName("return");
           /* setUserSession(user);
            if(user.getuType()==1){
                //教师
                modelAndView.setViewName("redirect:/teacher/table");
            }else if(user.getuType()==2){
                //单位
                modelAndView.setViewName("redirect:/q/management");
            }else if(user.getuType()==3){
                //省厅
                modelAndView.setViewName("redirect:/w/management");
            }else if(user.getuType()==4){
                //汉办
                modelAndView.setViewName("redirect:/r/management");
            }else{
                modelAndView.getModel().put("msg","登陆失败，请检查用户名密码");
                modelAndView.getModel().put("url","/common/login");
                modelAndView.setViewName("return");
            }*/
        }
        return modelAndView;
    }



    @RequestMapping(value = "/registerTe", method = RequestMethod.POST)
    public ModelAndView registerTe(
            @RequestParam(value = "u2IdCode")  String u2IdCode,
            @RequestParam(value = "u2Name")  String u2Name,
            @RequestParam(value = "u2Phone")  String u2Phone,
            @RequestParam(value = "u2Mail")  String u2Mail,
            @RequestParam(value = "u2Address")  String u2Address
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User2 user2 = new User2();
        user2.setU2IdCode(u2IdCode);
        user2.setU2Name(u2Name);
        user2.setU2Phone(u2Phone);
        user2.setU2Mail(u2Mail);
        user2.setU2Address(u2Address);
        int  re_code = commService.register2(user2);
        if (re_code==1){
            modelAndView.getModel().put("msg","申请已提交，请保持联系方式畅通");
            modelAndView.getModel().put("url","/common/login");
            modelAndView.setViewName("return");
        }else if(re_code==-1){
            modelAndView.getModel().put("msg","此手机号已被注册");
            modelAndView.getModel().put("url","/common/login");
            modelAndView.setViewName("return");
        }else{
            modelAndView.getModel().put("msg","系统错误");
            modelAndView.getModel().put("url","/common/login");
            modelAndView.setViewName("return");
           /* setUserSession(user);
            if(user.getuType()==1){
                //教师
                modelAndView.setViewName("redirect:/teacher/table");
            }else if(user.getuType()==2){
                //单位
                modelAndView.setViewName("redirect:/q/management");
            }else if(user.getuType()==3){
                //省厅
                modelAndView.setViewName("redirect:/w/management");
            }else if(user.getuType()==4){
                //汉办
                modelAndView.setViewName("redirect:/r/management");
            }else{
                modelAndView.getModel().put("msg","登陆失败，请检查用户名密码");
                modelAndView.getModel().put("url","/common/login");
                modelAndView.setViewName("return");
            }*/
        }
        return modelAndView;
    }

    @RequestMapping(value = "/sc_register", method = RequestMethod.GET)
    public ModelAndView sc_register() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/comm/register1");
        return modelAndView;
    }

    @RequestMapping(value = "/te_register", method = RequestMethod.GET)
    public ModelAndView te_register() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/comm/register2");
        return modelAndView;
    }

}
