package com.grain.utils.Interfaces.listView;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * @anthor GrainRain
 * @funcation
 * @date 2021/8/27
 */
public interface OnItemLongClickListener {

    boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position);
}
