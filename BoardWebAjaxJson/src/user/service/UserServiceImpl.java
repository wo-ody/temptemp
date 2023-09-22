package user.service;

import user.dao.UserDao;
import user.dao.UserDaoImpl;
import user.dto.UserDto;

public class UserServiceImpl implements UserService{

    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {}

	public static UserServiceImpl getInstance() {
	    return instance;
	}
	
	UserDao userDao = UserDaoImpl.getInstance();
	
	@Override
	public int userRegister(UserDto userDto) {
	    return userDao.userRegister(userDto);
	}
	
	@Override
	public boolean isEmailUnique(String userEmail) {
	    return userDao.isEmailUnique(userEmail);
	}
}