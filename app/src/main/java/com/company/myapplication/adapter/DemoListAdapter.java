package com.company.myapplication.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.company.myapplication.R;
import com.company.myapplication.adapter.base.ListAdapter;
import com.company.myapplication.item.DemoItem;

public class DemoListAdapter extends ListAdapter<DemoItem, DemoListAdapter.ViewHolder> {

    public DemoListAdapter(@NonNull OnItemClickListener<DemoItem> onItemClickListener) {
        super(PAGED_LIST_ADAPTER_MODE, onItemClickListener, createDiffCallback(DemoItem.class));
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.demo_item;
    }

    @NonNull
    @Override
    protected ViewHolder getViewHolder(View view, int viewType) {
        return new ViewHolder(this, view);
    }

    public static class ViewHolder extends ListAdapter.ViewHolder<DemoItem, DemoListAdapter> {

        private TextView textView;

        public ViewHolder(DemoListAdapter adapter, View itemView) {
            super(adapter, itemView);

            textView = itemView.findViewById(R.id.text_view);
        }

        @Override
        protected void updateItem(@NonNull DemoItem demoItem, int position) {
            textView.setText(demoItem.getName());
        }

        @Override
        protected void clearItem(int position) {

        }

    }
}
