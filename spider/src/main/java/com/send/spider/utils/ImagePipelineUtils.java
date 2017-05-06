package com.send.spider.utils;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ImagePipelineUtils {

    public static File saveImage(String url, String filePath, String fileName)
            throws Exception {

        HttpGet getMethod = new HttpGet(url);
        // 设置请求和传输超时时间
        RequestConfig.Builder builder = RequestConfig.custom()
                .setSocketTimeout(6000).setConnectTimeout(6000);
        // set the header Accept-Encoding ,下载压缩传输的图片
        getMethod.setHeader("Accept-Encoding", "identity");
        getMethod.setHeader("Referer", url);
        /**
         * 根据outputstream获取图片
         */
        BufferedInputStream bis = null;
        HttpURLConnection urlconnection = null;
        String filetype = "";//文件类型
        URL url2 = null;
        try {
            url2 = new URL(url);
            urlconnection = (HttpURLConnection) url2.openConnection();
            urlconnection.connect();
            bis = new BufferedInputStream(urlconnection.getInputStream());
            filetype = HttpURLConnection.guessContentTypeFromStream(bis).toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        filetype = filetype.substring(filetype.lastIndexOf("/") + 1, filetype.length());
        if (filetype == "" || null == filetype) {
            filetype = "jpeg";
        }

        String fileSuffix = url.substring(url.lastIndexOf("."));

        //过滤花瓣网
        if (url.contains("hbimg.b0.upaiyun.com")) {
            //伪造referer
            getMethod.setHeader("Referer", "http://huaban.com/");
            //修改图片后缀
            fileSuffix = "." + filetype;
        }


        getMethod.setConfig(builder.build());

        File file = null;
        File realFile = null;
        FileOutputStream outputStream = null;
        try {
            HttpResponse response = HttpClients.createDefault().execute(
                    getMethod);
            // 消除cookie
            // HttpClient httpClient = new DefaultHttpClient();
            // HttpClientParams.setCookiePolicy(httpClient.getParams(),
            // CookiePolicy.BROWSER_COMPATIBILITY);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                file = new File(filePath + fileName + FileUtils.ARTWORK + "_tmp" + fileSuffix);
                if (!file.exists())
                    file.createNewFile();
                outputStream = new FileOutputStream(file);
                InputStream inputStream = response.getEntity().getContent();

                byte buffer[] = new byte[32 * 1024];
                int l = -1;

                while ((l = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, l);
                }
                if (ImageUtils.getSize(file.getAbsolutePath()) == response.getEntity().getContentLength()) {
                    realFile = new File(filePath + fileName + FileUtils.ARTWORK + fileSuffix);
                    if (file.renameTo(realFile)) {
                        return realFile;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            if (realFile != null && realFile.exists())
                realFile.delete();
            throw new Exception("文件下载失败:" + url);
        } finally {
            if (file != null && file.exists())
                file.delete();
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
            getMethod.releaseConnection();
        }
    }

    // 花瓣网图片下载方式
    public static File saveImageHuaBan(String url, String filePath,
                                       String fileName) throws Exception {

        byte[] btImg = getImageFromNetByUrl(url);
        if (null != btImg && btImg.length > 0) {
            File file = null;
            try {

                file = new File(filePath + fileName + FileUtils.ARTWORK
                        + "_tmp" + ".jpg");
                FileOutputStream fops = new FileOutputStream(file);
                fops.write(btImg);
                fops.flush();
                fops.close();
                return file;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("没有从该连接获得内容");
        }
        return null;
    }

    public static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

}