package com.grain.utils.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.grain.utils.R;

/**
 * @anthor GrainRain
 * @funcation 自定义进度条
 * @date 2021/8/19
 */
public class ProgressView extends LinearLayout {

    private ProgressBar progressBar;
    private int progress = 0;
    private int max = 100;
    private String content;

    private TextView percentageText, progressText, maxText;
    private TextView contentText;

    public ProgressView(Context context) {
        super(context);
        initView(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.progress_view, this);

        progress = 0;
        max = 100;

        percentageText = view.findViewById(R.id.show_progress_bar_dialog_percentage);
        percentageText.setText(String.valueOf(progress / max * 100));
        progressText = view.findViewById(R.id.show_progress_bar_dialog_progress);
        progressText.setText(String.valueOf(progress));
        maxText = view.findViewById(R.id.show_progress_bar_dialog_max);
        maxText.setText(String.valueOf(max));
        contentText = view.findViewById(R.id.progress_content_dialog_view);
        contentText.setText(content);

        progressBar = view.findViewById(R.id.progress_bar_dialog_view);
        progressBar.setProgress(progress);
        progressBar.setMax(max);
    }

    /**
     * 设置进度
     * @param progress
     * @param activity 在UI线程下运行
     */
    public void setProgress(final int progress, Activity activity) {
        this.progress = progress;
        if (progressBar != null) progressBar.setProgress(progress);
        updateData(activity);
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        setProgress(progress, null);
    }

    /**
     * 设置最大值
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
        if (progressBar != null) progressBar.setMax(max);
        updateData();
    }

    /**
     * 在UI线程下更新数据
     * @param activity
     */
    private void updateData(Activity activity) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateData();
                }
            });
        } else {
            updateData();
        }
    }

    /**
     * 更新数据
     */
    private void updateData() {
        if (percentageText != null && progressText != null && maxText != null && progress <= max) {
            percentageText.setText(String.valueOf((int) (((float) progress / (float) max) * 100)));
            progressText.setText(String.valueOf(progress));
            maxText.setText(String.valueOf(max));
        }

        if (contentText != null && content != null) {
            contentText.setText(content);
        }
    }

    /**
     * 设置内容
     * @param content
     */
    public void setContent(String content) {
        setContent(content, null);
    }

    /**
     * 设置内容
     * @param content
     * @param activity
     */
    public void setContent(final String content, Activity activity) {
        this.content = content;
        updateData(activity);
    }
}
