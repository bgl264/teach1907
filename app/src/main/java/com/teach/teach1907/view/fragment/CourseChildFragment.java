package com.teach.teach1907.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.data.BaseInfo;
import com.teach.data.CourseListInfo;
import com.teach.data.SearchItemEntity;
import com.teach.frame.ApiConfig;
import com.teach.frame.LoadTypeConfig;
import com.teach.teach1907.R;
import com.teach.teach1907.adapter.CourseChildAdapter;
import com.teach.teach1907.base.BaseMvpFragment;
import com.teach.teach1907.interfaces.DataListener;
import com.teach.teach1907.interfaces.OnRecyclerItemClick;
import com.teach.teach1907.model.CourseModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CourseChildFragment extends BaseMvpFragment<CourseModel> implements DataListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int mIndex;
    private int page = 1;
    private  List<SearchItemEntity> mList = new ArrayList<>();
    private CourseChildAdapter mAdapter;

    public static CourseChildFragment getInstance(int index) {
        CourseChildFragment fragment = new CourseChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("whichFragment", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIndex = (int) getArguments().get("whichFragment");
        }
    }

    @Override
    public CourseModel setModel() {
        return new CourseModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.refresh_list_layout;
    }

    @Override
    public void setUpView() {
        initRecyclerView(recyclerView, refreshLayout, this);
        mAdapter = new CourseChildAdapter(mList, getContext());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnRecyclerItemClick((pos, pObjects) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("itemInfo",mList.get(pos));
            bundle.putString("record", "0");
            bundle.putString("f", "1004");
            getHomeActivity().mProjectController.navigate(R.id.home_to_course_detail,bundle);
        });
    }

    @Override
    public void setUpData() {
        mPresenter.allowLoading(getActivity());
        mPresenter.getData(ApiConfig.COURSE_CHILD, LoadTypeConfig.NORMAL, mIndex, page);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        BaseInfo<CourseListInfo> baseInfo = (BaseInfo<CourseListInfo>) pD[0];
        if (baseInfo.isSuccess()){
            List<SearchItemEntity> lists = baseInfo.result.lists;
            int load = (int) pD[1];
            if (load == LoadTypeConfig.REFRESH){
                refreshLayout.finishRefresh();
                mList.clear();
            } else if (load == LoadTypeConfig.MORE)refreshLayout.finishLoadMore();
            mList.addAll(lists);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void dataType(int mode) {
        if (mode == LoadTypeConfig.REFRESH)
            mPresenter.getData(ApiConfig.COURSE_CHILD, LoadTypeConfig.REFRESH, mIndex, 1);
        else {
            page++;
            mPresenter.getData(ApiConfig.COURSE_CHILD, LoadTypeConfig.MORE, mIndex, page);
        }
    }
}
