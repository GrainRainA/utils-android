package com.grain.utils.view.spinner;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;

import com.grain.utils.R;

import org.angmarch.views.NiceSpinner;

import java.util.List;

/**
 * @anthor GrainRain
 * @funcation 封装下拉选择器
 * @date 2022/02/17
 */
public class GrainSpinner extends NiceSpinner {

    public GrainSpinner(Context context) {
        super(context);
        initView(context);
    }

    public GrainSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GrainSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setBackgroundResource(R.drawable.rounded_rectangle_frame_blue_bg); //设置控件的形状和背景

    }

    public <T> void setList(List<T> list) {
        attachDataSource(list);
    }

//    app:arrowTint="@color/colorPrimary"
//    app:textTint="@color/colorAccent"
}
