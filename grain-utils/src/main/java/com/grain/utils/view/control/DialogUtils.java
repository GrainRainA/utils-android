package com.grain.utils.view.control;

import android.app.Activity;
import android.widget.Toast;

import com.grain.utils.hint.L;
import com.grain.utils.hint.toast;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;

/**
 * @anthor GrainRain
 * @funcation 封装对话框工具类
 * @date 2021/8/12
 */
public class DialogUtils {

    public static void showNormalDialog(Activity activity) {
        BasePopupView popupView = new XPopup.Builder(activity)
//                        .hasNavigationBar(true)
//                        .navigationBarColor(Color.BLUE)
                .isDestroyOnDismiss(true)
                .hasBlurBg(true)    //模糊背景
                .dismissOnTouchOutside(true)   //外部是否可触摸
//                         .autoDismiss(true)     //自动关闭?
                .popupAnimation(PopupAnimation.ScaleAlphaFromCenter)     //弹出动画
//                        .isLightStatusBar(true)
//                .setPopupCallback(new DemoXPopupListener())     //可设置拦截返回键
//                        .asCustom(new LoginPopup(getContext()));      //设置自定义布局
                .asConfirm("哈哈", "床前明月光，疑是地上霜；举头望明月，低头思故乡。",
                        "取消", "确定",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                toast.show("ok");
                            }
                        }, new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                toast.show("no");
                            }
                        }, false);
        popupView.show();
    }

    /**
     * 单选
     * @param activity
     */
    public static void showListDialog(final Activity activity) {
        new XPopup.Builder(activity)
//                .maxWidth(600)
                .maxHeight(800)
//                .isDarkTheme(true)
                .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                .asCenterList("请选择一项",
                        new String[]{"条目1", "条目2", "条目3", "条目4", "条目1", "条目2", "条目3", "条目4",
                                "条目4", "条目1", "条目2", "条目3", "条目4",},
//                        null, 1,    //是否带选中效果
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
//                                Toast.makeText(activity, "click " + position + " " + text, Toast.LENGTH_SHORT).show();
//                                L.e("click " + position + " " + text);
                            }
                        })
                .show();
    }

    public static void showProgressDialog(final Activity activity) {

            LoadingPopupView loadingPopup = (LoadingPopupView) new XPopup.Builder(activity)
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
