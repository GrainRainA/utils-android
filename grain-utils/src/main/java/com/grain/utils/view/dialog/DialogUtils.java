package com.grain.utils.view.dialog;

import android.content.Context;
import android.view.View;

import com.grain.utils.R;
import com.grain.utils.hint.toast;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;

import java.util.List;

/**
 * @anthor GrainRain
 * @funcation 封装对话框工具类
 * 解决自动弹出软键盘: 设置context属性 android:windowSoftInputMode="stateHidden|stateUnchanged"
 * @date 2021/8/12
 */
public class DialogUtils {


    public BasePopupView showListDialog(final Context context, String title, List<String> stringList, final com.grain.utils.Interfaces.OnSelectListener onSelectListener) {
        String[] data = stringList.toArray(new String[0]);
        return showListDialog(context, title, data, onSelectListener, -1);
    }

    public BasePopupView showListDialog(final Context context, String title, String[] data, final com.grain.utils.Interfaces.OnSelectListener onSelectListener) {
        return showListDialog(context, title, data, onSelectListener, -1);
    }

    public BasePopupView showListDialog(final Context context, String title, List<String> stringList, final com.grain.utils.Interfaces.OnSelectListener onSelectListener, int checkedPosition) {
        String[] data = stringList.toArray(new String[0]);
        return showListDialog(context, title, data, onSelectListener, checkedPosition);
    }

