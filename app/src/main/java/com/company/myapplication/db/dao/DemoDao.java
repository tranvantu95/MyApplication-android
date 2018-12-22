package com.company.myapplication.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.util.Log;

import com.company.myapplication.config.Debug;
import com.company.myapplication.db.AppDatabase;
import com.company.myapplication.db.entity.DemoEntity;
import com.company.myapplication.item.DemoItem;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Dao
public abstract class DemoDao {

    protected final String TAG = getClass().getSimpleName();

    private static final ExecutorService executor = AppDatabase.executor;

    // WorkerThread
    @Query("SELECT * FROM demo")
    public abstract List<DemoItem> _getAll();

    @Query("SELECT COUNT(*) FROM demo")
    public abstract int _getCount();

    @Insert//(onConflict = OnConflictStrategy.REPLACE)
    public abstract Long[] _insertAll(DemoEntity... demoEntities);

    @Update
    public abstract void _updateAll(DemoEntity... demoEntities);

    @Delete
    public abstract void _deleteAll(DemoEntity... demoEntities);

    // MainThread
    @Query("SELECT * FROM demo ORDER BY id DESC")
    public abstract DataSource.Factory<Integer, DemoItem> getAllWithDataSource();

    public LiveData<List<DemoItem>> getAll() {
        final MutableLiveData<List<DemoItem>> items = new MutableLiveData<>();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                items.postValue(_getAll());
            }
        });
        return items;
    }

    public LiveData<Integer> getCount() {
        final MutableLiveData<Integer> count = new MutableLiveData<>();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                count.postValue(_getCount());
            }
        });
        return count;
    }

    public LiveData<List<Long>> insertAll(final DemoEntity... demoEntities) {
        Log.d(Debug.TAG + TAG, "insertAll");
        final MutableLiveData<List<Long>> ids = new MutableLiveData<>();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Long[] _ids = _insertAll(demoEntities);
                int i = 0;
                for(long id : _ids) {
                    demoEntities[i].setId(id);
                    i++;
                }
                ids.postValue(Arrays.asList(_ids));
            }
        });
        return ids;
    }

    public void updateAll(final DemoEntity... demoEntities) {
        Log.d(Debug.TAG + TAG, "updateAll");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                _updateAll(demoEntities);
            }
        });
    }

    public void deleteAll(final DemoEntity... demoEntities) {
        Log.d(Debug.TAG + TAG, "deleteAll");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                _deleteAll(demoEntities);
            }
        });
    }
}
