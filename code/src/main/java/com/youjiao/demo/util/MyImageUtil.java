package com.youjiao.demo.util;

import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @author Zjp
 * 2019/03/15
 * 图片类
 */
public final class MyImageUtil {
    /**
     * @author Zjp
     * 通过数据库中的图片url获取图片的base64字符串
     */
    public static String downloadImage(String filename, String imagePath) throws BusinessException {
        //图片路径
        String filepath = imagePath + filename;

        //服务器中图片的路径
        String downloadRootPath = MySessionUtil.getRealPath() + filepath;
        File file = new File(downloadRootPath);
        if (!file.exists()) {
            filepath=Constants.FILE_PATH;
        }
        return filepath;
    }

    /**
     * @author Zjp
     * 将base64的图片字符串上传到服务器，并且返回上传后图片的url
     */
    public static String uploadImage(String imageFile, String imagePath)
            throws BusinessException {
        //服务器中存储图片的路径
        String uploadRootPath = MySessionUtil.getRealPath() + imagePath;

        //检查存储图片的文件夹是否存在，如果不存在，创建文件夹
        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists())
            uploadRootDir.mkdirs();

        //图片在服务器中的文件名
        String imageName = System.currentTimeMillis() + ".jpg";
        String filepath = uploadRootPath + imageName;

        //获取图片的数据
        String[] imageContent = imageFile.split(",");//图片base64数据以“,”分割
        String image = imageContent[imageContent.length - 1];//获得图片的内容

        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] byteArr = decoder.decodeBuffer(image);//获得图片文件字节数组

            FileOutputStream fos = new FileOutputStream(filepath);
            fos.write(byteArr);
            fos.close();
        } catch (Exception e) {
            MyLog.error("\"" + imageFile + "\"图片上传失败\n" + e.getMessage());
            throw new BusinessException(EmBusinessErr.UPLOAD_IMAGE_FAILED);
        }
        return imageName;
    }

    /**
     * @author Zjp
     * 删除指定路径压缩文件
     */
    public static void deleteImage(String filename, String imagePath) throws BusinessException {
        //服务器中存储图片的路径
        String filepath = MySessionUtil.getRealPath() + imagePath + filename;
        //获取图片文件
        File file = new File(filepath);
        //如果文件存在，则删除
        if (file.exists()) {
            try {
                if (!FileUtils.deleteQuietly(file)) {
                    throw new Exception("文件删除失败，可能是文件被系统占用");
                }
            } catch (Exception e) {
                MyLog.error("文件删除失败\n" + MyExceptionUtil.getErrorMsg(e));
                throw new BusinessException(EmBusinessErr.IMAGE_DELETE_FAILED);
            }
        }
    }

    /**
     * @author Zjp
     * 校验新图片和原图片是否相同
     * 如果新图片和原图片相同，则返回null
     * 如果不同则删除原图片，上传新图片，并返回新图片的路径
     */
    public static String replaceImage(String newImageFile, String unzipFilename, String imagePath) throws BusinessException {
        //获得原图片的base64字符串
        String oldImageFile = downloadImage(unzipFilename, imagePath);
        //新图片和原图片是否相同
        if (oldImageFile.equals(newImageFile)) {
            return null;
        } else {
            //删除原图片
            deleteImage(unzipFilename, imagePath);
            //上传新图片，并返回新图片的路径
            return uploadImage(newImageFile, imagePath);
        }
    }

    static String getImageBase64(String imgFile) {
        // 将图片文件转化为字节数组字符串，并对其进行 Base64 编码处理
        InputStream in;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (FileNotFoundException e) {
            MyLog.warn("method: getImageBase64-> fileNotFoundException");
            return null;
        } catch (IOException e) {
            MyLog.error(MyExceptionUtil.getErrorMsg(e));
        }
        // 对字节数组 Base64 编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回 Base64 编码过的字节数组字符串
        return encoder.encode(data).replace("\r\n", "");
    }
}
