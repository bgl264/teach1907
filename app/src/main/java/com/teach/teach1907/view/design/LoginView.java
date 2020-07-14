package com.teach.teach1907.view.design;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.teach.teach1907.R;
import com.teach.teach1907.interfaces.MyTextWatcher;
import com.yiyatech.utils.ext.ToastUtils;
import com.yiyatech.utils.newAdd.RegexUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 任小龙 on 2020/6/3.
 */
public class LoginView extends RelativeLayout {
    @BindView(R.id.account_login)
    TextView accountLogin;
    @BindView(R.id.verify_login)
    TextView verifyLogin;
    @BindView(R.id.account_point)
    View accountPoint;
    @BindView(R.id.verify_point)
    View verifyPoint;
    @BindView(R.id.account_name)
    EditText accountName;
    @BindView(R.id.account_secrete)
    EditText accountSecrete;
    @BindView(R.id.account_module)
    ConstraintLayout accountModule;
    @BindView(R.id.area_code)
    TextView areaCode;
    @BindView(R.id.verify_name)
    EditText verifyName;
    @BindView(R.id.verify_code)
    EditText verifyCode;
    @BindView(R.id.get_verify_code)
    public TextView getVerifyCode;
    @BindView(R.id.login_press)
    TextView loginPress;
    @BindView(R.id.verify_area)
    ConstraintLayout verifyView;
    @BindView(R.id.delete_account_name)
    ImageView deleteIcon;
    @BindView(R.id.is_visible)
    ImageView isPwdVisible;
    private Context mContext;
    public final int ACCOUNT_TYPE = 1, VERIFY_TYPE = 2;
    public int mCurrentLoginType = ACCOUNT_TYPE;
    private final boolean mIsMoreType;

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.login_view, this);
        ButterKnife.bind(this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoginView, 0, 0);
        mIsMoreType = ta.getBoolean(R.styleable.LoginView_isMoreType, true);
        initView();
        if (!mIsMoreType){
            verifyPoint.setVisibility(GONE);
            accountPoint.setVisibility(GONE);
            verifyLogin.setVisibility(GONE);
            accountLogin.setVisibility(GONE);
        }
        ta.recycle();
    }

    private void initView() {
        loginPress.setEnabled(false);
        accountName.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                deleteIcon.setVisibility(s.length() == 0 ? INVISIBLE : VISIBLE);
            }
        });
        accountSecrete.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5 && !TextUtils.isEmpty(accountName.getText().toString().trim())) {
                    loginPress.setEnabled(true);
                } else loginPress.setEnabled(false);
                isPwdVisible.setVisibility(s.length() > 0 ? VISIBLE : INVISIBLE);
            }
        });
        verifyCode.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5 && RegexUtil.isPhone(verifyName.getText().toString().trim()))
                    loginPress.setEnabled(true);
                else loginPress.setEnabled(false);
            }
        });
    }

    @OnClick({R.id.account_login, R.id.verify_login, R.id.get_verify_code, R.id.login_press,R.id.delete_account_name,R.id.is_visible})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.is_visible:
                accountSecrete.setTransformationMethod(isPwdVisible.isSelected() ? PasswordTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance());
                isPwdVisible.setSelected(!isPwdVisible.isSelected());
                break;
            case R.id.delete_account_name:
                accountName.setText("");
                break;
            case R.id.account_login:
                if (mCurrentLoginType != ACCOUNT_TYPE){
                    accountLogin.setTextColor(ContextCompat.getColor(mContext, R.color.red));
                    accountPoint.setVisibility(VISIBLE);
                    verifyLogin.setTextColor(ContextCompat.getColor(mContext, R.color.dark_gray));
                    verifyPoint.setVisibility(INVISIBLE);
                    accountModule.setVisibility(VISIBLE);
                    verifyView.setVisibility(INVISIBLE);
                    mCurrentLoginType = ACCOUNT_TYPE;
                }
                break;
            case R.id.verify_login:
                if (mCurrentLoginType != VERIFY_TYPE){
                    accountLogin.setTextColor(ContextCompat.getColor(mContext, R.color.dark_gray));
                    accountPoint.setVisibility(INVISIBLE);
                    verifyLogin.setTextColor(ContextCompat.getColor(mContext, R.color.red));
                    verifyPoint.setVisibility(VISIBLE);
                    accountModule.setVisibility(INVISIBLE);
                    verifyView.setVisibility(VISIBLE);
                    verifyPoint.setBackgroundColor(ContextCompat.getColor(mContext,R.color.red));
                    mCurrentLoginType = VERIFY_TYPE;
                }
                break;
            case R.id.get_verify_code:
                if (TextUtils.isEmpty(verifyName.getText().toString())) {
                    ToastUtils.show(mContext, "用户名为空");
                    return;
                }
                if (!RegexUtil.isPhone(verifyName.getText().toString().trim())) {
                    ToastUtils.show(mContext, "手机号格式错误");
                    return;
                }
                if (mLoginViewCallBack != null)
                    mLoginViewCallBack.sendVerifyCode(areaCode.getText().toString()+verifyName.getText().toString().trim());
                break;
            case R.id.login_press:
                String userName = "",passWord = "";
                if (mCurrentLoginType == ACCOUNT_TYPE){
                    userName = accountName.getText().toString().trim();
                    passWord = accountSecrete.getText().toString().trim();
                } else {
                    userName = verifyName.getText().toString().trim();
                    passWord = verifyCode.getText().toString().trim();
                }
                if (mLoginViewCallBack != null) mLoginViewCallBack.loginPress(mCurrentLoginType,userName,passWord);
                break;

        }
    }

    private LoginViewCallBack mLoginViewCallBack;

    public void setLoginViewCallBack(LoginViewCallBack pLoginViewCallBack) {
        mLoginViewCallBack = pLoginViewCallBack;
    }

    public interface LoginViewCallBack {
        void sendVerifyCode(String phoneNum);

        void loginPress(int type, String userName, String pwd);
    }
}
