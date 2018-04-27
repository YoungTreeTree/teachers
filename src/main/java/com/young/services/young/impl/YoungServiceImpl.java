/*
package com.young.services.young.impl;

import com.framework.util.DateUtil;
import com.young.entity.User;
import com.young.services.common.impl.CommServiceImpl;
import com.young.services.young.IYoungService;
import com.young.vo.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * Created by young on 2017/9/7.
 *//*

@Service(value = "youngService")
public class YoungServiceImpl extends CommServiceImpl implements IYoungService {
    */
/**
     * @param num
     * @param pw
     * @return
     *          id : 登录成功
     *          -3 : 用户不存在
     *          -4 : 密码不正确
     *          -2 : 未知错误
     *//*

    @Override
    public long check_login(long num, String pw ) {
        try {
            ArrayList<User> UserList=(ArrayList<User>)baseDAO.findByProperty("uCode",num,User.class);
            if(UserList.size()<=0){
                return -3;
            }else{
                if (pw.equals(UserList.get(0).getuPw())) {
                    return UserList.get(0).getuCode();
                }else{
                    return -4;
                }
            }
        } catch (Exception e) {
            return -2;
        }
    }

    @Override
    public int get_user_type(long code) {
        try {
            User user = baseDAO.findById(code,User.class);
            if (user!=null);
            {
                return user.getuType();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<UserVo> get_userList_pro(long u_id) {
        try {
            ArrayList<User>reList = (ArrayList<User>) baseDAO.findByProperty("uCodePro",u_id,User.class);
            List<UserVo> userVos = new ArrayList<>();
            for (User user: reList
                 ) {
                UserVo uv = new UserVo();
                uv.setuCode(user.getuCode());
                uv.setuName(user.getuName());
                userVos.add(uv);
            }
            return userVos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int total_register(List<Map<String, Object>> list , long user_id) {
        int num=0;
        try {
            for (Map<String,Object> map:list
                 ) {
                User user = new User();
                user.setuName(map.get("0").toString());
                long code = check_code(user_id,map.get("1").toString());
                if (code!=-1)user.setuCode(code);
                else continue;
                user.setuPw( map.get("2").toString());
                user.setuType(1);
                user.setuCodePro(user_id);
                baseDAO.save(user);
                num++;
            }
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return num;
        }
    }
    @Override
    public int total_register_top(List<Map<String, Object>> list , long user_id) {
        int num=0;
        try {
            for (Map<String,Object> map:list
                    ) {
                User user = new User();
                user.setuName(map.get("0").toString());
                if(map.get("1").toString().length()!=3)continue;
                long code = Long.parseLong(map.get("1").toString());
                if (code!=-1)user.setuCode(code);
                else continue;
                user.setuPw( map.get("2").toString());
                user.setuType(2);
                user.setuCodePro(1722);
                baseDAO.save(user);
                num++;
            }
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return num;
        }
    }
    */
