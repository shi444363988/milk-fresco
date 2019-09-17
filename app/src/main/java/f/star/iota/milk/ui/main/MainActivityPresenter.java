package f.star.iota.milk.ui.main;


import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import java.util.List;
import java.util.Map;

import f.star.iota.milk.Net;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private final MainActivityContract.View view;

    private final CompositeDisposable mCompositeDisposable;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void get() {
        int random = (int) (Math.random() * 4);
        random=1;
        if (random == 0) {
            mCompositeDisposable.add(
                    OkGo.<String>get(Net.HITOKOTO_BILIBILIJJ)
                            .converter(new StringConvert())
                            .adapt(new ObservableResponse<String>())
                            .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                            .map(new Function<Response<String>, JuZiBean>() {
                                @Override
                                public JuZiBean apply(@NonNull Response<String> response) throws Exception {
                                    String s = response.body();
                                    // s = s.substring(s.indexOf("(", 0) + 1, s.lastIndexOf(")"));
                                    Map map = new Gson().fromJson(s,Map.class);
                                    JuZiBean bean = null;
                                    if (map == null) {
                                        bean = new JuZiBean();
                                        bean.setHitokoto("解析错误，可能出现未知问题");
                                        bean.setSource("...");
                                    } else {
                                        bean = new JuZiBean();
                                        bean.setHitokoto((String) ((Map)((List)map.get("res")).get(0)).get("hitokoto"));
                                        bean.setSource((String) ((Map)((List)map.get("res")).get(0)).get("source"));
                                    }
                                    return bean;
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<JuZiBean>() {
                                @Override
                                public void accept(@NonNull JuZiBean bean) throws Exception {
                                    view.getSuccess(bean);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    JuZiBean bean = new JuZiBean();
                                    bean.setHitokoto(throwable.getMessage());
                                    bean.setSource("...");
                                    view.getSuccess(bean);
                                }
                            })
            );
        } else if (random == 1) {
            mCompositeDisposable.add(
                    OkGo.<String>get(Net.YIJU)
                            .converter(new StringConvert())
                            .adapt(new ObservableResponse<String>())
                            .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                            .map(new Function<Response<String>, JuZiBean>() {
                                @Override
                                public JuZiBean apply(@NonNull Response<String> response) throws Exception {
                                    JuZiBean bean = new JuZiBean();
                                    String s = response.body();
                                    if (s.contains("——")) {
                                        String ju = s.substring(0, s.indexOf("——"));
                                        bean.setHitokoto(ju);
                                        String source = s.substring(s.indexOf("——") + 2, s.length());
                                        bean.setSource(source);
                                    } else {
                                        bean.setHitokoto(s);
                                        bean.setSource("...");
                                    }
                                    return bean;
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<JuZiBean>() {
                                @Override
                                public void accept(@NonNull JuZiBean bean) throws Exception {
                                    view.getSuccess(bean);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    JuZiBean bean = new JuZiBean();
                                    bean.setHitokoto(throwable.getMessage());
                                    bean.setSource("...");
                                    view.getSuccess(bean);
                                }
                            })
            );
        } else if (random == 2) {
            mCompositeDisposable.add(
                    OkGo.<String>get(Net.HITOKOTO_IMJAD)
                            .converter(new StringConvert())
                            .adapt(new ObservableResponse<String>())
                            .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                            .map(new Function<Response<String>, JuZiBean>() {
                                @Override
                                public JuZiBean apply(@NonNull Response<String> response) throws Exception {
                                    return new Gson().fromJson(response.body(), JuZiBean.class);
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<JuZiBean>() {
                                @Override
                                public void accept(@NonNull JuZiBean bean) throws Exception {
                                    view.getSuccess(bean);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    JuZiBean bean = new JuZiBean();
                                    bean.setHitokoto(throwable.getMessage());
                                    bean.setSource("...");
                                    view.getSuccess(bean);
                                }
                            })
            );
        } else if (random == 3) {
            mCompositeDisposable.add(
                    OkGo.<String>get(Net.HITOKOTO_LOLI)
                            .converter(new StringConvert())
                            .adapt(new ObservableResponse<String>())
                            .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                            .map(new Function<Response<String>, JuZiBean>() {
                                @Override
                                public JuZiBean apply(@NonNull Response<String> response) throws Exception {
                                    return new Gson().fromJson(response.body(), JuZiBean.class);
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<JuZiBean>() {
                                @Override
                                public void accept(@NonNull JuZiBean bean) throws Exception {
                                    view.getSuccess(bean);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    JuZiBean bean = new JuZiBean();
                                    bean.setHitokoto(throwable.getMessage());
                                    bean.setSource("...");
                                    view.getSuccess(bean);
                                }
                            })
            );
        }
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

}
