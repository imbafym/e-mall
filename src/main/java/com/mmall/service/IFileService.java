package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yimingfan on 10/9/18
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
