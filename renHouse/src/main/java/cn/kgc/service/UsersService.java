package cn.kgc.service;

import cn.kgc.domain.Users;
import cn.kgc.domain.UsersCondition;
import com.github.pagehelper.PageInfo;

public interface UsersService {
    PageInfo<Users> queryUserByPage(UsersCondition condition);

    //判断用户名是否存在
    boolean checkUserName(String name);//int也可以

    Integer addUser(Users users);

    Users login(String username,String password);
}
