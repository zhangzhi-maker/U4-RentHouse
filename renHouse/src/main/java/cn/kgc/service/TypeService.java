package cn.kgc.service;

import cn.kgc.domain.District;
import cn.kgc.domain.Type;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface TypeService {
    public PageInfo<Type> getAllType(Integer page, Integer rows);

    int insertType(Type type);

    Type selectTypeById(Integer id);

    Integer updateType(Type type);

    Integer deleteType(Integer id);

    Integer deleteTypeMore(Integer[] ids);

    List<Type> getAllType();

}
