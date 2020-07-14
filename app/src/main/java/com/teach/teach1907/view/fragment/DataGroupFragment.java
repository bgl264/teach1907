package com.teach.teach1907.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.data.BaseInfo;
import com.teach.data.DataGroupListEntity;
import com.teach.frame.ApiConfig;
import com.teach.frame.FrameApplication;
import com.teach.frame.LoadTypeConfig;
import com.teach.frame.constants.ConstantKey;
import com.teach.teach1907.R;
import com.teach.teach1907.adapter.DataGroupAdapter;
import com.teach.teach1907.base.BaseMvpFragment;
import com.teach.teach1907.interfaces.DataListener;
import com.teach.teach1907.interfaces.OnRecyclerItemClick;
import com.teach.teach1907.model.DataModel;
import com.teach.teach1907.view.activity.HomeActivity;
import com.teach.teach1907.view.activity.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.teach.teach1907.adapter.DataGroupAdapter.FOCUS_TYPE;
import static com.teach.teach1907.adapter.DataGroupAdapter.ITEM_TYPE;
import static com.teach.teach1907.constants.JumpConstant.*;

public class DataGroupFragment extends BaseMvpFragment<DataModel> implements DataListener, OnRecyclerItemClick {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private List<DataGroupListEntity> mList = new ArrayList<>();
    private DataGroupAdapter mAdapter;

    public static DataGroupFragment newInstance() {
        DataGroupFragment fragment = new DataGroupFragment();
        return fragment;
    }

    @Override
    public DataModel setModel() {
        return new DataModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.refresh_list_layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        HomeActivity a = (HomeActivity) getActivity();
        if (a.isBackFromDetail)dataType(LoadTypeConfig.REFRESH);
    }

    @Override
    public void setUpView() {
        initRecyclerView(recyclerView, refreshLayout, this);
        mAdapter = new DataGroupAdapter(mList, getContext());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnRecyclerItemClick(this);
    }

    @Override
    public void setUpData() {
        mPresenter.allowLoading(getActivity());
        mPresenter.getData(ApiConfig.DATA_GROUP, LoadTypeConfig.NORMAL, page);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi){
            case ApiConfig.DATA_GROUP:
                BaseInfo<List<DataGroupListEntity>> info = (BaseInfo<List<DataGroupListEntity>>) pD[0];
                if (info.isSuccess()) {
                    List<DataGroupListEntity> result = info.result;
                    int loadMode = (int) pD[1];
                    if (loadMode == LoadTypeConfig.REFRESH) {
                        mList.clear();
                        refreshLayout.finishRefresh();
                    } else if (loadMode == LoadTypeConfig.MORE) refreshLayout.finishLoadMore();
                    mList.addAll(result);
                    mAdapter.notifyDataSetChanged();
                }
                break;
            case ApiConfig.CLICK_CANCEL_FOCUS:
                BaseInfo base = (BaseInfo) pD[0];
                int clickPos = (int) pD[1];
                if (base.isSuccess()){
                    showToast("取消成功");
                    mList.get(clickPos).setIs_ftop(0);
                    mAdapter.notifyItemChanged(clickPos);
                }
                break;
            case ApiConfig.CLICK_TO_FOCUS:
                BaseInfo baseSuc = (BaseInfo) pD[0];
                int clickJoinPos = (int) pD[1];
                if (baseSuc.isSuccess()){
                    showToast("关注成功");
                    mList.get(clickJoinPos).setIs_ftop(1);
                    mAdapter.notifyItemChanged(clickJoinPos);
                }
                break;
        }
    }

    @Override
    public void dataType(int mode) {
        if (mode == LoadTypeConfig.REFRESH) {
            mPresenter.getData(ApiConfig.DATA_GROUP, LoadTypeConfig.REFRESH, 1);
        } else {
            page++;
            mPresenter.getData(ApiConfig.DATA_GROUP, LoadTypeConfig.MORE, page);
        }
    }

    @Override
    public void onItemClick(int pos, Object[] pObjects) {
        if (pObjects != null && pObjects.length != 0){
            switch ((int)pObjects[0]){
                case ITEM_TYPE:
                    HomeActivity activity = (HomeActivity) getActivity();
                    Bundle bundle = new Bundle();
                    bundle.putString(ConstantKey.GROU_TO_DETAIL_GID,mList.get(pos).getGid());
                    bundle.putString(ConstantKey.GROU_TO_DETAIL_NAME,mList.get(pos).getGroup_name());
                    activity.mProjectController.navigate(R.id.dataGroupDetailFragment,bundle);
                    break;
                case FOCUS_TYPE:
                    boolean login = FrameApplication.getFrameApplication().isLogin();
                    if (login){
                        DataGroupListEntity entity = mList.get(pos);
                        if (entity.isFocus()){//已经关注，取消关注
                            mPresenter.getData(ApiConfig.CLICK_CANCEL_FOCUS,entity.getGid(),pos);//绿码
                        } else  {//没有关注，点击关注
                            mPresenter.getData(ApiConfig.CLICK_TO_FOCUS,entity.getGid(),entity.getGroup_name(),pos);
                        }
                    } else {
                        startActivity(new Intent(getContext(), LoginActivity.class).putExtra(JUMP_KEY,DATAGROUPFRAGMENT_TO_LOGIN));
                    }
                    break;
            }
        }
    }
}
