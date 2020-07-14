package com.teach.teach1907.view.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.data.GroupDetailEntity;
import com.teach.teach1907.R;
import com.teach.teach1907.adapter.DataGroupDetailBottomAdapter;
import com.teach.teach1907.base.BaseMvpFragment;
import com.teach.teach1907.model.DataModel;

import java.util.ArrayList;

import butterknife.BindView;

public class DataDetailChildFragment extends BaseMvpFragment<DataModel> {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArrayList<GroupDetailEntity.Thread> mList;

    public static DataDetailChildFragment newInstance(ArrayList<GroupDetailEntity.Thread> param1) {
        DataDetailChildFragment fragment = new DataDetailChildFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mList = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Override
    public DataModel setModel() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.refresh_list_layout;
    }

    @Override
    public void setUpView() {
        initRecyclerView(recyclerView, null, null);
        DataGroupDetailBottomAdapter adapter = new DataGroupDetailBottomAdapter(getContext(), mList);
        recyclerView.setAdapter(adapter);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
