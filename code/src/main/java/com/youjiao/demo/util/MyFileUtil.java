package com.youjiao.demo.util;

import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Zjp
 * 2019/04/14
 * 文件上传下载删除
 */
public final class MyFileUtil {

    /**
     * @author Zjp
     * 单文件上传
     * filePath：包含路径和文件名
     */
    public static void upload(MultipartFile file, String filepath) throws BusinessException {
        if (file.isEmpty()) {
            throw new BusinessException(EmBusinessErr.FILE_IS_EMPTY);
        }

        //上传文件的文件名
        String fileName = file.getOriginalFilename();

        //服务器中存储文件的路径
        String serverPath = MySessionUtil.getRealPath() + filepath;

        try {
            File dest = new File(serverPath);
            //如果目的文件不存在，会自动创建
            FileUtils.copyInputStreamToFile(file.getInputStream(), dest);
            //file.transferTo(dest);//不会自动创建
        } catch (Exception e) {
            MyLog.error(MyExceptionUtil.getErrorMsg(e) + "\n" + "文件:\"" + fileName + "\"上传失败");
            throw new BusinessException(EmBusinessErr.FILE_UPLOAD_FAILED, fileName + "上传失败");
        }
    }

    /**
     * @author Zjp
     * 多文件上传
     * 文件列表，文件保存路径
     */
    public static void upload(List<MultipartFile> files, String filePath) throws BusinessException {
        for (MultipartFile file : files) {
            upload(file, filePath);
        }
    }

    /**
     * @author Zjp
     * 文件下载
     * 根据文件路径获取文件，直接将文件存入HttpServletResponse对象中
     * filePath:包含路径和文件名
     */
    public static void downloadFile(String filepath) throws BusinessException {
        //文件完整路径
        String pathname = MySessionUtil.getRealPath() + filepath;
        //获得文件对象
        Path file = Paths.get(pathname);
        if (!Files.exists(file)) {
            MyLog.error("文件:\"" + pathname + "\"不存在");
            throw new BusinessException(EmBusinessErr.FILE_NOT_EXIST);
        }
        //获得文件名
        String fileName = file.getFileName().toString();
        try {
            //设置response header
            HttpServletResponse response = MySessionUtil.getResponse();
            response.setHeader("Content-type", "application/octet-stream;charset=UTF-8");
            response.setContentType("application/octet-stream");
            //如果文件名有中文的话，进行URL编码，让中文正常显示
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

            //将文件写入response中
            Files.copy(file, response.getOutputStream());
        } catch (Exception e) {
            MyLog.error(MyExceptionUtil.getErrorMsg(e) + "\n" + "文件:\"" + fileName + "\"下载失败");
            throw new BusinessException(EmBusinessErr.FILE_DOWNLOAD_FAILED, fileName + "下载失败");
        }
    }

    /**
     * @author Zjp
     * 文件删除
     * 根据文件路径删除服务器中文件
     * filepath：包含路径和文件名
     */
    public static void deleteFile(String filepath) throws BusinessException {
        //文件完整路径
        String pathname = MySessionUtil.getRealPath() + filepath;
        try {
            File file = new File(pathname);
            if (!file.delete()) {
                MyLog.error("文件:\"" + pathname + "\"删除失败");
                throw new BusinessException(EmBusinessErr.FILE_DELETE_FAILED);
            }
        } catch (Exception e) {
            MyLog.error(MyExceptionUtil.getErrorMsg(e) + "\n" + "文件:\"" + pathname + "\"删除失败");
            throw new BusinessException(EmBusinessErr.FILE_DELETE_FAILED);
        }
    }
}
