package cn.kgc.service.impl;

import cn.kgc.domain.District;
import cn.kgc.domain.DistrictExample;
import cn.kgc.mapper.DistrictMapper;
import cn.kgc.service.DemoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public PageInfo<District> getAllDistrict(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        DistrictExample districtExample=new DistrictExample();
        List<District> districtList = districtMapper.selectByExample(districtExample);
        PageInfo<District> pageInfo = new PageInfo<>(districtList);
        return pageInfo;
    }

    @Override
    public int insertDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public District selectDistrictById(Integer id) {
        District district = districtMapper.selectByPrimaryKey(id);
        return district;
    }

    @Override
    public Integer updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    @Transactional
    public Integer deleteDistrict(Integer id) {
        return districtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteDistrictMore(Integer[] ids) {
        return districtMapper.deleteDistrictMore(ids);
    }

    @Override
    public List<District> getDistrictAll() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
