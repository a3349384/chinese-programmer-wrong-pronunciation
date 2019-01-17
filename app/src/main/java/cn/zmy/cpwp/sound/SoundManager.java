package cn.zmy.cpwp.sound;

import android.media.SoundPool;
import android.text.TextUtils;
import android.util.ArrayMap;

import java.io.File;

import cn.zmy.common.utils.EncryptUtil;
import cn.zmy.common.utils.context.ContextManager;
import cn.zmy.cpwp.http.HttpClients;
import cn.zmy.cpwp.http.Requests;
import cn.zmy.http.HttpHelper;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zmy on 2019/1/17.
 * 声音管理
 */

public class SoundManager
{
    public static final SoundManager instance = new SoundManager();

    private SoundPool mSoundPool;
    private OnSoundLoadCompleteListener mSoundLoadCompleteListener;
    private ArrayMap<String, Integer> mSoundMap;
    private Disposable mSoundDisposable;

    private SoundManager()
    {
        mSoundPool = new SoundPool.Builder().setMaxStreams(1)
                             .build();
        mSoundLoadCompleteListener = new OnSoundLoadCompleteListener();
        mSoundPool.setOnLoadCompleteListener(mSoundLoadCompleteListener);
        mSoundMap = new ArrayMap<>();
    }

    /**
     * 播放指定单词的发音。
     * <p>
     * 首先，会尝试从本地文件中寻找是否存在指定单词已下载完成的发音文件，如果成功找到则直接播放该发音文件。否则发音文件会从网络下载。
     * </p>
     * <p>
     * 后面发起的播放指定会覆盖之前的播放指令。
     * </p>
     *
     * @param wordsName             单词名称
     * @param wordsPronunciationUrl 发音url
     */
    public void play(String wordsName, String wordsPronunciationUrl)
    {
        if (TextUtils.isEmpty(wordsName) || TextUtils.isEmpty(wordsPronunciationUrl))
        {
            return;
        }
        if (mSoundDisposable != null && !mSoundDisposable.isDisposed())
        {
            mSoundDisposable.dispose();
        }
        Maybe.create(new MaybeOnSubscribe<Integer>()
        {
            @Override
            public void subscribe(MaybeEmitter<Integer> emitter) throws Exception
            {
                String md5 = EncryptUtil.md5(wordsName);
                if (mSoundMap.containsKey(md5))
                {
                    emitter.onSuccess(mSoundMap.get(md5));
                    emitter.onComplete();
                    return;
                }
                int soundId = tryLoadPronunciationFile(md5);
                if (soundId > 0)
                {
                    emitter.onSuccess(soundId);
                    emitter.onComplete();
                }
                HttpHelper.downloadFileSync(HttpClients.common(), Requests.downloadWordsPronunciation(wordsPronunciationUrl),
                        getPronunciationRoot(), md5);
                soundId = tryLoadPronunciationFile(md5);
                if (soundId > 0)
                {
                    emitter.onSuccess(soundId);
                    emitter.onComplete();
                    return;
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MaybeObserver<Integer>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                mSoundDisposable = d;
            }

            @Override
            public void onSuccess(Integer integer)
            {
                if (mSoundPool.play(integer, 1, 1, 0, 0, 1) == 0)
                {
                    mSoundLoadCompleteListener.mReadToPlaySoundId = integer;
                }
                else
                {
                    mSoundLoadCompleteListener.mReadToPlaySoundId = 0;
                }
            }

            @Override
            public void onError(Throwable e)
            {

            }

            @Override
            public void onComplete()
            {

            }
        });
    }

    private File getPronunciationFile(String wordsNameMd5)
    {
        return new File(getPronunciationRoot().getAbsolutePath() + "/" + wordsNameMd5);
    }

    private File getPronunciationRoot()
    {
        return new File(ContextManager.getAppContext().getFilesDir().getAbsolutePath() +
                                "/pronunciations/");
    }

    private int tryLoadPronunciationFile(String md5)
    {
        File pronunciationFile = getPronunciationFile(md5);
        if (pronunciationFile != null && pronunciationFile.exists())
        {
            int soundId = mSoundPool.load(pronunciationFile.getAbsolutePath(), 1);
            mSoundMap.put(md5, soundId);
            return soundId;
        }
        return 0;
    }

    private static final class OnSoundLoadCompleteListener implements SoundPool.OnLoadCompleteListener
    {
        private int mReadToPlaySoundId;

        @Override
        public void onLoadComplete(SoundPool soundPool, int sampleId, int status)
        {
            if (sampleId == mReadToPlaySoundId && status == 0)
            {
                soundPool.play(sampleId, 1, 1, 0, 0, 1);
            }
        }
    }
}
