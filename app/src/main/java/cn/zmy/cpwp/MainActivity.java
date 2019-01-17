package cn.zmy.cpwp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import cn.zmy.common.utils.IntentUtil;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.wordslist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuDataSource:
            {
                try
                {
                    IntentUtil.openWithBrowser(this, "https://github.com/shimohq/chinese-programmer-wrong-pronunciation");
                }
                catch (Exception ignored){}
                break;
            }
            case R.id.menuViewSource:
            {
                try
                {
                    IntentUtil.openWithBrowser(this, "https://github.com/a3349384/chinese-programmer-wrong-pronunciation");
                }
                catch (Exception ignored){}
                break;
            }
        }
        return true;
    }
}