    /**
     * 单选对话框
     * @param context         上下文
     * @param title            标题
     * @param data             数据
     * @param onSelectListener 选择回调
     * @param checkedPosition  选中的位置，传-1为不选中
     */
    public BasePopupView showListDialog(final Context context, String title, String[] data, final com.grain.utils.Interfaces.OnSelectListener onSelectListener, int checkedPosition) {
        BasePopupView basePopupView = new XPopup.Builder(context)
//                .maxWidth(600)
                .maxHeight(800)
                .isDestroyOnDismiss(true)
                .asCenterList(title, data, null, checkedPosition,
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                if (onSelectListener != null)
                                    onSelectListener.onSelect(position, text);
                            }
                        })
                .show();
        return basePopupView;
    }


    public BasePopupView showInputDialog(Context context, String title, final com.grain.utils.Interfaces.OnInputConfirmListener onInputConfirmListener) {
        return showInputDialog(context, title, onInputConfirmListener, null);
    }

    public BasePopupView showInputDialog(Context context, String title, final com.grain.utils.Interfaces.OnInputConfirmListener onInputConfirmListener, final com.grain.utils.Interfaces.OnCancelListener onCancelListener) {
        return showInputDialog(context, title, onInputConfirmListener, onCancelListener, true, true);
    }

    public BasePopupView showInputDialog(Context context, String title, final com.grain.utils.Interfaces.OnInputConfirmListener onInputConfirmListener, boolean isDismissOnTouchOutside, boolean isDismissOnBackPressed) {
        return showInputDialog(context, title, onInputConfirmListener, null, isDismissOnTouchOutside, isDismissOnBackPressed);
    }

    public BasePopupView showInputDialog(Context context, String title, final com.grain.utils.Interfaces.OnInputConfirmListener onInputConfirmListener, final com.grain.utils.Interfaces.OnCancelListener onCancelListener, boolean isDismissOnTouchOutside, boolean isDismissOnBackPressed) {
        return showInputDialog(context, title, null, null, null, onInputConfirmListener, onCancelListener, isDismissOnTouchOutside, isDismissOnBackPressed);
    }

    /**
     * 输入弹窗
     *
     * @param context                上下文
     * @param title                   标题
     * @param content                 内容
     * @param inputContent            输入框填充
     * @param hint                    提示
     * @param onInputConfirmListener  输入确认监听器
     * @param onCancelListener        取消监听器
     * @param isDismissOnTouchOutside 外部是否可触摸
     * @param isDismissOnBackPressed  按下返回键是否关闭弹窗
     * @return
     */
    public BasePopupView showInputDialog(Context context, String title, String content, String inputContent, String hint,
                                         final com.grain.utils.Interfaces.OnInputConfirmListener onInputConfirmListener,
                                         final com.grain.utils.Interfaces.OnCancelListener onCancelListener,
                                         boolean isDismissOnTouchOutside,
                                         boolean isDismissOnBackPressed) {
        BasePopupView basePopupView = new XPopup.Builder(context)
                .isDestroyOnDismiss(true)
                .hasStatusBarShadow(false)
                .autoOpenSoftInput(true)
                .dismissOnTouchOutside(isDismissOnTouchOutside)
                .dismissOnBackPressed(isDismissOnBackPressed)
                .asInputConfirm(title, content, inputContent, hint,
                        new OnInputConfirmListener() {
                            @Override
                            public void onConfirm(String text) {
                                if (onInputConfirmListener != null)
                                    onInputConfirmListener.onConfirm(text);
                            }
                        }, new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                if (onCancelListener != null) onCancelListener.onCancel();
                            }
                        }, 0)
                .show();

        return basePopupView;
    }


    public BasePopupView showNormalDialog(Context context, String title, String content) {
        return showNormalDialog(context, title, content, null);
    }

    public BasePopupView showNormalDialog(Context context, String title, String content, int bindLayoutId) {
        return showNormalDialog(context, title, content, null, null, true, true, bindLayoutId);
    }

    public BasePopupView showNormalDialog(Context context, String title, String content, final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener) {
        return showNormalDialog(context, title, content, onConfirmListener, null);
    }

    public BasePopupView showNormalDialog(Context context, String title, String content, final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener, final com.grain.utils.Interfaces.OnCancelListener onCancelListener) {
        return showNormalDialog(context, title, content, onConfirmListener, onCancelListener, true, true, 0);
    }

    /**
     * 普通弹窗
     *
     * @param context                上下文
     * @param title                   标题
     * @param content                 内容
     * @param onConfirmListener       确认按钮监听器
     * @param onCancelListener        取消按钮监听器
     * @param isDismissOnTouchOutside 外部是否可触摸
     * @param isDismissOnBackPressed  按下返回键是否关闭弹窗
     * @param bindLayoutId            layoutId 要求布局中必须包含的TextView以及id有：tv_title，tv_content，tv_cancel，tv_confirm
     * @return
     */
    public BasePopupView showNormalDialog(Context context, String title, String content,
                                          final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener,
                                          final com.grain.utils.Interfaces.OnCancelListener onCancelListener,
                                          boolean isDismissOnTouchOutside,
                                          boolean isDismissOnBackPressed,
                                          int bindLayoutId) {

        BasePopupView normalDialog = new XPopup.Builder(context)
                .isDestroyOnDismiss(true)
                .hasBlurBg(true)    //模糊背景
                .dismissOnTouchOutside(isDismissOnTouchOutside)   //外部是否可触摸
                .dismissOnBackPressed(isDismissOnBackPressed)        //按下返回键是否关闭弹窗
                .popupAnimation(PopupAnimation.ScaleAlphaFromCenter)     //弹出动画
                .asConfirm(title, content,
                        "取消", "确定",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                if (onConfirmListener != null) onConfirmListener.onCancel();

                            }
                        }, new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                if (onCancelListener != null) onCancelListener.onCancel();
                            }
                        }, (onCancelListener == null), bindLayoutId)
                .show();
        return normalDialog;
    }

    public BasePopupView showCustomizeDialog(final Context context, String title, View view) {
        return showCustomizeDialog(context, title, null, view);
    }

    public BasePopupView showCustomizeDialog(final Context context, String title, boolean isDismissOnTouchOutside, boolean isDismissOnBackPressed, View view) {
        return showCustomizeDialog(context, title, null, isDismissOnTouchOutside, isDismissOnBackPressed, view);
    }

    public BasePopupView showCustomizeDialog(final Context context, String title, final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener, View view) {
        return showCustomizeDialog(context, title, onConfirmListener, true, true, view);
    }

    public BasePopupView showCustomizeDialog(final Context context, String title, final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener, boolean isDismissOnTouchOutside, boolean isDismissOnBackPressed, View view) {
        return showCustomizeDialog(context, title, null, onConfirmListener, null, isDismissOnTouchOutside, isDismissOnBackPressed, view);
    }

    public BasePopupView showCustomizeDialog(final Context context, String title, String content,
                                             final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener,
                                             final com.grain.utils.Interfaces.OnCancelListener onCancelListener,
                                             boolean isDismissOnTouchOutside, boolean isDismissOnBackPressed,
                                             View view) {
        return showCustomizeDialog(context, title, null, onConfirmListener, null, isDismissOnTouchOutside, isDismissOnBackPressed, null, view);
    }

    /**
     * 自定义弹窗
     *
     * @param context
     * @param title                   标题
     * @param content                 内容
     * @param onConfirmListener       确认按钮监听器
     * @param onCancelListener        取消按钮监听器
     * @param isDismissOnTouchOutside 外部是否可触摸
     * @param isDismissOnBackPressed  按下返回键是否关闭弹窗
     * @param view                    自定义view
     * @return
     */
    public BasePopupView showCustomizeDialog(final Context context, String title, String content,
                                             final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener,
                                             final com.grain.utils.Interfaces.OnCancelListener onCancelListener,
                                             boolean isDismissOnTouchOutside, boolean isDismissOnBackPressed,
                                             CustomizePopupView customizePopupView, View view) {

        if (customizePopupView == null) customizePopupView = creatCustomizePopupView(context, title, content, onConfirmListener, onCancelListener, view);

        BasePopupView popupView = new XPopup.Builder(context)
                .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
                .enableDrag(true)
                .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
//              .isThreeDrag(true) //是否开启三阶拖拽，如果设置enableDrag(false)则无效
                .dismissOnTouchOutside(isDismissOnTouchOutside)   //外部是否可触摸
                .dismissOnBackPressed(isDismissOnBackPressed)        //按下返回键是否关闭弹窗
                .asCustom(customizePopupView)
                .show();

        return popupView;
    }

    /**
     * 创建自定义视图
     * @param context
     * @param title
     * @param content
     * @param onConfirmListener
     * @param onCancelListener
     * @param view
     * @return
     */
    public CustomizePopupView creatCustomizePopupView(final Context context, String title, String content,
                                                      final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener,
                                                      final com.grain.utils.Interfaces.OnCancelListener onCancelListener, View view) {
        CustomizePopupView customizePopupView = new CustomizePopupView(context, R.layout._xpopup_customize_popup_view);
        customizePopupView.isHideCancel = (onCancelListener == null);
        customizePopupView.addCustomizeView(view);
        customizePopupView.setTitleContent(title, content, null);

        customizePopupView.setListener(new OnConfirmListener() {
            @Override
            public void onConfirm() {
                if (onConfirmListener != null) onConfirmListener.onCancel();
            }
        }, new OnCancelListener() {
            @Override
            public void onCancel() {
                if (onCancelListener != null) onCancelListener.onCancel();
            }
        });

        return customizePopupView;
    }

    public ProgressDialog showProgressDialog(final Context context, String title, final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener) {
        return showProgressDialog(context, title, onConfirmListener, null);
    }

    public ProgressDialog showProgressDialog(final Context context, String title, String content, final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener) {
        return showProgressDialog(context, title, content, onConfirmListener, null, true, true);
    }

    public ProgressDialog showProgressDialog(final Context context, String title, final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener, boolean isDismissOnTouchOutside, boolean isDismissOnBackPressed) {
        return showProgressDialog(context, title, null, onConfirmListener, null, isDismissOnTouchOutside, isDismissOnBackPressed);
    }

    public ProgressDialog showProgressDialog(final Context context, String title, final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener, final com.grain.utils.Interfaces.OnCancelListener onCancelListener) {
        return showProgressDialog(context, title, null, onConfirmListener, onCancelListener, true, true);
    }

    /**
     * 进度条弹窗
     *
     * @param context
     * @param title                   标题
     * @param content                 内容
     * @param onConfirmListener       确认按钮监听器
     * @param onCancelListener        取消按钮监听器
     * @param isDismissOnTouchOutside 外部是否可触摸
     * @param isDismissOnBackPressed  按下返回键是否关闭弹窗
     * @return
     */
    public ProgressDialog showProgressDialog(final Context context, String title, String content,
                                             final com.grain.utils.Interfaces.OnConfirmListener onConfirmListener,
                                             final com.grain.utils.Interfaces.OnCancelListener onCancelListener,
                                             boolean isDismissOnTouchOutside, boolean isDismissOnBackPressed) {
        return new ProgressDialog(context, title, content, onConfirmListener, onCancelListener, isDismissOnTouchOutside, isDismissOnBackPressed);
    }


    public void showProgressDialog(final Context context, int i) {

        LoadingPopupView loadingPopup = (LoadingPopupView) new XPopup.Builder(context)
                .dismissOnBackPressed(false)
                .asLoading("加载中")
                .show();
        loadingPopup.show();

//        loadingPopup.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                loadingPopup.setTitle("加载中长度变化啊");
//                loadingPopup.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadingPopup.setTitle("");
//                    }
//                }, 2000);
//            }
//        }, 2000);
//                loadingPopup.smartDismiss();
//                loadingPopup.dismiss();
        loadingPopup.delayDismissWith(6000, new Runnable() {
            @Override
            public void run() {
                toast.show("我消失了！！！");
            }
        });
    }
}
