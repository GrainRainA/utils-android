package com.grain.universalutils;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.grain.utils.MQTTManager;
import com.grain.utils.file.FileUtils;
import com.grain.utils.file.TxtFile;
import com.grain.utils.hint.L;
import com.grain.utils.utils.GetTime;
import com.grain.utils.utils.Permission.ApplyPermission;
import com.grain.utils.utils.json.JSONUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.lxj.xpopup.interfaces.XPopupCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ApplyPermission(this);

//        MQTTManager.connect("", new MQTTManager.MessageInterdace() {
//            @Override
//            public void messageArrived(String topic, byte[] bytes) {
//                L.e(new String(bytes));
//            }
//        });
//
//        MQTTManager.subscribe("test");
//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
////                MQTTManager.publish("test", "GrainRain " + GetTime.getCurrentTime());
//            }
//        },0, 1000);

//        L.e(TxtFile.read(path));
//        new ApplyPermission(this);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FileUtils.deleteFile(new File(Environment.getExternalStorageDirectory() + "/test"), true);

            }
        });
    }

    private void test() {
        new XPopup.Builder(this)
                .hasStatusBarShadow(false)
                //.dismissOnBackPressed(false)
                .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
                .autoOpenSoftInput(true)
                .isDarkTheme(true)
                .setPopupCallback(new DemoXPopupListener())
//                        .autoFocusEditText(false) //是否让弹窗内的EditText自动获取焦点，默认是true
                //.moveUpToKeyboard(false)   //是否移动到软键盘上面，默认为true
                .asInputConfirm("我是标题", null, null, "我是默认Hint文字",
                        new OnInputConfirmListener() {
                            @Override
                            public void onConfirm(String text) {
//                                new XPopup.Builder(getContext()).asLoading().show();
                            }
                        })
                .show();
    }

    static class DemoXPopupListener extends SimpleCallback {
        @Override
        public void onCreated(BasePopupView pv) {
            Log.e("tag", "onCreated");
        }

        @Override
        public void onShow(BasePopupView popupView) {
            Log.e("tag", "onShow");
        }

        @Override
        public void onDismiss(BasePopupView popupView) {
            Log.e("tag", "onDismiss");
        }

        @Override
        public void beforeDismiss(BasePopupView popupView) {
            Log.e("tag", "beforeDismiss");
        }

        //如果你自己想拦截返回按键事件，则重写这个方法，返回true即可
        @Override
        public boolean onBackPressed(BasePopupView popupView) {
            Log.e("tag", "拦截的返回按键，按返回键XPopup不会关闭了");
            return true;
        }

        @Override
        public void onKeyBoardStateChanged(BasePopupView popupView, int height) {
            super.onKeyBoardStateChanged(popupView, height);
            Log.e("tag", "onKeyBoardStateChanged height: " + height);
        }
    }

    private static String path = Environment.getExternalStorageDirectory() + "/UAVCar/VoiceOrder.txt";

}