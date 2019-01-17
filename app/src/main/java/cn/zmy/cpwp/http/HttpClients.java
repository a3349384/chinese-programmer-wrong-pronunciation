package cn.zmy.cpwp.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by zmy on 2019/1/17.
 * 管理所有HttpClient
 */

public class HttpClients
{
    private static OkHttpClient mCommonHttpClient;

    /**
     * 获取常规的Http Client
     * */
    public static OkHttpClient common()
    {
        if (mCommonHttpClient == null)
        {
            synchronized (HttpClients.class)
            {
                if (mCommonHttpClient == null)
                {
                    mCommonHttpClient = new OkHttpClient.Builder()
                                          .connectTimeout(5, TimeUnit.SECONDS)
                                          .readTimeout(5, TimeUnit.SECONDS)
                                          .writeTimeout(5, TimeUnit.SECONDS)
                                          .retryOnConnectionFailure(false)
                                          .build();
                }
            }
        }
        return mCommonHttpClient;
    }
}
