package f.star.iota.milk.ui.rosimm.rosi;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class ROSIMMPresenter extends StringPresenter<List<ROSIMMBean>> {

    public ROSIMMPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<ROSIMMBean> dealResponse(String s, HashMap<String, String> headers) {
        List<ROSIMMBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("body > section > div.excerpts-wrapper > div.excerpts > article.excerpt");
        for (Element element : select) {
            ROSIMMBean bean = new ROSIMMBean();
            String preview = element.select("a > img").attr("src");
            bean.setPreview(preview);
            String url = element.select("a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("h2").text();
            bean.setDescription(description);
            list.add(bean);
        }
        return list;
    }
}
