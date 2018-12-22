package com.company.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.company.myapplication.R;
import com.company.myapplication.activity.base.BaseActivity;
import com.company.myapplication.db.AppDatabase;
import com.company.myapplication.fragment.DemoListFragment;
import com.company.myapplication.item.DemoItem;
import com.company.myapplication.utils.AppUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

}
