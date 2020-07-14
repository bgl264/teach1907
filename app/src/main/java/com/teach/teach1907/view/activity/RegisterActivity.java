package com.teach.teach1907.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.teach.data.BaseInfo;
import com.teach.frame.ApiConfig;
import com.teach.teach1907.R;
import com.teach.teach1907.base.BaseMvpActivity;
import com.teach.teach1907.interfaces.MyTextWatcher;
import com.teach.teach1907.model.AccountModel;
import com.yiyatech.utils.newAdd.RegexUtil;
import com.yiyatech.utils.newAdd.SoftInputControl;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import razerdp.design.DialogPopup;

import static android.icu.text.DateTimePatternGenerator.PatternInfo.OK;
import static com.teach.teach1907.constants.JumpConstant.JUMP_KEY;

public class RegisterActivity extends BaseMvpActivity<AccountModel> implements DialogPopup.DialogClickCallBack {

    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.telephone_desc)
    TextView telephoneDesc;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.getVerification)
    TextView getVerification;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.next_page)
    TextView nextPage;
    private Disposable mTimeObserver;
    private DialogPopup mConfirmDialog;

    @Override
    public AccountModel setModel() {
        return new AccountModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_regiester;
    }

    @Override
    public void setUpView() {
        titleContent.setText("填写手机号码");
        nextPage.setEnabled(false);
        password.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                nextPage.setEnabled(s.length() == 6 ? true : false);
            }
        });
        showLog("r:oncreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLog("r:onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLog("r:onResume");
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.CHECK_PHONE_IS_USED:
                BaseInfo checkInfo = (BaseInfo) pD[0];
                if (checkInfo.isSuccess()){
                    mConfirmDialog = new DialogPopup(this, true);
                    mConfirmDialog.setContent(userName.getText().toString()+"\n"+"是否将短信发送到该手机");
                    mConfirmDialog.setDialogClickCallBack(this);
                    mConfirmDialog.showPopupWindow();
                } else {
                    showToast("该手机已注册");
                }
                break;
            case ApiConfig.SEND_REGISTER_VERIFY:
                BaseInfo sendResult = (BaseInfo) pD[0];
                if (sendResult.isSuccess()){
                    showToast("验证码发送成功");
                    goTime();
                }else showLog(sendResult.msg);
                break;
            case ApiConfig.REGISTER_PHONE:
                BaseInfo info = (BaseInfo) pD[0];
                if (info.isSuccess()){
                    showToast("验证码验证成功");
                    startActivity(new Intent(this,RegisterPhoneActivity.class).putExtra("phoneNum",telephoneDesc.getText().toString() + userName.getText().toString().trim()));
                    finish();
                } else showLog(info.msg);
                break;
        }
    }

    @OnClick({R.id.back_image, R.id.getVerification, R.id.next_page})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_image:
                finish();
                break;
            case R.id.getVerification:
                if (userName.getText() == null) {
                    showToast("请输入手机号");
                    return;
                }
                boolean phone = RegexUtil.isPhone(userName.getText().toString().trim());
                if (phone) {
                    SoftInputControl.hideSoftInput(this,userName);
                    mPresenter.getData(ApiConfig.CHECK_PHONE_IS_USED,telephoneDesc.getText().toString() + userName.getText().toString().trim());
                }
                else showToast("手机号格式错误");
                break;
            case R.id.next_page:
                mPresenter.getData(ApiConfig.REGISTER_PHONE, telephoneDesc.getText().toString() + userName.getText().toString().trim(), password.getText().toString().trim());
                break;
        }
    }

    private int preTime = 60;

    private void goTime() {
        mTimeObserver = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(l -> {
                    if (preTime - l > 0) {
                        getVerification.setText(preTime - l + "s");
                    } else {
                        getVerification.setText("获取验证码");
                        disPose();
                    }
                });
    }

    private void disPose() {
        if (mTimeObserver != null && !mTimeObserver.isDisposed()) mTimeObserver.dispose();
    }

    @Override
    protected void onStop() {
        super.onStop();
        disPose();
    }

    @Override
    public void onClick(int type) {
        if (type == mConfirmDialog.OK){
            mPresenter.getData(ApiConfig.SEND_REGISTER_VERIFY,telephoneDesc.getText().toString() + userName.getText().toString().trim());
        }
        mConfirmDialog.dismiss();
    }
}
