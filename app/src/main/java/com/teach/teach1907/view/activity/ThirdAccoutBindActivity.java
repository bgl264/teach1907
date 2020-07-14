package com.teach.teach1907.view.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.teach.data.ThirdLoginData;
import com.teach.frame.ApiConfig;
import com.teach.frame.constants.ConstantKey;
import com.teach.teach1907.R;
import com.teach.teach1907.base.BaseMvpActivity;
import com.teach.teach1907.model.AccountModel;

import butterknife.BindView;
import butterknife.OnClick;

public class ThirdAccoutBindActivity extends BaseMvpActivity<AccountModel> {

    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.password)
    EditText password;
    private ThirdLoginData mThirdData;

    @Override
    public AccountModel setModel() {
        return new AccountModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_third_accout_bind;
    }

    @Override
    public void setUpView() {
        mThirdData = getIntent().getParcelableExtra("thirdData");
    }

    @Override
    public void setUpData() {
        titleContent.setText("绑定账号");
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.BIND_ACCOUNT:
                setResult(ConstantKey.BIND_BACK_LOGIN);
                finish();
                break;
        }
    }

    @OnClick({R.id.back_image, R.id.button_bind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                finish();
                break;
            case R.id.button_bind:
                if (TextUtils.isEmpty(account.getText().toString())) {
                    showToast("用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())) {
                    showToast("密码不能为空");
                    return;
                }
                mPresenter.getData(ApiConfig.BIND_ACCOUNT, account.getText().toString(), password.getText().toString(), mThirdData);
                break;
        }
    }
}
