package cn.kgc.service;

import cn.kgc.domain.House;
import cn.kgc.utils.PageUtil;
import cn.kgc.utils.SearchCondition;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface HouseService {
    Integer addHouse(House house);

    PageInfo<House> getUserById(Integer id, PageUtil pageUtil);

    //返回出租房信息
    House getHouseById(String id);

    Integer updateHouse(House house);

    //更新出租房删除的状态                        删除状态
    Integer deleteHouse(String houseId,Integer delState);

    PageInfo<House> getAllHouse(PageUtil pageUtil);

    //修改审核状态
    Integer updateState(String houseId,Integer passState);

    PageInfo<House> searchHouseByCondition(SearchCondition searchCondition);
}
