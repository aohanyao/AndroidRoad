package com.jc.td.templates.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jc.td.R;
import com.jc.td.templates.bean.TemplatesBean;
import com.jc.td.templates.contract.TemplatesContract;
import com.jc.td.templates.presenter.TemplatesPresenter;
import com.jc.td.templates.view.adapter.TemplatesAdapter;

import java.util.ArrayList;
import java.util.List;

public class TemplatesActivity extends AppCompatActivity implements TemplatesContract.View,
        BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "TemplatesActivity";
    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private TemplatesAdapter mAdapter;


    private List<TemplatesBean> mDatas = new ArrayList<>();

    private Activity mActivity;


    private TemplatesPresenter p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        p = new TemplatesPresenter(this);
        initView();
        initAdapter();
        onRefresh();

    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mAdapter = new TemplatesAdapter(mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 初始化View
     */
    private void initView() {
        mRecyclerView = findViewById(R.id.rv_templates);
        mSwipeRefreshLayout = findViewById(R.id.srl_templates);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void refreshSuccess(List<TemplatesBean> datas) {
        mSwipeRefreshLayout.setRefreshing(false);
        mDatas = datas;
        mAdapter.setNewData(mDatas);
        Log.e(TAG, "refreshSuccess: " + datas.size());
    }

    @Override
    public void loadMoreSuccess(List<TemplatesBean> datas) {
        mDatas.addAll(datas);
        mAdapter.notifyDataSetChanged();
        mAdapter.loadMoreComplete();


    }

    @Override
    public void onLoadMoreRequested() {
        p.loadMore();
    }

    @Override
    public void onRefresh() {
        p.refresh();
    }
}
