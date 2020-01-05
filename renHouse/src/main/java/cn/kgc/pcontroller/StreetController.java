package cn.kgc.pcontroller;

import cn.kgc.domain.Street;
import cn.kgc.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/page/")
public class StreetController {

    @Autowired
    private StreetService streetService;

    @RequestMapping("queryStreetById")
    @ResponseBody
    public List<Street> queryStreetById(Integer did){
        return streetService.selectByDistrictId(did);
    }
}