/**
     *
     * @param code1
     * @param code2
     * @return  -1 code长度或者格式不对
     *          -2 code里面含有非数字
     *//*

    private long check_code(long code1, String code2){
        if(code2==null||code2.length()!=3)return -1;
        try {
            long code2_long = Long.parseLong(code2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -2;
        }
        return Long.parseLong(code1+code2);
    }

    @Override
    public Map<String, Object> get_table_by_code_and_year(long code) {
        try {
            Map<String, Object>reMap = new HashMap<>();
            int year = DateUtil.getYearForDate(DateUtil.getCurrentDate());
            Map<String , Object>pMap = new HashMap<>();
            pMap.put("uCode",code);
            pMap.put("year",year);
            ArrayList<MainTable>reList = (ArrayList<MainTable>) baseDAO.findByProperties(pMap, MainTable.class);
            if(reList.size()>0){
                reMap.put("main",reList.get(0));
                ArrayList<PerTable>reList_PerTable = (ArrayList<PerTable>) baseDAO.findByProperty("tId",reList.get(0).gettId(), PerTable.class);
                int dis = 10-reList_PerTable.size();
                if (dis>0){
                    for (int i = 0 ; i <dis ;i++){
                        PerTable pt = new PerTable();
                        pt.setpId(-1);
                        reList_PerTable.add(pt);
                    }
                }else if(dis<10){
                    for (int i = 0 ; i<-dis ;i++){
                        reList_PerTable.remove(10);
                    }
                }else{

                }
                reMap.put("perList",reList_PerTable);
            }else{
                reMap.put("main",null);
                ArrayList<PerTable>reList_PerTable = new ArrayList<>();
                for (int i = 0 ; i <10 ;i++){
                    PerTable pt = new PerTable();
                    pt.setpId(-1);
                    reList_PerTable.add(pt);
                }
                reMap.put("perList",reList_PerTable);
            }
            return reMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<TableShortVo> get_tables(long code) {
        try {
            ArrayList<TableShortVo> tsv = (ArrayList<TableShortVo>)baseDAO.findBySQLForVO(
                    "select t_id as tId , year as year , state as state from main_table where u_code = ? order by year desc",TableShortVo.class,new Object[]{code});
            for (TableShortVo t:tsv
                 ) {
                if(t.getState()==1){
                    t.setStateString("暂存");
                }else if (t.getState()==2){
                    t.setStateString("已提交");
                }else if (t.getState()==3){
                    t.setStateString("已驳回");
                }
            }
            return tsv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, Object> get_table_by_id(long id) {
        try {
            Map<String, Object>reMap = new HashMap<>();
            int year = DateUtil.getYearForDate(DateUtil.getCurrentDate());

            MainTable mt =baseDAO.findById(id, MainTable.class);
            if(mt!=null){
                reMap.put("main",mt);
                ArrayList<PerTable>reList_PerTable = (ArrayList<PerTable>) baseDAO.findByProperty("tId",id, PerTable.class);
                int dis = 10-reList_PerTable.size();
                if (dis>0){
                    for (int i = 0 ; i <dis ;i++){
                        reList_PerTable.add(new PerTable());
                    }
                }else if(dis<10){
                    for (int i = 0 ; i<-dis ;i++){
                        reList_PerTable.remove(10);
                    }
                }else{

                }
                reMap.put("perList",reList_PerTable);
            }else{
                reMap.put("main",null);
            }
            return reMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int change_pw(String old_pw, String new_pw, long code) {
        try {
            if(old_pw==null||new_pw==null){
                return -1;
            }
            if (old_pw==new_pw){
                return -3;
            }
            User user = baseDAO.findById(code,User.class);
            if(old_pw.equals(user.getuPw())){
                user.setuPw(new_pw);
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

    @Override
    public int reset_pw(long code) {
        try {
            User user = baseDAO.findById(code,User.class);
            user.setuPw("123456");
            baseDAO.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Map<String, Object> init_table_statistics_pro(int year,long pro_id) {
        Map<String,Object>reMap = new HashMap<>();
        reMap.put("year" ,year );
        Map pMaps = new HashMap();
        pMaps.put("year",year);
        pMaps.put("state",2);
        pMaps.put("proUCode",pro_id);
        ArrayList<MainTable>relist = ( ArrayList<MainTable>)baseDAO.findByProperties(pMaps,MainTable.class);
        //reMap.put("perTables",relist);

        ArrayList<MainTableVo>mainTableVos = new ArrayList<>();
        for (MainTable main:relist){
            MainTableVo mtv = new MainTableVo(main);
            mtv.setName(get_name_by_ucode(mtv.getuCode()));
            mainTableVos.add(mtv);
        }
        reMap.put("perTables",mainTableVos);

        MainTable totalTable = new MainTable();
        int longA = 0;
        int longB = 0;
        int longC = 0;
        int longTotal = 0;
        int shortA = 0;
        int shortB = 0;
        int shortC = 0;
        int shortTotal = 0;
        int volA = 0;
        int volB = 0;
        int volC = 0;
        int volTotal = 0;
        int localA = 0;
        int localB = 0;
        int localC = 0;
        int localD = 0;
        int total  = 0;
        for (MainTable perTable:relist
                ) {
            longA+=perTable.getLongA();
            longB+=perTable.getLongB();
            longC+=perTable.getLongC();
            longTotal+=perTable.getLongTotal();
            shortA+=perTable.getShortA();
            shortB+=perTable.getShortB();
            shortC+=perTable.getShortC();
            shortTotal+=perTable.getShortTotal();
            volA+=perTable.getVolA();
            volB+=perTable.getVolB();
            volC+=perTable.getVolC();
            volTotal+=perTable.getVolTotal();
            localA+=perTable.getLocalA();
            localB+=perTable.getLocalB();
            localC+=perTable.getLocalC();
            localD+=perTable.getLocalD();
            total+=perTable.getTotal();
        }
        totalTable.setLongA(longA);
        totalTable.setLongB(longB);
        totalTable.setLongC(longC);
        totalTable.setLongTotal(longTotal);
        totalTable.setShortA(shortA);
        totalTable.setShortB(shortB);
        totalTable.setShortC(shortC);
        totalTable.setShortTotal(shortTotal);
        totalTable.setVolA(volA);
        totalTable.setVolB(volB);
        totalTable.setVolC(volC);
        totalTable.setVolTotal(volTotal);
        totalTable.setLocalA(localA);
        totalTable.setLocalB(localB);
        totalTable.setLocalC(localC);
        totalTable.setLocalD(localD);
        totalTable.setTotal(total);
        reMap.put("totalTable",totalTable);
        ArrayList<YearVo> reYears = ( ArrayList<YearVo>)baseDAO.findBySQLForVO(
                "select distinct year from main_table"
                 ,YearVo.class,new Object[]{});
        reMap.put("years",reYears);
        return reMap;
    }

    private String get_name_by_ucode(long code){
        try {
            User user = baseDAO.findById(code,User.class);
            return user.getuName();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    @Override
    public Map<String, Object> init_table_statistics_top(int year,long code) {
        Map<String,Object>reMap = new HashMap<>();
        reMap.put("year" ,year );
        ArrayList<MainTable>relist;
        Map pMaps = new HashMap();
        pMaps.put("year",year);
        pMaps.put("state",2);
        if (code>0){
            pMaps.put("proUCode",code);
            relist = ( ArrayList<MainTable>)baseDAO.findByProperties(pMaps,MainTable.class);
        }else{
            relist = ( ArrayList<MainTable>)baseDAO.findByProperties(pMaps,MainTable.class);
        }

       // reMap.put("perTables",relist);
        ArrayList<MainTableVo>mainTableVos = new ArrayList<>();
        for (MainTable main:relist){
            MainTableVo mtv = new MainTableVo(main);
            mtv.setName(get_name_by_ucode(mtv.getuCode()));
            mainTableVos.add(mtv);
        }
        reMap.put("perTables",mainTableVos);


        MainTable totalTable = new MainTable();
        int longA = 0;
        int longB = 0;
        int longC = 0;
        int longTotal = 0;
        int shortA = 0;
        int shortB = 0;
        int shortC = 0;
        int shortTotal = 0;
        int volA = 0;
        int volB = 0;
        int volC = 0;
        int volTotal = 0;
        int localA = 0;
        int localB = 0;
        int localC = 0;
        int localD = 0;
        int total  = 0;
        for (MainTable perTable:relist
                ) {
            longA+=perTable.getLongA();
            longB+=perTable.getLongB();
            longC+=perTable.getLongC();
            longTotal+=perTable.getLongTotal();
            shortA+=perTable.getShortA();
            shortB+=perTable.getShortB();
            shortC+=perTable.getShortC();
            shortTotal+=perTable.getShortTotal();
            volA+=perTable.getVolA();
            volB+=perTable.getVolB();
            volC+=perTable.getVolC();
            volTotal+=perTable.getVolTotal();
            localA+=perTable.getLocalA();
            localB+=perTable.getLocalB();
            localC+=perTable.getLocalC();
            localD+=perTable.getLocalD();
            total+=perTable.getTotal();
        }
        totalTable.setLongA(longA);
        totalTable.setLongB(longB);
        totalTable.setLongC(longC);
        totalTable.setLongTotal(longTotal);
        totalTable.setShortA(shortA);
        totalTable.setShortB(shortB);
        totalTable.setShortC(shortC);
        totalTable.setShortTotal(shortTotal);
        totalTable.setVolA(volA);
        totalTable.setVolB(volB);
        totalTable.setVolC(volC);
        totalTable.setVolTotal(volTotal);
        totalTable.setLocalA(localA);
        totalTable.setLocalB(localB);
        totalTable.setLocalC(localC);
        totalTable.setLocalD(localD);
        totalTable.setTotal(total);
        reMap.put("totalTable",totalTable);
        return reMap;
    }


    @Override
    public ArrayList<YearVo> get_years() {
        try {
            ArrayList<YearVo> reList =(ArrayList<YearVo>)baseDAO.findBySQLForVO(
                    "select distinct year as year from main_table order by year desc",
                    YearVo.class,
                    new Object[]{}
            );
            return reList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<CodeVo> get_codes_for_pro(long user_id) {
        try {
            ArrayList<CodeVo> reList =(ArrayList<CodeVo>)baseDAO.findBySQLForVO(
                    "select distinct u_code as code,u_name as name  from user  where u_code like ? order by u_code",
                    CodeVo.class,
                    new Object[]{user_id+"%"}
            );
            return reList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<CodeVo> get_codes_for_top() {
        try {
            ArrayList<CodeVo> reList =(ArrayList<CodeVo>)baseDAO.findBySQLForVO(
                    "select distinct u_code as code ,u_name as name from user  where u_type = 2 order by u_code",
                    CodeVo.class,
                    new Object[]{}
            );
            return reList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<CodeVo> get_codes() {
        try {
            ArrayList<CodeVo> reList =(ArrayList<CodeVo>)baseDAO.findBySQLForVO(
                    "select distinct u_code as code from user order by u_code",
                    CodeVo.class,
                    new Object[]{}
            );
            return reList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<TableShortVo> get_table_by_code_a_year(long code, int year) {
        try {
            ArrayList<TableShortVo> tsv=new ArrayList<>();
            if (code<0&&year<0){
                tsv = (ArrayList<TableShortVo>) baseDAO.findBySQLForVO(
                        "select u_name as name , main_table.u_code as code , t_id as tId , year as year , state as state from main_table  left join user on main_table.u_code = user.u_code where main_table.state = 2 ", TableShortVo.class, new Object[]{});
                for (TableShortVo t : tsv
                        ) {
                    if (t.getState() == 1) {
                        t.setStateString("暂存");
                    } else if (t.getState() == 2) {
                        t.setStateString("已提交");
                    }else if (t.getState() == 3) {
                        t.setStateString("已驳回");
                    }
                }
                return tsv;
            }
            if(code<0){
                tsv = (ArrayList<TableShortVo>) baseDAO.findBySQLForVO(
                        "select u_name as name , main_table.u_code as code , t_id as tId , year as year , state as state from main_table  left join user on main_table.u_code = user.u_code where   main_table.year = ? and main_table.state = 2 ", TableShortVo.class, new Object[]{year});
                for (TableShortVo t : tsv
                        ) {
                    if (t.getState() == 1) {
                        t.setStateString("暂存");
                    } else if (t.getState() == 2) {
                        t.setStateString("已提交");
                    }else if (t.getState() == 3) {
                        t.setStateString("已驳回");
                    }
                }
            }else if(year<0){
                tsv = (ArrayList<TableShortVo>) baseDAO.findBySQLForVO(
                        "select u_name as name , main_table.u_code as code , t_id as tId , year as year , state as state from main_table  left join user on main_table.u_code = user.u_code where main_table.u_code = ? and main_table.state = 2  ", TableShortVo.class, new Object[]{code});
                for (TableShortVo t : tsv
                        ) {
                    if (t.getState() == 1) {
                        t.setStateString("暂存");
                    } else if (t.getState() == 2) {
                        t.setStateString("已提交");
                    }else if (t.getState() == 3) {
                        t.setStateString("已驳回");
                    }
                }
            }else{
                tsv = (ArrayList<TableShortVo>) baseDAO.findBySQLForVO(
                        "select u_name as name , main_table.u_code as code , t_id as tId , year as year , state as state from main_table  left join user on main_table.u_code = user.u_code where main_table.u_code = ? and main_table.year = ? and main_table.state = 2  ", TableShortVo.class, new Object[]{code,year});
                for (TableShortVo t : tsv
                        ) {
                    if (t.getState() == 1) {
                        t.setStateString("暂存");
                    } else if (t.getState() == 2) {
                        t.setStateString("已提交");
                    }else if (t.getState() == 3) {
                        t.setStateString("已驳回");
                    }
                }
            }

            return tsv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


    */
