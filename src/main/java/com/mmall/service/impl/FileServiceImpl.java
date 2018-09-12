package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by yimingfan on 10/9/18
 */


@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename();
        //扩展名
        //123.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        //防止重复文件名
        String uploadFileName = UUID.randomUUID().toString()+ "." + fileExtensionName;
        logger.info("开始上传文件，上传文件名：{}， 上传的路径：{}，新文件名：{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            //文件上传成功

            //将targetfile 文件上传到ftp服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));

            //上传完之后删除文件
            targetFile.delete();

        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }

        return targetFile.getName();

    }

}
