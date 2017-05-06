package com.send.spider.utils;

import java.util.ArrayList;
import java.util.List;


public class ProcessorUtils {
    public static List<String> fixUrlPath(String domain, List<String> imageDetailUrls) {
        List<String> rtUrls = new ArrayList<>();
        for (String url : imageDetailUrls) {
            if (url.startsWith("http")) {
                rtUrls.add(url);
            } else {
                rtUrls.add("http://" + domain + url);
            }
        }
        return rtUrls;
    }
}
