package com.jc.road.test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jc.road.R;
import com.jc.road.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjc on 2018/9/13
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {
    private List<TestBean> mDatas = new ArrayList<>();

    @Override
    public TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_test, parent, false));
    }

    @Override
    public void onBindViewHolder(TestHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addItems(List<TestBean> datas) {
        this.mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    class TestHolder extends RecyclerView.ViewHolder {
        TestHolder(View itemView) {
            super(itemView);
        }
    }
}