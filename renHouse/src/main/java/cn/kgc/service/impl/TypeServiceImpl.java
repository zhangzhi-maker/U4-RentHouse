package cn.kgc.service.impl;

import cn.kgc.domain.District;
import cn.kgc.domain.DistrictExample;
import cn.kgc.domain.Type;
import cn.kgc.domain.TypeExample;
import cn.kgc.mapper.DistrictMapper;
import cn.kgc.mapper.TypeMapper;
import cn.kgc.service.DemoService;
import cn.kgc.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public PageInfo<Type> getAllType(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        TypeExample typeExample=new TypeExample();
        List<Type> typeList = typeMapper.selectByExample(typeExample);
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        return pageInfo;
    }

    @Override
    public int insertType(Type type) {
        return typeMapper.insertSelective(type);
    }

    @Override
    public Type selectTypeById(Integer id) {
        Type type = typeMapper.selectByPrimaryKey(id);
        return type;
    }

    @Override
    public Integer updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    @Transactional
    public Integer deleteType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteTypeMore(Integer[] ids) {
        return typeMapper.deleteTypeMore(ids);
    }

    @Override
    public List<Type> getAllType() {
        TypeExample typeExample=new TypeExample();
        return typeMapper.selectByExample(typeExample);
    }
}
