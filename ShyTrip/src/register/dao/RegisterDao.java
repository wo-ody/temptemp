package register.dao;

import dto.RegisterDto;

public interface RegisterDao {
	int createUser(RegisterDto registerDto);
}
