package com.young.services.le_c;

import com.young.entity.TableCopy;
import com.young.entity.User;
import com.young.vo.TableExtendVo;
import com.young.vo.YearVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ILecService {
    ArrayList<TableCopy> get_table_by_year_user(String year, User user);
    ArrayList<TableExtendVo> get_tables(String year, long u_id, User user);
    ArrayList<User> get_users_by_pre_code(String phone);//用于单位查询下属教师
    ArrayList<YearVo> get_years();//用于单位查询下属年份
    int total_register(List<Map<String, Object>> list, String w, String e);
    int cancel(long code, String form_cancel_text);
    int up(long code, String form_cancel_text);
}
