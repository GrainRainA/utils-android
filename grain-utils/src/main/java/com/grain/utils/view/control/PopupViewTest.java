package com.grain.utils.view.control;

import android.content.Context;
import android.view.View;

import com.grain.utils.R;
import com.grain.utils.hint.toast;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.core.CenterPopupView;

import androidx.annotation.NonNull;

/**
 * @anthor GrainRain
 * @funcation
 * @date 2021/8/17
 */
public class PopupViewTest extends CenterPopupView {

    CenterPopupView basePopupView;

    public PopupViewTest(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_view_layout;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        basePopupView = this;

        findViewById(R.id.ss).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                basePopupView.dismiss();
            }
        });
    }
}
