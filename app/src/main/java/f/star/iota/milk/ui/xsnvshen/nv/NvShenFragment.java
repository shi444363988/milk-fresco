package f.star.iota.milk.ui.xsnvshen.nv;


import android.os.Bundle;

import f.star.iota.milk.Menus;
import f.star.iota.milk.base.MoreScrollImageFragment;


public class NvShenFragment extends MoreScrollImageFragment<NvShenPresenter, NvShenAdapter> {


    public static NvShenFragment newInstance(String url) {
        NvShenFragment fragment = new NvShenFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url",url+"?p=");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected NvShenPresenter getPresenter() {
        return new NvShenPresenter(this);
    }

    @Override
    protected NvShenAdapter getAdapter() {
        return new NvShenAdapter();
    }

    @Override
    public int getFragmentMenuID() {
        return Menus.MENU_XSNVSHEN_MEINV_ID;
    }
}
