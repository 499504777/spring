package com.web;

import com.client.FileUploadClinet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangShaowei on 2017/6/12 14:23
 */
@RestController
public class UIFileUploadController {

    private static final String PATH = "C:\\Users\\ZhangShaowei\\Desktop\\";

    @Autowired
    private FileUploadClinet fileUploadClinet;

    /**
     * @param files 文件
     * @return
     */
    @PostMapping("batch/upload")
    public List<String> bacthUpload(@RequestParam("file") MultipartFile[] files) {
        List<String> result = new ArrayList<>();
        for (MultipartFile file : files) {
            result.add(this.fileUploadClinet.singleUpload(file, PATH));
        }
        return result;
    }

    @PostMapping("batch/upload2")
    public String bacthUpload2(HttpServletRequest request) throws IOException {

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = multipartRequest.getFiles("file");
            for (MultipartFile file : files) {
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为"",说明该文件存在，否则说明该文件不存在
                    if (!"".equals(myFileName.trim())) {
                        System.out.println(myFileName);
                        //重命名上传后的文件名?
                        //定义上传路径
                        String path = PATH + myFileName;
                        File localFile = new File(path);
                        file.transferTo(localFile);
                    }
                }
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
            }

        }
        return "success";

    }

    @PostMapping("single/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        return this.fileUploadClinet.singleUpload(multipartFile, "G:\\upload\\");
    }

}