/**
     * 每个省只能获取自己分区下的数据
     * @param code
     * @param year
     * @return
     *//*

    @Override
    public ArrayList<TableShortVo> get_table_by_code_a_year_for_pro(long code, int year,long user_id) {
        try {
            ArrayList<TableShortVo> tsv=new ArrayList<>();
            if (code<0&&year<0){
                tsv = (ArrayList<TableShortVo>) baseDAO.findBySQLForVO(
                        "select u_name as name , main_table.u_code as code , t_id as tId , year as year , state as state from main_table  left join user on main_table.u_code = user.u_code where main_table.state = 2 and main_table.u_code like ?", TableShortVo.class, new Object[]{user_id+"%"});
                for (TableShortVo t : tsv
                        ) {
                    if (t.getState() == 1) {
                        t.setStateString("暂存");
                    } else if (t.getState() == 2) {
                        t.setStateString("已提交");
                    }else if (t.getState() == 3) {
                        t.setStateString("已驳回");
                    }
                }
                return tsv;
            }
            if(code<0){
                tsv = (ArrayList<TableShortVo>) baseDAO.findBySQLForVO(
                        "select u_name as name , main_table.u_code as code , t_id as tId , year as year , state as state from main_table  left join user on main_table.u_code = user.u_code where   main_table.year = ? and main_table.state = 2 and main_table.u_code like ?", TableShortVo.class, new Object[]{year,user_id+"%"});
                for (TableShortVo t : tsv
                        ) {
                    if (t.getState() == 1) {
                        t.setStateString("暂存");
                    } else if (t.getState() == 2) {
                        t.setStateString("已提交");
                    }else if (t.getState() == 3) {
                        t.setStateString("已驳回");
                    }
                }
            }else if(year<0){
                tsv = (ArrayList<TableShortVo>) baseDAO.findBySQLForVO(
                        "select u_name as name , main_table.u_code as code , t_id as tId , year as year , state as state from main_table  left join user on main_table.u_code = user.u_code where main_table.u_code = ? and main_table.state = 2 ", TableShortVo.class, new Object[]{code});
                for (TableShortVo t : tsv
                        ) {
                    if (t.getState() == 1) {
                        t.setStateString("暂存");
                    } else if (t.getState() == 2) {
                        t.setStateString("已提交");
                    }else if (t.getState() == 3) {
                        t.setStateString("已驳回");
                    }
                }
            }else{
                tsv = (ArrayList<TableShortVo>) baseDAO.findBySQLForVO(
                        "select u_name as name , main_table.u_code as code , t_id as tId , year as year , state as state from main_table  left join user on main_table.u_code = user.u_code where main_table.u_code = ? and main_table.year = ? and main_table.state = 2  ", TableShortVo.class, new Object[]{code,year});
                for (TableShortVo t : tsv
                        ) {
                    if (t.getState() == 1) {
                        t.setStateString("暂存");
                    } else if (t.getState() == 2) {
                        t.setStateString("已提交");
                    }else if (t.getState() == 3) {
                        t.setStateString("已驳回");
                    }
                }
            }

            return tsv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    */
