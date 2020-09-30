package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传的入门案例
     * url:http://localhost:8091/file
     * 参数: fileImage 名称
     * 返回值:  文件上传成功!!!
     * SpringMVC 提供了工具API 专门操作流文件.
     *
     * 文件上传的具体步骤:
     *  1.准备文件目录
     *  2.准备文件的全名   xxxx.jpg
     *  3.准备文件上传的路径  D:/JT-SOFT/images/xxxx.jpg
     *  4.将字节信息输出即可.
     *  大小不要超过1M
     */
    @RequestMapping("/file")
    public String file(MultipartFile fileImage) throws IOException {

        String dirPath = "E:/temp/images";
        File dirFile = new File(dirPath);
        if(!dirFile.exists()){
            dirFile.mkdirs();   //一劳永逸的写法
        }
        //获取文件的名称
        String fileName = fileImage.getOriginalFilename();
        //获取文件全路径
        String filePath = dirPath + "/" + fileName;
        File file = new File(filePath);
        fileImage.transferTo(file); //将字节信息输出到指定的位置中

        return "文件上传成功!!!!";
    }

    /**
     * 实现文件上传
     * url地址: http://localhost:8091/pic/upload?dir=image
     * 参数:    uploadFile: 文件的字节信息.
     * 返回值:  {"error":0,"url":"图片的保存路径","width":图片的宽度,"height":图片的高度}
     *          ImageVO对象...
     */
    @RequestMapping("/pic/upload")
    public ImageVO upload(MultipartFile uploadFile){

        return fileService.upload(uploadFile);
    }


}
