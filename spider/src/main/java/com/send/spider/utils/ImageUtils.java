package com.send.spider.utils;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.process.ArrayListOutputConsumer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/5.
 */
public class ImageUtils {
    /**
     * 功能描述：获取图片大小
     *
     * @param imagePath
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/5
     */
    public static long getSize(String imagePath) {
        long size = 0;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(imagePath);
            size = inputStream.available();
            inputStream.close();
            inputStream = null;
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到!");
        } catch (IOException e) {
            System.out.println("读取文件大小错误!");
        } finally {
            // 可能异常为关闭输入流,所以需要关闭输入流
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("关闭文件读入流异常");
                }
                inputStream = null;
            }
        }
        return size;
    }

    /**
     * 功能描述：获取图片宽度
     *
     * @param imagePath
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/5
     */
    public static int getWidth(String imagePath) throws Exception {
        int line = 0;
        try {
            IMOperation op = new IMOperation();
            op.format("%w"); // 设置获取宽度参数
            op.addImage(1);
            IdentifyCmd identifyCmd = new IdentifyCmd(true);
            ArrayListOutputConsumer output = new ArrayListOutputConsumer();
            identifyCmd.setOutputConsumer(output);
            identifyCmd.run(op, imagePath);
            ArrayList<String> cmdOutput = output.getOutput();
            assert cmdOutput.size() == 1;
            line = Integer.parseInt(cmdOutput.get(0));
        } catch (Exception e) {
            throw new Exception("运行指令出错!", e);
        }
        return line;
    }

    /**
     * 功能描述：获取图片高度
     *
     * @param imagePath
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/5
     */
    public static int getHeight(String imagePath) throws Exception {
        int line = 0;
        try {
            IMOperation op = new IMOperation();
            op.format("%h"); // 设置获取高度参数
            op.addImage(1);
            IdentifyCmd identifyCmd = new IdentifyCmd(true);
            ArrayListOutputConsumer output = new ArrayListOutputConsumer();
            identifyCmd.setOutputConsumer(output);
            identifyCmd.run(op, imagePath);
            ArrayList<String> cmdOutput = output.getOutput();
            assert cmdOutput.size() == 1;
            line = Integer.parseInt(cmdOutput.get(0));
        } catch (Exception e) {
            throw new Exception("运行指令出错!" + e.toString());
        }
        return line;
    }

    /**
     * 功能描述：剪切图片
     *
     * @param imagePath 源图片路径
     * @param newPath   处理后图片路径
     * @param x         起始X坐标
     * @param y         起始Y坐标
     * @param width     裁剪宽度
     * @param height    裁剪高度
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/5
     */
    public static boolean cutImage(String imagePath, String newPath, int x, int y,
                                   int width, int height) {
        boolean flag = false;
        try {
            IMOperation op = new IMOperation();
            op.addImage(imagePath);
            /** width：裁剪的宽度 * height：裁剪的高度 * x：裁剪的横坐标 * y：裁剪纵坐标 */
            op.crop(width, height, x, y);
            op.addImage(newPath);
            ConvertCmd convert = new ConvertCmd(true);
            convert.run(op);
            flag = true;
        } catch (IOException e) {
            System.out.println("文件读取错误!");
        } catch (InterruptedException e) {
        } catch (IM4JavaException e) {
        } finally {
        }
        return flag;
    }

    public static boolean zoomImage(String imagePath, String newPath, Integer width,
                                    Integer height) throws Exception {
        return zoomImage(imagePath, newPath, width, height, 100.0);
    }


    /**
     * 功能描述：缩放图片
     *
     * @param imagePath 源图片路径
     * @param newPath   处理后图片路径
     * @param width     缩放后的图片宽度
     * @param height    缩放后的图片高度
     * @return
     * @author 董森
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2017/5/5
     */
    public static boolean zoomImage(String imagePath, String newPath, Integer width,
                                    Integer height, Double quality) throws Exception {
        boolean flag = false;
        try {
            IMOperation op = new IMOperation();
            op.addImage(imagePath);
            // 设置清晰度
            if (quality != null) {
                op.quality(quality);
            }
            if (width == null) {// 根据高度缩放图片
                op.resize(null, height);
            } else if (height == null) {// 根据宽度缩放图片
                op.resize(width, null);
            } else {
                op.resize(width, height);
            }
            op.addImage(newPath);
            ConvertCmd convert = new ConvertCmd(true);
            convert.run(op);
            flag = true;
        } catch (IOException e) {
            System.out.println("文件读取错误!");
            throw new Exception("文件读取错误!" + e.toString());
        } catch (InterruptedException e) {
            throw new Exception(e);
        } catch (IM4JavaException e) {
            throw new Exception(e);
        } finally {
        }
        return flag;
    }

    public static boolean zoomImageWidth(String imagePath, String newPath, Integer width) throws Exception {
        return zoomImage(imagePath, newPath, width, null, null);
    }

    public static boolean zoomImageWidth(String imagePath, String newPath, Integer width, double quality) throws Exception {
        return zoomImage(imagePath, newPath, width, null, quality);
    }

    public static boolean zoomImageHight(String imagePath, String newPath, Integer height, double quality) throws Exception {
        return zoomImage(imagePath, newPath, null, height, quality);
    }

    public static Double widthByHeightRadio(String imagePath) {
        try {
            return Double.valueOf(getWidth(imagePath)) / getHeight(imagePath);
        } catch (Exception e) {
            return 0.0;
        }
    }
}
