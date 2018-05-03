package com.young.services.teacher.impl;

import com.young.entity.TableCopy;
import com.young.json.BaseJson;
import com.young.services.common.impl.CommServiceImpl;
import com.young.services.teacher.ITeacherService;
import com.young.vo.TableVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service(value = "teacherService")
public class TeacherServiceImpl extends CommServiceImpl implements ITeacherService {
    @Override
    public BaseJson up_table(String[] p, long u_id, int if_temp,String year) {
        BaseJson queryjson = new BaseJson();
        try {
            Map map = new HashMap();
            map.put("uId",u_id);
            map.put("tUserInputYear",year);
            ArrayList<TableCopy> tables = (ArrayList<TableCopy>)baseDAO.findByProperties(map,TableCopy.class);

            if (tables.size()<=0){
                TableCopy table = new TableCopy();
                table =  change_table(table,p,if_temp,u_id,year);
                baseDAO.save(table);
                queryjson.setErrno("1");
                queryjson.setErrmsg("成功保存");
            }else{
                TableCopy table = tables.get(0);
                if (table.gettIfTemp()==1){
                    table =  change_table(table,p,if_temp,u_id,year);
                    baseDAO.update(table);
                    queryjson.setErrno("1");
                    queryjson.setErrmsg("成功保存");
                }else{
                    queryjson.setErrno("2");
                    queryjson.setErrmsg("已经提交");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            queryjson.setErrno("-1");
            queryjson.setErrmsg("出错");
        }
        return  queryjson;
    }

    @Override
    public TableCopy get_table_u_id(long u_id) {
        Map map = new HashMap();
        map.put("uId",u_id);
        map.put("tUserInputYear",(new Date(System.currentTimeMillis()).getYear()+1900)+"");
        ArrayList<TableCopy> tables = (ArrayList<TableCopy>)baseDAO.findByProperties(map,TableCopy.class);
        if (tables.size()<=0){
            return null;
        }else{
            return tables.get(0);
        }
    }

    private TableCopy change_table(TableCopy table, String p[], int if_temp, long u_id,String year){
        System.out.println(Arrays.toString(p));
        table.settName(p[0]);
        table.settIdCard(p[1]);
        table.settChinaQ(p[2]);
        table.settChinaW(p[3]);
        table.settChinaE(p[4]);
        table.settWorldQ(p[5]);
        table.settWorldW(p[6]);
        table.settWorldE(p[7]);
        table.settWorldQE(p[8]);
        table.settWorldWE(p[9]);
        table.settWorldEE(p[10]);
        table.settStartY(p[11]);
        table.settStartM(p[12]);
        table.settStartD(p[13]);
        table.settEndY(p[14]);
        table.settEndM(p[15]);
        table.settEndD(p[16]);
        table.settPhone(p[17]);
        table.settMail(p[18]);
        table.settWho(p[19]);
        table.settZuoji(p[20]);
        table.settAddress(p[21]);
        table.settEms(p[22]);

        if (isNumeric(p[23])){
            table.settIfGo(Integer.parseInt(p[23]));
        }else{
            table.settIfGo(0);
        }
        System.out.println(p.length);
        if (p.length<=24){
            table.settGoReason("  ");
        }else{
            table.settGoReason(p[24]);
        }
        if (table.gettIfQOk()==-1)table.settIfQOk(1);
        if (table.gettIfWOk()==-1)table.settIfWOk(1);
        if (table.gettIfEOk()==-1)table.settIfEOk(1);
        if (table.gettIfQOk()==3)table.settIfQOk(1);
        if (table.gettIfWOk()==3)table.settIfWOk(1);
        if (table.gettIfEOk()==3)table.settIfEOk(1);
        /*table.settIfQOk(1);
        table.settIfWOk(1);
        table.settIfEOk(1);*/
        table.setuId(u_id);
        table.settIfTemp(if_temp);
        table.settUserInputYear(year);
        return table;
    }
     public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    @Override
    public ArrayList<TableVo> get_tables_u_id(long u_id) {
        ArrayList<TableCopy> tables = (ArrayList<TableCopy>)baseDAO.findByProperty("uId",u_id,TableCopy.class);
        ArrayList<TableVo> re_tables = new ArrayList<>();
        for (TableCopy t:tables
             ) {
            TableVo tv = new TableVo();
            tv.settId(t.gettId());
            tv.settIfQOk(t.gettIfQOk());
            tv.settIfWOk(t.gettIfWOk());
            tv.settIfEOk(t.gettIfEOk());
            tv.settIfTemp(t.gettIfTemp());
            tv.settUserInputYear(t.gettUserInputYear());
            re_tables.add(tv);
        }
        return re_tables;
    }

    @Override
    public TableCopy get_table_by_id(long t_id) {
        TableCopy tc = baseDAO.findById(t_id,TableCopy.class);
        return tc;
    }


}
