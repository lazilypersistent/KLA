package com.klalibrary.serviceinterfaceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klalibrary.bean.User;
import com.klalibrary.daointerfaceimpl.UserDaoImpl;
import com.klalibrary.serviceinterface.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDaoImpl userDaoImpl;

	@Override
	public User getUserDetails(int userId) {
		return userDaoImpl.getUserDetails(userId);
	}

}
