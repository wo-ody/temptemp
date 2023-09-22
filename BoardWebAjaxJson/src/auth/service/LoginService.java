package auth.service;

import user.dto.UserDto;

public interface LoginService {
    UserDto login(String userEmail, String userPassword);
}