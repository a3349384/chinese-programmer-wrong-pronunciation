package cn.zmy.cpwp;

import android.app.Application;

import cn.zmy.common.utils.context.ContextManager;

/**
 * Created by zmy on 2019/1/17.
 */

public class TheApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        ContextManager.setAppContext(this);
    }
}
