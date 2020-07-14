package com.teach.teach1907.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.teach.data.LessonComment;
import com.teach.teach1907.R;
import com.teach.teach1907.utils.CenterAlignImageSpan;
import com.teach.teach1907.utils.SpanUtils;
import com.teach.teach1907.view.design.AutoLineFeedLayout;
import com.teach.teach1907.view.design.RatingBar;
import com.teach.teach1907.view.design.RoundImage;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.StringUtils;
import com.yiyatech.utils.newAdd.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2020/6/21.
 */
public class CourseDescribeAdapter extends RecyclerView.Adapter<CourseDescribeAdapter.ViewHolder> {

    private Context mContext;
    private List<LessonComment> commentList;

    public CourseDescribeAdapter(Context pContext, List<LessonComment> pCommentList) {
        mContext = pContext;
        commentList = pCommentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.course_desc_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LessonComment comment = commentList.get(position);
        GlideUtil.loadImage(holder.avater, comment.getImg());
        holder.name.setText(comment.getUsername());
        holder.time.setText(TimeUtil.parseTimeFormat(Long.parseLong(comment.getAdd_time()), "yyyy-MM-dd"));
        holder.ratingBar.setStar(StringUtils.getRoundRank(comment.getRank()));
        if (comment.getTags() != null && comment.getTags().size() > 0) {
            holder.aflContainer.setVisibility(View.VISIBLE);
            holder.aflContainer.removeAllViews();
            for (String tag : comment.getTags()) {
                View child = LayoutInflater.from(mContext).inflate(R.layout.item_comment_tag, holder.aflContainer, false);
                TextView view = child.findViewById(R.id.tv_comment_tag);
                view.setText(tag);
                holder.aflContainer.addView(child);
            }
        }
        holder.content.setText(SpanUtils.handlerEmotion(mContext, comment.getContent()));
        if (comment.getReplycontent() != null && comment.getReplycontent().size() > 0) {
            holder.replyContent.setVisibility(View.VISIBLE);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < comment.getReplycontent().size(); i++) {
                if (i == 0)
                    builder.append(mContext.getString(R.string.teacher_reply)).append(comment.getReplycontent().get(i));
                else
                    builder.append(mContext.getString(R.string.editor_reply)).append(comment.getReplycontent().get(i));
                if (i != comment.getReplycontent().size() - 1) builder.append("\n");
            }
            SpannableString spannable = new SpannableString(builder.toString());
            Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_teacher);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            spannable.setSpan(new CenterAlignImageSpan(drawable, ImageSpan.ALIGN_BASELINE), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.red)), 3, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.replyContent.setText(spannable);
        } else holder.replyContent.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return commentList != null ?  commentList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avater)
        RoundImage avater;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.rating_bar)
        RatingBar ratingBar;
        @BindView(R.id.afl_container)
        AutoLineFeedLayout aflContainer;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.reply_content)
        TextView replyContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