/**
     * 每个省只能获取自己分区下的数据
     * @param code
     * @param year
     * @return
     *//*

    @Override
    public ArrayList<TableShortVo> get_table_by_code_a_year_for_top(long code, int year) {
        try {
            ArrayList<TableShortVo> tsv=new ArrayList<>();
            if (code<0&&year<0){
                tsv = (ArrayList<TableShortVo>) baseDAO.findBySQLForVO(
                        "select u_name as name , main_table.u_code as code , t_id as tId , year as year , state as state from main_table  left join user on main_table.u_code = user.u_code where main_table.state = 2 ", TableShortVo.class, new Object[]{});
                for (TableShortVo t : tsv
                        ) {
                    if (t.getState() == 1) {
                        t.setStateString("暂存");
                    } else if (t.getState() == 2) {
                        t.setStateString("已提交");
                    }else if (t.getState() == 3) {
                        t.setStateString("已驳回");
                    }
                }
                return tsv;
            }
            if(code<0){
                tsv = (ArrayList<TableShortVo>) baseDAO.findBySQLForVO(
                        "select u_name as name , main_table.u_code as code , t_id as tId , year as year , state as state from main_table  left join user on main_table.u_code = user.u_code where   main_table.year = ? and main_table.state = 2 ", TableShortVo.class, new Object[]{year});
                for (TableShortVo t : tsv
                        ) {
                    if (t.getState() == 1) {
                        t.setStateString("暂存");
                    } else if (t.getState() == 2) {
                        t.setStateString("已提交");
                    }else if (t.getState() == 3) {
                        t.setStateString("已驳回");
                    }
                }
            }else if(year<0){
                tsv = (ArrayList<TableShortVo>) baseDAO.findBySQLForVO(
                        "select u_name as name , main_table.u_code as code , t_id as tId , year as year , state as state from main_table  left join user on main_table.u_code = user.u_code where main_table.u_code like ? and main_table.state = 2 ", TableShortVo.class, new Object[]{code+"%"});
                for (TableShortVo t : tsv
                        ) {
                    if (t.getState() == 1) {
                        t.setStateString("暂存");
                    } else if (t.getState() == 2) {
                        t.setStateString("已提交");
                    }else if (t.getState() == 3) {
                        t.setStateString("已驳回");
                    }
                }
            }else{
                tsv = (ArrayList<TableShortVo>) baseDAO.findBySQLForVO(
                        "select u_name as name , main_table.u_code as code , t_id as tId , year as year , state as state from main_table  left join user on main_table.u_code = user.u_code where main_table.u_code like ? and main_table.year = ? and main_table.state = 2  ", TableShortVo.class, new Object[]{code+"%",year});
                for (TableShortVo t : tsv
                        ) {
                    if (t.getState() == 1) {
                        t.setStateString("暂存");
                    } else if (t.getState() == 2) {
                        t.setStateString("已提交");
                    }else if (t.getState() == 3) {
                        t.setStateString("已驳回");
                    }
                }
            }

            return tsv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

*/
/*
先判断这个用户今年有没有填写过表格
分为填过和没填过
填过的话分为提交和没提交

1:这个年份还没填过，成功保存
-1：这个年份填过 无法修改
-2：不知道
 *//*

    @Override
    public int table_up_temp(MainTable mt, String[] per_tables,long code) {
        try {
            HashMap<String , Object> pMap = new HashMap<>();
            int year = mt.getYear();
            pMap.put("uCode",code);
            pMap.put("year",year);
            ArrayList<MainTable> reMainTableList = (ArrayList<MainTable>)baseDAO.findByProperties(pMap,MainTable.class);
            if(reMainTableList==null||reMainTableList.size()<1){
            }else if(reMainTableList.get(0).getState()==2){
                return -1;
            } else{
                //mt.settId(reMainTableList.get(0).gettId());
                reMainTableList.get(0).settProvince(mt.gettProvince());
                reMainTableList.get(0).settSchool(mt.gettSchool());
                reMainTableList.get(0).settPhone(mt.gettPhone());
                reMainTableList.get(0).settFax(mt.gettPhone());
                reMainTableList.get(0).settName(mt.gettName());
                reMainTableList.get(0).setYear(mt.getYear());
                mt = reMainTableList.get(0);
            }
            int longA = 0;
            int longB = 0;
            int longC = 0;
            int longTotal = 0;
            int shortA = 0;
            int shortB = 0;
            int shortC = 0;
            int shortTotal = 0;
            int volA = 0;
            int volB = 0;
            int volC = 0;
            int volTotal = 0;
            int localA = 0;
            int localB = 0;
            int localC = 0;
            int localD = 0;
            int total  = 0;
            ArrayList<PerTable> perTables = deal_Per_String(per_tables);
            for (PerTable perTable:perTables
                 ) {
                longA+=perTable.getLongA();
                longB+=perTable.getLongB();
                longC+=perTable.getLongC();
                longTotal+=perTable.getLongTotal();
                shortA+=perTable.getShortA();
                shortB+=perTable.getShortB();
                shortC+=perTable.getShortC();
                shortTotal+=perTable.getShortTotal();
                volA+=perTable.getVolA();
                volB+=perTable.getVolB();
                volC+=perTable.getVolC();
                volTotal+=perTable.getVolTotal();
                localA+=perTable.getLocalA();
                localB+=perTable.getLocalB();
                localC+=perTable.getLocalC();
                localD+=perTable.getLocalD();
                total+=perTable.getTotal();
            }
            mt.setLongA(longA);
            mt.setLongB(longB);
            mt.setLongC(longC);
            mt.setLongTotal(longTotal);
            mt.setShortA(shortA);
            mt.setShortB(shortB);
            mt.setShortC(shortC);
            mt.setShortTotal(shortTotal);
            mt.setVolA(volA);
            mt.setVolB(volB);
            mt.setVolC(volC);
            mt.setVolTotal(volTotal);
            mt.setLocalA(localA);
            mt.setLocalB(localB);
            mt.setLocalC(localC);
            mt.setLocalD(localD);
            mt.setTotal(total);
            mt.setuCode(code);
            mt.setYear(year);
            //设置成已经暂存
            mt.setState(1);
            mt.setProUCode(code/1000);
            if (mt.gettId()==0){
                baseDAO.clear();
                baseDAO.save(mt);
            }else{
                baseDAO.clear();
                baseDAO.update(mt);
            }
            reMainTableList = (ArrayList<MainTable>)baseDAO.findByProperties(pMap,MainTable.class);
            long t_id = reMainTableList.get(0).gettId();
            for (PerTable perTable:perTables
                    ) {
                perTable.settId(t_id);
                if(perTable.getpId()==0){
                    baseDAO.clear();
                    baseDAO.save(perTable);
                }else{
                    baseDAO.clear();
                    baseDAO.update(perTable);
                }

            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    @Override
    public int table_up(MainTable mt, String[] per_tables, long code) {
        try {
            HashMap<String , Object> pMap = new HashMap<>();
            int year = mt.getYear();
            pMap.put("uCode",code);
            pMap.put("year",year);
            ArrayList<MainTable> reMainTableList = (ArrayList<MainTable>)baseDAO.findByProperties(pMap,MainTable.class);
            if(reMainTableList==null||reMainTableList.size()<1){
            }else if(reMainTableList.get(0).getState()==2){
                return -1;
            } else{
                //mt.settId(reMainTableList.get(0).gettId());
                reMainTableList.get(0).settProvince(mt.gettProvince());
                reMainTableList.get(0).settSchool(mt.gettSchool());
                reMainTableList.get(0).settPhone(mt.gettPhone());
                reMainTableList.get(0).settFax(mt.gettPhone());
                reMainTableList.get(0).settName(mt.gettName());
                reMainTableList.get(0).setYear(mt.getYear());
                mt = reMainTableList.get(0);
            }
            int longA = 0;
            int longB = 0;
            int longC = 0;
            int longTotal = 0;
            int shortA = 0;
            int shortB = 0;
            int shortC = 0;
            int shortTotal = 0;
            int volA = 0;
            int volB = 0;
            int volC = 0;
            int volTotal = 0;
            int localA = 0;
            int localB = 0;
            int localC = 0;
            int localD = 0;
            int total  = 0;
            ArrayList<PerTable> perTables = deal_Per_String(per_tables);
            for (PerTable perTable:perTables
                    ) {
                longA+=perTable.getLongA();
                longB+=perTable.getLongB();
                longC+=perTable.getLongC();
                longTotal+=perTable.getLongTotal();
                shortA+=perTable.getShortA();
                shortB+=perTable.getShortB();
                shortC+=perTable.getShortC();
                shortTotal+=perTable.getShortTotal();
                volA+=perTable.getVolA();
                volB+=perTable.getVolB();
                volC+=perTable.getVolC();
                volTotal+=perTable.getVolTotal();
                localA+=perTable.getLocalA();
                localB+=perTable.getLocalB();
                localC+=perTable.getLocalC();
                localD+=perTable.getLocalD();
                total+=perTable.getTotal();
            }
            mt.setLongA(longA);
            mt.setLongB(longB);
            mt.setLongC(longC);
            mt.setLongTotal(longTotal);
            mt.setShortA(shortA);
            mt.setShortB(shortB);
            mt.setShortC(shortC);
            mt.setShortTotal(shortTotal);
            mt.setVolA(volA);
            mt.setVolB(volB);
            mt.setVolC(volC);
            mt.setVolTotal(volTotal);
            mt.setLocalA(localA);
            mt.setLocalB(localB);
            mt.setLocalC(localC);
            mt.setLocalD(localD);
            mt.setTotal(total);
            mt.setuCode(code);
            mt.setYear(year);
            //设置成已经提交
            mt.setState(2);
            mt.setProUCode(code/1000);
            if (mt.gettId()==0){
                baseDAO.clear();
                baseDAO.save(mt);
            }else{
                baseDAO.clear();
                baseDAO.update(mt);
            }
            reMainTableList = (ArrayList<MainTable>)baseDAO.findByProperties(pMap,MainTable.class);
            long t_id = reMainTableList.get(0).gettId();
            for (PerTable perTable:perTables
                    ) {
                perTable.settId(t_id);
                if(perTable.getpId()==0){
                    baseDAO.clear();
                    baseDAO.save(perTable);
                }else{
                    baseDAO.clear();
                    baseDAO.update(perTable);
                }

            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }
    @Override
    public ArrayList<PerTable> deal_Per_String(String [] per_tables)throws  Exception{
        ArrayList<PerTable> reList = new ArrayList<>();
        for (String s:per_tables
             ) {
            if ("-1_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0".equals(s)){
                //说明这一行就没有填内容
            }else{
                reList.add(str_to_pertable(s));
            }
        }
        return reList;
    }

    private PerTable str_to_pertable(String s) throws Exception{
        System.out.println(s);
        PerTable pt = new PerTable();
        pt.setpId(0);
        String [] p = s.split("_");

       for(int i =1 ; i< p.length ; i++){
           if(i==1){
               pt.setLongA(Integer.parseInt(p[i]));
           }
           if(i==2){
               pt.setLongB(Integer.parseInt(p[i]));
           }
           if(i==3){
               pt.setLongC(Integer.parseInt(p[i]));
           }
           if(i==4){
               pt.setLongTotal(Integer.parseInt(p[i]));
           }
           if(i==5){
               pt.setShortA(Integer.parseInt(p[i]));
           }if(i==6){
               pt.setShortB(Integer.parseInt(p[i]));
           }
           if(i==7){
               pt.setShortC(Integer.parseInt(p[i]));
           }if(i==8){
               pt.setShortTotal(Integer.parseInt(p[i]));
           }if(i==9){
               pt.setVolA(Integer.parseInt(p[i]));
           }if(i==10){
               pt.setVolB(Integer.parseInt(p[i]));
           }
           if(i==11){
               pt.setVolC(Integer.parseInt(p[i]));
           }
           if(i==12){
               pt.setVolTotal(Integer.parseInt(p[i]));
           }
           if(i==13){
               pt.setLocalA(Integer.parseInt(p[i]));
           }
           if(i==14){
               pt.setLocalB(Integer.parseInt(p[i]));
           }
           if(i==15){
               pt.setLocalC(Integer.parseInt(p[i]));
           }
           if(i==16){
               pt.setLocalD(Integer.parseInt(p[i]));
           }
           if(i==17){
               pt.setTotal(Integer.parseInt(p[i]));
           }
       }
       if (Long.parseLong(p[0])==-1){

       }else{
           pt.setpId(Long.parseLong(p[0]));
        }

        return  pt ;
    }

    */
