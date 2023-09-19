package register.service;

import dto.RegisterDto;
import register.dao.RegisterDao;
import register.dao.RegisterDaoImpl;

public class RegisterServiceImpl implements RegisterService{
	private RegisterDao registerDao = new RegisterDaoImpl();
	
	@Override
	public int createUser(RegisterDto registerDto) {
		return registerDao.createUser(registerDto);
	}
}
