package com.young.services.common;


import com.young.entity.TableCopy;
import com.young.entity.TableCopyCopy;
import com.young.entity.User;
import com.young.entity.User1;

/**
 * 公共服务类 文件名稱：ICommService 描述：
 * 
 * @author Zhang Xiaofeng
 * @create 2012-4-11 汇环
 */
public interface ICommService {
	
	public <T> T getEntity(String id, Class<T> cls) throws Exception;

	int change_pw(String old_pw, String new_pw, long code);
	int change_pw2(String old_pw, String new_pw, long code,String name , String phone , String mail);
	int reset_pw(long u_id);
	TableCopy get_table_by_id(long t_id);
	TableCopyCopy get_table_by_id2(long t_id);
	User check_login(String code , String pw,int type);
	int register(User user,int type,String q);

}
