package com.cmz.web1.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cmz.web1.domain.User;

public interface UserDao {
	
	@Update("UPDATE user SET uname=#{uname} where id=#{id}")
	public int update(@Param("uname")String uname,@Param("id")int id);
	
	@Select("SELECT count(1) FROM user")
	public int count(@Param("a")String a);
	
	@Select("SELECT * FROM user LIMIT 1")
	public User getUser();
}
