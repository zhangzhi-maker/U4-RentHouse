package cn.kgc.utils;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtil {

    //保存的位置
    private static final String savePosition="f:\\images\\";

    public static String upload(CommonsMultipartFile pfile){
        try {
            //1.实现文件上传
            //获取文件扩展名
            String filename = pfile.getOriginalFilename();
            String fexpName = filename.substring(filename.lastIndexOf("."));
            //生成新的文件名
            String unique = System.currentTimeMillis()+"";
            String saveFileName = unique + fexpName;
            String savePath=savePosition+saveFileName;
            File saveFile=new File(savePath);
            pfile.transferTo(saveFile);//上传
            return saveFileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean delFile(String fileName){
        File file=new File(savePosition+fileName);
        return file.delete();
    }
}
