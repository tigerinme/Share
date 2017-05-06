package com.send.spider.test;

import com.send.spider.spider.PageProcessor_huaban;
import org.junit.jupiter.api.Test;
import us.codecraft.webmagic.Spider;

/**
 * Created by guojinyun on 16-4-7.
 */
public class TestSpiders {


    public static void main(String[] args) {
        Spider spider = Spider.create(new PageProcessor_huaban())
                .addUrl("http://huaban.com/favorite/beauty/").thread(2);
        spider.start();
    }

}