/**
     * 暂存
     * @param mt
     * @param per_tables
     * @param code
     * @return
     *//*

    @Override
    public int table_up_temp2(MainTable mt, String[] per_tables, long code) {
        try {
            HashMap<String , Object> pMap = new HashMap<>();
            int year = mt.getYear();
            pMap.put("uCode",code);
            pMap.put("year",year);
            ArrayList<MainTable> reMainTableList = (ArrayList<MainTable>)baseDAO.findByProperties(pMap,MainTable.class);
            if(reMainTableList==null||reMainTableList.size()<1){
                int longA = 0;
                int longB = 0;
                int longC = 0;
                int longTotal = 0;
                int shortA = 0;
                int shortB = 0;
                int shortC = 0;
                int shortTotal = 0;
                int volA = 0;
                int volB = 0;
                int volC = 0;
                int volTotal = 0;
                int localA = 0;
                int localB = 0;
                int localC = 0;
                int localD = 0;
                int total  = 0;
                ArrayList<PerTable> perTables = deal_Per_String(per_tables);
                for (PerTable perTable:perTables
                        ) {
                    longA+=perTable.getLongA();
                    longB+=perTable.getLongB();
                    longC+=perTable.getLongC();
                    longTotal+=perTable.getLongTotal();
                    shortA+=perTable.getShortA();
                    shortB+=perTable.getShortB();
                    shortC+=perTable.getShortC();
                    shortTotal+=perTable.getShortTotal();
                    volA+=perTable.getVolA();
                    volB+=perTable.getVolB();
                    volC+=perTable.getVolC();
                    volTotal+=perTable.getVolTotal();
                    localA+=perTable.getLocalA();
                    localB+=perTable.getLocalB();
                    localC+=perTable.getLocalC();
                    localD+=perTable.getLocalD();
                    total+=perTable.getTotal();
                }
                mt.setLongA(longA);
                mt.setLongB(longB);
                mt.setLongC(longC);
                mt.setLongTotal(longTotal);
                mt.setShortA(shortA);
                mt.setShortB(shortB);
                mt.setShortC(shortC);
                mt.setShortTotal(shortTotal);
                mt.setVolA(volA);
                mt.setVolB(volB);
                mt.setVolC(volC);
                mt.setVolTotal(volTotal);
                mt.setLocalA(localA);
                mt.setLocalB(localB);
                mt.setLocalC(localC);
                mt.setLocalD(localD);
                mt.setTotal(total);
                mt.setuCode(code);
                mt.setYear(year);
                //设置成已经暂存
                mt.setState(1);
                mt.setProUCode(code/1000);
                baseDAO.save(mt);
                reMainTableList = (ArrayList<MainTable>)baseDAO.findByProperties(pMap,MainTable.class);
                long t_id = reMainTableList.get(0).gettId();
                for (PerTable perTable:perTables
                        ) {
                    perTable.settId(t_id);
                    if(perTable.getpId()==0){
                        baseDAO.clear();
                        baseDAO.save(perTable);
                    }else{
                        baseDAO.clear();
                        baseDAO.update(perTable);
                    }
                }
                return 1;
            }else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    */
