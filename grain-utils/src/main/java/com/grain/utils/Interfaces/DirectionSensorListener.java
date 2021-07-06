package com.grain.utils.Interfaces;

/**
 * @anthor GrainRain
 * @funcation 方向传感器数据回调接口
 * @date 2021/1/26
 */
public interface DirectionSensorListener {

    void onSensorChanged(float x, float y, float z);
}
