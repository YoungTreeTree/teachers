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
			if (old_pw==new_pw){
				return -3;
			}
			User user = baseDAO.findById(code,User.class);
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
	public User check_login(String code, String pw) {
		if (code.startsWith("sc")){
			code = code.replace("sc","");
			return get_user(User1.class,code,pw);
		}
		if (code.startsWith("st")){
			code = code.replace("st","");
			return get_user2(User1.class,code,pw);
		}
		if (code.startsWith("te")){
			code = code.replace("te","");
			return get_user3(User2.class,code,pw);
		}


		int user_type = 0;
		HashMap mp = new HashMap();
		String q="-",w="-",e="-";
		if (code.equals("1722")){
			user_type = 4;
			mp.put("uId",Long.parseLong(code));
			mp.put("uPw",pw);
			mp.put("uType",user_type);
		}else if(code.length()==3){
			user_type = 3;
			e=code.substring(0,3);


			mp.put("uPw",pw);
			mp.put("uType",user_type);
			mp.put("uCodeQ","999");
			mp.put("uCodeW","999");
			mp.put("uCodeE",e);

		}else if(code.length()==6){
			user_type = 2;
			e=code.substring(0,3);
			w=code.substring(3,6);

			mp.put("uPw",pw);
			mp.put("uType",user_type);
			mp.put("uCodeQ","999");
			mp.put("uCodeW",w);
			mp.put("uCodeE",e);

		}else if(code.length()==9){
			user_type = 1;
			e=code.substring(0,3);
			w=code.substring(3,6);
			q=code.substring(6,9);

			mp.put("uPw",pw);
			mp.put("uType",user_type);
			mp.put("uCodeQ",q);
			mp.put("uCodeW",w);
			mp.put("uCodeE",e);
		}else{
			return null;
		}
		ArrayList<User> users= (ArrayList<User>)baseDAO.findByProperties(mp, User.class);
		if (users.size()<=0){
			return null;
		}else{
			return users.get(0);
		}
	}

	public User get_user(Class T,String code, String pw){
		if (T==User1.class){
			HashMap mp = new HashMap();
			mp.put("u1CodeQ",code);
			mp.put("u1States",1);
			mp.put("u1Pw",pw);
			ArrayList<User1> users= (ArrayList<User1>)baseDAO.findByProperties(mp, User1.class);
			if (users.size()<=0){
				return null;
			}else{
				return  user1Touser( users.get(0));
			}
		}
		return null;
	}

	public User get_user2(Class T,String code, String pw){
		if (T==User1.class){
			HashMap mp = new HashMap();
			mp.put("u1CodeW",code);
			mp.put("u1Pw",pw);
			ArrayList<User1> users= (ArrayList<User1>)baseDAO.findByProperties(mp, User1.class);
			if (users.size()<=0){
				return null;
			}else{
				return  user1Touser( users.get(0));
			}
		}
		return null;
	}

	public User get_user3(Class T,String code, String pw){
		if (T==User2.class){
			HashMap mp = new HashMap();
			mp.put("u2Phone",code);
			mp.put("u2CodePw",pw);
			ArrayList<User2> users= (ArrayList<User2>)baseDAO.findByProperties(mp, User2.class);
			if (users.size()<=0){
				return null;
			}else{
				return  user2Touser( users.get(0));
			}
		}
		return null;
	}


	@Override
	public int register(User1 user1) {
		HashMap mp = new HashMap();
		mp.put("u1CodeQ",user1.getU1Phone());
		mp.put("u1Type",5);
		ArrayList<User1> users= (ArrayList<User1>)baseDAO.findByProperties(mp, User1.class);
		if (users.size()>0){
			return -1;
		}
		user1.setU1Type(5);
		user1.setU1Pw("123456");
		user1.setU1States(2);
		user1.setU1CodeQ(user1.getU1Phone());
		baseDAO.save(user1);
		return 1;
	}
	public int register2(User2 user2) {
		HashMap mp = new HashMap();
		mp.put("u2Phone",user2.getU2Phone());
		mp.put("u2CodeType",7);
		ArrayList<User2> users= (ArrayList<User2>)baseDAO.findByProperties(mp, User2.class);
		if (users.size()>0){
			return -1;
		}
		user2.setU2CodePw("123456");
		user2.setU2CodeType(7);
		user2.setU2States(2);
		baseDAO.save(user2);
		return 1;
	}

	public User user1Touser(User1 u){
		User user = new User();
		user.setuId(u.getU1Id());
		user.setuCodeQ(u.getU1CodeQ());
		user.setuCodeW(u.getU1CodeW());
		user.setuCodeE(u.getU1CodeE());
		user.setuName(u.getU1ScName());
		user.setuV2Name(u.getU1UName());
		user.setuType(u.getU1Type());
		return user;
	}

	public User user2Touser(User2 u){
		User user = new User();
		user.setuId(u.getU2Id());
		user.setuCodeQ(u.getU2CodeQ());
		user.setuCodeW(u.getU2CodeW());
		user.setuCodeE(u.getU2CodeE());
		user.setuName(u.getU2Name());
		user.setuV2Phone(u.getU2Phone());
		user.setuV2Name(u.getU2IdCode());
		user.setuType(u.getU2CodeType());
		return user;
	}



}
