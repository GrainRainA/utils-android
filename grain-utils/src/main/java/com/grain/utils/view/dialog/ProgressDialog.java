package com.grain.utils.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.lxj.xpopup.core.BasePopupView;

/**
 * @anthor GrainRain
 * @funcation 进度条弹窗
 * @date 2021/8/19
 */
public class ProgressDialog {

    private ProgressView progressView;
    private BasePopupView basePopupView;
    private CustomizePopupView customizePopupView;

    public ProgressDialog(final Context context, String title, String content,
                          final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener,
                          final com.grain.utils.Interfaces.OnCancelListener onCancelListener,
                          boolean isDismissOnTouchOutside, boolean isDismissOnBackPressed) {

        progressView = new ProgressView(context);
        setContent(content);

        customizePopupView = new DialogUtils().creatCustomizePopupView(context, title, null, null, null, progressView);
        basePopupView = new DialogUtils().showCustomizeDialog(context, title, content, onConfirmListener, onCancelListener, isDismissOnTouchOutside, isDismissOnBackPressed, customizePopupView, progressView);
    }

    /**
     * 设置进度
     *
     * @param progress
     * @param activity 在UI线程下运行
     */
    public void setProgress(final int progress, Activity activity) {
        progressView.setProgress(progress, activity);
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        progressView.setProgress(progress, null);
    }

    /**
     * 设置最大值
     *
     * @param max
     */
    public void setMax(int max) {
        progressView.setProgress(max);
    }

    /**
     * 设置内容
     * @param content
     */
    public void setContent(String content) {
        progressView.setContent(content, null);
    }

    /**
     * 设置内容
     * @param content
     * @param activity
     */
    public void setContent(final String content, Activity activity) {
        progressView.setContent(content, activity);
    }

    public void dismiss() {
        if (basePopupView != null) basePopupView.dismiss();
    }
}
