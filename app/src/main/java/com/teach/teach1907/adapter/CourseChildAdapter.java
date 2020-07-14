package com.teach.teach1907.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teach.data.SearchItemEntity;
import com.teach.teach1907.R;
import com.teach.teach1907.interfaces.OnRecyclerItemClick;
import com.yiyatech.utils.newAdd.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2020/6/9.
 */
public class CourseChildAdapter extends RecyclerView.Adapter<CourseChildAdapter.ViewHodler> {

    private List<SearchItemEntity> mList;
    private Context mContext;

    public CourseChildAdapter(List<SearchItemEntity> pList, Context pContext) {
        mList = pList;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHodler(LayoutInflater.from(mContext).inflate(R.layout.course_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        if (mList == null || mList.size() == 0) return;
        SearchItemEntity entity = mList.get(position);
        holder.tvTitle.setText(entity.getLesson_name());
        GlideUtil.loadImage(holder.ivPhoto, entity.getThumb());
        holder.tvLearnNum.setText(entity.getStudentnum() + "人学习");
        holder.tvLikeNum.setText("好评率" + entity.getRate());
        holder.tvPrice.setText("￥" + entity.getPrice());
        holder.itemView.setOnClickListener(v -> {
            if (mOnRecyclerItemClick != null)mOnRecyclerItemClick.onItemClick(position);
        });
    }

    private OnRecyclerItemClick mOnRecyclerItemClick;

    public void setOnRecyclerItemClick(OnRecyclerItemClick pOnRecyclerItemClick){
        mOnRecyclerItemClick = pOnRecyclerItemClick;
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_learn_num)
        TextView tvLearnNum;
        @BindView(R.id.tv_like_num)
        TextView tvLikeNum;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
