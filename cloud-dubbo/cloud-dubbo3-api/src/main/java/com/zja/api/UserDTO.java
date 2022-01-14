/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-21 15:39
 * @Since:
 */
package com.zja.api;

import java.io.Serializable;

/**
 *
 */
public class UserDTO implements Serializable {

    private String userName;
    private int age;

    public UserDTO() {
    }

    public UserDTO(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
