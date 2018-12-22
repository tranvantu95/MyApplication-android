package com.company.myapplication.fragment;

import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.company.myapplication.R;
import com.company.myapplication.adapter.DemoListAdapter;
import com.company.myapplication.adapter.base.ListAdapter;
import com.company.myapplication.db.AppDatabase;
import com.company.myapplication.fragment.base.ListFragment;
import com.company.myapplication.item.DemoItem;
import com.company.myapplication.model.DemoListModel;

import java.util.List;

public class DemoListFragment extends ListFragment<DemoItem, DemoListModel, DemoListAdapter> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setDataSourceFactory(AppDatabase.getInstance(getContext()).getDemoDao().getAllWithDataSource());
    }

    @Override
    protected void updateListAdapter(@NonNull List<DemoItem> demoItems) {
        super.updateListAdapter(demoItems);
        if(listView != null) listView.smoothScrollToPosition(0);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.demo_list_fragment;
    }

    @Override
    protected DemoListModel onCreateModel(int modelOwner) {
        return getModel(modelOwner, DemoListModel.class);
    }

    @Override
    protected PagedList.Config getPagedListConfig() {
        return new PagedList.Config.Builder().setPageSize(20).build();
    }

    @NonNull
    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @NonNull
    @Override
    protected DemoListAdapter onCreateListAdapter() {
        return new DemoListAdapter(new ListAdapter.OnItemClickListener<DemoItem>() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, @NonNull DemoItem demoItem, int position) {

            }
        });
    }

    @Override
    protected int onCreateDivider() {
        return 0;
    }
}
