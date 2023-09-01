package com.controller;


import cn.hutool.core.io.FileUtil;
import com.service.ItemService;
import com.utils.Result;
import com.utils.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    @PostMapping("/image")
    public Result uploadImage(@RequestParam("file") MultipartFile image) {
        try {
            // 获取原始文件名称
            String originalFilename = image.getOriginalFilename();
            // 生成新文件名
            String fileName = originalFilename;
            // 保存文件
            File file = new File(SystemConstants.IMAGE_UPLOAD_DIR, fileName);
            image.transferTo(file);
            // 返回结果
            log.debug("文件上传成功，{}", fileName);
            Result result = new Result();
            result.setData(fileName);
            result.setFlag(true);
            return result;
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }

    @DeleteMapping("/image/{imagePath}")
    public Result deleteBlogImg(@PathVariable("imagePath") String filename) {
        System.out.println(filename);
        File file = new File(SystemConstants.IMAGE_UPLOAD_DIR, filename);
        if (file.isDirectory()) {
            Result result = new Result();
            result.setMessage("错误的文件名称");
            result.setFlag(false);
            return result;
        }
        boolean delSuccess = FileUtil.del(file);
        Result result = new Result();
        result.setFlag(delSuccess);
        result.setMessage(delSuccess ? "移除图片成功":"移除图片失败，请重试...");
        return result;
    }

}
