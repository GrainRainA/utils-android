package com.grain.universalutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.grain.utils.InitUtilsModule;
import com.grain.utils.Interfaces.OnCancelListener;
import com.grain.utils.Interfaces.OnConfirmListener;
import com.grain.utils.Interfaces.OnInputConfirmListener;
import com.grain.utils.Interfaces.OnSelectListener;
import com.grain.utils.hint.L;
import com.grain.utils.hint.toast;
import com.grain.utils.utils.Permission.ApplyPermission;
import com.grain.utils.view.FullScreen;
import com.grain.utils.view.control.PopupViewTest;
import com.grain.utils.view.dialog.DialogUtils;

import java.util.ArrayList;
import java.util.List;

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

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FileUtils.deleteFile(new File(Environment.getExternalStorageDirectory() + "/showCustomizeDialog"), true);
                test();
            }
        });
    }

    private void test() {
        new DialogUtils().showProgressDialog(this);
    }


    private static String path = Environment.getExternalStorageDirectory() + "/UAVCar/VoiceOrder.txt";

}