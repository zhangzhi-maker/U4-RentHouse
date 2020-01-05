package cn.kgc.service.impl;

import cn.kgc.domain.House;
import cn.kgc.mapper.HouseMapper;
import cn.kgc.service.HouseService;
import cn.kgc.utils.PageUtil;
import cn.kgc.utils.SearchCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;

    @Override
    public Integer addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getUserById(Integer id, PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> houseList = houseMapper.getHouseByUser(id);
        PageInfo<House> pageInfo = new PageInfo<>(houseList);
        return pageInfo;
    }

    @Override
    public House getHouseById(String id) {
        return houseMapper.getHouseById(id);
    }

    @Override
    public Integer updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public Integer deleteHouse(String houseId, Integer delState) {
        House house=new House();
        house.setId(houseId);//条件
        house.setIsdel(delState);//更新状态
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getAllHouse(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> houseList = houseMapper.getAllHouse();
        return new PageInfo<>(houseList);
    }

    @Override
    public Integer updateState(String houseId, Integer passState) {
        House house=new House();
        house.setId(houseId);//条件
        house.setIsdel(passState);//更新审核状态
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> searchHouseByCondition(SearchCondition searchCondition) {
        PageHelper.startPage(searchCondition.getPage(),searchCondition.getRows());
        List<House> houseList = houseMapper.searchHouseByCondition(searchCondition);
        return new PageInfo<>(houseList);
    }

}
