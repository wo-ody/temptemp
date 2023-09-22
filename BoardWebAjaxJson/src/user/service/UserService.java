
package user.service;

import user.dto.UserDto;

public interface UserService {
    int userRegister(UserDto userDto);
    boolean isEmailUnique(String userEmail);
}