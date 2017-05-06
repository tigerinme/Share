package com.send.spider.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;

import java.util.ArrayList;
import java.util.List;

public class PageProcessor_huaban extends BaseProcessor {


    public PageProcessor_huaban() {
        this.URL_HOME = "http://huaban.com/favorite/beauty/";
        this.URL_LIST = "http://huaban\\.com/favorite/beauty/?[a-z 0-9]{8}&max=[0-9]*&limit=20&wfl=1";
        this.URL_DETAIL = "http://huaban.com/boards/[0-9]*";
    }

    public void processList(Page page) {
        String maxReg = "[\\x21-\\x7e]" + "\"pin_id\":[0-9]*,";
        String strUrl = "[\\x21-\\x7e]" + "\"board_id\":[0-9]*,";
        String strTitle = "\"title\":(.*?),";
        List<String> board_id_tem = page.getHtml().regex(strUrl).all();
        List<String> pin_id_tem = page.getHtml().regex(maxReg).all();
        List<String> title_tem = page.getHtml().regex(strTitle).all();
        List<String> board_id_real = new ArrayList<>();
        List<String> title_real = new ArrayList<>();
        String max = null;
        if (pin_id_tem.size() > 0) {
            max = pin_id_tem.get(pin_id_tem.size() - 1).split(":")[1].replace(",", "");
        }
        for (int i = 0; i < board_id_tem.size(); i++) {
            board_id_real.add("http://huaban.com/boards/" + board_id_tem.get(i).split(":")[1].replace(",", "")); //详情页
        }
        for (int i = 0; i < title_tem.size(); i++) {
            title_real.add(title_tem.get(i).replace("\"", ""));
        }
        for (int i = 0; i < board_id_real.size(); i++) {
            Request request = new Request(board_id_real.get(i)).setPriority(1)
                    .putExtra("galleryTitle", title_real.get(i))
                    .putExtra("galleryUrl", board_id_real.get(i));
            page.addTargetRequest(request);
        }
        //瀑布流加载下一页
        if (max != null) {
            page.addTargetRequest("http://huaban.com/favorite/beauty/?max=" + max + "&limit=20&wfl=1");
        }
    }

    public void processDetail(Page page) {
        List<String> imageDetailUrls = page.getHtml().xpath("//div[@class='pin wfc']/a/img/@src").all();
        List<String> maxList = page.getHtml().xpath("//div[@class='pin wfc']/@data-id").all();
        List<String> images = new ArrayList<String>();
        if (imageDetailUrls != null && !imageDetailUrls.isEmpty()) {
            for (String url : imageDetailUrls) {
                url = url.replace("fw236", "fw658");
                if (!url.startsWith("http")) {
                    url = "http:" + url;
                    images.add(url);
                } else {
                    images.add(url);
                }
            }
        } else {
            images = imageDetailUrls;
        }
        page.putField("imageDetailUrls", images);
        page.putField("galleryTitle", page.getRequest().getExtra("galleryTitle"));
        page.putField("galleryUrl", page.getRequest().getExtra("galleryUrl"));
        page.putField("sourceId", this.getSourceId());
        //瀑布流加载图集
        if (maxList.size() > 0) {
            Request req = new Request(page.getRequest().getExtra("galleryUrl") + "?max=" + maxList.get(maxList.size() - 1) + "&limit=20&wfl=1").setPriority(1)
                    .putExtra("galleryTitle", page.getRequest().getExtra("galleryTitle"))
                    .putExtra("galleryUrl", page.getRequest().getExtra("galleryUrl"));
            page.addTargetRequest(req);
        }


    }
}