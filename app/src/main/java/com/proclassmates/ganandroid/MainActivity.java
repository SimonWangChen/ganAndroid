package com.proclassmates.ganandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GreenAdapter.ListItemClickListener{
    private static final int NUM_LIST_ITEMS = 100;

    GreenAdapter mGreenAdapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_numbers);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setHasFixedSize(true);

        mGreenAdapter = new GreenAdapter(this, NUM_LIST_ITEMS);

        mRecyclerView.setAdapter(mGreenAdapter);
    }

    @Override
    public void onListItemClick(int index) {
        Toast.makeText(this, "item: " + index + "clicked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId){
            case(R.menu.refresh):
                mGreenAdapter = new GreenAdapter(this, NUM_LIST_ITEMS);
                mRecyclerView.setAdapter(mGreenAdapter);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
