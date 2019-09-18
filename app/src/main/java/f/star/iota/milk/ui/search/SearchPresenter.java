package f.star.iota.milk.ui.search;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Menus;
import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class SearchPresenter extends StringPresenter<List<SearchBean>> {

    private  int type;


    public SearchPresenter(PVContract.View view,int type) {
        super(view);
        this.type = type;
    }

    @Override
    protected List<SearchBean> dealResponse(String s, HashMap<String, String> headers) {
        List<SearchBean> list = new ArrayList<>();
        Elements select = null;
        switch (type) {
            case Menus.MENU_XSNVSHEN_MEINV_ID:
                select = Jsoup.parse(s).select("#newspiclist > li");
                for (Element element : select) {
                    SearchBean bean = new SearchBean();
                    String preview = element.select("div.picbox > a > img").attr("src");
                    bean.setPreview("https:"+preview);
                    String url = element.select("div.picbox > a").attr("href");
                    bean.setUrl(Net.XSNVSHEN_BASE + url);
                    bean.setHeaders(headers);
                    String description = element.select("div.titlebox").text();
                    bean.setDescription(description);
                    bean.setType(type);
                    list.add(bean);
                }
                break;
            case Menus.MENU_MEITUMEN_MENU_ID:
                select = Jsoup.parse(s).select("#load-img > div.wtn > div.pos__1.camWholeBox.borderTop > ul > li.min-h-imgall");
                for (Element element : select) {
                    SearchBean bean = new SearchBean();
                    String preview = element.select("a > img").attr("src");
                    bean.setPreview(preview);
                    String url = element.select("a").attr("href");
                    bean.setUrl(url);
                    bean.setHeaders(headers);
                    String description = element.select("a").attr("title");
                    bean.setDescription(description);
                    bean.setType(type);
                    list.add(bean);
                }
                break;
            case Menus.MENU_MEITULU_ID:
                select = Jsoup.parse(s).select("body > div.main > div.boxs > ul > li");
                for (Element element : select) {
                    SearchBean bean = new SearchBean();
                    String preview = element.select("a > img").attr("src");
                    bean.setPreview(preview);
                    String url = element.select("a").attr("href");
                    bean.setUrl(url);
                    bean.setHeaders(headers);
                    String description = element.select("a > img").attr("alt");
                    bean.setDescription(description);
                    bean.setType(type);
                    list.add(bean);
                }
                break;
            case Menus.MENU_MZITU_ID:
                select = Jsoup.parse(s).select("#pins > li");
                for (Element element : select) {
                    SearchBean bean = new SearchBean();
                    String preview = element.select("a > img").attr("data-original");
                    bean.setPreview(preview);
                    String url = element.select("span:nth-child(2) > a").attr("href") + "/";
                    bean.setUrl(url);
                    bean.setHeaders(headers);
                    String description = element.select("span:nth-child(2) > a").text();
                    bean.setDescription(description);
                    bean.setType(type);
                    list.add(bean);
                }
                break;
            case Menus.MENU_GIRLSKY_ID:
                select = Jsoup.parse(s).select("body > div.wrap > div.TypeList > ul > li");
                for (Element element : select) {
                    SearchBean bean = new SearchBean();
                    String preview = element.select("a.TypeBigPics > img").attr("data-original");
                    if(!preview.contains("http")){
                        preview = "http:" + preview;
                    }
                    bean.setPreview(preview);
                    String url = element.select("a").attr("href");
                    bean.setUrl(url);
                    bean.setHeaders(headers);
                    String description = element.select("a > span").text();
                    bean.setDescription(description);
                    bean.setType(type);
                    list.add(bean);
                }
                break;
        }
        return list;
    }

}
