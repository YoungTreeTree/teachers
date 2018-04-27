package com.young.controller.young;

import com.young.controller.base.BaseController;
import com.young.entity.*;
import com.young.json.BaseJson;
import com.young.services.u.LeuServiceImpl;
import com.young.services.y.impl.LeyServiceImpl;
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
@RequestMapping(value = "/u")
public class LeuController extends BaseController {

    @Resource
    private LeuServiceImpl leuService;

    private BaseJson queryJson = new BaseJson();

    @RequestMapping(value = "/table", method = RequestMethod.GET)
    public ModelAndView table() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=7){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        modelAndView.getModel().put("user",user);
        TableCopyCopyCopy tableCopy = leuService.get_table_u_id(user.getuId());
        modelAndView.getModel().put("table",tableCopy);
        modelAndView.setViewName("leu/table");
        return modelAndView;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ModelAndView history() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=7){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        modelAndView.getModel().put("user",user);
        ArrayList<TableVo> tables = leuService.get_tables_u_id(user.getuId());
        modelAndView.getModel().put("tables",tables);
        modelAndView.setViewName("leu/history");
        return modelAndView;
    }


    @RequestMapping(value = "{t_id}/detail", method = RequestMethod.GET)
    public ModelAndView detail(
            @PathVariable("t_id") long t_id
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        if (user.getuType()!=7){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        modelAndView.getModel().put("user",user);
        TableCopyCopyCopy tableCopy = leuService.get_table_by_id3(t_id);
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
        modelAndView.setViewName("leu/table_detail");
        return modelAndView;
    }


    @RequestMapping(value = "/psd", method = RequestMethod.GET)
    public ModelAndView psd() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        User user = getUserSession();
        User2 user1 = leuService.getEntity(user.getuId()+"",User2.class);
        if (user.getuType()!=7){
            modelAndView.setViewName("/common/login");
            return modelAndView;
        }
        modelAndView.getModel().put("user",user);
        modelAndView.getModel().put("user1",user1);

        modelAndView.setViewName("leu/psd");
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
        if (user.getuType()!=7){
            modelAndView.setViewName("common/login");
            return modelAndView;
        }
        queryJson  = leuService.up_table(p,user.getuId(),if_temp, year);
        if(queryJson.getErrno().equals("1")){
            modelAndView.getModel().put("msg","录入表格成功");
        }else if(queryJson.getErrno().equals("2")){
            modelAndView.getModel().put("msg","此表格已经提交 ， 无法修改");
        }else {
            modelAndView.getModel().put("msg","录入表格失败");
        }
        modelAndView.getModel().put("url","/u/history");
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
            @RequestParam("mail") String mail,
            @RequestParam("address") String address
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if (!new_pw1.equals(new_pw)){
            modelAndView.getModel().put("msg","两次新密码必须相同");
            modelAndView.getModel().put("url","/u/psd");
            modelAndView.setViewName("return");
            return modelAndView;
        }
        User user =getUserSession();
        if (user.getuType()!=7){
            modelAndView.setViewName("common/login");
            return modelAndView;
        }
        int flag = leuService.change_info(old_pw,new_pw,user.getuId(),name,phone,mail,address);
        if (flag==1){
            modelAndView.getModel().put("msg","修改个人信息成功");
            modelAndView.getModel().put("url","/common/login");
        }else if (flag==-2){
            modelAndView.getModel().put("msg","旧密码不正确");
            modelAndView.getModel().put("url","/u/psd");
        }
        else if (flag==-3){
            modelAndView.getModel().put("msg","新旧密码不能相同");
            modelAndView.getModel().put("url","/u/psd");
        }else{
            modelAndView.getModel().put("msg","修改密码失败，请联系管理员修改密码");
            modelAndView.getModel().put("url","/u/psd");
        }
        modelAndView.setViewName("return");
        return modelAndView;
    }

}
