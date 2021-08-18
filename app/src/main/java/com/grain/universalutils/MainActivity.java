package com.grain.universalutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;

import com.grain.utils.InitUtilsModule;
import com.grain.utils.hint.L;
import com.grain.utils.utils.Permission.ApplyPermission;
import com.grain.utils.view.FullScreen;
import com.grain.utils.view.dialog.CustomizePopupView;
import com.grain.utils.view.dialog.DialogUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;

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
                test();

//                linear_layout.addView(new TestLayout(MainActivity.this));
            }
        });
    }

    private void test() {
//        new DialogUtils().showCustomizeDialog(this, new PopupViewTest(this, R.layout.popup_view_layout));
//        new DialogUtils().showNormalDialog(this, "ti", "sfsd", R.layout.popup_view_layout);
        new DialogUtils().showCustomizeDialog(this, new TestLayout(MainActivity.this));
    }


    private static String path = Environment.getExternalStorageDirectory() + "/UAVCar/VoiceOrder.txt";

}