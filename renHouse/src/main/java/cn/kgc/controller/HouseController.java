package cn.kgc.controller;

import cn.kgc.domain.House;
import cn.kgc.service.HouseService;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//spring容易只会创建一个对象   单例模式
@RestController(value = "houseController2")
@RequestMapping("/admin/")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping("queryAllHouse")
    public Map<String,Object> queryAllHouse(PageUtil pageUtil){
        PageInfo<House> pageInfo = houseService.getAllHouse(pageUtil);
        Map<String,Object> map=new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    //更新出租房审核状态
    @RequestMapping("updateState")
    public Map<String,Object> updateState(String id,Integer state){
        Integer temp = houseService.updateState(id, state);
        Map<String,Object> map=new HashMap<>();
        map.put("result",temp);
        return map;
    }

}
