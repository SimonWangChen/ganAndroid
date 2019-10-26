package com.proclassmates.ganandroid.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.proclassmates.ganandroid.BuildConfig;
import com.proclassmates.ganandroid.database.AppDatabase;
import com.proclassmates.ganandroid.database.TaskEntry;

import java.util.List;

public class RecyclerViewModel extends AndroidViewModel {

    private static final String TAG = RecyclerViewModel.class.getSimpleName();

    private LiveData<List<TaskEntry>> tasks;

    public RecyclerViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(this.getApplication());

        if (BuildConfig.DEBUG) Log.d(TAG, "Actively retrieving the tasks from the DataBase");

        tasks = db.taskDao().loadAllTasks();
    }

    public LiveData<List<TaskEntry>> getTasks() {
        return tasks;
    }
}
