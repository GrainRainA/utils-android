package com.grain.universalutils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.view.View;

import com.grain.utils.InitUtilsModule;
import com.grain.utils.Interfaces.OnSelectListener;
import com.grain.utils.file.DocumentsUtils;
import com.grain.utils.file.FileUtils;
import com.grain.utils.hint.toast;
import com.grain.utils.mqtt.MQTTManager;
import com.grain.utils.mqtt.MQTTManagerIm;
import com.grain.utils.hint.L;
import com.grain.utils.utils.GetTime;
import com.grain.utils.utils.Permission.ApplyPermission;
import com.grain.utils.view.FullScreen;
import com.grain.utils.view.dialog.DialogUtils;
import com.grain.utils.view.dialog.ProgressDialog;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        L.e(TxtFile.read(path));
        new ApplyPermission(this);
        InitUtilsModule.init(this);
        FullScreen.setHideAllBar(this);

        findViewById(R.id.btn).setOnClickListener(view -> {

            List<String> test = new ArrayList<>();
            test.add("fsafsd");
            test.add("hfhgh");
            new DialogUtils().showListDialog(MainActivity.this, null, test, new OnSelectListener() {
                @Override
                public void onSelect(int position, String text) {
                    L.e(position, text);
                }
            });

        });
    }



    //    public static String host = "tcp://120.79.6.170:1883";
//    public static String host = "tcp://192.168.5.74:1883";
    public static String host = "tcp://socket.jizhenkeji.com:6001";

    static MQTTManager mqttManager;
    private void mqttTest() {
        mqttManager = new MQTTManagerIm();
        mqttManager.connect(host, new MQTTManager.Callback() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void messageArrived(String topic, byte[] bytes) {
                String message = new String(bytes);

                L.e(topic, message);
            }

            @Override
            public void onFailure(String error) {
                L.e("onStateUpload", error);
            }
        });

        mqttManager.subscribe("test");
    }


    int i = 0;

    private static String path = Environment.getExternalStorageDirectory() + "/UAVCar/VoiceOrder.txt";

}