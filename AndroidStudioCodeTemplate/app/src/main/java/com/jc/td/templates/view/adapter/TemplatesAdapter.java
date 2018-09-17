package com.jc.td.templates.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jc.td.R;
import com.jc.td.templates.bean.TemplatesBean;

import java.util.List;

/**
 * Created by 江俊超 on 2018/9/17.
 * Version:1.0
 * Description:
 * ChangeLog:
 */
public class TemplatesAdapter extends BaseQuickAdapter<TemplatesBean, BaseViewHolder> {
    public TemplatesAdapter(@Nullable List<TemplatesBean> data) {
        super(R.layout.layout_templates_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TemplatesBean item) {
        helper.setText(R.id.tv_templates_item, "item:" + item.itemName);
    }
}
