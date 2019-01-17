package cn.zmy.cpwp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.zmy.cpwp.data.WordsFactoryDirector;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                WordsFactoryDirector.instance.provide();
            }
        }).start();
    }
}
