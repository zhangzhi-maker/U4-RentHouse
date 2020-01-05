package cn.kgc.pcontroller;

import cn.kgc.domain.Users;
import cn.kgc.domain.UsersCondition;
import cn.kgc.service.UsersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller(value = "usersController2")
@RequestMapping("/page/")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("checkUserName")
    @ResponseBody
    public String queryUsersByPage(String name){
        boolean res = usersService.checkUserName(name);
        return "{\"result\":"+res+"}";

    }

    //注册
    @RequestMapping("reg")
    public String regUser(Users users){
        Integer i = usersService.addUser(users);
        if (i>0){
            return "redirect:login.jsp";
        }else {
            return  "redirect:regs.jsp";
        }

    }

    @RequestMapping("login")
    public String login(String username, String password, HttpSession session){
        Users users = usersService.login(username, password);
        if (users==null){
            return "redirect:login.jsp";
        }else {
            //只要登陆，肯定使用cookie或者session保存登入者的信息
            //使用session保存登入者的信息
            session.setAttribute("loginInfo",users);
            //设置有效期
            session.setMaxInactiveInterval(10*60);
            return "redirect:/page/showHouse";
        }
    }
}
