package com.zxtcode.remembertheword.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WearableRecyclerView;

import com.zxtcode.remembertheword.R;
import com.zxtcode.remembertheword.adpater.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends WearableActivity {

    private WearableRecyclerView mMainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        List<MainAdapter.Item> itemList = new ArrayList<>();
        itemList.add(new MainAdapter.Item(getString(R.string.activity_main_remind_cycle), new Intent(this, RemindMeActivity.class)));

        mMainList = (WearableRecyclerView) findViewById(R.id.main_list);
        mMainList.setAdapter(new MainAdapter(MainActivity.this, itemList));
    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {

    }
}
