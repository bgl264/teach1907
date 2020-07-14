package com.teach.teach1907.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;

import androidx.annotation.ColorInt;

import com.yiyatech.utils.newAdd.DensityUtil;
import com.yiyatech.utils.newAdd.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpanUtils {

    /**
     * 把内容转换为表情 例{男-晕}之类表情
     */
    public static SpannableString handlerEmotion(Context mContext, String content) {
        String mContent = StringUtils.translation(content);
        SpannableString sb = new SpannableString(mContent);
        Pattern p = Pattern.compile("\\{男-.{1,3}\\}");
        Matcher m = p.matcher(mContent);
        while (m.find()) {
            String tempText = m.group();
            try {
                // 把图片缩小到指定大小
                Bitmap bitmap = BitmapFactory.decodeResource(
                        mContext.getResources(),
                        PublishExpression.getExpressionMap(tempText));
                bitmap = ImageUtils.zoomImg(bitmap,
                        DensityUtil.dip2px(mContext, 22), DensityUtil.dip2px(mContext, 22));
                sb.setSpan(new ImageSpan(mContext, bitmap), m.start(), m.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb;
    }

    public static SpannableString changeStringColor(String content, @ColorInt int colorId, int start, int end) {
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new ForegroundColorSpan(colorId),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    public static SpannableString changeStringSize(String content, int sizeDp, int start, int end) {
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new AbsoluteSizeSpan(sizeDp, true),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }


    /**
     * 修改文本颜色
     *
     * @param text   文本内容
     * @param target 修改文本
     */
    public static CharSequence changeTextColor(String text, String target, int color) {
        return changeTextStyle(text, target, 0, color, -1);

    }

    /**
     * 修改文本字体大小
     *
     * @param text   文本内容
     * @param target 修改文本
     */
    public static CharSequence changeTextSize(String text, String target, int size) {
        return changeTextStyle(text, target, size, 0, -1);
    }

    /**
     * 修改文本样式
     *
     * @param text   文本内容
     * @param target 修改文本
     */
    public static CharSequence changeTextTypeface(String text, String target, int typeface) {
        return changeTextStyle(text, target, 0, 0, typeface);
    }

    /**
     * 修改文本样式
     *
     * @param text   文本内容
     * @param target 修改文本
     */
    public static CharSequence changeTextStyle(String text, String target, int size, @ColorInt int colorId, int typeface) {
        if (TextUtils.isEmpty(text) || TextUtils.isEmpty(target))
            return null;
        int start = text.indexOf(target);
        int end = start + target.length();
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        if (size != 0) {
            builder.setSpan(new AbsoluteSizeSpan(size, true),
                    start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (colorId != 0) {
            builder.setSpan(new ForegroundColorSpan(colorId), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (typeface != -1) {
            builder.setSpan(new StyleSpan(typeface),
                    start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }
}
