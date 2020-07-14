package com.teach.teach1907.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.teach.data.LessonChapterEntity;
import com.teach.data.LessonPartEntity;
import com.teach.teach1907.R;
import com.teach.teach1907.interfaces.OnRecyclerItemClick;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2020/6/23.
 */
public class CourseListGroupAdapter extends RecyclerView.Adapter<CourseListGroupAdapter.ViewHolder> {

    private Context mContext;
    private List<LessonChapterEntity> mCatalogueList;
    private List<String> mMergeList;

    public CourseListGroupAdapter(Context pContext, List<LessonChapterEntity> pCatalogueList, List<String> pMergeList) {
        mContext = pContext;
        mCatalogueList = pCatalogueList;
        mMergeList = pMergeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.course_group_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LessonChapterEntity entity = mCatalogueList.get(position);
        holder.groupName.setText(entity.getCatalogueName());
        holder.ivCollapsed.setImageResource(mMergeList.contains(entity.getCatalogueName()) ? R.drawable.ic_lesson_expand : R.drawable.ic_lesson_collapsed);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        holder.groupChildRecycle.setLayoutManager(manager);
        ((SimpleItemAnimator)holder.groupChildRecycle.getItemAnimator()).setSupportsChangeAnimations(false);
        ArrayList<LessonPartEntity> partList = entity.getPartList();
        CourseListChildAdapter childAdapter = new CourseListChildAdapter(mContext, partList);
        holder.groupChildRecycle.setAdapter(childAdapter);
        holder.groupChildRecycle.setVisibility(mMergeList.contains(entity.getCatalogueName()) ? View.GONE : View.VISIBLE);
        holder.groupName.setOnClickListener(v -> {
            if (mOnRecyclerItemClick != null)mOnRecyclerItemClick.onItemClick(position);
        });
        childAdapter.setOnRecyclerItemClick((pos, pObjects) -> {
            if (mOnCourseGroupListClick != null)mOnCourseGroupListClick.onItemClick(position,pos, (int) pObjects[0]);
        });
    }

    public interface OnCourseGroupListClick{
        void onItemClick(int fatherPos,int childPos,int clickType);
    }

    private OnCourseGroupListClick mOnCourseGroupListClick;

    //协助子条目回调item点击播放视频、点击下载
    public void setOnCourseGroupListClick(OnCourseGroupListClick pOnCourseGroupListClick){
        mOnCourseGroupListClick = pOnCourseGroupListClick;
    }

    private OnRecyclerItemClick mOnRecyclerItemClick;

    //控制展开合并的监听
    public void setOnRecyclerItemClick(OnRecyclerItemClick pOnRecyclerItemClick){
        mOnRecyclerItemClick = pOnRecyclerItemClick;
    }

    @Override
    public int getItemCount() {
        return mCatalogueList != null ? mCatalogueList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.groupName)
        TextView groupName;
        @BindView(R.id.iv_collapsed)
        ImageView ivCollapsed;
        @BindView(R.id.groupChildRecycle)
        RecyclerView groupChildRecycle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
