package com.hbwl.service;


import com.hbwl.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//实现UserDetailsService接口
@Component
public class UserService implements UserDetailsService {
    //模拟数据库数据
    static Map<String,com.hbwl.pojo.User> userMap=new HashMap<>();
    static {
        com.hbwl.pojo.User user1=new User();
        user1.setUsername("tony");
        user1.setPassword("$2a$10$RJ6A.jd1tGWYPExPKINkJ.o6X2hl81AL8xYxm0yf5W3kGhLrEy8Gu");


        user1.setPhoneNumber("321");
        com.hbwl.pojo.User user2=new User();
        user2.setUsername("jack");
        user2.setPassword("$2a$10$DubL0vTzkIBIedWxhBLB3OnUJ1Z1Q.YkHfTvnScZp.O1oDoUbCXLe");
        user2.setPhoneNumber("321");
        //通过userName封装为map的key
        userMap.put(user1.getUsername(),user1);
        userMap.put(user2.getUsername(),user2);
    }

    /**
     * 根据用户名加载用户信息，用于授权操作
     * @param username the username identifying the user whose data is required.
     *
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //模拟数据库拿到该用户对象
        User userInDb = userMap.get(username);
        //判断数据库用户名是否存在
        if (userInDb ==null) {
            //可根据具体情况，抛出异常或处理
            return null;
        }
        //模拟数据库密码,此时得注意springSecurity的加密方式
        String password = userInDb.getPassword();
        //给该用户授权
        List<GrantedAuthority> authorityList=new ArrayList<>();
        //权限名为add，根据自己定义
        authorityList.add(new SimpleGrantedAuthority("add"));
        authorityList.add(new SimpleGrantedAuthority("delete"));
        authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        //返回springSecurity的User对象用于验证
        //public User(String username, String password,Collection<? extends GrantedAuthority> authorities)
        //username: 需要验证的用户username    password：密码     authorities权限信息集合
        return new org.springframework.security.core.userdetails.User(username,password,authorityList);
    }
}
