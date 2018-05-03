package com.young.services.le_r.impl;

import com.young.entity.*;
import com.young.services.common.impl.CommServiceImpl;
import com.young.services.le_r.ILerService;
import com.young.vo.TableExtendVo;
import com.young.vo.YearVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "lerService")
public class LerServiceImpl extends CommServiceImpl implements ILerService {
    public int get_table_num(){
        int count = baseDAO.countBySQL("select * from table_copy where t_if_q_ok = 2 and t_if_e_ok = 2 and t_if_temp = 0",new Object[]{});
        return count;
    }

    @Override
    public ArrayList<YearVo> get_years() {
        try {
            ArrayList<YearVo> reList =(ArrayList<YearVo>)baseDAO.findBySQLForVO(
                    "select distinct t_user_input_year as year from table_copy order by year desc",
                    YearVo.class,
                    new Object[]{}
            );
            ArrayList<YearVo> yearVos = new ArrayList<>();
            for (YearVo yv:reList
                    ) {
                if (yv.getYear().length()==4){
                    yearVos.add(yv);
                }
            }
            return yearVos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<User> get_users_by_type(int type) {
        Map map = new HashMap();
        map.put("uType",type);
        ArrayList<User> users = (ArrayList<User>)baseDAO.findByProperties(map,User.class);
        return users;
    }

    public ArrayList<User> get_teachers(User user){
        Map map = new HashMap();
        map.put("uCodeQ",user.getuCodeQ());
        map.put("uType",1);
        ArrayList<User>relist  = (ArrayList<User>)baseDAO.findByProperties(map,User.class);
        return relist;
    }

    public ArrayList<User> get_teachers_by_type(int type){
        Map map = new HashMap();
        map.put("uType",type);
        ArrayList<User>relist = new ArrayList<>();
        ArrayList<User>users  = (ArrayList<User>)baseDAO.findByProperties(map,User.class);
        for (User u :users
             ) {
            map = new HashMap();
            map.put("uCodeQ",u.getuV2Phone());
            relist.addAll((ArrayList<User>)baseDAO.findByProperties(map,User.class));
        }
        return relist;
    }

    @Override
    public ArrayList<TableExtendVo> get_tables(String year, long u_id, User user,int type) {
        ArrayList<TableExtendVo> tableCopies = new ArrayList<>();
        User user1 = baseDAO.findById(u_id,User.class);

        if (year.equals("-1")&&u_id==-1){
            ArrayList<User> users = get_teachers_by_type(type);
            for (User u:users
                    ) {
                Map map = new HashMap();
                map.put("uId", u.getuId());
                map.put("tIfTemp",0);
                map.put("tIfQOk",2);
                ArrayList<TableCopy> result = (ArrayList<TableCopy>) baseDAO.findByProperties(map, TableCopy.class);
                for (TableCopy rc:result
                        ) {
                    TableExtendVo tableExtendVo = new TableExtendVo();
                    tableExtendVo.setTableCopy(rc);
                    tableExtendVo.setUser(u);
                    tableCopies.add(tableExtendVo);
                }
            }
        }else if(u_id==-1){
            ArrayList<User> users = get_teachers_by_type(type);
            for (User u:users
                    ) {
                Map map = new HashMap();
                map.put("uId", u.getuId());
                map.put("tUserInputYear",year);
                map.put("tIfTemp",0);
                map.put("tIfQOk",2);
                ArrayList<TableCopy> result = (ArrayList<TableCopy>) baseDAO.findByProperties(map, TableCopy.class);
                for (TableCopy rc:result
                        ) {
                    TableExtendVo tableExtendVo = new TableExtendVo();
                    tableExtendVo.setTableCopy(rc);
                    tableExtendVo.setUser(u);
                    tableCopies.add(tableExtendVo);
                }
            }

        }else if(year.equals("-1")){
            ArrayList<User> users = get_teachers(user1);
            for (User u:users
                    ) {
                Map map = new HashMap();
                map.put("uId", u.getuId());
                map.put("tIfTemp",0);
                map.put("tIfQOk",2);
                ArrayList<TableCopy> result = (ArrayList<TableCopy>) baseDAO.findByProperties(map, TableCopy.class);
                for (TableCopy rc:result
                        ) {
                    TableExtendVo tableExtendVo = new TableExtendVo();
                    tableExtendVo.setTableCopy(rc);
                    tableExtendVo.setUser(u);
                    tableCopies.add(tableExtendVo);
                }
            }
        }
        else{
            ArrayList<User> users = get_teachers(user1);
            for (User u:users
                    ) {
                Map map = new HashMap();
                map.put("uId", u.getuId());
                map.put("tIfQOk",2);
                map.put("tIfTemp",0);
                map.put("tUserInputYear",year);
                ArrayList<TableCopy> result = (ArrayList<TableCopy>) baseDAO.findByProperties(map, TableCopy.class);
                for (TableCopy rc:result
                        ) {
                    TableExtendVo tableExtendVo = new TableExtendVo();
                    tableExtendVo.setTableCopy(rc);
                    tableExtendVo.setUser(u);
                    tableCopies.add(tableExtendVo);
                }
            }
        }
        return tableCopies;
    }

    @Override
    public int total_register(List<Map<String, Object>> list ,int type) {
        int num=0;
        try {
            for (Map<String,Object> map:list
                    ) {
                User user = new User();
                user.setuName(map.get("0").toString());
                user.setuCodeQ(map.get("1").toString());
                user.setuV2Phone(map.get("1").toString());
                user.setuPw(map.get("2").toString());
                user.setuType(type);

                Map map2 = new HashMap();

                map2.put("uCodeQ",user.getuCodeQ());
                //todo:
                ArrayList<User> users = (ArrayList<User>)baseDAO.findByProperties(map2,User.class);
                if (users.size()>0)continue;
                baseDAO.save(user);
                baseDAO.clear();
                num++;
            }
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return num;
        }
    }

    @Override
    public int cancel(long code , String form_cancel_text) {
        try {
            TableCopy tableCopy = baseDAO.findById(code , TableCopy.class);
            tableCopy.settIfEOk(3);
            tableCopy.settENoReason(form_cancel_text);
            tableCopy.settIfTemp(1);
            baseDAO.update(tableCopy);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int up(long code, String form_cancel_text,String tNumber,String dateY,String dateM,String dateD) {
        try {
            TableCopy tableCopy = baseDAO.findById(code , TableCopy.class);
            tableCopy.settNumber(tNumber);
            tableCopy.settDateY(dateY);
            tableCopy.settDateM(dateM);
            tableCopy.settDateD(dateD);
            tableCopy.settIfEOk(2);
            tableCopy.settENoReason(form_cancel_text);
            tableCopy.settIfTemp(0);
            baseDAO.update(tableCopy);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ArrayList<TableCopy> get_table_by_year_user(int type ,String year, User user) {
        ArrayList<TableCopy> reList = new ArrayList<>();
        if (type==-1&&"-1".equals(year)){
            Map map = new HashMap();
            map.put("tIfQOk",2);
            map.put("tIfEOk",2);
            map.put("tIfTemp",0);
            ArrayList<TableCopy> result = (ArrayList<TableCopy>)baseDAO.findByProperties(map,TableCopy.class);
            reList.addAll(result);
        }else if(type==-1){
            Map map = new HashMap();
            map.put("tIfQOk",2);
            map.put("tIfEOk",2);
            map.put("tUserInputYear",year);
            map.put("tIfTemp",0);
            ArrayList<TableCopy> result = (ArrayList<TableCopy>)baseDAO.findByProperties(map,TableCopy.class);
            reList.addAll(result);
        }else if("-1".equals(year)) {
            ArrayList<User> users = get_teachers_by_type(type);
            for (User u:users
                    ) {
                Map map = new HashMap();
                map.put("uId", u.getuId());
                map.put("tIfTemp",0);
                map.put("tIfQOk",2);
                map.put("tIfEOk",2);
                ArrayList<TableCopy> result = (ArrayList<TableCopy>) baseDAO.findByProperties(map, TableCopy.class);
                reList.addAll(result);
            }
        }else{
            ArrayList<User> users = get_teachers_by_type(type);
            for (User u:users
                    ) {
                Map map = new HashMap();
                map.put("uId", u.getuId());
                map.put("tIfTemp",0);
                map.put("tIfQOk",2);
                map.put("tUserInputYear",year);
                map.put("tIfEOk",2);
                ArrayList<TableCopy> result = (ArrayList<TableCopy>) baseDAO.findByProperties(map, TableCopy.class);
                reList.addAll(result);
            }
        }
        return reList;
    }



}
