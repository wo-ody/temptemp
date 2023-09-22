package user.dao;

import user.dto.UserDto;

public interface UserDao {
    int userRegister(UserDto userDto);
    boolean isEmailUnique(String userEmail);
}