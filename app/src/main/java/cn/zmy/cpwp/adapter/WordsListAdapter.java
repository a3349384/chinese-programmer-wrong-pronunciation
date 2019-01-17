package cn.zmy.cpwp.adapter;

import cn.zmy.common.listfragment.adapter.BaseBindingListAdapter;
import cn.zmy.cpwp.R;
import cn.zmy.cpwp.databinding.WordslistItemBinding;
import cn.zmy.cpwp.model.Words;

/**
 * Created by zmy on 2019/1/17.
 * 单词列表适配器
 */

public class WordsListAdapter extends BaseBindingListAdapter<Words, WordslistItemBinding>
{
    @Override
    protected int getItemLayout(int viewType)
    {
        return R.layout.wordslist_item;
    }

    @Override
    protected void onBindItem(WordslistItemBinding binding, int position)
    {
        Words model = mItems.get(position);
        binding.setModel(model);
        binding.executePendingBindings();
    }
}
