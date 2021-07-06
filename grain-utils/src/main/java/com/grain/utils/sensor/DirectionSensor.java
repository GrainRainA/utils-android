package com.grain.utils.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.grain.utils.InitUtilsModule;
import com.grain.utils.Interfaces.DirectionSensorListener;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static android.content.Context.SENSOR_SERVICE;

/**
 * @anthor GrainRain
 * @funcation 方向传感器
 * @date 2021/1/26
 */
public class DirectionSensor {

    private DirectionSensorListener directionSensorListener;

    private SensorManager sensorManager;
    private Sensor sensor;

    public DirectionSensor(DirectionSensorListener mDirectionSensorListener) {

        directionSensorListener = mDirectionSensorListener;
        if (sensorManager == null) {
            sensorManager = (SensorManager) InitUtilsModule.getActivity().getSystemService(SENSOR_SERVICE);
        }

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            DecimalFormat format = new DecimalFormat( "0.00 ");

            float x = (float) Double.parseDouble(format.format(event.values[0]));  //顺时针旋转时:0~360 逆时针旋转时:360~0
            float y = (float) Double.parseDouble(format.format(event.values[1]));  //从底部抬起时: 0~180 从顶部抬起时: 0~-180
            float z = (float) Double.parseDouble(format.format(event.values[2]));  //从右侧抬起时: 0~180 从左侧抬起时: 0~-180

            if (directionSensorListener != null) {
                directionSensorListener.onSensorChanged(x, y, z);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
