package f.star.iota.milk.ui.itmtu.mtu;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class MtuPresenter extends StringPresenter<List<MtuBean>> {

    public MtuPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<MtuBean> dealResponse(String s, HashMap<String, String> headers) {
        List<MtuBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#image_div > p > a > img");
        for (Element element : select) {
            MtuBean bean = new MtuBean();
            String url = element.select("img").attr("src");
            bean.setUrl(url);
            bean.setHeaders(headers);
            list.add(bean);
        }
        return list;
    }

    @Override
    protected String dealUrl(String url) {
        if (url.contains("_1.html")) {
            url = url.replace("_1.html", ".html");
        }
        return url;
    }

}
