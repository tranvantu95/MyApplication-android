package com.company.myapplication;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.company.myapplication.config.AppData;
import com.company.myapplication.config.Debug;
import com.company.myapplication.model.AppModel;
import com.company.myapplication.utils.General;
import com.company.myapplication.utils.ModelUtils;

public class MyApplication extends Application implements SharedPreferences.OnSharedPreferenceChangeListener {

    protected final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Debug.TAG + TAG, "onCreate");

        // set support vector drawable for api < 21
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        // AppData
        AppData.initialize(this);

        SharedPreferences sharedPreferences = AppData.getInstance().getDefaultPref();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        General.typeView = AppData.getInstance().getTypeView();
        getModel(AppModel.class).getTypeView().setValue(General.typeView);

        //
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            private int numActivityCreated;

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                numActivityCreated++;

                if(numActivityCreated == 1) {

                }
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                numActivityCreated--;
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    protected <Model extends ViewModel> Model getModel(Class<Model> clazz) {
        return ModelUtils.ofApp(this).get(clazz);
    }

}
