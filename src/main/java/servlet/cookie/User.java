package servlet.cookie;

import java.util.Arrays;

/**
 * @author JellyfishMIX
 * @date 2021/6/19 11:50
 */
public class User {
    // 私有参数跟页面实体类的参数保持一致，
    private String username;
    private String password;
    private String remember;

    // 无参构造方法
    public User() {
        super();
    }

    // 有参构造。
    // JavaBean规范中规定的，必须有一个公有的无参构造。这一点很重要，如果不写，后面的EL表达式就会出错，因为EL表达式默认调用无参构造。
    // 后面的很多框架，默认都是调用无参构造。为了后面不出错，建议遵守规范，写上无参构造。
    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    // 重写toString()方法


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", remember='" + remember + '\'' +
                '}';
    }

    // get, set方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }
}