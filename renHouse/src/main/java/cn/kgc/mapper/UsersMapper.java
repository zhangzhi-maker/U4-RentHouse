package cn.kgc.mapper;

import cn.kgc.domain.House;
import cn.kgc.domain.Users;
import cn.kgc.domain.UsersExample;
import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);


}