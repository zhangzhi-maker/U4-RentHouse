package cn.kgc.controller;

import cn.kgc.domain.Users;
import cn.kgc.domain.UsersCondition;
import cn.kgc.service.UsersService;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("queryUsersByPage")
    @ResponseBody
    public Map<String,Object> queryUsersByPage(UsersCondition condition){
        PageInfo<Users> pageInfo = usersService.queryUserByPage(condition);
        //封装返回数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
