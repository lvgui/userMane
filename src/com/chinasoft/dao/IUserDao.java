package com.chinasoft.dao;

import com.chinasoft.entity.Users;

public interface IUserDao {

	public boolean LoginByName(Users user);
	public boolean chackByName(Users user);
}
