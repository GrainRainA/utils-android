package com.grain.universalutils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.grain.utils.hint.toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @anthor GrainRain
 * @funcation
 * @date 2021/8/18
 */
public class TestLayout extends RelativeLayout {

    public TestLayout(Context context) {
        super(context);
        initView(context);
    }

    public TestLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TestLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_layout, this);

        view.findViewById(R.id.test1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.show("test1");
            }
        });

    }
}
