package cn.kgc.service;

import cn.kgc.domain.District;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface DemoService {
    public PageInfo<District> getAllDistrict(Integer page, Integer rows);

    int insertDistrict(District district);

    District selectDistrictById(Integer id);

    Integer updateDistrict(District district);

    Integer deleteDistrict(Integer id);

    Integer deleteDistrictMore(Integer[] ids);

    List<District> getDistrictAll();

}
