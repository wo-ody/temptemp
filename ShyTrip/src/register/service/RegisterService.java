package register.service;

import dto.RegisterDto;

public interface RegisterService {
	int createUser(RegisterDto registerDto);
}
