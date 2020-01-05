package cn.kgc.service.impl;

import cn.kgc.domain.Users;
import cn.kgc.domain.UsersCondition;
import cn.kgc.domain.UsersExample;
import cn.kgc.mapper.UsersMapper;
import cn.kgc.service.UsersService;
import cn.kgc.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public PageInfo<Users> queryUserByPage(UsersCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        //查询所有用户
        UsersExample usersExample=new UsersExample();
        //封装条件
        UsersExample.Criteria criteria=usersExample.createCriteria();
        //动态查询
        if (condition.getName()!=null&&!condition.getName().isEmpty()){
            criteria.andNameLike("%"+condition.getName()+"%");
        }
        if (condition.getTel()!=null&&!condition.getTel().isEmpty()){
            criteria.andTelephoneLike("%"+condition.getTel()+"%");
        }
        List<Users> list=usersMapper.selectByExample(usersExample);
        return new PageInfo<>(list);
    }

    @Override
    public boolean checkUserName(String name) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(new Integer("0"));//保证是房东用户
        criteria.andNameEqualTo(name);
        List<Users> usersList = usersMapper.selectByExample(usersExample);
        //如果当前集合为null说明没有查询到记录，用户名可用
        if (usersList!=null&&usersList.size()!=0){
            return false;
        }
        else
        return true;
    }

    @Override
    public Integer addUser(Users users) {
        //对密码加密
        String newPassword=MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(newPassword);
        //设置房东用户
        users.setIsadmin(0);
        return usersMapper.insertSelective(users);
    }

    @Override
    public Users login(String username, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(new Integer("0"));//保证是房东用户
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> usersList = usersMapper.selectByExample(usersExample);
        if (usersList!=null&&usersList.size()>0){
            return usersList.get(0);//返回登入的人
        }
        return null;
    }
}
