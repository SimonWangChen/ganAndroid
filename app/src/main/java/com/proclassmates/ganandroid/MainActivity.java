package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    /*
     * This constant String will be used to store the content of the TextView used to display the
     * list of callbacks. The reason we are storing the contents of the TextView is so that you can
     * see the entire set of callbacks as they are called.
     */
    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";

    /* Constant values for the names of each respective lifecycle callback */
    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";

    /*
     * This TextView will contain a running log of every lifecycle callback method called from this
     * Activity. This TextView can be reset to its default state by clicking the Button labeled
     * "Reset Log"
     */
    private TextView mLifecycleDisplay;

    // COMPLETED (1) Declare and instantiate a static ArrayList of Strings called mLifecycleCallbacks
    /*
     * This ArrayList will keep track of lifecycle callbacks that occur after we are able to save
     * them. Since, as we've observed, the contents of the TextView are saved in onSaveInstanceState
     * BEFORE onStop and onDestroy are called, we must track when onStop and onDestroy are called,
     * and then update the UI in onStart when the Activity is back on the screen.
     */

    private static final ArrayList<String> mLifecycleCallbacks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLifecycleDisplay = findViewById(R.id.tv_lifecycle_events_display);
        if(savedInstanceState !=null) {
            if(savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY )){
                String allPreSavedInstanceState = savedInstanceState.getString(LIFECYCLE_CALLBACKS_TEXT_KEY);
                mLifecycleDisplay.setText(allPreSavedInstanceState);
            }
        }

        for (int i = mLifecycleCallbacks.size() -1 ; i>=0; i--){
            mLifecycleDisplay.setText(mLifecycleCallbacks.get(i) + "\n");
        }

        mLifecycleCallbacks.clear();

        logAndAppend("on create");

    }

    @Override
    protected void onStart() {
        super.onStart();
        logAndAppend("on start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logAndAppend("on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logAndAppend("on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        // COMPLETED (2) Add the ON_STOP String to the front of mLifecycleCallbacks
        /*
         * Since any updates to the UI we make after onSaveInstanceState (onStop, onDestroy, etc),
         * we use an ArrayList to track if these lifecycle events had occurred. If any of them have
         * occurred, we append their respective name to the TextView.
         */
        mLifecycleCallbacks.add(0, ON_STOP);

        logAndAppend(ON_STOP);
    }

    /**
     * Called after your activity has been stopped, prior to it being started again.
     *
     * Always followed by onStart()
     */
    @Override
    protected void onRestart() {
        super.onRestart();

        logAndAppend(ON_RESTART);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // COMPLETED (3) Add the ON_DESTROY String to the front of mLifecycleCallbacks
        /*
         * Since any updates to the UI we make after onSaveInstanceState (onStop, onDestroy, etc),
         * we use an ArrayList to track if these lifecycle events had occurred. If any of them have
         * occurred, we append their respective name to the TextView.
         */
        mLifecycleCallbacks.add(0, ON_DESTROY);

        logAndAppend(ON_DESTROY);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logAndAppend(ON_SAVE_INSTANCE_STATE);
        String lifecycleDisplayTextViewContents = mLifecycleDisplay.getText().toString();
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY, lifecycleDisplayTextViewContents);
    }

    private void logAndAppend(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);

        mLifecycleDisplay.append(lifecycleEvent + "\n");
    }

    public void resetLifecycleDisplay(View view) {
        mLifecycleDisplay.setText("Lifecycle callbacks:\n");
    }
}
