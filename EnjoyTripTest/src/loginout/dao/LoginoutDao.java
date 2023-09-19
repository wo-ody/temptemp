package loginout.dao;

import dto.UserDto;

public interface LoginoutDao {
	UserDto login(String userEmail);
}
