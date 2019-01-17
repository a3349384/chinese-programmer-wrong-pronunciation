package cn.zmy.cpwp.data;

import java.util.List;

import cn.zmy.cpwp.model.Words;

/**
 * Created by zmy on 2019/1/17.
 * 单词工厂指挥者
 */

public class WordsFactoryDirector implements IWordsFactory
{
    public static final WordsFactoryDirector instance = new WordsFactoryDirector();

    private IWordsFactory mLocalWordsFactory;
    private IWordsFactory mGitHubWordsFactory;

    private WordsFactoryDirector()
    {
        mLocalWordsFactory = new LocalWordsFactory();
        mGitHubWordsFactory = new GitHubWordsFactory();
    }

    /**
     * 尝试先通过GitHub获取单词数据，如果GitHub获取失败，再从本地获取单词数据。
     * */
    @Override
    public List<Words> provide()
    {
        List<Words> words = mGitHubWordsFactory.provide();
        if (words == null)
        {
            words = mLocalWordsFactory.provide();
        }
        return words;
    }
}
