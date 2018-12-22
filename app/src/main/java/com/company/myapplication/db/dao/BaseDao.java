package com.company.myapplication.db.dao;

import android.arch.lifecycle.LiveData;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

import java.util.List;

public interface BaseDao<Entity> {

    // WorkerThread
    public abstract Long[] _insertAll(Entity... entities);

    public abstract void _updateAll(Entity... entities);

    public abstract void _deleteAll(Entity... entities);

    // MainThread
    public abstract LiveData<List<Long>> insertAll(Entity... entities);

    public abstract void updateAll(Entity... entities);

    public abstract void deleteAll(Entity... entities);

}
