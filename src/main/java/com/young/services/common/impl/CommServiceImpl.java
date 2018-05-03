package com.young.services.common.impl;
import javax.annotation.Resource;

import com.young.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.young.daos.BaseDAO;
import com.young.services.common.ICommService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value="commService")
public class CommServiceImpl   implements ICommService {

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	protected BaseDAO baseDAO;

	@Override
	public <T> T getEntity(String id, Class<T> cls) throws Exception {
		// TODO Auto-generated method stub
		T t=baseDAO.findById(Long.parseLong(id), cls);
		return t;
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
	public int change_pw2(String old_pw, String new_pw, long code,String name , String phone , String mail) {
		try {
			if(old_pw==null||new_pw==null){
				return -1;
			}
			User user = baseDAO.findById(code,User.class);
            if (!phone.equals(user.getuV2Phone())){
                ArrayList<User>users = (ArrayList<User>)baseDAO.findByProperty("uV2Phone",phone,User.class);
                if (users.size()>0){
                    return -4;
                }
            }
			if(old_pw.equals(user.getuPw())){
				user.setuPw(new_pw);
				user.setuV2Name(name);
				user.setuV2Phone(phone);
				user.setuV2Email(mail);
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
	public int reset_pw(long u_id) {
		try {
			User user = baseDAO.findById(u_id,User.class);
			user.setuPw("123456");
			baseDAO.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public TableCopy get_table_by_id(long t_id) {
		TableCopy tc = baseDAO.findById(t_id,TableCopy.class);
		return tc;
	}

	@Override
	public TableCopyCopy get_table_by_id2(long t_id) {
		TableCopyCopy tc = baseDAO.findById(t_id,TableCopyCopy.class);
		return tc;
	}

	public TableCopyCopyCopy get_table_by_id5(long t_id) {
		TableCopyCopyCopy tc = baseDAO.findById(t_id,TableCopyCopyCopy.class);
		return tc;
	}

	@Override
	public User check_login(String code, String pw,int type) {
		HashMap mp = new HashMap();
		List<User> re = new ArrayList<>();
		if (type == 1){
			mp.put("uType",1);
			mp.put("uV2Phone",code);
			mp.put("uPw",pw);
			re= baseDAO.findByProperties(mp,User.class);
		}else if(type ==2){
			mp.put("uType",2);
			mp.put("uCodeQ",code);
			mp.put("uPw",pw);
			List<User> re1 =  baseDAO.findByProperties(mp,User.class);

			mp.replace("uType",3);
			List<User> re2 =  baseDAO.findByProperties(mp,User.class);

			mp.replace("uType",4);
			List<User> re3 =  baseDAO.findByProperties(mp,User.class);

			re.addAll(re1);
			re.addAll(re2);
			re.addAll(re3);
		}else{
			mp.put("uType",5);
			mp.put("uV2Phone",code);
			mp.put("uPw",pw);
			re= baseDAO.findByProperties(mp,User.class);
		}
		if (re.size()>0){
			return re.get(0);
		}else{
			return null;
		}
	}






	@Override
	public int register(User user , int type,String q) {
		HashMap mp = new HashMap();
		mp.put("uV2Phone",user.getuV2Phone());
		ArrayList<User> users= (ArrayList<User>)baseDAO.findByProperties(mp, User.class);
		if (users.size()>0){
			return -1;
		}
        user.setuCodeQ(q);
		user.setuType(1);
		user.setuPw("123456");
		baseDAO.save(user);
		return 1;
	}

	public ArrayList<User> get_users_by_type(int type) {
		Map map = new HashMap();
		map.put("uType",type);
		ArrayList<User> users = (ArrayList<User>)baseDAO.findByProperties(map,User.class);
		return users;
	}
}
