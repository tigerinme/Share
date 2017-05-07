package com.send.spider.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.util.Random;

/**
 * Created by Administrator on 2017/5/5.
 */
public class FileUtils {
    public final static String ARTWORK = "_100";//原图
    public final static String HD = "_75";//高清
    public final static String LS = "_78";//Landscape mode
    public final static String STANDARD = "_40";//标清
    private static Random random = new Random();

    public static String generateFilePath() {
        return  "D:/share/pictures/"+ (random.nextInt(3000) + 1) + File.separator;
    }

    public static String generateFileName(Integer keyPrefix, String key) {
        return keyPrefix + "_" + DigestUtils.md5Hex(key);
    }
}
