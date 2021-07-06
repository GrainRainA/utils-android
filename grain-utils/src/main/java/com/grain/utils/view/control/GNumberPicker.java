package com.grain.utils.view.control;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

/**
 * @anthor GrainRain
 * @funcation 数字选择器
 * @date 2021/6/5
 */
public class GNumberPicker extends NumberPicker {

    /**
     * setMinValue
     * setMaxValue
     * setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
     * @param context
     */
    public GNumberPicker(Context context) {
        super(context);
    }

    public GNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
