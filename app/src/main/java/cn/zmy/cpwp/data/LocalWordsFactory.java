package cn.zmy.cpwp.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.zmy.common.utils.context.ContextManager;
import cn.zmy.cpwp.model.Words;

/**
 * Created by zmy on 2019/1/17.
 * 本地单词工厂
 */

public class LocalWordsFactory implements IWordsFactory
{
    @Override
    public List<Words> provide()
    {
        InputStream inputStream = null;
        try
        {
            inputStream = ContextManager.getAppContext().getAssets()
                    .open("pronunciation.md");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return MarkdownWordsParser.parse(inputStream);
    }
}
