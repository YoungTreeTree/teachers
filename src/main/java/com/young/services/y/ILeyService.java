package com.young.services.y;

import com.young.entity.TableCopy;
import com.young.entity.TableCopyCopy;
import com.young.json.BaseJson;
import com.young.vo.TableVo;

import java.util.ArrayList;

public interface ILeyService {
    BaseJson up_table(String p[], long u_id, int if_temp, String year);
    TableCopyCopy get_table_u_id(long u_id);
    TableCopyCopy get_table_by_id2(long t_id);
    int change_info(String old_pw,String new_pw,long u_id,String name,String phone,String mail,String address);
    ArrayList<TableVo> get_tables_u_id(long u_id);

}
