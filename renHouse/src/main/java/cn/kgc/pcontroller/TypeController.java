package cn.kgc.pcontroller;

import cn.kgc.domain.Type;
import cn.kgc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller(value = "typeController2")
@RequestMapping("/page/")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("queryAllType")
    @ResponseBody
    public List<Type> queryAllType(){
        return typeService.getAllType();
    }
}
