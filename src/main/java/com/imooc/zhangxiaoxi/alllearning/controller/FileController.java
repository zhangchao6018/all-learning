package com.imooc.zhangxiaoxi.alllearning.controller;

import com.imooc.zhangxiaoxi.alllearning.domain.common.ResponseResult;
import com.imooc.zhangxiaoxi.alllearning.exception.BusinessException;
import com.imooc.zhangxiaoxi.alllearning.exception.ErrorCodeEnum;
import com.imooc.zhangxiaoxi.alllearning.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 类名称：FileController
 * ********************************
 * <p>
 * 类描述：文件服务Controller
 *
 * @author
 * @date 上午9:38
 */
@RestController
@RequestMapping("/api/files")
@Slf4j
public class FileController {

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public ResponseResult<String> upload(@NotNull MultipartFile file) {

        // 文件上传
        try {

            fileService.upload(file.getInputStream(), file.getOriginalFilename());
        } catch (Exception e) {

            log.error("文件上传失败！", e);
            throw new BusinessException(
                    ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }

        return ResponseResult.success(file.getOriginalFilename());
    }

}
