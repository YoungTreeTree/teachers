package com.young.services.le_t.impl;

import com.young.entity.TableCopy;
import com.young.entity.TableCopyCopy;
import com.young.entity.User;
import com.young.entity.User1;
import com.young.services.common.impl.CommServiceImpl;
import com.young.services.le_t.ILetService;
import com.young.vo.TableExtendVo;
import com.young.vo.YearVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service(value = "letService")
public class LetServiceImpl extends CommServiceImpl implements ILetService {

    @Override
    public ArrayList<TableCopyCopy> get_table_by_year_user(String year, User user) {
        ArrayList<TableCopyCopy> reList = new ArrayList<>();
        List<User> users = get_users_by_pre_code(user.getuCodeQ());

        for (User u:users
             ) {
            Map map = new HashMap();
            map.put("tUserInputYear",year);
            map.put("uId",u.getuId());
            map.put("tIfQOk",2);
            ArrayList<TableCopyCopy> result = (ArrayList<TableCopyCopy>)baseDAO.findByProperties(map,TableCopyCopy.class);
            reList.addAll(result);
        }

        return reList;
    }

    @Override
    public ArrayList<TableExtendVo> get_tables(String year, long u_id, User user) {
        ArrayList<TableExtendVo> tableCopies = new ArrayList<>();

        if (year.equals("-1")&&u_id==-1){
            List<User> users = get_users_by_pre_code(user.getuCodeQ());
            for (User u:users
                    ) {
                Map map = new HashMap();
                map.put("uId",u.getuId());
                map.put("tIfTemp",0);
                ArrayList<TableCopyCopy> result = (ArrayList<TableCopyCopy>)baseDAO.findByProperties(map,TableCopyCopy.class);
                for (TableCopyCopy rc:result
                     ) {
                    TableExtendVo tableExtendVo = new TableExtendVo();
                    tableExtendVo.setTableCopyCopy(rc);
                    tableExtendVo.setUser(u);
                    tableCopies.add(tableExtendVo);
                }
            }
        }else if(u_id==-1){
            List<User> users = get_users_by_pre_code(user.getuCodeQ());

            for (User u:users
                    ) {
                Map map = new HashMap();
                map.put("tUserInputYear", year);
                map.put("tIfTemp",0);
                map.put("uId", u.getuId());
                ArrayList<TableCopyCopy> result = (ArrayList<TableCopyCopy>)baseDAO.findByProperties(map,TableCopyCopy.class);
                for (TableCopyCopy rc:result
                        ) {
                    TableExtendVo tableExtendVo = new TableExtendVo();
                    tableExtendVo.setTableCopyCopy(rc);
                    tableExtendVo.setUser(u);
                    tableCopies.add(tableExtendVo);
                }
            }
        }else if(year.equals("-1")){
            Map map = new HashMap();
            map.put("uId", u_id);
            map.put("tIfTemp",0);
            ArrayList<TableCopyCopy> result = (ArrayList<TableCopyCopy>)baseDAO.findByProperties(map,TableCopyCopy.class);
            for (TableCopyCopy rc:result
                    ) {
                TableExtendVo tableExtendVo = new TableExtendVo();
                tableExtendVo.setTableCopyCopy(rc);
                tableExtendVo.setUser(user1Touser(baseDAO.findById(u_id,User1.class)));
                tableCopies.add(tableExtendVo);
                }
        }
        else{
            Map map = new HashMap();
            map.put("tUserInputYear", year);
            map.put("uId", u_id);
            map.put("tIfTemp",0);
            ArrayList<TableCopyCopy> result = (ArrayList<TableCopyCopy>)baseDAO.findByProperties(map,TableCopyCopy.class);
            User user1 = user1Touser(baseDAO.findById(u_id,User1.class));
            for (TableCopyCopy rc:result
                    ) {
                TableExtendVo tableExtendVo = new TableExtendVo();
                tableExtendVo.setTableCopyCopy(rc);
                tableExtendVo.setUser(user1);
                tableCopies.add(tableExtendVo);
            }
        }
        return tableCopies;
    }

    @Override
    public List<User> get_users_by_pre_code(String precode) {
        Map map = new HashMap();
        map.put("u1CodeQ",precode);
        map.put("u1Type",6);
        ArrayList<User1> users = (ArrayList<User1>)baseDAO.findByProperties(map,User1.class);
        List<User> userArrayList = users.stream().map(user1 -> user1Touser(user1)).collect(Collectors.toList());
        return userArrayList;
    }


    @Override
    public List<User1> get_users_by_pre_code1(String precode) {
        Map map = new HashMap();
        map.put("u1CodeQ",precode);
        map.put("u1Type",6);
        ArrayList<User1> users = (ArrayList<User1>)baseDAO.findByProperties(map,User1.class);
        return users;
    }

    @Override
    public ArrayList<YearVo> get_years() {
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

    @Override
    public int cancel(long code , String form_cancel_text) {
        try {
            TableCopyCopy tableCopy = baseDAO.findById(code , TableCopyCopy.class);
            tableCopy.settIfQOk(3);
            tableCopy.settQNoReason(form_cancel_text);
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
            TableCopyCopy tableCopy = baseDAO.findById(code , TableCopyCopy.class);
            tableCopy.settIfQOk(2);
            tableCopy.settQNoReason(form_cancel_text);
            tableCopy.settIfTemp(0);
            baseDAO.update(tableCopy);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public int total_register(List<Map<String, Object>> list , String q) {
        int num=0;
        try {
            for (Map<String,Object> map:list
                    ) {
                User1 user = new User1();
                user.setU1UName(map.get("0")+"");
                long code = check_phone(map.get("1")+"");
                if (code>0)user.setU1CodeW(code+"");
                else continue;
                user.setU1Pw( map.get("2").toString());
                user.setU1Type(6);
                user.setU1CodeQ(q);
                user.setU1States(1);
                user.setU1Phone(code+"");
                Map map2 = new HashMap();
                map2.put("u1CodeW",code+"");
                ArrayList<User1> users = (ArrayList<User1>)baseDAO.findByProperties(map2,User1.class);
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
/**
 *
 * @param code2
 * @return  -1 code长度或者格式不对
 *          -2 code里面含有非数字
 */

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

    private long check_phone(String code2){
        if(code2==null)return -1;
        return Long.parseLong(code2);
    }

    @Override
    public int reset_pw_2(long u_id) {
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

    @Override
    public int change_info(String old_pw, String new_pw, long u_id, String name, String mail, String address) {
        try {
            if(old_pw==null||new_pw==null){
                return -1;
            }
            if (old_pw==new_pw){
                return -3;
            }
            User1 user = baseDAO.findById(u_id,User1.class);
            if(old_pw.equals(user.getU1Pw())){
                user.setU1Pw(new_pw);
                user.setU1UName(name);
                user.setU1Email(mail);
                user.setU1Address(address);
                baseDAO.update(user);
                return 1;
            }else{
                return -2;//旧密码不正确
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
