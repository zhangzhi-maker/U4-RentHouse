package cn.kgc.service.impl;

import cn.kgc.domain.Street;
import cn.kgc.domain.StreetExample;
import cn.kgc.mapper.StreetMapper;
import cn.kgc.service.DemoService;
import cn.kgc.service.StreetService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "streetServiceImpl2")
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetMapper streetMapper;


    @Override
    public List<Street> selectByDistrictId(Integer did) {
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did);
        return streetMapper.selectByExample(streetExample);
    }

  /*  //测试业务方法
    public static void main(String[] args) {
        //创建spring容器
        //创建spring容器
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        //找对象调有
        StreetService streetService=(StreetService)ctx.getBean("streetServiceImpl2");
        System.out.println("街道个数是:"+streetService.selectByDistrictId(1002).size());
    }*/
}
