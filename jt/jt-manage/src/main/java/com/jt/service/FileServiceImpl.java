package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@PropertySource("classpath:/properties/images.properties") //容器动态加载指定的配置文件
public class FileServiceImpl implements FileService{

    //由于属性的值后期可能会发生变化,所以应该动态的获取属性数据. 利用pro配置文件
    @Value("${image.rootDirPath}")
    private String rootDirPath;             //   = "D:/JT-SOFT/images";
    @Value("${image.urlPath}")
    private String urlPath;                 // = "http://image.jt.com";

    //1.2 准备图片的集合  包含了所有的图片类型.
    private static Set<String> imageTypeSet;
    static {
        imageTypeSet = new HashSet<>();
        imageTypeSet.add(".jpg");
        imageTypeSet.add(".png");
        imageTypeSet.add(".gif");
    }


    /**
     * 完善的校验的过程
     * 1. 校验是否为图片
     * 2. 校验是否为恶意程序
     * 3. 防止文件数量太多,分目录存储.
     * 4. 防止文件重名
     * 5. 实现文件上传.
     * @param uploadFile
     * @return
     */
    @Override
    public ImageVO upload(MultipartFile uploadFile) {
        //0.防止有多余的空格 所以先做去空格的处理
        rootDirPath.trim();
        urlPath.trim();

        //1.校验图片类型  jpg|png|gif..JPG|PNG....
        //1.1 获取当前图片的名称 之后截取其中的类型.   abc.jpg
        String fileName = uploadFile.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);
        //将数据转化为小写
        fileType = fileType.toLowerCase();
        //1.3 判断图片类型是否正确.
        if(!imageTypeSet.contains(fileType)){
            //图片类型不匹配
            return ImageVO.fail();
        }

        //2.校验是否为恶意程序 根据宽度/高度进行判断
        try {
            //2.1 利用工具API对象 读取字节信息.获取图片对象类型
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            //2.2 校验是否有宽度和高度
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if(width==0 || height==0){
                return ImageVO.fail();
            }

            //3.分目录存储  yyyy/MM/dd 分隔
            //3.1 将时间按照指定的格式要求 转化为字符串.
            String dateDir = new SimpleDateFormat("/yyyy/MM/dd/")
                             .format(new Date());
            //3.2 拼接文件存储的目录对象
            String fileDirPath = rootDirPath + dateDir;
            File dirFile = new File(fileDirPath);
            //3.3 动态创建目录
            if(!dirFile.exists()){
                dirFile.mkdirs();
            }

            //4.防止文件重名  uuid.jpg 动态拼接
            //4.1 动态生成uuid  实现文件名称拼接  名.后缀
            String uuid =
                    UUID.randomUUID().toString().replace("-", "");
            String realFileName = uuid + fileType;

            //5 实现文件上传
            //5.1 拼接文件真实路径 dir/文件名称.
            String realFilePath = fileDirPath + realFileName;
            //5.2 封装对象  实现上传
            File realFile = new File(realFilePath);
            uploadFile.transferTo(realFile);

            //实现文件上传成功!!! http://image.jt.com\2020\09\30\a.jpg
            String url = urlPath + dateDir + realFileName;
            return ImageVO.success(url,width,height);
        } catch (IOException e) {
            e.printStackTrace();
            return ImageVO.fail();
        }
    }
}
