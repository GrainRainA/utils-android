package com.grain.utils.view.listView;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.grain.utils.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @anthor GrainRain
 * @funcation 封装自定义字符列表
 * @date 2021/8/30
 */
public class GrainStringListView {

    private GrainStringListViewAdapter adapter;

    public GrainStringListView(Context context, RecyclerView recyclerView, List<String> list) {
        init(context, recyclerView, list, R.layout.simple_list_item_1, null, null);
    }

    public GrainStringListView(Context context, RecyclerView recyclerView, List<String> list, com.grain.utils.Interfaces.listView.OnItemClickListener onItemClickListener) {
        init(context, recyclerView, list, R.layout.simple_list_item_1, onItemClickListener, null);
    }

    public GrainStringListView(Context context, RecyclerView recyclerView, List<String> list, com.grain.utils.Interfaces.listView.OnItemClickListener onItemClickListener, com.grain.utils.Interfaces.listView.OnItemLongClickListener onItemLongClickListener) {
        init(context, recyclerView, list, R.layout.simple_list_item_1, onItemClickListener, onItemLongClickListener);
    }

    public GrainStringListView(Context context, RecyclerView recyclerView, List<String> list, int layoutResId) {
        init(context, recyclerView, list, layoutResId, null, null);
    }

    public GrainStringListView(Context context, RecyclerView recyclerView, List<String> list, int layoutResId, com.grain.utils.Interfaces.listView.OnItemClickListener onItemClickListener) {
        init(context, recyclerView, list, layoutResId, onItemClickListener, null);
    }

    /**
     *
     * @param context
     * @param recyclerView
     * @param list
     * @param layoutResId 自定义布局 需要有id为text1的TextView
     * @param onItemClickListener
     * @param onItemLongClickListener
     */
    public GrainStringListView(Context context, RecyclerView recyclerView, List<String> list, int layoutResId, com.grain.utils.Interfaces.listView.OnItemClickListener onItemClickListener, com.grain.utils.Interfaces.listView.OnItemLongClickListener onItemLongClickListener) {
        init(context, recyclerView, list, layoutResId, onItemClickListener, onItemLongClickListener);
    }

    private void init(Context context, RecyclerView recyclerView, List<String> list, int layoutResId,
                      final com.grain.utils.Interfaces.listView.OnItemClickListener onItemClickListener,
                      final com.grain.utils.Interfaces.listView.OnItemLongClickListener onItemLongClickListener) {

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new GrainStringListViewAdapter(layoutResId, new ArrayList<String>());
        recyclerView.setAdapter(adapter);
        adapter.setNewInstance(list);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(adapter, view, position);
                }
            }
        });

        adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(adapter, view, position);
                }
                return true;
            }
        });
    }

    /**
     * 更新数据
     * @param list
     */
    public void setList(List<String> list) {
        if (adapter != null && list != null) {
            adapter.setList(list);
        }
    }

    /**
     * 获取适配器
     * @return
     */
    public GrainStringListViewAdapter getAdapter() {
        return adapter;
    }

    /**
     * 适配器
     */
    private class GrainStringListViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public GrainStringListViewAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, String s) {
            //设置数据
            baseViewHolder.setText(R.id.text1, s);
        }
    }
}
