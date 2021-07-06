package com.grain.utils.view.control;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * @anthor GrainRain
 * @funcation 封装Dialog工具类
 * @date 2021/6/5
 */
public class Dialog {

    /**
     * 显示自定义View的Dialog
     * @param context 上下文
     * @param view 自定义View dialogView = LayoutInflater.from(InitUtilsModule.getActivity()).inflate(R.layout.ayout, null);
     * @param title 标题
     * @param positiveText 确定按钮文字
     * @param positiveButton 确定按钮回调接口
     * @param negativeText 取消按钮文字
     * @param negativeButton 取消按钮回调接口
     */
    public void show(Context context, View view, String title,
                     String positiveText, final PositiveButton positiveButton,
                     String negativeText, final NegativeButton negativeButton) {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(context);
        customizeDialog.setTitle(title);
        customizeDialog.setView(view);

        if(positiveButton != null) {
            customizeDialog.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    positiveButton.onClick();
                }
            });
        }

        if(negativeButton != null) {
            customizeDialog.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    negativeButton.onClick();
                }
            });
        }

        customizeDialog.show();
    }

    /**
     * Dialog确定按钮
     */
    public interface PositiveButton{
        void onClick();
    }

    /**
     * Dialog取消按钮
     */
    public interface NegativeButton{
        void onClick();
    }




//    public void show() {
//        AlertDialog.Builder normalDialog =
//                new AlertDialog.Builder(InitUtilsModule.getActivity());
//        normalDialog.setTitle("我是一个普通Dialog");
//        normalDialog.setMessage("你要点击哪一个按钮呢?");
//        normalDialog.setPositiveButton("确定",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //...To-do
//                    }
//                });
//        normalDialog.setNegativeButton("关闭",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //...To-do
//                    }
//                });
//        // 显示
//        normalDialog.show();
//    }
}
