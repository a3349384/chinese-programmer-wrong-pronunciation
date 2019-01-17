package cn.zmy.cpwp.model;

/**
 * Created by zmy on 2019/1/17.
 * 单词模型
 */

public class Words
{
    private String name;
    private String rightPronunciation;
    private String wrongPronunciation;
    private String pronunciationUrl;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRightPronunciation()
    {
        return rightPronunciation;
    }

    public void setRightPronunciation(String rightPronunciation)
    {
        this.rightPronunciation = rightPronunciation;
    }

    public String getWrongPronunciation()
    {
        return wrongPronunciation;
    }

    public void setWrongPronunciation(String wrongPronunciation)
    {
        this.wrongPronunciation = wrongPronunciation;
    }

    public String getPronunciationUrl()
    {
        return pronunciationUrl;
    }

    public void setPronunciationUrl(String pronunciationUrl)
    {
        this.pronunciationUrl = pronunciationUrl;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name);
        builder.append(':');
        builder.append("正确读音:");
        builder.append(this.rightPronunciation);
        builder.append("错误读音:");
        builder.append(this.wrongPronunciation);
        builder.append("读音地址:");
        builder.append(this.pronunciationUrl);
        return builder.toString();
    }
}
