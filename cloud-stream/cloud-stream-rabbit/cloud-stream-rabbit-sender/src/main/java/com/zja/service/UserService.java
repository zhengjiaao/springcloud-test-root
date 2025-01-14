/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2023-07-24 17:11
 * @Since:
 */
package com.zja.service;

import com.zja.model.dto.UserDTO;
import com.zja.model.request.UserRequest;

/**
 * 用户服务
 *
 * @author: zhengja
 * @since: 2023/07/24 17:11
 */
public interface UserService {

    /**
     * 新增用户
     */
    UserDTO save(UserRequest request);

    /**
     * 更新用户
     */
    UserDTO update(String id, UserRequest request);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteById(String id);
}