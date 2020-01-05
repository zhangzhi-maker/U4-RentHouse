package cn.kgc.pcontroller;

import cn.kgc.domain.District;
import cn.kgc.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "districtController2")
@RequestMapping("/page/")
public class DistrictController {
    @Autowired
    private DemoService demoService;

    @RequestMapping("queryDistrictAll")
    @ResponseBody
    public List<District> queryDistrictAll(){
        return demoService.getDistrictAll();
    }
}
