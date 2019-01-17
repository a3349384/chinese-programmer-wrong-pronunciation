package cn.zmy.cpwp.data;

import java.io.InputStream;
import java.util.List;

import cn.zmy.cpwp.http.HttpClients;
import cn.zmy.cpwp.http.Requests;
import cn.zmy.cpwp.model.Words;
import cn.zmy.http.HttpHelper;

/**
 * Created by zmy on 2019/1/17.
 * GitHub单词工厂
 */

public class GitHubWordsFactory implements IWordsFactory
{
    @Override
    public List<Words> provide()
    {
        InputStream inputStream = HttpHelper.getInputStream(HttpClients.common(), Requests.getWordsFromGitHub());
        return MarkdownWordsParser.parse(inputStream);
    }
}
