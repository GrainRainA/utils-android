package com.grain.universalutils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.grain.utils.Convert;
import com.grain.utils.InitUtilsModule;
import com.grain.utils.Interfaces.listView.OnItemClickListener;
import com.grain.utils.Interfaces.listView.OnItemLongClickListener;
import com.grain.utils.MQTTManager;
import com.grain.utils.hint.L;
import com.grain.utils.utils.Permission.ApplyPermission;
import com.grain.utils.view.FullScreen;
import com.grain.utils.view.dialog.DialogUtils;
import com.grain.utils.view.dialog.ProgressDialog;
import com.grain.utils.view.listView.GrainStringListView;
import com.grain.utils.view.spinner.GrainSpinner;

import org.angmarch.views.NiceSpinner;

import java.text.NumberFormat;
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
        new ApplyPermission(this);
        InitUtilsModule.init(this);
        FullScreen.setHideAllBar(this);

        mqttTest();

//        final List<String> list = new ArrayList<>();
//        list.add("ss");
//        list.add("ss1");
//        list.add("ss2");
//
        RecyclerView recyclerView = findViewById(R.id.listView);
//        final GrainStringListView listView = new GrainStringListView(
//                this, recyclerView, list,
//                new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                        L.e(position);
//                    }
//                },
//                new OnItemLongClickListener() {
//                    @Override
//                    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
//                        L.e("long", position);
//                        return false;
//                    }
//                });


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FileUtils.deleteFile(new File(Environment.getExternalStorageDirectory() + "/showCustomizeDialog"), true);
//                linear_layout.addView(new TestLayout(MainActivity.this));
//                test();

//                list.set(1, "basan");
//                list.remove(1);
//                listView.setList(list);

                MQTTManager.switchHost("tcp://120.79.6.170:1883");
            }
        });

        GrainSpinner niceSpinner = findViewById(R.id.nice_spinner);
//        niceSpinner.setBackgroundResource(R.drawable.rounded_rectangle_frame_blue_bg); //设置控件的形状和背景


        List<String> list = new ArrayList<>();
        list.add("grain");
        list.add("grain11");
        list.add("grain22");
        niceSpinner.setList(list);
//        niceSpinner.setSelectedIndex(0);

        niceSpinner.addOnItemClickListener((adapterView, view, i, l) -> {
            L.e(list.get(i));
        });

        L.e(Convert.cutOutFractionDigits("dsafdas", 5, -1));

    }

//    public static String host = "tcp://120.79.6.170:1883";
    public static String host = "tcp://192.168.5.74:1883";
    private void mqttTest() {
        MQTTManager.connect(host, (s, bytes) -> {
            String message = new String(bytes);
            L.e(s, message);
        });

        MQTTManager.subscribe("test");
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