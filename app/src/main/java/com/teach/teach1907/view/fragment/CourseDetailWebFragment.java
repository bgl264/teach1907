package com.teach.teach1907.view.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.data.CourseDetailInfo;
import com.teach.frame.constants.ConstantKey;
import com.teach.teach1907.R;
import com.teach.teach1907.base.BaseMvpFragment;
import com.teach.teach1907.model.CourseModel;
import com.teach.teach1907.view.design.RichWebView;

import butterknife.BindView;

/**
 * Created by 任小龙 on 2020/6/21.
 */
public class CourseDetailWebFragment extends BaseMvpFragment<CourseModel> {
    @BindView(R.id.webView)
    RichWebView webView;
    @BindView(R.id.webRefreshLayout)
    SmartRefreshLayout webRefreshLayout;
    private CourseDetailInfo mCourseDetailInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCourseDetailInfo = getArguments().getParcelable(ConstantKey.COURSE_DESCRIBE);
        }
    }

    public static CourseDetailWebFragment getInstance(CourseDetailInfo courseInfo) {
        CourseDetailWebFragment fragment = new CourseDetailWebFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantKey.COURSE_DESCRIBE, courseInfo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public CourseModel setModel() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_course_detail_web;
    }

    @Override
    public void setUpView() {
        webRefreshLayout.setEnableLoadMore(false);
        CourseDetailFragment parentFragment = (CourseDetailFragment) getParentFragment();
        webRefreshLayout.setOnRefreshListener(refreshLayout -> {
            refreshLayout.finishRefresh();
            parentFragment.viewPager11.setCurrentItem(0);
        });
        if (!TextUtils.isEmpty(mCourseDetailInfo.getZt_url())){
            webView.loadUrl(mCourseDetailInfo.getZt_url());
        } else {
            webView.loadDataWithBaseURL("about:blank",mCourseDetailInfo.getInfo(),"text/html", "UTF-8", "about:blank");
        }
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(webView != null){
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
    }
}
