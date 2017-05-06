package com.send.spider.spider;


import com.send.spider.utils.ProcessorUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;



public abstract class BaseProcessor implements PageProcessor {
    Logger logger = Logger.getLogger("spider");
    private int sourceId;
    protected String URL_DETAIL;
    protected String URL_LIST;
    protected String URL_HOME;

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    protected Site site;

    public BaseProcessor() {
        site = Site.me()
                .setRetryTimes(3)
                .setRetrySleepTime(10000)
                .setSleepTime(5000)
                .setTimeOut(10000)
                .setUserAgent("Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html）");
        /*List<String[]> proxyList = RedisUtils.getProxyList();
        if(!CollectionUtils.isEmpty(proxyList)){
        	site.setHttpProxyPool(proxyList);
        }*/
    }

    public Site getSite() {
        return site;
    }

    public List<String> getImageUrls(List<String> imageDetailUrls) {
        return ProcessorUtils.fixUrlPath(this.getSite().getDomain(), imageDetailUrls);
    }

    public void process(Page page) {
        //home page
        if (page.getUrl().regex(URL_DETAIL).match()) {
            logger.log(Level.DEBUG, "处理页面:" + page.getUrl());
            processDetail(page);
        }
        //list page
        else if (page.getUrl().regex(URL_LIST).match()) {
            logger.log(Level.DEBUG, "处理页面:" + page.getUrl());
            processList(page);
        }
        //detail page
        else if (page.getUrl().regex(URL_HOME).match()) {
            logger.log(Level.DEBUG, "处理页面:" + page.getUrl());
            processList(page);
        } else {
            logger.log(Level.WARN, "没有爬取的页面:" + page.getUrl());
        }
    }

    public abstract void processList(Page page);

    public abstract void processDetail(Page page);


    public void addListNext(Page page, String xpath) {
        List<Selectable> nexts = page.getHtml().xpath(xpath).nodes();
        if (nexts != null) {
            for (int i = nexts.size() - 1; i >= 0; i--) {
                Selectable p = nexts.get(i);
                if ((p.get().contains("下一页") || p.get().contains("下一张")
                        || p.get().contains("»") || p.xpath("//a/text()").get().contains(">"))
                        && !page.getUrl().get().equals(p.links().get())
                        ) {
                    page.addTargetRequest(p.links().get());
                    break;
                }
            }
        }
    }

    public void addDetailNext(Page page, String xpath) {
        List<Selectable> nexts = page.getHtml().xpath(xpath).nodes();
        if (nexts != null && nexts.size() > 0) {
            for (int i = nexts.size() - 1; i >= 0; i--) {
                Selectable n = nexts.get(i);
                if (n.get() != null
                        && (n.get().contains("下一页") || n.get().contains("下一张"))
                        && !n.get().contains(page.getUrl().get())
                        ) {
                    String next = n.links().get();
                    if (next.contains("http")) {
                        Request req = new Request(next).setPriority(1)
                                .putExtra("galleryTitle", page.getRequest().getExtra("galleryTitle"))
                                .putExtra("galleryUrl", page.getRequest().getExtra("galleryUrl"));
                        page.addTargetRequest(req);
                        break;
                    }
                }
            }
        }
    }

}
