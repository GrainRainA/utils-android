package com.grain.utils.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grain.utils.R;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.util.XPopupUtils;

import androidx.annotation.NonNull;

/**
 * Description: 确定和取消的对话框
 * Create by dance, at 2018/12/16
 */
public class CustomizePopupView extends CenterPopupView implements View.OnClickListener {
    OnCancelListener cancelListener;
    OnConfirmListener confirmListener;
    TextView tv_title, tv_content, tv_cancel, tv_confirm;
    CharSequence title, content, hint, cancelText, confirmText;
    EditText et_input;
    View divider1, divider2;
    public boolean isHideCancel = false;

    View customizeView;
    RelativeLayout customizeLayout;

    /**
     *
     * @param context
     * @param bindLayoutId layoutId 要求布局中必须包含的TextView以及id有：tv_title，tv_content，tv_cancel，tv_confirm
     */
    public CustomizePopupView(@NonNull Context context, int bindLayoutId) {
        super(context);
        this.bindLayoutId = bindLayoutId;
        addInnerContent();
    }

    @Override
    protected int getImplLayoutId() {
        return bindLayoutId != 0 ? bindLayoutId : com.lxj.xpopup.R.layout._xpopup_center_impl_confirm;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        getPopupImplView().setBackground(XPopupUtils.createDrawable(Color.parseColor("#FFFFFF"), popupInfo.borderRadius));
        tv_title = findViewById(com.lxj.xpopup.R.id.tv_title);
        tv_content = findViewById(com.lxj.xpopup.R.id.tv_content);
        tv_cancel = findViewById(com.lxj.xpopup.R.id.tv_cancel);
        tv_confirm = findViewById(com.lxj.xpopup.R.id.tv_confirm);
        tv_content.setMovementMethod(LinkMovementMethod.getInstance());
        et_input = findViewById(com.lxj.xpopup.R.id.et_input);
        divider1 = findViewById(com.lxj.xpopup.R.id.xpopup_divider1);
        divider2 = findViewById(com.lxj.xpopup.R.id.xpopup_divider2);
        customizeLayout = findViewById(R.id.customize_layout);
        if(customizeView != null) customizeLayout.addView(customizeView);

        tv_cancel.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);

        if (!TextUtils.isEmpty(title)) {
            tv_title.setText(title);
        } else {
            tv_title.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(content)) {
            tv_content.setText(content);
        }else {
            tv_content.setVisibility(GONE);
        }
        if (!TextUtils.isEmpty(cancelText)) {
            tv_cancel.setText(cancelText);
        }
        if (!TextUtils.isEmpty(confirmText)) {
            tv_confirm.setText(confirmText);
        }
        if (isHideCancel) {
            tv_cancel.setVisibility(GONE);
            if(divider2!=null) divider2.setVisibility(GONE);
        }
        applyTheme();
    }

    public void addCustomizeView(View view) {
        this.customizeView = view;
    }

    protected void applyLightTheme() {
        super.applyLightTheme();
        tv_title.setTextColor(getResources().getColor(com.lxj.xpopup.R.color._xpopup_content_color));
        tv_content.setTextColor(getResources().getColor(com.lxj.xpopup.R.color._xpopup_content_color));
        tv_cancel.setTextColor(Color.parseColor("#666666"));
        tv_confirm.setTextColor(XPopup.getPrimaryColor());
        if(divider1!=null)divider1.setBackgroundColor(getResources().getColor(com.lxj.xpopup.R.color._xpopup_list_divider));
        if(divider2!=null)divider2.setBackgroundColor(getResources().getColor(com.lxj.xpopup.R.color._xpopup_list_divider));
    }

    public TextView getTitleTextView(){
        return findViewById(com.lxj.xpopup.R.id.tv_title);
    }

    public TextView getContentTextView(){
        return findViewById(com.lxj.xpopup.R.id.tv_content);
    }

    public TextView getCancelTextView(){
        return findViewById(com.lxj.xpopup.R.id.tv_cancel);
    }

    public TextView getConfirmTextView(){
        return findViewById(com.lxj.xpopup.R.id.tv_confirm);
    }
    @Override
    protected void applyDarkTheme() {
        super.applyDarkTheme();
        tv_title.setTextColor(getResources().getColor(com.lxj.xpopup.R.color._xpopup_white_color));
        tv_content.setTextColor(getResources().getColor(com.lxj.xpopup.R.color._xpopup_white_color));
        tv_cancel.setTextColor(getResources().getColor(com.lxj.xpopup.R.color._xpopup_white_color));
        tv_confirm.setTextColor(getResources().getColor(com.lxj.xpopup.R.color._xpopup_white_color));
        if(divider1!=null)divider1.setBackgroundColor(getResources().getColor(com.lxj.xpopup.R.color._xpopup_list_dark_divider));
        if(divider2!=null)divider2.setBackgroundColor(getResources().getColor(com.lxj.xpopup.R.color._xpopup_list_dark_divider));
    }

    public CustomizePopupView setListener(OnConfirmListener confirmListener, OnCancelListener cancelListener) {
        this.cancelListener = cancelListener;
        this.confirmListener = confirmListener;
        return this;
    }

    public CustomizePopupView setTitleContent(CharSequence title, CharSequence content, CharSequence hint) {
        this.title = title;
        this.content = content;
        this.hint = hint;
        return this;
    }

    public CustomizePopupView setCancelText(CharSequence cancelText) {
        this.cancelText = cancelText;
        return this;
    }

    public CustomizePopupView setConfirmText(CharSequence confirmText) {
        this.confirmText = confirmText;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v == tv_cancel) {
            if (cancelListener != null) cancelListener.onCancel();
            dismiss();
        } else if (v == tv_confirm) {
            if (confirmListener != null) confirmListener.onConfirm();
            if (popupInfo.autoDismiss) dismiss();
        }
    }
}
