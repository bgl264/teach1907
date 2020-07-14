package com.teach.teach1907.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.teach.data.CourseDetailInfo;
import com.teach.data.LessonChapterEntity;
import com.teach.frame.constants.ConstantKey;
import com.teach.teach1907.R;
import com.teach.teach1907.adapter.CourseListChildAdapter;
import com.teach.teach1907.adapter.CourseListGroupAdapter;
import com.teach.teach1907.base.BaseMvpFragment;
import com.teach.teach1907.interfaces.OnRecyclerItemClick;
import com.teach.teach1907.model.CourseModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 任小龙 on 2020/6/21.
 */
public class CourseDetailListFragment extends BaseMvpFragment<CourseModel> implements OnRecyclerItemClick, CourseListGroupAdapter.OnCourseGroupListClick {
    @BindView(R.id.courseListRecycle)
    RecyclerView courseListRecycle;
    private CourseDetailInfo courseInfo;
    private String code_f;
    private String homeLiveId;
    private List<LessonChapterEntity> mCatalogueList;
    private CourseListGroupAdapter mAdapter;
    List<String> mMergeList = new ArrayList<>();
    private CourseListChildAdapter mChildAdapter;

    public static CourseDetailListFragment getInstance(CourseDetailInfo courseInfo, String code_f, String homeLiveId) {
        CourseDetailListFragment fragment = new CourseDetailListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantKey.COURSE_LIST, courseInfo);
        bundle.putString(ConstantKey.COURSE_LIST_CODE_F, code_f);
        bundle.putString(ConstantKey.COURSE_LIST_HOMELIVE_ID, homeLiveId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            courseInfo = getArguments().getParcelable(ConstantKey.COURSE_LIST);
            code_f = getArguments().getString(ConstantKey.COURSE_LIST_CODE_F);
            homeLiveId = getArguments().getString(ConstantKey.COURSE_LIST_HOMELIVE_ID);
            mCatalogueList = courseInfo.getCatalogue().getCatalogueList();
        }
    }

    @Override
    public CourseModel setModel() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_course_detail_list;
    }

    @Override
    public void setUpView() {
        initRecyclerView(courseListRecycle, null, null);
        if (mCatalogueList.size() > 1){
            mAdapter = new CourseListGroupAdapter(getContext(), mCatalogueList,mMergeList);
            courseListRecycle.setAdapter(mAdapter);
            mAdapter.setOnRecyclerItemClick(this);
            mAdapter.setOnCourseGroupListClick(this);
        } else if (mCatalogueList.size() == 1){
            mChildAdapter = new CourseListChildAdapter(getContext(), mCatalogueList.get(0).getPartList());
            courseListRecycle.setAdapter(mChildAdapter);
            mChildAdapter.setOnRecyclerItemClick((pos, pObjects) -> {
                showLog("pos:"+pos+","+((int)pObjects[0]== CourseListChildAdapter.ITEM_TYPE?"播放":"下载"));
            });
        }
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }

    /**
     * 控制组条目点击时展开和合并组内子recycleview成员
     * @param pos
     * @param pObjects
     */
    @Override
    public void onItemClick(int pos, Object[] pObjects) {
        String name = mCatalogueList.get(pos).getCatalogueName();
        if (mMergeList.contains(name))mMergeList.remove(name);
        else mMergeList.add(name);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 当有分组时，控制适配器中recycleview的条目点击逻辑
     * @param fatherPos 子条目所对应父条目的位置
     * @param childPos 子条目的位置
     * @param clickType 点击类型
     */
    @Override
    public void onItemClick(int fatherPos, int childPos, int clickType) {
        showLog("fatherPos:"+fatherPos+",childPos:"+childPos+","+(clickType == CourseListChildAdapter.ITEM_TYPE?"播放":"下载"));
    }
}
