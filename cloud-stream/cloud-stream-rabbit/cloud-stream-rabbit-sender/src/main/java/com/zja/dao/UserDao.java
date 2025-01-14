/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-24 17:12
 * @Since:
 */
package com.zja.dao;

import com.zja.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 用户数据库操作 (模拟)
 *
 * @author: zhengja
 * @since: 2023/07/24 17:12
 */
@Component
public class UserDao {

    public User save(User user) {
        user.setUserId(String.valueOf(new Random().nextInt(1000)));
        return user;
    }

    public void deleteById(String id) {

    }
}
