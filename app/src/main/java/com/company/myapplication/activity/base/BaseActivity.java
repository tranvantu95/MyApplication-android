package com.company.myapplication.activity.base;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

import com.company.myapplication.config.Debug;
import com.company.myapplication.utils.ModelUtils;
import com.facebook.CallbackManager;

public abstract class BaseActivity extends AppCompatActivity {
    
    protected final String TAG = getClass().getSimpleName();

    protected boolean isDestroy = true;
    protected boolean isStop = true;
    protected boolean isPause = true;

    protected CallbackManager mCallbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Debug.TAG + TAG, "onCreate");
        isDestroy = false;
        beforeSetContentView();
        setContentView(getContentLayoutId());
        mCallbackManager = CallbackManager.Factory.create();
    }

    protected abstract int getContentLayoutId();

    protected void beforeSetContentView() {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public CallbackManager getCallbackManager() {
        return mCallbackManager;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Debug.TAG + TAG, "onStart");
        isStop = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Debug.TAG + TAG, "onResume");
        isPause = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Debug.TAG + TAG, "onPause");
        isPause = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Debug.TAG + TAG, "onPause");
        isStop = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Debug.TAG + TAG, "onDestroy");
        isDestroy = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(Debug.TAG + TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(Debug.TAG + TAG, "onRestoreInstanceState");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(Debug.TAG + TAG, "onCreateOptionsMenu");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d(Debug.TAG + TAG, "onPrepareOptionsMenu");
        return super.onPrepareOptionsMenu(menu);
    }

    // Model
    protected <Model extends ViewModel> Model getModel(Class<Model> clazz) {
        return ModelUtils.of(this).get(clazz);
    }

    protected <Model extends ViewModel> Model getAppModel(Class<Model> clazz) {
        return ModelUtils.ofApp(getApplication()).get(clazz);
    }

    protected <T> void observe(LiveData<T> liveData, Observer<T> observer) {
        liveData.observe(getLifecycleOwner(), observer);
    }

    protected <T> void removeObserve(LiveData<T> liveData, Observer<T> observer) {
        liveData.removeObserver(observer);
    }

    protected <T> void removeObserves(LiveData<T> liveData) {
        liveData.removeObservers(getLifecycleOwner());
    }

    protected LifecycleOwner getLifecycleOwner() {
        return this;
    }

}
