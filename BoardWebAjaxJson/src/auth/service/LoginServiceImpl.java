
package auth.service;

import auth.dao.LoginDao;
import auth.dao.LoginDaoImpl;
import user.dto.UserDto;

public class LoginServiceImpl implements LoginService{

    private static LoginServiceImpl instance = new LoginServiceImpl();

    private LoginServiceImpl() {}


    public static LoginServiceImpl getInstance() {
        return instance;
    }

    LoginDao loginDao = LoginDaoImpl.getInstance();

    // session 에 사용자 정보를 저장하기 위해 UserDto 를 리턴
    // null 리턴이 로그인 실패를 의미
    @Override
    public UserDto login(String userEmail, String userPassword) {

        UserDto userDto = loginDao.login(userEmail);

        if( userDto != null && userDto.getUserPassword().equals(userPassword)) {
            // password 삭제
            userDto.setUserPassword(null);
            return userDto;
        }else {
            return null;
        }
    }
}