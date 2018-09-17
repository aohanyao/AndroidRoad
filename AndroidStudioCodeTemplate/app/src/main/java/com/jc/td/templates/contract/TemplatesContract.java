package com.jc.td.templates.contract;

import com.jc.td.templates.bean.TemplatesBean;

import java.util.List;

/**
 * Created by 江俊超 on 2018/9/17.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public interface TemplatesContract {
    interface View {
        /**
         * 刷新更多成功
         */
        void refreshSuccess(List<TemplatesBean> datas);

        /**
         * 加载更多成功
         */
        void loadMoreSuccess(List<TemplatesBean> datas);
    }

    abstract class Presenter {
        protected View view;


        public Presenter(View view) {
            this.view = view;
        }

        /**
         * 刷新
         */
        public abstract void refresh();

        /**
         * 加载更多
         */
        public abstract void loadMore();
    }
}
