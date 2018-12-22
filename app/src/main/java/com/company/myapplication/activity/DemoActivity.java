package com.company.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.company.myapplication.R;
import com.company.myapplication.activity.base.BaseActivity;
import com.company.myapplication.db.AppDatabase;
import com.company.myapplication.fragment.DemoListFragment;
import com.company.myapplication.item.DemoItem;
import com.company.myapplication.utils.AppUtils;

public class DemoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        demo();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.demo_activity;
    }

    private void demo() {

        findViewById(R.id.btn_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DemoItem newItem = new DemoItem();
                newItem.setName("" + System.currentTimeMillis());
                Log.d("btn_plus","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

                AppDatabase.getInstance(getApplicationContext()).getDemoDao().insertAll(newItem);
            }
        });

        AppUtils.replaceFragment(getSupportFragmentManager(), R.id.fragment_container, DemoListFragment.class, false);

    }

}
