package com.youjiao.demo.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/**
 * @author Zjp
 * 用户端验证码图片生成类
 */
public final class CaptchaImage {
    // 验证码最大位数
    private static final int MAX_STRING_COUNT = 4;
    private static final String UPLOAD_ROOT_PATH = "/statics/images/upload/picture/";
    private static final String IMAGE_NAME = "captcha";
    private static final String IMAGE_FORMAT = "jpg";
    private static final String IMAGE_PATH = UPLOAD_ROOT_PATH + IMAGE_NAME + "." + IMAGE_FORMAT;
    //随机字符字典
    private static final char[] CHARS = {'2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    // 随机数
    private static Random random = new Random();

    private CaptchaImage() {
    }

    // 获取6位随机数
    private static String getRandomString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < MAX_STRING_COUNT; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    // 获取随机数颜色
    private static Color getRandomColor() {
        return new Color(192, 192, 192);
    }

    // 返回某颜色的反色
    private static Color getReverseColor(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(),
                255 - c.getBlue());
    }

    /**
     * @author Zjp
     * 返回验证码图片的base64编码字符串
     */
    public static String outputCaptcha() {
        MySessionUtil.getResponse().setContentType("image/jpeg");

        //生成随机字符串，并存入session
        String randomString = getRandomString();
        MySessionUtil.setAttribute(Constants.VERIFICATION_CODE, randomString);

        int width = 80;
        int height = 30;

        Color color = getRandomColor();
        Color reverse = getReverseColor(color);

        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.setColor(reverse);
        g.drawString(randomString, 18, 20);
        for (int i = 0, n = random.nextInt(100); i < n; i++) {
            g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
        }

        String uploadRootPath = MySessionUtil.getRealPath() + IMAGE_PATH;
        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists())
            uploadRootDir.mkdirs();

        //生成验证码图片
        // 可以是jpg,png,gif格式
        File imageFile = new File(uploadRootPath);
        // 不管输出什么格式图片，此处不需改动，将数据流转换成图片并保存到指定路径中
        try {
            ImageIO.write(bi, IMAGE_FORMAT, imageFile);
        } catch (Exception e) {
            MyLog.error("生成验证码图片失败:" + MyExceptionUtil.getErrorMsg(e));
        }
        //返回图片生成的base64字符串
        return MyImageUtil.getImageBase64(imageFile.getPath());
    }
}
