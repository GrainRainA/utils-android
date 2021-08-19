package com.grain.universalutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;

import com.grain.utils.InitUtilsModule;
import com.grain.utils.utils.Permission.ApplyPermission;
import com.grain.utils.view.FullScreen;
import com.grain.utils.view.dialog.DialogUtils;
import com.grain.utils.view.dialog.ProgressDialog;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ApplyPermission(this);

//        L.e(TxtFile.read(path));
//        new ApplyPermission(this);
        InitUtilsModule.init(this);
        FullScreen.setHideAllBar(this);

        final LinearLayout linear_layout = findViewById(R.id.linear_layout);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FileUtils.deleteFile(new File(Environment.getExternalStorageDirectory() + "/showCustomizeDialog"), true);
//                linear_layout.addView(new TestLayout(MainActivity.this));
                test();
            }
        });

    }

    private void test() {
//        final ProgressDialog progressPopupView = new DialogUtils().showProgressDialog(this, "进度条1", null, null, null, true, true);
        final ProgressDialog progressPopupView = new DialogUtils().showProgressDialog(this, "进度条1", "提示", null);
//        final ProgressDialog progressPopupView = new ProgressDialog(this);
        progressPopupView.setMax(100);
        i = 0;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                progressPopupView.setProgress(i++, MainActivity.this);
                progressPopupView.setContent("内容 " + i, MainActivity.this);
            }
        }, 0, 300);
    }
    int i = 0;


    private static String path = Environment.getExternalStorageDirectory() + "/UAVCar/VoiceOrder.txt";

}