package com.zxtcode.remembertheword.adpater;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxtcode.remembertheword.R;

import java.util.List;

/**
 * Created by tarent on 2017/3/14.
 */

public class MainAdapter extends Adapter<MainAdapter.MainHolder> {

    private List<Item> mItemList;
    private Activity mActivity;

    public MainAdapter(Activity activity, List<Item> itemList) {
        mActivity = activity;
        mItemList = itemList;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainHolder(mActivity.getLayoutInflater().inflate(R.layout.item_mian, parent, false));
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    public Item getItem(int position) {
        return mItemList == null ? null :mItemList.get(position);
    }

    class MainHolder extends RecyclerView.ViewHolder {

        TextView mNameTextView;

        public MainHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView;
        }

        public void bind(final Item item) {
            mNameTextView.setText(item.name);
            mNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActivity.startActivity(item.jumpActivityIntent);
                }
            });
        }
    }

    public static class Item {

        private final String name;
        private final Intent jumpActivityIntent;

        public Item(String name, Intent jumpActivityIntent) {
            this.name = name;
            this.jumpActivityIntent = jumpActivityIntent;
        }
    }

}
