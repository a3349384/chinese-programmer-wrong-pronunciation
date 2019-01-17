package cn.zmy.cpwp.http;

import okhttp3.Request;

/**
 * Created by zmy on 2019/1/17.
 * 管理所有Http请求
 */

public class Requests
{
    /**
     * 从GitHub上获取单词
     * */
    public static Request getWordsFromGitHub()
    {
        return new Request.Builder()
                       .get()
                       .url("https://raw.githubusercontent.com/shimohq/chinese-programmer-wrong-pronunciation/master/README.md")
                       .build();
    }

    /**
     * 下载单词发音文件
     * */
    public static Request downloadWordsPronunciation(String url)
    {
        return new Request.Builder()
                       .get()
                       .url(url)
                       .build();
    }
}
