/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-11 10:45
 * @Since:
 */
package com.zja.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*@Autowired
    private UserDao userDao;*/

    /*@Autowired
    private PermissionService permissionService;*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //推荐-根据username查询数据库  获取用户详细信息 如果查询用户不存在，直接返回 null

        //测试-模拟数据
        String userName = "user";
        String password = "pass";

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;

        /*for (Role role : member.getRoles()) {
            //角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(grantedAuthority);
            //获取权限
            for (Permission permission : role.getPermissions()) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUri());
                authorities.add(authority);
            }
        }*/

        String pwd = passwordEncoder.encode(password);

        //一般直接使用 User ，也可以自定义 myUser 继承 User
        /*User user = new User(
                userName,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                grantedAuthorities);*/

   /*     MyUser user = new MyUser(
                userName,
//                password,
                pwd, // 编码后的密码
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        //扩展属性字段
        user.setMobile("158***");

        log.info("登录成功！用户: {}", JSON.toJSONString(user));
        return user;*/

        if ("admin".equalsIgnoreCase(username)) {
            User user = mockUser();
            return user;
        }
        return null;
    }

    private User mockUser() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        //模拟 数据库种必须已经加密后的密码
        String pwd = passwordEncoder.encode("123456");
        User user = new User("admin", pwd, authorities);
        return user;
    }


}
