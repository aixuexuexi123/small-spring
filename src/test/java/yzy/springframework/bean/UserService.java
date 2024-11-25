package yzy.springframework.bean;
/*
 *@author yzy
 *@Date 2024/10/8
 * */

public class UserService {
    private String uId;

    private UserDao userDao;



    public String getName() {
        return uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setName(String name) {
        this.uId = name;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String queryUserInfo() {
        return userDao.queryUserName(uId);
    }


}
