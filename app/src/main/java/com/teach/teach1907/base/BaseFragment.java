package com.teach.teach1907.base;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teach.frame.LoadTypeConfig;
import com.teach.teach1907.interfaces.DataListener;
import com.teach.teach1907.view.activity.HomeActivity;


public class BaseFragment extends Fragment {
    /**
     * recyclerview在整个项目中使用比较频繁，将公共代码进行抽取
     * @param pRecyclerView 要操作的recyclerview
     * @param pRefreshLayout 如果有刷新和加载更多的问题，所使用的smartRefreshLayout
     * @param pDataListener 刷新和加载的监听，如果实际使用中部涉及到刷新和加载更多，直接传null
     */
    public void initRecyclerView(RecyclerView pRecyclerView, SmartRefreshLayout pRefreshLayout, DataListener pDataListener) {
        if (pRecyclerView != null) {
            pRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            //将recycleview的出场动画禁掉，防止刷新闪烁
            ((SimpleItemAnimator)pRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        if (pRefreshLayout != null && pDataListener != null) {
            pRefreshLayout.setOnRefreshListener(refreshLayout -> pDataListener.dataType(LoadTypeConfig.REFRESH));
            pRefreshLayout.setOnLoadMoreListener(refreshLayout -> pDataListener.dataType(LoadTypeConfig.MORE));
        }
    }

    public void showLog(Object content) {
        Log.e("睚眦", content.toString());
    }

    public void showToast(Object content) {
        Toast.makeText(getContext(), content.toString(), Toast.LENGTH_SHORT).show();
    }

    public int setColor(@ColorRes int pColor){
        return ContextCompat.getColor(getContext(),pColor);
    }

    public HomeActivity getHomeActivity(){
        return (HomeActivity)getActivity();
    }
}
