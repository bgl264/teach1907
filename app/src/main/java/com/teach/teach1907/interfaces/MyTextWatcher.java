package com.teach.teach1907.interfaces;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by 任小龙 on 2020/6/3.
 */
public abstract class MyTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        onMyTextChanged(s, start, before, count);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public abstract void onMyTextChanged(CharSequence s, int start, int before, int count);
}
