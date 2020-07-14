package com.teach.teach1907.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.data.BaseInfo;
import com.teach.data.CourseDetailInfo;
import com.teach.data.LoginInfo;
import com.teach.data.SearchItemEntity;
import com.teach.frame.ApiConfig;
import com.teach.frame.FrameApplication;
import com.teach.frame.constants.ConstantKey;
import com.teach.frame.utils.ParamHashMap;
import com.teach.teach1907.R;
import com.teach.teach1907.adapter.MyFragmentAdapter;
import com.teach.teach1907.base.BaseMvpFragment;
import com.teach.teach1907.model.CourseModel;
import com.teach.teach1907.utils.PagerSelectedListener;
import com.teach.teach1907.view.activity.LoginActivity;
import com.yiyatech.utils.newAdd.DensityUtil;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.teach.teach1907.constants.JumpConstant.*;

public class CourseDetailFragment extends BaseMvpFragment<CourseModel> {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_collect_share_bar)
    LinearLayout llCollectShareBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.courseDetailSlideTab)
    SlidingTabLayout courseDetailSlideTab;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.viewPager11)
    ViewPager viewPager11;
    @BindView(R.id.nullLoadLayout)
    public SmartRefreshLayout nullLoadLayout;
    @BindView(R.id.collect)
    ImageView collect;
    @BindView(R.id.buy_course)
    TextView buyCourse;
    @BindView(R.id.share)
    public ImageView mShareImage;
    @BindView(R.id.toolbar_layout)
    public CollapsingToolbarLayout collapsing;
    @BindView(R.id.video_bg)
    RelativeLayout videoBack;
    private SearchItemEntity itemInfo;
    private String mCodeF;
    private String mRecord;
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private MyFragmentAdapter mMyFragmentAdapter;
    public CourseDetailInfo mResult;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            itemInfo = (SearchItemEntity) getArguments().getSerializable("itemInfo");
            mCodeF = getArguments().getString("f");
            mRecord = getArguments().getString("record");
        }
    }

    @Override
    public CourseModel setModel() {
        return new CourseModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_course_detail;
    }

    @Override
    public void setUpView() {
        getHomeActivity().setSupportActionBar(toolbar);
        collapsing.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.app_theme));
        nullLoadLayout.setEnableLoadMore(false);
        nullLoadLayout.setEnableRefresh(false);
        mMyFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), mFragments, mTitleList);
        viewPager11.setAdapter(mMyFragmentAdapter);
        courseDetailSlideTab.setViewPager(viewPager11);
        viewPager11.addOnPageChangeListener(new PagerSelectedListener() {
            @Override
            public void pageSelected(int position) {
                videoBack.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void setUpData() {
        mPresenter.allowLoading(getActivity());
        ParamHashMap add = new ParamHashMap().add("lesson_id", itemInfo.getLesson_id()).add("from_type", itemInfo.getType()).add("f", mCodeF)
                .add("width", Float.valueOf(DensityUtil.px2dip(getContext(), DensityUtil.getDisplaySize(getContext()).x) + ""))
                .add(ConstantKey.SECRET_KEY, getString(R.string.secrectKey));
        mPresenter.getData(ApiConfig.COURSE_DETAIL_INFO, add);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.COURSE_DETAIL_INFO:
                BaseInfo<CourseDetailInfo> info = (BaseInfo<CourseDetailInfo>) pD[0];
                if (info.isSuccess()) {
                    mResult = info.result;
                    tvTitle.setText(mResult.getLesson_name());
                    GlideUtil.loadImage(image, mResult.getThumb());
                    if (mResult.getIs_collect() != null)collect.setImageDrawable(ContextCompat.getDrawable(getContext(), mResult.getIs_collect().equals("0") ? R.drawable.collect_white : R.drawable.collect_red));
                    mResult.setBelongToUser(mResult.isBought() || mResult.isVip() && mResult.isVipCourse());
                    boolean b = mResult.getCourse_type().equals("3") && !mResult.isBelongToUser() ||
                            mResult.getCatalogue() == null || mResult.getCatalogue().getCatalogueList() == null ||
                            mResult.getCatalogue().getCatalogueList().size() == 0;
                    mResult.setHideLessonList(b);
                    buyCourse.setText(mResult.isLessonFree() ? "立即听课" : "立即购买");
                    if (mResult.isHideLessonList()) {
                        Collections.addAll(mTitleList, "简介", "详情", "评价");
                    } else {
                        Collections.addAll(mTitleList, "简介", "详情", "课表", "评价");
                    }
                    mFragments.add(CourseDescribeFragment.getInstance(mResult,mCodeF,info.discount_info));
                    mFragments.add(CourseDetailWebFragment.getInstance(mResult));
                    if (!mResult.isHideLessonList())mFragments.add(CourseDetailListFragment.getInstance(mResult,mCodeF,""));
                    mFragments.add(CourseDetailCommentFragment.getInstance(mResult,itemInfo,"评价"));
                    courseDetailSlideTab.notifyDataSetChanged();
                    mMyFragmentAdapter.notifyDataSetChanged();
                } else {
                    showToast(info.msg);
                    if (info.msg.equals("登录信息过期，请重新登录")) ;
                    FrameApplication.getFrameApplication().setLoginInfo(new LoginInfo());
                    SharedPrefrenceUtils.putObject(getContext(), ConstantKey.LOGIN_INFO, FrameApplication.getFrameApplication().getLoginInfo());
                    startActivity(new Intent(getContext(), LoginActivity.class).putExtra(JUMP_KEY, COURSE_DETAIL_TO_LOGIN));
                }
                break;
        }
    }

    @OnClick({R.id.play, R.id.back, R.id.collect, R.id.share, R.id.ask_teacher, R.id.buy_course})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (mResult != null && !mResult.isHideLessonList())viewPager11.setCurrentItem(2);
                break;
            case R.id.back:
                NavHostFragment.findNavController(this).navigateUp();
                break;
            case R.id.collect:
                break;
            case R.id.share:
                break;
            case R.id.ask_teacher:
                break;
            case R.id.buy_course:
                break;
        }
    }
}
