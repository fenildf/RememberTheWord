package com.zxtcode.remembertheword.activity;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

import com.zxtcode.remembertheword.R;

/**
 * Created by tarent on 2017/3/14.
 */

public class ImportWordActivity extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_word);
    }
}
