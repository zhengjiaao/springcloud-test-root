/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-21 15:42
 * @Since:
 */
package com.zja.provider;

import com.zja.api.UserDTO;
import com.zja.api.UserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 *
 */
@DubboService
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO getUserInfo() {
        return new UserDTO("李四",18);
    }
}
