package com.young.services.u;

import com.young.entity.TableCopyCopy;
import com.young.entity.TableCopyCopyCopy;
import com.young.entity.User1;
import com.young.entity.User2;
import com.young.json.BaseJson;
import com.young.services.common.impl.CommServiceImpl;
import com.young.services.y.ILeyService;
import com.young.vo.TableVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service(value = "leuService")
public class LeuServiceImpl extends CommServiceImpl  {



    public BaseJson up_table(String[] p, long u_id, int if_temp,String year) {
        BaseJson queryjson = new BaseJson();
        try {
            Map map = new HashMap();
            map.put("uId",u_id);
            map.put("tUserInputYear",year);
            ArrayList<TableCopyCopyCopy> tables = (ArrayList<TableCopyCopyCopy>)baseDAO.findByProperties(map,TableCopyCopyCopy.class);

            if (tables.size()<=0){
                TableCopyCopyCopy table = new TableCopyCopyCopy();
                table =  change_table(table,p,1,u_id,year);
                baseDAO.save(table);
                queryjson.setErrno("1");
                queryjson.setErrmsg("成功保存");
            }else{
                TableCopyCopyCopy table = tables.get(0);
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

    public TableCopyCopyCopy get_table_u_id(long u_id) {
        Map map = new HashMap();
        map.put("uId",u_id);
        map.put("tUserInputYear",(new Date(System.currentTimeMillis()).getYear()+1900)+"");
        ArrayList<TableCopyCopyCopy> tables = (ArrayList<TableCopyCopyCopy>)baseDAO.findByProperties(map,TableCopyCopyCopy.class);
        if (tables.size()<=0){
            return null;
        }else{
            return tables.get(0);
        }
    }

    private TableCopyCopyCopy change_table(TableCopyCopyCopy table, String p[], int if_temp, long u_id,String year){
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

    public ArrayList<TableVo> get_tables_u_id(long u_id) {
        ArrayList<TableCopyCopyCopy> tables = (ArrayList<TableCopyCopyCopy>)baseDAO.findByProperty("uId",u_id,TableCopyCopyCopy.class);
        ArrayList<TableVo> re_tables = new ArrayList<>();
        for (TableCopyCopyCopy t:tables
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

    public TableCopyCopyCopy get_table_by_id3(long t_id) {
        TableCopyCopyCopy tc = baseDAO.findById(t_id,TableCopyCopyCopy.class);
        return tc;
    }

    public int change_info(String old_pw, String new_pw, long u_id, String name, String phone, String mail, String address) {
        try {
            if(old_pw==null||new_pw==null){
                return -1;
            }
            if (old_pw==new_pw){
                return -3;
            }
            User2 user = baseDAO.findById(u_id,User2.class);
            if(old_pw.equals(user.getU2CodePw())){
                user.setU2CodePw(new_pw);
                user.setU2Name(name);
                user.setU2Phone(phone);
                user.setU2Mail(mail);
                user.setU2Address(address);
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
