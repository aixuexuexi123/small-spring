package yzy.springframework.test.bean;

import yzy.springframework.beans.factory.annotation.Autowired;
import yzy.springframework.beans.factory.annotation.Value;
import yzy.springframework.stereotype.Component;

import java.util.Random;

/**
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */

@Component("userService")
public class UserService implements IUserService {

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao userDao;




    public String queryUserInfo() {

        return userDao.queryUserName("10001")+","+token;
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
