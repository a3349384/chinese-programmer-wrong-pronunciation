package cn.zmy.cpwp.data;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.zmy.cpwp.model.Words;

/**
 * Created by zmy on 2019/1/17.
 * 负责从Markdown单词流中解析出单词
 */

public class MarkdownWordsParser
{
    /**
     * 从流中解析单词
     * @param inputStream markdown单词流。本方法会负责流的释放。
     * */
    public static List<Words> parse(InputStream inputStream)
    {
        BufferedReader reader = null;
        try
        {
            List<Words> wordsList = null;
            if (inputStream == null)
            {
                return wordsList;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while (true)
            {
                String line = reader.readLine();
                if (line == null)
                {
                    break;
                }
                if (TextUtils.isEmpty(line))
                {
                    continue;
                }
                try
                {
                    Words words = parseLine(line);
                    if (words != null)
                    {
                        if (wordsList == null)
                        {
                            wordsList = new ArrayList<>();
                        }
                        wordsList.add(words);
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            return wordsList;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();
                }
            }
            catch (IOException ignored){}
        }
    }

    private static Words parseLine(String line)
    {
        //9989为正确的char值
        //10060为错误的char值
        if (line.startsWith("|") && line.endsWith("|") && line.indexOf(9989) > 0)
        {
            String[] lineSplits = line.split("\\|");
            Words words = new Words();
            String nameWithUrl = lineSplits[1].trim();
            int index1 = nameWithUrl.indexOf('[');
            int index2 = nameWithUrl.indexOf(']');
            if (index1 >= 0 && index2 >= 0)
            {
                words.setName(nameWithUrl.substring(0, index1).trim());
                int index3 = nameWithUrl.lastIndexOf(')');
                words.setPronunciationUrl(nameWithUrl.substring(index2 + 2, index3));
            }
            else
            {
                words.setName(nameWithUrl);
            }
            words.setRightPronunciation(lineSplits[2].replace((char) 9989, ' ').trim());
            words.setWrongPronunciation(lineSplits[3].replace((char) 10060, ' ').trim());
            Log.d("MarkdownWordsParser", words.toString());
            return words;
        }
        return null;
    }
}
