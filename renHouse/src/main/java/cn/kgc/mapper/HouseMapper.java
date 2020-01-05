package cn.kgc.mapper;

import cn.kgc.domain.House;
import cn.kgc.domain.HouseExample;
import cn.kgc.utils.SearchCondition;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询当前用户下发布的出租房
    List<House> getHouseByUser(Integer userid);

    House getHouseById(String id);

    //查询所有出租房信息(进行审核) 后台
    List<House> getAllHouse();

    List<House> searchHouseByCondition(SearchCondition searchCondition);
}