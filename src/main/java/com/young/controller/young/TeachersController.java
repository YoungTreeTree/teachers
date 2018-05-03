package com.young.controller.young;

import com.young.controller.base.BaseController;
import com.young.entity.TableCopy;
import com.young.entity.User;
import com.young.json.BaseJson;
import com.young.services.teacher.impl.TeacherServiceImpl;
import com.young.vo.TableVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/teacher")
public class TeachersController extends BaseController {
    @Resource
    private TeacherServiceImpl teacherService;

    private BaseJson queryJson = new BaseJson();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teachers/login");
        return modelAndView;
    }


    @RequestMapping(value = "/table", method = RequestMethod.GET)
    public ModelAndView table() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();

        if (user.getuType()!=1){
            modelAndView.setViewName("/teacher/login");
            return modelAndView;
        }
        modelAndView.getModel().put("user",user);
        TableCopy tableCopy = teacherService.get_table_u_id(user.getuId());
        modelAndView.getModel().put("table",tableCopy);
        modelAndView.setViewName("teachers/table");
        return modelAndView;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ModelAndView history() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=1){
            modelAndView.setViewName("/teacher/login");
            return modelAndView;
        }
        modelAndView.getModel().put("user",user);
        ArrayList<TableVo> tables = teacherService.get_tables_u_id(user.getuId());
        modelAndView.getModel().put("tables",tables);
        modelAndView.setViewName("teachers/history");
        return modelAndView;
    }


    @RequestMapping(value = "{t_id}/detail", method = RequestMethod.GET)
    public ModelAndView detail(
            @PathVariable("t_id") long t_id
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=1){
            modelAndView.setViewName("/teacher/login");
            return modelAndView;
        }
        modelAndView.getModel().put("user",user);
        TableCopy tableCopy = teacherService.get_table_by_id(t_id);
        String reason = "";
        if (tableCopy.gettIfQOk()==3){
            reason+="驳回原因 ："+tableCopy.gettQNoReason();
        } if (tableCopy.gettIfWOk()==3){
            reason+="驳回原因 ："+tableCopy.gettWNoReason();
        } if (tableCopy.gettIfEOk()==3){
            reason+="驳回原因 ："+tableCopy.gettENoReason();
        }
        modelAndView.getModel().put("table",tableCopy);
        modelAndView.getModel().put("reason",reason);
        modelAndView.setViewName("teachers/table_detail");
        return modelAndView;
    }


    @RequestMapping(value = "/psd", method = RequestMethod.GET)
    public ModelAndView psd() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=1){
            modelAndView.setViewName("/teacher/login");
            return modelAndView;
        }
        modelAndView.getModel().put("user",user);
        modelAndView.setViewName("teachers/psd");
        return modelAndView;
    }




    @RequestMapping(value = "/up_table", method = RequestMethod.POST)
    public ModelAndView up_table(
            @RequestParam(value ="p",defaultValue = "") String p[],
            @RequestParam(value ="year",defaultValue = "2018") String year,
            @RequestParam(value ="if_temp",defaultValue = "1") int if_temp
    ) {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=1){
            modelAndView.setViewName("/teacher/login");
            return modelAndView;
        }
        queryJson  = teacherService.up_table(p,user.getuId(),if_temp, year);
        if(queryJson.getErrno().equals("1")){
            modelAndView.getModel().put("msg","录入表格成功");
        }else if(queryJson.getErrno().equals("2")){
            modelAndView.getModel().put("msg","此表格已经提交 ， 无法修改");
        }else {
            modelAndView.getModel().put("msg","录入表格失败");
        }
        modelAndView.getModel().put("url","/teacher/history");
        modelAndView.setViewName("return");
        return modelAndView;
    }

    @RequestMapping(value = "/change_pw", method = RequestMethod.POST)
    public ModelAndView change_pw(
            @RequestParam("old_pw") String old_pw,
            @RequestParam("new_pw1") String new_pw1,
            @RequestParam("new_pw") String new_pw,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("mail") String mail
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if (!new_pw1.equals(new_pw)){
            modelAndView.getModel().put("msg","两次新密码必须相同");
            modelAndView.getModel().put("url","/teacher/psd");
            modelAndView.setViewName("return");
            return modelAndView;
        }
        User user =getUserSession();
        if (user.getuType()!=1){
            modelAndView.setViewName("/teacher/login");
            return modelAndView;
        }
        int flag = teacherService.change_pw2(old_pw,new_pw,user.getuId(),name,phone,mail);
        if (flag==1){
            modelAndView.getModel().put("msg","修改密码成功");
            modelAndView.getModel().put("url","/teacher/login");
        }else if (flag==-2){
            modelAndView.getModel().put("msg","旧密码不正确");
            modelAndView.getModel().put("url","/teacher/psd");
        }
        /*else if (flag==-3){
            modelAndView.getModel().put("msg","新旧密码不能相同");
            modelAndView.getModel().put("url","/teacher/psd");
        }*/else if (flag==-4){
            modelAndView.getModel().put("msg","手机号重复");
            modelAndView.getModel().put("url","/teacher/psd");
        }else{
            modelAndView.getModel().put("msg","修改密码失败，请联系管理员修改密码");
            modelAndView.getModel().put("url","/teacher/psd");
        }
        modelAndView.setViewName("return");
        return modelAndView;
    }

}
