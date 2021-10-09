package net.wangjifeng.watermelon.test;

/**
 * @author: wjf
 * @date: 2021/9/28 15:28
 *
 * 测试用户对象。
 */
public class User implements TestInterface {

    private String username;

    private Integer age;

    private String sex;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
