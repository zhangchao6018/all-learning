package com.imooc.zhangxiaoxi.alllearning.service.impl;

import com.imooc.zhangxiaoxi.alllearning.exception.BusinessException;
import com.imooc.zhangxiaoxi.alllearning.exception.ErrorCodeEnum;
import com.imooc.zhangxiaoxi.alllearning.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 类名称：LocalFileServiceImpl
 * ********************************
 * <p>
 * 类描述：本地文件上传服务实现类
 *
 * @author
 * @date 上午9:44
 */
@Slf4j
@Service("localFileServiceImpl")
public class LocalFileServiceImpl implements FileService {

    /**
     * 存储空间
     */
    private static final String BUCKET = "uploads";

    @Override
    public void upload(InputStream inputStream, String filename) {
        // 拼接文件的存储路径
        String storagePath = BUCKET + "/" + filename;

        try (
                // JDK8 TWR 不能关闭外部资源的
                InputStream innerInputStream = inputStream;

                FileOutputStream outputStream =
                        new FileOutputStream(new File(storagePath))
        ) {
            // 拷贝缓冲区
            byte[] buffer = new byte[1024];
            // 读取文件流长度
            int len;

            // 循环读取inputStream中数据写入到outputStream
            while ((len = innerInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }

            // 冲刷流
            outputStream.flush();

        } catch (Exception e) {

            log.error("文件上传失败！", e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }
    }

    @Override
    public void upload(File file) {
        try {

            upload(new FileInputStream(file), file.getName());
        } catch (Exception e) {

            log.error("文件上传失败", e);
            throw new BusinessException(
                    ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }
    }


}
