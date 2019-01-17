package cn.zmy.cpwp;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import cn.zmy.common.listfragment.SimpleListFragment;
import cn.zmy.cpwp.adapter.WordsListAdapter;
import cn.zmy.cpwp.data.WordsFactoryDirector;
import cn.zmy.cpwp.model.Words;

/**
 * Created by zmy on 2019/1/17.
 * 单词列表
 */

public class WordsListFragment extends SimpleListFragment<Words>
{
    @Override
    protected void onConfig()
    {
        super.onConfig();
        setAdapter(new WordsListAdapter());
    }

    @Override
    protected void onViewCreate(View root, RecyclerView recyclerView)
    {
        super.onViewCreate(root, recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(recyclerView.getResources().getDrawable(R.drawable.divider__lightgray_1px));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected List<Words> getItems(int pageIndex)
    {
        return WordsFactoryDirector.instance.provide();
    }
}
