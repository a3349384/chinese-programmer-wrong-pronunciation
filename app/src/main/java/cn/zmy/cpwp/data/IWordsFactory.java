package cn.zmy.cpwp.data;

import java.util.List;

import cn.zmy.cpwp.model.Words;

/**
 * Created by zmy on 2019/1/17.
 * 单词工厂，负责提供单词
 */

public interface IWordsFactory
{
    List<Words> provide();
}
