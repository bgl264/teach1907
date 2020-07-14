package com.teach.teach1907.view.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.teach.frame.LoadView;
import com.teach.teach1907.R;
import com.teach.teach1907.base.BaseMvpActivity;
import com.teach.teach1907.model.CommonHomeModel;

public class HomeActivity extends BaseMvpActivity<CommonHomeModel> implements NavController.OnDestinationChangedListener {

    public NavController mProjectController;

    public boolean isBackFromDetail;

    @Override
    public CommonHomeModel setModel() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void setUpView() {
        mProjectController = Navigation.findNavController(this, R.id.project_fragment_control);
        mProjectController.addOnDestinationChangedListener(this);
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        String currentFragment = destination.getLabel().toString();
        showLog(currentFragment);
    }
}
