package com.young.services.le_w;

import com.young.entity.TableCopy;
import com.young.entity.User;
import com.young.vo.TableExtendVo;
import com.young.vo.YearVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ILewService {
    ArrayList<YearVo> get_years();//用于单位查询下属年份
    ArrayList<User> get_users_by_pre_code(String precode1);//用于省厅查询下属单位
    ArrayList<TableExtendVo> get_tables(String year , long u_id, User user);//这里的u_id是单位id   这个函数查询的是该下属单位的所有教师的表格
    int total_register(List<Map<String, Object>> list  , String e);
    int cancel(long code , String form_cancel_text);
    int up(long code , String form_cancel_text);
    ArrayList<TableCopy> get_table_by_year_user(String year, User user);
}
