package cn.kgc.controller;

import cn.kgc.domain.District;
import cn.kgc.domain.Type;
import cn.kgc.service.DemoService;
import cn.kgc.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("queryAllType")
    public Map<String,Object> queryAllType(Integer page,Integer rows){
        PageInfo<Type> pageInfo = typeService.getAllType(page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("addType")
    public Map<String,Object> addType(Type type){
        int result = typeService.insertType(type);
        Map<String,Object> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

    @RequestMapping("selectTypeById")
    public Type selectTypeById(Integer id){
        try {
            Type type = typeService.selectTypeById(id);
            return type;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("updateType")
    public Map<String,Object> updateType(Type type){
        Map<String,Object> map=new HashMap<>();
        try{
            Integer updates = typeService.updateType(type);
            map.put("updates",updates);
            return map;
        }catch (Exception e){
            map.put("error","-1");
            return map;
        }
    }

    @RequestMapping("deleteType")
    public Map<String,Object> deleteType(Integer id){
        Integer integer = typeService.deleteType(id);
        Map<String,Object> map=new HashMap<>();
        map.put("integer",integer);
        return map;
    }

    //批量删除区域
    //前台传递的数据:id=1,2,3,4... 后台：String ids变量接收数据
    //前台传递的数据：id=1&id=2&id=3 后台：Integer[] ids变量接收数据
    @RequestMapping("delTypeMore")
    public Map<String,Object> delTypeMore(String ids){
        Map<String,Object> map=new HashMap<>();
        try{
            //字符串转化为数组
            String[] strList=ids.split(",");
            Integer[] idList = new Integer[strList.length];
            for (int i = 0; i < strList.length; i++) {
                 idList[i] = new Integer(strList[i]);
            }

            Integer temp = typeService.deleteTypeMore(idList);
            map.put("temp",temp);
            return map;
        }catch (Exception e){
            map.put("temp","-1");
            return map;
        }
    }
}