/**
     * tijiao
     * @param mt
     * @param per_tables
     * @param code
     * @return
     *//*

    @Override
    public int table_up2(MainTable mt, String[] per_tables, long code) {
        try {
            HashMap<String , Object> pMap = new HashMap<>();
            int year = mt.getYear();
            pMap.put("uCode",code);
            pMap.put("year",year);
            ArrayList<MainTable> reMainTableList = (ArrayList<MainTable>)baseDAO.findByProperties(pMap,MainTable.class);
            if(reMainTableList==null||reMainTableList.size()<1){
                int longA = 0;
                int longB = 0;
                int longC = 0;
                int longTotal = 0;
                int shortA = 0;
                int shortB = 0;
                int shortC = 0;
                int shortTotal = 0;
                int volA = 0;
                int volB = 0;
                int volC = 0;
                int volTotal = 0;
                int localA = 0;
                int localB = 0;
                int localC = 0;
                int localD = 0;
                int total  = 0;
                ArrayList<PerTable> perTables = deal_Per_String(per_tables);
                for (PerTable perTable:perTables
                        ) {
                    longA+=perTable.getLongA();
                    longB+=perTable.getLongB();
                    longC+=perTable.getLongC();
                    longTotal+=perTable.getLongTotal();
                    shortA+=perTable.getShortA();
                    shortB+=perTable.getShortB();
                    shortC+=perTable.getShortC();
                    shortTotal+=perTable.getShortTotal();
                    volA+=perTable.getVolA();
                    volB+=perTable.getVolB();
                    volC+=perTable.getVolC();
                    volTotal+=perTable.getVolTotal();
                    localA+=perTable.getLocalA();
                    localB+=perTable.getLocalB();
                    localC+=perTable.getLocalC();
                    localD+=perTable.getLocalD();
                    total+=perTable.getTotal();
                }
                mt.setLongA(longA);
                mt.setLongB(longB);
                mt.setLongC(longC);
                mt.setLongTotal(longTotal);
                mt.setShortA(shortA);
                mt.setShortB(shortB);
                mt.setShortC(shortC);
                mt.setShortTotal(shortTotal);
                mt.setVolA(volA);
                mt.setVolB(volB);
                mt.setVolC(volC);
                mt.setVolTotal(volTotal);
                mt.setLocalA(localA);
                mt.setLocalB(localB);
                mt.setLocalC(localC);
                mt.setLocalD(localD);
                mt.setTotal(total);
                mt.setuCode(code);
                mt.setYear(year);
                mt.setProUCode(code/1000);
                //设置成已经提交
                mt.setState(2);
                baseDAO.save(mt);
                reMainTableList = (ArrayList<MainTable>)baseDAO.findByProperties(pMap,MainTable.class);
                long t_id = reMainTableList.get(0).gettId();
                for (PerTable perTable:perTables
                        ) {
                    perTable.settId(t_id);
                    if(perTable.getpId()==0){
                        baseDAO.clear();
                        baseDAO.save(perTable);
                    }else{
                        baseDAO.clear();
                        baseDAO.update(perTable);
                    }
                }
                return 1;
            }else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }


    @Override
    public int cancel(long code , String form_cancel_text) {
        try {
            MainTable mainTable = baseDAO.findById(code , MainTable.class);
            mainTable.setState(3);
            mainTable.settText(form_cancel_text);
            baseDAO.update(mainTable);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ArrayList<String> u_names(ArrayList<MainTable> main) {
        ArrayList<String> names = new ArrayList<>();
        for (MainTable mt :main
             ) {
            User user = baseDAO.findById(mt.getuCode(),User.class);
            names.add(user.getuName());
        }
        return names;
    }
}
*/
