package com.zxtcode.remembertheword.adpater;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.wearable.view.FragmentGridPagerAdapter;

/**
 * Created by tarent on 2017/3/1.
 */

public class WordPagerAdapter extends FragmentGridPagerAdapter {
    
    public WordPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getFragment(int i, int i1) {
        return null;
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount(int i) {
        return 0;
    }
}
