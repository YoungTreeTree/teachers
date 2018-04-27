package com.young.services.le_w.impl;

import com.young.entity.TableCopy;
import com.young.entity.User;
import com.young.services.common.impl.CommServiceImpl;
import com.young.services.le_w.ILewService;
import com.young.vo.TableExtendVo;
import com.young.vo.YearVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "lewService")
public class LewServiceImpl extends CommServiceImpl implements ILewService {
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
    public ArrayList<User> get_users_by_pre_code(String precode1) {
        Map map = new HashMap();
        map.put("uCodeE",precode1);
        map.put("uCodeQ","999");
        ArrayList<User> users = (ArrayList<User>)baseDAO.findByProperties(map,User.class);
        int to_delet = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getuCodeQ().equals("999")
                    &&users.get(i).getuCodeW().equals("999"))to_delet = i;
        }
        if (to_delet!=-1){
            users.remove(to_delet);
        }
        return users;
    }

    public ArrayList<User> get_teachers(User user){
        Map map = new HashMap();
        map.put("uCodeE",user.getuCodeE());
        map.put("uCodeW",user.getuCodeW());
        map.put("uType",1);
        ArrayList<User>relist  = (ArrayList<User>)baseDAO.findByProperties(map,User.class);
        return relist;
    }

    @Override
    public ArrayList<TableExtendVo> get_tables(String year, long u_id, User user) {
        ArrayList<TableExtendVo> tableCopies = new ArrayList<>();


        if (year.equals("-1")&&u_id==-1){
            ArrayList<User> danwei = get_users_by_pre_code(user.getuCodeE());
            ArrayList<User>users = new ArrayList<>();
            for (User u:danwei
                 ) {
                ArrayList<User> temp = get_teachers(u);
                users.addAll(temp);
            }
            for (User u:users
                    ) {
                Map map = new HashMap();
                map.put("uId",u.getuId());
                map.put("tIfTemp",0);
                map.put("tIfQOk",2);
                ArrayList<TableCopy> result = (ArrayList<TableCopy>)baseDAO.findByProperties(map,TableCopy.class);
                for (TableCopy rc:result
                        ) {
                    TableExtendVo tableExtendVo = new TableExtendVo();
                    tableExtendVo.setTableCopy(rc);
                    tableExtendVo.setUser(u);
                    tableCopies.add(tableExtendVo);
                }
            }
        }else if(u_id==-1){
            ArrayList<User> danwei = get_users_by_pre_code(user.getuCodeE());
            ArrayList<User>users = new ArrayList<>();
            for (User u:danwei
                    ) {
                ArrayList<User> temp = get_teachers(u);
                users.addAll(temp);
            }
            for (User u:users
                    ) {
                Map map = new HashMap();
                map.put("uId",u.getuId());
                map.put("tIfQOk",2);
                map.put("tIfTemp",0);
                map.put("tUserInputYear",year);
                ArrayList<TableCopy> result = (ArrayList<TableCopy>)baseDAO.findByProperties(map,TableCopy.class);
                for (TableCopy rc:result
                        ) {
                    TableExtendVo tableExtendVo = new TableExtendVo();
                    tableExtendVo.setTableCopy(rc);
                    tableExtendVo.setUser(u);
                    tableCopies.add(tableExtendVo);
                }
            }
        }else if(year.equals("-1")){
            User user1 = baseDAO.findById(u_id,User.class);
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
            User user1 = baseDAO.findById(u_id,User.class);
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
    public int total_register(List<Map<String, Object>> list , String e2) {
        int num=0;
        try {
            for (Map<String,Object> map:list
                    ) {
                User user = new User();
                user.setuName(map.get("0").toString());
                long code = check_code(map.get("1").toString());
                if (code>0)user.setuCodeW(code+"");
                else continue;
                user.setuPw( map.get("2").toString());
                user.setuType(2);
                user.setuCodeQ("999");
                user.setuCodeE(e2);
                Map map2 = new HashMap();
                map2.put("uCodeQ",user.getuCodeQ());
                map2.put("uCodeW",user.getuCodeW());
                map2.put("uCodeE",user.getuCodeE());
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
    private long check_code(String code2){
        if(code2==null||code2.length()!=3)return -1;
        try {
            long code2_long = Long.parseLong(code2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -2;
        }
        return Long.parseLong(code2);
    }


    @Override
    public int cancel(long code , String form_cancel_text) {
        try {
            TableCopy tableCopy = baseDAO.findById(code , TableCopy.class);
            tableCopy.settIfWOk(3);
            tableCopy.settWNoReason(form_cancel_text);
            tableCopy.settIfTemp(1);
            baseDAO.update(tableCopy);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int up(long code, String form_cancel_text) {
        try {
            TableCopy tableCopy = baseDAO.findById(code , TableCopy.class);
            tableCopy.settIfWOk(2);
            tableCopy.settWNoReason(form_cancel_text);
            tableCopy.settIfTemp(0);
            baseDAO.update(tableCopy);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public ArrayList<TableCopy> get_table_by_year_user(String year, User user) {
        ArrayList<TableCopy> reList = new ArrayList<>();

        ArrayList<User> danwei = get_users_by_pre_code(user.getuCodeE());

        ArrayList<User>users = new ArrayList<>();
        for (User u:danwei
                ) {
            ArrayList<User> temp = get_teachers(u);
            users.addAll(temp);
        }

        for (User u:users
                ) {
            Map map = new HashMap();
            map.put("tUserInputYear",year);
            map.put("uId",u.getuId());
            map.put("tIfQOk",2);
            map.put("tIfWOk",2);
            ArrayList<TableCopy> result = (ArrayList<TableCopy>)baseDAO.findByProperties(map,TableCopy.class);
            reList.addAll(result);
        }
        return reList;
    }
}
