package com.proclassmates.ganandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.proclassmates.ganandroid.data.WaitlistContract;
import com.proclassmates.ganandroid.data.WaitlistDBHelper;

public class MainActivity extends AppCompatActivity {

    private GuestListAdapter guestListAdapter;

    private SQLiteDatabase sqLiteDatabase;
    private EditText mNewGuestNameET;
    private EditText mNewPartySizeET;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView waitlistRV;

        waitlistRV = findViewById(R.id.all_guests_list_view);

        mNewGuestNameET = findViewById(R.id.person_name_edit_text);
        mNewPartySizeET = findViewById(R.id.party_count_edit_text);

        waitlistRV.setLayoutManager(new LinearLayoutManager(this));

        WaitlistDBHelper waitListDBHelper = new WaitlistDBHelper(this);

        sqLiteDatabase = waitListDBHelper.getWritableDatabase();

        Cursor cursor = getAllGuests();

        guestListAdapter = new GuestListAdapter(this, cursor);

        waitlistRV.setAdapter(guestListAdapter);

        // todo: itemTouchHelper
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                removeGuest(id);

                guestListAdapter.swapCursor(getAllGuests());
            }


        }).attachToRecyclerView(waitlistRV);


    }

    private boolean removeGuest(long id) {
        return sqLiteDatabase.delete(WaitlistContract.WaitlistEntry.TABLE_NAME, WaitlistContract.WaitlistEntry._ID + "=" + id, null) > 0;
    }

    private Cursor getAllGuests() {
        return sqLiteDatabase.query(
                WaitlistContract.WaitlistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP

        );
    }


    public void addToWaitlist(View view) {
        if (mNewGuestNameET.getText().length() == 0 ||
                mNewPartySizeET.getText().length() == 0) {
            return;
        }
        //default party size to 1
        int partySize = 1;
        try {
            //mNewPartyCountEditText inputType="number", so this should always work
            partySize = Integer.parseInt(mNewPartySizeET.getText().toString());
        } catch (NumberFormatException ex) {
            Log.e(LOG_TAG, "Failed to parse party size text to number: " + ex.getMessage());
        }

        // Add guest info to mDb
        addNewGuest(mNewGuestNameET.getText().toString(), partySize);

        // Update the cursor in the adapter to trigger UI to display the new list
        guestListAdapter.swapCursor(getAllGuests());

        //clear UI text fields
        mNewPartySizeET.clearFocus();
        mNewGuestNameET.getText().clear();
        mNewPartySizeET.getText().clear();
    }

    private long addNewGuest(String name, int size) {
        ContentValues cv = new ContentValues();

        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, name);
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, size);
        return sqLiteDatabase.insert(WaitlistContract.WaitlistEntry.TABLE_NAME, null, cv);
    }
}
