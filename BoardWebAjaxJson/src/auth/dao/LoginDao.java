package auth.dao;

import user.dto.UserDto;

public interface LoginDao {
    public UserDto login(String userEmail);
}