package com.grain.universalutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.grain.utils.MQTTManager;
import com.grain.utils.hint.L;
import com.grain.utils.utils.GetTime;
import com.grain.utils.utils.json.JSONUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}