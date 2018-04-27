package com.young.services.le_t;

import com.young.entity.TableCopy;
import com.young.entity.TableCopyCopy;
import com.young.entity.User;
import com.young.entity.User1;
import com.young.vo.TableExtendVo;
import com.young.vo.YearVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ILetService {
    ArrayList<TableCopyCopy> get_table_by_year_user(String year, User user);
    ArrayList<TableExtendVo> get_tables(String year, long u_id, User user);
    List<User>get_users_by_pre_code(String precode);//用于单位查询下属教师
    List<User1>get_users_by_pre_code1(String precode);//用于单位查询下属教师
    ArrayList<YearVo> get_years();//用于单位查询下属年份
    public int reset_pw_2(long u_id);
    int total_register(List<Map<String, Object>> list, String q);
    int cancel(long code, String form_cancel_text);
    int up(long code, String form_cancel_text);
    int change_info(String old_pw,String new_pw,long u_id,String name,String mail,String address);
}
