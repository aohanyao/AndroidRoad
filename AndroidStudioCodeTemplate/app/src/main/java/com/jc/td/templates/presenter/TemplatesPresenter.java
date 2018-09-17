package com.jc.td.templates.presenter;

import android.os.Handler;

import com.jc.td.templates.bean.TemplatesBean;
import com.jc.td.templates.contract.TemplatesContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 江俊超 on 2018/9/17.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class TemplatesPresenter extends TemplatesContract.Presenter {

    private int count = 0;

    public TemplatesPresenter(TemplatesContract.View view) {
        super(view);
    }


    @Override
    public void refresh() {
        // 这里只是为了模拟数据，使用线程，真实情况不能这么用
        count = 0;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 模拟数据
                List<TemplatesBean> beans = new ArrayList<>();

                for (; count <= 15; count++) {
                    beans.add(new TemplatesBean(String.valueOf(count)));
                }

                // 回调数据
                view.refreshSuccess(beans);
            }
        }, 1500);

    }

    @Override
    public void loadMore() {
        // 这里只是为了模拟数据，使用线程，真实情况不能这么用
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 模拟数据
                // 模拟数据
                List<TemplatesBean> beans = new ArrayList<>();

                int maxPage=count + 15;

                for (; count <= maxPage; count++) {
                    beans.add(new TemplatesBean(String.valueOf(count)));
                }

                // 回调数据
                view.loadMoreSuccess(beans);
            }
        }, 1500);
    }
}
