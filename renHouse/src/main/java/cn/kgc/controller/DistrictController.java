package cn.kgc.controller;

import cn.kgc.domain.District;
import cn.kgc.service.DemoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/")
public class DistrictController {
    @Autowired
    private DemoService demoService;

    @RequestMapping("queryAllDistrict")
    public Map<String,Object> queryAllDistrict(Integer page,Integer rows){
        PageInfo<District> pageInfo = demoService.getAllDistrict(page, rows);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("addDistrict")
    public Map<String,Object> addDistrict(District district){
        int result = demoService.insertDistrict(district);
        Map<String,Object> map=new HashMap<>();
        map.put("result",result);
        return map;
    }

    @RequestMapping("selectById")
    public District selectById(Integer id){
        try {
            District district = demoService.selectDistrictById(id);
            return district;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("updateDistrict")
    public Map<String,Object> updateDistrict(District district){
        Map<String,Object> map=new HashMap<>();
        try{
            Integer updates = demoService.updateDistrict(district);
            map.put("updates",updates);
            return map;
        }catch (Exception e){
            map.put("error","-1");
            return map;
        }
    }

    @RequestMapping("deleteDistrict")
    public Map<String,Object> deleteDistrict(Integer id){
        Integer integer = demoService.deleteDistrict(id);
        Map<String,Object> map=new HashMap<>();
        map.put("integer",integer);
        return map;
    }

    //批量删除区域
    //前台传递的数据:id=1,2,3,4... 后台：String ids变量接收数据
    //前台传递的数据：id=1&id=2&id=3 后台：Integer[] ids变量接收数据
    @RequestMapping("delDistrictMore")
    public Map<String,Object> delDistrictMore(String ids){
        Map<String,Object> map=new HashMap<>();
        try{
            //字符串转化为数组
            String[] strList=ids.split(",");
            Integer[] idList = new Integer[strList.length];
            for (int i = 0; i < strList.length; i++) {
                 idList[i] = new Integer(strList[i]);
            }

            Integer temp = demoService.deleteDistrictMore(idList);
            map.put("temp",temp);
            return map;
        }catch (Exception e){
            map.put("temp","-1");
            return map;
        }
    }

}
