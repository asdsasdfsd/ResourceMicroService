package top.snailclimb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.snailclimb.common.CommonResult;
import top.snailclimb.service.AliyunOssPicStorageService;

/**
 * @author shuang.kou
 */
@Controller
@RequestMapping("/oss/pictures")
public class AliyunOssPicStorageController {

    private final AliyunOssPicStorageService aliyunOssService;

    public AliyunOssPicStorageController(AliyunOssPicStorageService aliyunOssService) {
        this.aliyunOssService = aliyunOssService;
    }

    @PostMapping
    @ResponseBody
    public CommonResult handleUploadImage(@RequestParam("file") MultipartFile file) {
        return aliyunOssService.handleUploadImage(file);
    }

    @PostMapping("/deletePicByKey")
    @ResponseBody
    public void deletePicByKey(@RequestBody String picKey) {
        aliyunOssService.deletePicByKey(picKey);
    }

    @GetMapping
    @ResponseBody
    public CommonResult listPicturesByFileHost() {
        return aliyunOssService.listPicturesByFileHost("secondHand/");
    }

}
