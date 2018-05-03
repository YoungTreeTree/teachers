package com.young.services.le_r;

import com.young.entity.TableCopy;
import com.young.entity.User;
import com.young.vo.TableExtendVo;
import com.young.vo.YearVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ILerService {
    ArrayList<YearVo> get_years();//用于单位查询下属年份
    ArrayList<User> get_users_by_type(int type);
    ArrayList<TableExtendVo> get_tables(String year , long u_id, User user,int type);//这里的u_id是单位id   这个函数查询的是该下属单位的所有教师的表格
    int total_register(List<Map<String, Object>> list,int type);
    int cancel(long code , String form_cancel_text);
    int up(long code , String form_cancel_text,String tNumber,String dateY,String dateM,String dateD);
    ArrayList<TableCopy> get_table_by_year_user(int type,String year, User user);
}
