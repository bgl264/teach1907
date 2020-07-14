package com.teach.teach1907.view.fragment;

import com.teach.teach1907.R;
import com.teach.teach1907.base.BaseMvpFragment;
import com.teach.teach1907.model.DataModel;

public class RecentlyBestFragment extends BaseMvpFragment<DataModel> {

    public static RecentlyBestFragment newInstance() {
        RecentlyBestFragment fragment = new RecentlyBestFragment();
        return fragment;
    }


    @Override
    public DataModel setModel() {
        return new DataModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_recently_best;
    }

    @Override
    public void setUpView() {

    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
