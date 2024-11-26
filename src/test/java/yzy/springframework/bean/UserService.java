package yzy.springframework.bean;
/*
 *@author yzy
 *@Date 2024/10/8
 * */

import yzy.springframework.beans.factory.DisposableBean;
import yzy.springframework.beans.factory.InitializingBean;

public class UserService implements InitializingBean, DisposableBean {
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


    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化");
    }
}
