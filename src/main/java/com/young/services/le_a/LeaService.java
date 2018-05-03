package com.young.services.le_a;

import com.young.entity.TableCopy;
import com.young.json.BaseJson;
import com.young.vo.TableVo;

import java.util.ArrayList;

public interface LeaService {
    BaseJson up_table(String p[], long u_id, int if_temp, String year);
    TableCopy get_table_u_id(long u_id);
    TableCopy get_table_by_id(long t_id);
    ArrayList<TableVo> get_tables_u_id(long u_id);

}
