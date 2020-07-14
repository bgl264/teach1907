package com.teach.teach1907.view.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bokecc.sdk.mobile.download.Downloader;
import com.teach.teach1907.R;

/**
 * Created by whb on 2017/8/3.
 * Email 18720982457@163.com
 */

public class DownLoadRoundProgressBar extends RelativeLayout {
    private View view;
    private RoundProgressBar roundProgressBar;
    private ImageView ivState;
    private ImageView ivDownloadingState;

    public DownLoadRoundProgressBar(Context context) {
        super(context);
        init();
    }

    public DownLoadRoundProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DownLoadRoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        initView();
    }

    public void initView() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_download_progressbar, null);
        roundProgressBar = view.findViewById(R.id.RoundProgressBar);
        ivState = view.findViewById(R.id.iv_state);
        ivDownloadingState = view.findViewById(R.id.iv_downloading_state);
        addView(view);
    }

    public void setMax(long max) {
        roundProgressBar.setMax(max);
    }

    public void setState(long progress, int state) {
        switch (state) {
            case Downloader.WAIT:
                if (progress == 0) {
                    roundProgressBar.setVisibility(GONE);
                    ivState.setVisibility(VISIBLE);
                    ivState.setImageResource(R.drawable.ic_download_wait);
                    ivDownloadingState.setVisibility(GONE);
                } else {
                    roundProgressBar.setVisibility(VISIBLE);
                    roundProgressBar.setProgress(progress);
                    ivState.setVisibility(GONE);
                    ivDownloadingState.setVisibility(VISIBLE);
                    ivDownloadingState.setImageResource(R.drawable.ic_downloading);
                }
                break;
            case Downloader.DOWNLOAD:
                roundProgressBar.setVisibility(VISIBLE);
                roundProgressBar.setProgress(progress);
                ivState.setVisibility(GONE);
                ivDownloadingState.setVisibility(VISIBLE);
                ivDownloadingState.setImageResource(R.drawable.ic_downloading);
                break;
            case Downloader.FINISH:
                roundProgressBar.setVisibility(GONE);
                ivState.setVisibility(VISIBLE);
                ivState.setImageResource(R.drawable.ic_download_complete);
                ivDownloadingState.setVisibility(GONE);
                break;
            case Downloader.PAUSE:
                roundProgressBar.setVisibility(VISIBLE);
                roundProgressBar.setProgress(progress);
                ivState.setVisibility(GONE);
                ivDownloadingState.setVisibility(VISIBLE);
                ivDownloadingState.setImageResource(R.drawable.ic_download_pause);
                break;
            default:
                roundProgressBar.setVisibility(GONE);
                ivState.setVisibility(VISIBLE);
                ivState.setImageResource(R.drawable.ic_begin_download);
                ivDownloadingState.setVisibility(GONE);
                break;

        }
    }
}
