package com.grain.universalutils;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.grain.utils.InitUtilsModule;
import com.grain.utils.MQTTManager;
import com.grain.utils.file.FileUtils;
import com.grain.utils.file.TxtFile;
import com.grain.utils.hint.L;
import com.grain.utils.utils.GetTime;
import com.grain.utils.utils.Permission.ApplyPermission;
import com.grain.utils.utils.json.JSONUtils;
import com.grain.utils.view.FullScreen;
import com.grain.utils.view.control.DialogUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.impl.CenterListPopupView;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;
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

//        L.e(TxtFile.read(path));
//        new ApplyPermission(this);
        FullScreen.setHideAllBar(this);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FileUtils.deleteFile(new File(Environment.getExternalStorageDirectory() + "/test"), true);
//                test();
            }
        });
    }

    String t1 = "{\"attitude\":{\"pitch\":0.013980170201192087,\"roll\":1.8744314267704882,\"yaw\":-32.48069092706881},\"flyMode\":\"DISARM\",\"imuStateInfo\":{\"XAccelerometer\":1,\"XAccelerometer1\":65,\"XGyroscope\":-6,\"XGyroscope1\":-7,\"XMagnetometer\":-1314,\"YAccelerometer\":-24,\"YAccelerometer1\":66,\"YGyroscope\":-3,\"YGyroscope1\":25,\"YMagnetometer\":-862,\"ZAccelerometer\":800,\"ZAccelerometer1\":4085,\"ZGyroscope\":-1,\"ZGyroscope1\":-9,\"ZMagnetometer\":2833,\"absolutePressure\":100285,\"airspeedPressure\":-50,\"fieldsUpdated\":4131839,\"temperature\":5910},\"locationCoordinate\":{\"altitude\":40.41,\"altitudeAndSpeedInfo\":{\"airSpeed\":0.001,\"altitude\":40.41,\"relativeAltitude\":40.41,\"speed\":1.46},\"gpsType\":0,\"homeAltitude\":4041,\"homeLatitude\":30.8600318,\"homeLongitude\":118.7963657,\"latitude\":30.8600318,\"longitude\":118.7963657,\"rtkInfo\":{\"positionType\":\"SINGLE\",\"satellitesCount\":0}}}";
    String t2 = "{\"attitude\":{\"pitch\":0.013980170201192087,\"roll\":1.8744314267704882,\"yaw\":-32.48069092706881},\"flyMode\":\"DISARM\",\"imuStateInfo\":{\"XAccelerometer\":1,\"XAccelerometer1\":65,\"XGyroscope\":-6,\"XGyroscope1\":-7,\"XMagnetometer\":-1314,\"YAccelerometer\":-24,\"YAccelerometer1\":66,\"YGyroscope\":-3,\"YGyroscope1\":25,\"YMagnetometer\":-862,\"ZAccelerometer\":800,\"ZAccelerometer1\":4085,\"ZGyroscope\":-1,\"ZGyroscope1\":-9,\"ZMagnetometer\":2833,\"absolutePressure\":100285,\"airspeedPressure\":-50,\"fieldsUpdated\":4131839,\"temperature\":5910},\"locationCoordinate\":{\"altitude\":40.41,\"altitudeAndSpeedInfo\":{\"airSpeed\":0.001,\"altitude\":40.41,\"relativeAltitude\":40.41,\"speed\":1.46},\"gpsType\":0,\"homeAltitude\":4041,\"homeLatitude\":30.8600318,\"homeLongitude\":118.7963657,\"latitude\":30.8600318,\"longitude\":118.7963657,\"rtkInfo\":{\"positionType\":\"SINGLE\",\"satellitesCount\":0}}}";
    private void test() {
        L.e(t1.equals(t2));
//        DialogUtils.showProgressDialog(this);
    }


    private static String path = Environment.getExternalStorageDirectory() + "/UAVCar/VoiceOrder.txt";

}