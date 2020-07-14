package com.teach.teach1907.view.design;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.IdRes;
import androidx.annotation.RequiresApi;

import com.teach.teach1907.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 任小龙 on 2020/6/4.
 */
public class BottomTabView extends RelativeLayout {
    @BindView(R.id.main_page_tab)
    TextView mainPageTab;
    @BindView(R.id.course_tab)
    TextView courseTab;
    @BindView(R.id.vip_tab)
    TextView vipTab;
    @BindView(R.id.data_tab)
    public TextView dataTab;
    @BindView(R.id.mine_tab)
    TextView mineTab;
    private Context mContext;
    private final int mTabNum;
    //根据自定义属性中tab数量的设置，过滤掉不用的view，剩下接下来使用的view
    private List<TextView> usedTabView = new ArrayList<>();
    private List<Integer> normalIcon;//为选中的icon
    private List<Integer> selectedIcon;// 选中的icon
    private List<String> tabContent;//tab对应的内容
    private int defaultTab;//默认显示第几个tab
    private  @ColorInt int mSelectedColor;
    private  @ColorInt int mUnSelectedColor;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BottomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.bottom_tav_view, this);
        ButterKnife.bind(this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomTabView, 0, 0);
        mTabNum = ta.getInt(R.styleable.BottomTabView_tabNum, 4);
        mSelectedColor = ta.getColor(R.styleable.BottomTabView_selectedColor, Color.RED);
        mUnSelectedColor = ta.getColor(R.styleable.BottomTabView_unSelectedColor, Color.BLACK);
        defaultTab = ta.getInt(R.styleable.BottomTabView_defaultSelect, 1);
        Collections.addAll(usedTabView,mainPageTab,courseTab,vipTab,dataTab,mineTab);
        if (mTabNum <5){
            for (int i = 5;i > mTabNum;i--){
                usedTabView.get(i-1).setVisibility(GONE);
                usedTabView.remove(i-1);
            }
        }
        ta.recycle();
    }

    public void changeSelected(int pos){
        defaultTab = pos;
        setStyle();
    }

    /**
     * 给自定义tab设置数据
     * @param normalIcon 未选中的icon集合
     * @param selectedIcon 选中的icon集合
     * @param tabContent tab内容描述
     */
    public void setResource(List<Integer> normalIcon,List<Integer> selectedIcon,List<String> tabContent){
        if (defaultTab<=0){
            Log.e(this.getClass().getSimpleName(), "0个tab，你玩lz呢" );
            return;
        }
        if (usedTabView.size() != normalIcon.size() || usedTabView.size()!= selectedIcon.size()|| usedTabView.size() != tabContent.size()){
            Log.e(this.getClass().getName(),"---------"+"自定义属性的数量和传递的资源数量不匹配");
            return;
        }
        this.normalIcon = normalIcon;this.selectedIcon = selectedIcon;this.tabContent = tabContent;
        setContent();
        setStyle();
    }

    private void setContent() {
        for (int i = 0;i<tabContent.size();i++) {
            usedTabView.get(i).setText(tabContent.get(i));
        }
    }

    private void setStyle() {
        for (int i = 0;i<usedTabView.size();i++){
            if (i == defaultTab-1){
                usedTabView.get(i).setTextColor(mSelectedColor);
                usedTabView.get(i).setCompoundDrawablesWithIntrinsicBounds(0,selectedIcon.get(i),0,0);
            } else {
                usedTabView.get(i).setTextColor(mUnSelectedColor);
                usedTabView.get(i).setCompoundDrawablesWithIntrinsicBounds(0,normalIcon.get(i),0,0);
            }
        }
    }

    private @IdRes int currentId;
    @OnClick({R.id.main_page_tab, R.id.course_tab, R.id.vip_tab, R.id.data_tab, R.id.mine_tab})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (currentId == id){
            Log.e(this.getClass().getName(), "onViewClicked: "+"你点击的是已选中的位置" );
            return;
        }
        currentId = id;
        if (id == R.id.main_page_tab) {
            defaultTab = 1;
        } else if (id == R.id.course_tab) {
            defaultTab = 2;
        } else if (id == R.id.vip_tab) {
            defaultTab = 3;
        } else if (id == R.id.data_tab) {
            defaultTab = 4;
        } else if (id == R.id.mine_tab) {
            defaultTab = 5;
        }
        setStyle();
        if (mOnBottomTabClickCallBack != null) mOnBottomTabClickCallBack.clickTab(defaultTab);
    }

    private OnBottomTabClickCallBack mOnBottomTabClickCallBack;

    public void setOnBottomTabClickCallBack(OnBottomTabClickCallBack pOnBottomTabClickCallBack){
        mOnBottomTabClickCallBack = pOnBottomTabClickCallBack;
    }

    public interface OnBottomTabClickCallBack{
        void clickTab(int tab);
    }
}
