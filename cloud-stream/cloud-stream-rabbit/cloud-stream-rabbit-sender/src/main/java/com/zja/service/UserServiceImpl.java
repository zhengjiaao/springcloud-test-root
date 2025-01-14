/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-24 17:11
 * @Since:
 */
package com.zja.service;

import com.zja.dao.UserDao;
import com.zja.model.dto.UserDTO;
import com.zja.model.entity.User;
import com.zja.model.request.UserRequest;
import com.zja.rabbit.UserRabbitSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author: zhengja
 * @since: 2023/07/24 17:11
 */
@Slf4j
@Validated
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRabbitSender userRabbitSender;

    @Override
    public UserDTO save(UserRequest request) {
        User saveUser = User.builder()
                .userName(request.getUserName())
                .password(request.getPassword()).build();
        // 保存用户入库
        User user = userDao.save(saveUser);
        log.info("save user:{}", user);

        // 发送至 mq 消息中间件，通知其他系统
        userRabbitSender.addUser(user);

        // 返回数据
        return new UserDTO(user.getUserId(), user.getUserName(), user.getPassword());
    }

    @Override
    public UserDTO update(String id, UserRequest request) {
        // 更新数据库中用户
        User user = userDao.save(new User(id, request.getUserName(), request.getPassword()));
        log.info("update user:{}", user);

        // 发送至 mq 消息中间件，通知其他系统
        userRabbitSender.updateUser(user);

        // 返回数据
        return new UserDTO(id, request.getUserName(), request.getPassword());
    }

    @Override
    public void deleteById(String id) {
        // 删除数据库中用户
        userDao.deleteById(id);
        log.info("deleteById:{}", id);

        // 发送至 mq 消息中间件，通知其他系统
        userRabbitSender.deleteUser(id);
    }

}