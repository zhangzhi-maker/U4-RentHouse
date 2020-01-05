package cn.kgc.pcontroller;

import cn.kgc.domain.House;
import cn.kgc.domain.Users;
import cn.kgc.service.HouseService;
import cn.kgc.utils.FileUploadUtil;
import cn.kgc.utils.PageUtil;
import cn.kgc.utils.SearchCondition;
import com.github.pagehelper.PageInfo;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/page/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    //处理请求方法
    @RequestMapping("addHouse")
    //一个表单对应一个参数或者实体
    //一个文件域对象与一个CommonsMultipartResolver相对应
    public String addHouse(House house, HttpSession session, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile){
        try {

            String saveFileName = FileUploadUtil.upload(pfile);

            //2.将数据添加数据库
            //修改house实体，添加额外属性值
            //设置用户编号
            house.setId(System.currentTimeMillis() + "");
            //设置所属用户
            Users users =(Users) session.getAttribute("loginInfo");
            house.setIspass(0);
            house.setIsdel(0);
            house.setUserId(users.getId());
            //设置图片路径
            house.setPath(saveFileName);
            //调用业务
            houseService.addHouse(house);
            return "fabu";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    //显示发布出租房信息
    @RequestMapping("showHouse")
    public String showHouse(PageUtil pageUtil, HttpSession session, Model model){
        //设置页大小  选择设置默认值
        pageUtil.setRows(5);
        /*if (pageUtil.getPage()==0){
            pageUtil.setPage(1);
        }*/

        //获取用户登录id
        //调用业务获取数据
        //Integer userid=1007;//固定法
        Users users = (Users) session.getAttribute("loginInfo");

        PageInfo<House> pageInfo = houseService.getUserById(users.getId(), pageUtil);
        //将数据填充到作用域
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";

    }

    //显示修改出租房信息
    @RequestMapping("queryHouseById")
    public String queryHouseById(String id,Model model){
        House house = houseService.getHouseById(id);
        model.addAttribute("house",house);
        return "updatefabu";
    }

    //怕house对接数据的时候冲突所以使用变量接收旧图片
    @RequestMapping("updateHouse")
    public String updateHouse(String oldPath,House house,@RequestParam(value = "pfile",required = false)CommonsMultipartFile pfile){

        try {
            if (!pfile.getOriginalFilename().equals("")){
                //上传图片
                String saveFileName = FileUploadUtil.upload(pfile);
                house.setPath(saveFileName);

                //删除旧图                            关联的文件
                FileUploadUtil.delFile(oldPath);
            }
            //修改数据库信息
            houseService.updateHouse(house);
            return "redirect:showHouse";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    //逻辑删除出租房
    @RequestMapping("delHouse")
    public String delHouse(String id){
        try{
            Integer temp = houseService.deleteHouse(id, 1);
            return "redirect:showHouse";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    //回复出租房
    @RequestMapping("backDelHouse")
    public String backDelHouse(String id){
        try{
            Integer temp = houseService.deleteHouse(id, 0);
            return "redirect:showHouse";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    //条件查询
    @RequestMapping("queryHouseByCondition")
    public String queryHouseByCondition(SearchCondition searchCondition,Model model){
        searchCondition.setRows(5);//设置页大小
        PageInfo<House> pageInfo = houseService.searchHouseByCondition(searchCondition);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchCondition",searchCondition);//回显
        return "list";
    }
}
