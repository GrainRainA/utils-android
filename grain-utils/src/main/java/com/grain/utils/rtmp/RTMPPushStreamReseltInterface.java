package com.grain.utils.rtmp;

/**
 * @anthor GrainRain
 * @funcation 推流结果回调接口
 * @date 2021/4/28
 */
public interface RTMPPushStreamReseltInterface {

    void onSuccess();
    void onFailure();
}
