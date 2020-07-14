package com.teach.teach1907.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teach.data.LessonPartEntity;
import com.teach.teach1907.R;
import com.teach.teach1907.interfaces.OnRecyclerItemClick;
import com.teach.teach1907.view.design.DownLoadRoundProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2020/6/23.
 */
public class CourseListChildAdapter extends RecyclerView.Adapter<CourseListChildAdapter.ViewHolder> {

    public static final int ITEM_TYPE = 0,DOWNLOAD_TYPE = 1;

    private Context mContext;
    private ArrayList<LessonPartEntity> partList;

    public CourseListChildAdapter(Context pContext, ArrayList<LessonPartEntity> pPartList) {
        mContext = pContext;
        partList = pPartList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.course_child_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LessonPartEntity entity = partList.get(position);
        holder.tvPartName.setText(!TextUtils.isEmpty(entity.getPart_name()) ? entity.getPart_name() : !TextUtils.isEmpty(entity.getLive_name()) ? entity.getLive_name() : "无名课程");
        holder.videoTime.setText(!TextUtils.isEmpty(entity.getMins())?entity.getMins():"0" + "分钟");
        if (!TextUtils.isEmpty(entity.getCcvideo_size())){
            holder.videoSize.setVisibility(View.VISIBLE);
            holder.videoSize.setText(entity.getCcvideo_size());
        } else holder.videoSize.setVisibility(View.GONE);
        holder.tvFreeStudy.setVisibility(entity.IsDome() ? View.VISIBLE : View.GONE);
        holder.studyProgress.setText("已学"+entity.getMins_hund()+"%");
        holder.itemView.setOnClickListener(v -> {
            if (mOnRecyclerItemClick != null)mOnRecyclerItemClick.onItemClick(position,ITEM_TYPE);
        });
        holder.DLRProgressBar.setOnClickListener(v -> {
            if (mOnRecyclerItemClick != null)mOnRecyclerItemClick.onItemClick(position,DOWNLOAD_TYPE);
        });
    }

    private OnRecyclerItemClick mOnRecyclerItemClick;

    public void setOnRecyclerItemClick(OnRecyclerItemClick pOnRecyclerItemClick){
        mOnRecyclerItemClick = pOnRecyclerItemClick;
    }

    @Override
    public int getItemCount() {
        return partList != null ? partList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.leftIcon)
        ImageView leftIcon;
        @BindView(R.id.tv_part_name)
        TextView tvPartName;
        @BindView(R.id.tv_free_study)
        TextView tvFreeStudy;
        @BindView(R.id.videoTime)
        TextView videoTime;
        @BindView(R.id.videoSize)
        TextView videoSize;
        @BindView(R.id.study_progress)
        TextView studyProgress;
        @BindView(R.id.DLR_ProgressBar)
        DownLoadRoundProgressBar DLRProgressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
