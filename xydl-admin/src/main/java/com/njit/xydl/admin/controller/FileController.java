package com.njit.xydl.admin.controller;

import com.njit.xydl.admin.entity.FileInfo;
import com.njit.xydl.admin.service.FileService;
import com.njit.xydl.admin.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/test/insert")
    public JSONResult insert(){
        try {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName("test");
            fileInfo.setFileSize(1.01);
            fileInfo.setFileType("image");
            fileInfo.setFileUrl("http://sadsads/dsad2e323/78321.jpg");
            fileService.insert(fileInfo);
            System.out.println("id==================="+fileInfo.getId());
            return JSONResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg(e.getMessage());
        }
    }

}
