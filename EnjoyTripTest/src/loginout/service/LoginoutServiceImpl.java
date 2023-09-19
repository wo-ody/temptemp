package loginout.service;

import dto.UserDto;
import loginout.dao.LoginoutDao;
import loginout.dao.LoginoutDaoImpl;

public class LoginoutServiceImpl implements LoginoutService{

	private LoginoutDao loginoutDao = new LoginoutDaoImpl();
	
	@Override
	public UserDto login(String userEmail, String userPassword) {
		UserDto userDto = loginoutDao.login(userEmail);
		
		if(userDto != null && userDto.getUserPassword().equals(userPassword)) {
			// password invalidation
			userDto.setUserPassword(null);
			return userDto;
		}
		return null; // userEmail 이 올바르지 않거나, userPassword 가 올바르지 않은 2가지 모두 포함.
	}

}
