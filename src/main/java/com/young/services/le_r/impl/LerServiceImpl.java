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
        map.put("uType",3);
        ArrayList<User> users = (ArrayList<User>)baseDAO.findByProperties(map,User.class);
        return users;
    }

    public ArrayList<User> get_teachers(User user){
        Map map = new HashMap();
        map.put("uCodeE",user.getuCodeE());
        map.put("uType",1);
        ArrayList<User>relist  = (ArrayList<User>)baseDAO.findByProperties(map,User.class);
        return relist;
    }
    public ArrayList<User1> get_teachers2(User1 user){
        Map map = new HashMap();
        map.put("u1CodeQ",user.getU1CodeQ());
        map.put("u1Type",6);
        map.put("u1States",1);
        ArrayList<User1>relist  = (ArrayList<User1>)baseDAO.findByProperties(map,User1.class);
        return relist;
    }
    public ArrayList<User2> get_teachers3(){
        Map map = new HashMap();
        map.put("u2CodeType",6);
        map.put("u2States",1);
        ArrayList<User2>relist  = (ArrayList<User2>)baseDAO.findByProperties(map,User2.class);
        return relist;
    }

    @Override
    public ArrayList<TableExtendVo> get_tables(String year, long u_id, User user) {
        ArrayList<TableExtendVo> tableCopies = new ArrayList<>();
        User user1 = baseDAO.findById(u_id,User.class);

        if (year.equals("-1")&&u_id==-1){
            Map map = new HashMap();
            map.put("tIfTemp",0);
            map.put("tIfQOk",2);
            map.put("tIfWOk",2);
            ArrayList<TableCopy> result = (ArrayList<TableCopy>)baseDAO.findByProperties(map,TableCopy.class);
            for (TableCopy rc:result
                    ) {
                TableExtendVo tableExtendVo = new TableExtendVo();
                tableExtendVo.setTableCopy(rc);
                tableExtendVo.setUser(baseDAO.findById(rc.getuId(),User.class));
                tableCopies.add(tableExtendVo);
            }
        }else if(u_id==-1){
            Map map = new HashMap();
            map.put("tUserInputYear",year);
            map.put("tIfTemp",0);
            map.put("tIfQOk",2);
            map.put("tIfWOk",2);
            ArrayList<TableCopy> result = (ArrayList<TableCopy>)baseDAO.findByProperties(map,TableCopy.class);
            for (TableCopy rc:result
                    ) {
                TableExtendVo tableExtendVo = new TableExtendVo();
                tableExtendVo.setTableCopy(rc);
                tableExtendVo.setUser(baseDAO.findById(rc.getuId(),User.class));
                tableCopies.add(tableExtendVo);
            }
        }else if(year.equals("-1")){
            ArrayList<User> users = get_teachers(user1);
            for (User u:users
                    ) {
                Map map = new HashMap();
                map.put("uId", u.getuId());
                map.put("tIfTemp",0);
                map.put("tIfQOk",2);
                map.put("tIfWOk",2);
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
                map.put("tIfWOk",2);
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
    public int total_register(List<Map<String, Object>> list ) {
        int num=0;
        try {
            for (Map<String,Object> map:list
                    ) {
                User user = new User();
                user.setuName(map.get("0").toString());
                long code = check_code(map.get("1").toString());
                if (code>0)user.setuCodeE(code+"");
                else continue;
                user.setuPw( map.get("2").toString());
                user.setuType(3);
                user.setuCodeQ("999");
                user.setuCodeW("999");
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
    public ArrayList<TableCopy> get_table_by_year_user(String year, User user) {
        ArrayList<TableCopy> reList = new ArrayList<>();
        Map map = new HashMap();
        map.put("tUserInputYear",year);
        map.put("tIfQOk",2);
        map.put("tIfWOk",2);
        map.put("tIfEOk",2);
        ArrayList<TableCopy> result = (ArrayList<TableCopy>)baseDAO.findByProperties(map,TableCopy.class);
        reList.addAll(result);
        return reList;
    }


    public int cancel2(long code , String form_cancel_text) {
        try {
            TableCopyCopy tableCopy = baseDAO.findById(code , TableCopyCopy.class);
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

    public int up2(long code, String form_cancel_text,String tNumber,String dateY,String dateM,String dateD) {
        try {
            TableCopyCopy tableCopy = baseDAO.findById(code , TableCopyCopy.class);
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


    public ArrayList<TableCopyCopy> get_table_by_year_user2(String year, User user) {
        ArrayList<TableCopyCopy> reList = new ArrayList<>();
        Map map = new HashMap();
        map.put("tUserInputYear",year);
        map.put("tIfQOk",2);
        map.put("tIfEOk",2);
        ArrayList<TableCopyCopy> result = (ArrayList<TableCopyCopy>)baseDAO.findByProperties(map,TableCopyCopy.class);
        reList.addAll(result);
        return reList;
    }



    public ArrayList<TableExtendVo> get_tables2(String year, long u_id, User user) {
        ArrayList<TableExtendVo> tableCopies = new ArrayList<>();
        User1 user1 = baseDAO.findById(u_id,User1.class);

        if (year.equals("-1")&&u_id==-1){
            Map map = new HashMap();
            map.put("tIfTemp",0);
            map.put("tIfQOk",2);
            ArrayList<TableCopyCopy> result = (ArrayList<TableCopyCopy>)baseDAO.findByProperties(map,TableCopyCopy.class);
            for (TableCopyCopy rc:result
                    ) {
                TableExtendVo tableExtendVo = new TableExtendVo();
                tableExtendVo.setTableCopyCopy(rc);
                tableExtendVo.setUser(user1Touser(baseDAO.findById(rc.getuId(),User1.class)));
                tableCopies.add(tableExtendVo);
            }
        }else if(u_id==-1){
            Map map = new HashMap();
            map.put("tUserInputYear",year);
            map.put("tIfTemp",0);
            map.put("tIfQOk",2);
            ArrayList<TableCopyCopy> result = (ArrayList<TableCopyCopy>)baseDAO.findByProperties(map,TableCopyCopy.class);
            for (TableCopyCopy rc:result
                    ) {
                TableExtendVo tableExtendVo = new TableExtendVo();
                tableExtendVo.setTableCopyCopy(rc);
                tableExtendVo.setUser(user1Touser(baseDAO.findById(rc.getuId(),User1.class)));
                tableCopies.add(tableExtendVo);
            }
        }else if(year.equals("-1")){
            ArrayList<User1> users = get_teachers2(user1);
            for (User1 u:users
                    ) {
                Map map = new HashMap();
                map.put("uId", u.getU1Id());
                map.put("tIfTemp",0);
                map.put("tIfQOk",2);
                ArrayList<TableCopyCopy> result = (ArrayList<TableCopyCopy>) baseDAO.findByProperties(map, TableCopyCopy.class);
                for (TableCopyCopy rc:result
                        ) {
                    TableExtendVo tableExtendVo = new TableExtendVo();
                    tableExtendVo.setTableCopyCopy(rc);
                    tableExtendVo.setUser(user1Touser(u));
                    tableCopies.add(tableExtendVo);
                }
            }
        }
        else{
            ArrayList<User1> users = get_teachers2(user1);
            for (User1 u:users
                    ) {
                Map map = new HashMap();
                map.put("uId", u.getU1Id());
                map.put("tIfQOk",2);
                map.put("tIfTemp",0);
                map.put("tUserInputYear",year);
                ArrayList<TableCopyCopy> result = (ArrayList<TableCopyCopy>) baseDAO.findByProperties(map, TableCopyCopy.class);
                for (TableCopyCopy rc:result
                        ) {
                    TableExtendVo tableExtendVo = new TableExtendVo();
                    tableExtendVo.setTableCopyCopy(rc);
                    tableExtendVo.setUser(user1Touser(u));
                    tableCopies.add(tableExtendVo);
                }
            }
        }
        return tableCopies;
    }


    public ArrayList<User1> get_users_by_pre_code2() {
        Map map = new HashMap();
        map.put("u1Type",5);
        map.put("u1States",1);
        ArrayList<User1> users = (ArrayList<User1>)baseDAO.findByProperties(map,User1.class);
        return users;
    }

    public ArrayList<User1> req2() {
        Map map = new HashMap();
        map.put("u1Type",5);
        map.put("u1States",2);
        ArrayList<User1> users = (ArrayList<User1>)baseDAO.findByProperties(map,User1.class);
        return users;
    }

    public ArrayList<YearVo> get_years2() {
        try {
            ArrayList<YearVo> reList =(ArrayList<YearVo>)baseDAO.findBySQLForVO(
                    "select distinct t_user_input_year as year from table_copy_copy order by year desc",
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

    public int reset_pw2(long u_id) {
        try {
            User1 user = baseDAO.findById(u_id,User1.class);
            user.setU1Pw("123456");
            baseDAO.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int reset_ok2(long u_id) {
        try {
            User1 user = baseDAO.findById(u_id,User1.class);
            user.setU1Pw("123456");
            user.setU1States(1);
            baseDAO.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int reset_nook2(long u_id) {
        try {
            User1 user = baseDAO.findById(u_id,User1.class);
           baseDAO.delete(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }





    public int cancel3(long code , String form_cancel_text) {
        try {
            TableCopyCopyCopy tableCopy = baseDAO.findById(code , TableCopyCopyCopy.class);
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

    public int up3(long code, String form_cancel_text,String tNumber,String dateY,String dateM,String dateD) {
        try {
            TableCopyCopyCopy tableCopy = baseDAO.findById(code , TableCopyCopyCopy.class);
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


    public ArrayList<TableCopyCopyCopy> get_table_by_year_user3(String year, User user) {
        ArrayList<TableCopyCopyCopy> reList = new ArrayList<>();
        Map map = new HashMap();
        map.put("tUserInputYear",year);
        map.put("tIfEOk",2);
        ArrayList<TableCopyCopyCopy> result = (ArrayList<TableCopyCopyCopy>)baseDAO.findByProperties(map,TableCopyCopyCopy.class);
        reList.addAll(result);
        return reList;
    }



    public ArrayList<TableExtendVo> get_tables3(String year, long u_id, User user) {
        ArrayList<TableExtendVo> tableCopies = new ArrayList<>();
        User2 user1 = baseDAO.findById(u_id,User2.class);

        if (year.equals("-1")&&u_id==-1){
            Map map = new HashMap();
            map.put("tIfTemp",0);
            ArrayList<TableCopyCopyCopy> result = (ArrayList<TableCopyCopyCopy>)baseDAO.findByProperties(map,TableCopyCopyCopy.class);
            for (TableCopyCopyCopy rc:result
                    ) {
                TableExtendVo tableExtendVo = new TableExtendVo();
                tableExtendVo.setTableCopyCopyCopy(rc);
                tableExtendVo.setUser(user2Touser(baseDAO.findById(rc.getuId(),User2.class)));
                tableCopies.add(tableExtendVo);
            }
        }else if(u_id==-1){
            Map map = new HashMap();
            map.put("tUserInputYear",year);
            map.put("tIfTemp",0);
            ArrayList<TableCopyCopyCopy> result = (ArrayList<TableCopyCopyCopy>)baseDAO.findByProperties(map,TableCopyCopyCopy.class);
            for (TableCopyCopyCopy rc:result
                    ) {
                TableExtendVo tableExtendVo = new TableExtendVo();
                tableExtendVo.setTableCopyCopyCopy(rc);
                tableExtendVo.setUser(user2Touser(baseDAO.findById(rc.getuId(),User2.class)));
                tableCopies.add(tableExtendVo);
            }
        }else if(year.equals("-1")){
            Map map = new HashMap();
            map.put("uId",u_id);
            map.put("tIfTemp",0);
            ArrayList<TableCopyCopyCopy> result = (ArrayList<TableCopyCopyCopy>) baseDAO.findByProperties(map, TableCopyCopyCopy.class);
            for (TableCopyCopyCopy rc:result
                    ) {
                TableExtendVo tableExtendVo = new TableExtendVo();
                tableExtendVo.setTableCopyCopyCopy(rc);
                tableExtendVo.setUser(user2Touser(
                        baseDAO.findById(u_id,User2.class)
                ));
                tableCopies.add(tableExtendVo);
            }
        }
        else{
            Map map = new HashMap();
            map.put("uId",u_id);
            map.put("tUserInputYear",year);
            map.put("tIfTemp",0);
            ArrayList<TableCopyCopyCopy> result = (ArrayList<TableCopyCopyCopy>) baseDAO.findByProperties(map, TableCopyCopyCopy.class);
            for (TableCopyCopyCopy rc:result
                    ) {
                TableExtendVo tableExtendVo = new TableExtendVo();
                tableExtendVo.setTableCopyCopyCopy(rc);
                tableExtendVo.setUser(user2Touser(
                        baseDAO.findById(u_id,User2.class)
                ));
                tableCopies.add(tableExtendVo);
            }
        }
        return tableCopies;
    }


    public ArrayList<User2> get_users_by_pre_code3() {
        Map map = new HashMap();
        map.put("u2CodeType",7);
        map.put("u2States",1);
        ArrayList<User2> users = (ArrayList<User2>)baseDAO.findByProperties(map,User2.class);
        return users;
    }

    public ArrayList<User2> req3() {
        Map map = new HashMap();
        map.put("u2CodeType",7);
        map.put("u2States",2);
        ArrayList<User2> users = (ArrayList<User2>)baseDAO.findByProperties(map,User2.class);
        return users;
    }

    public ArrayList<YearVo> get_years3() {
        try {
            ArrayList<YearVo> reList =(ArrayList<YearVo>)baseDAO.findBySQLForVO(
                    "select distinct t_user_input_year as year from table_copy_copy_copy order by year desc",
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

    public int reset_pw3(long u_id) {
        try {
            User2 user = baseDAO.findById(u_id,User2.class);
            user.setU2CodePw("123456");
            baseDAO.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int reset_ok3(long u_id) {
        try {
            User2 user = baseDAO.findById(u_id,User2.class);
            user.setU2CodePw("123456");
            user.setU2States(1);
            baseDAO.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int reset_nook3(long u_id) {
        try {
            User2 user = baseDAO.findById(u_id,User2.class);
            baseDAO.delete(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
