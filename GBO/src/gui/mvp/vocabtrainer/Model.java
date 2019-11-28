package gui.mvp.vocabtrainer;

import java.util.LinkedHashMap;

public class Model
{

    private LinkedHashMap<String, String> vocabulary;

    private int counter;

    public Model(LinkedHashMap<String, String> vocabs)
    {
        this.vocabulary = vocabs;
    }

    public Model()
    {
        vocabulary = new LinkedHashMap<String, String>();
        vocabulary.put("test", "test");
        vocabulary.put("test2", "test");
        vocabulary.put("test3", "test");
    }

    public String nextVocab()
    {
        counter++;
        counter %= this.vocabulary.size();
        return getVocab();
    }

    public String getVocab()
    {
        return (String) vocabulary.keySet().toArray()[counter];
    }

    public boolean checkTranslation(String input)
    {
        return input.contentEquals((String) vocabulary.values().toArray()[counter]);
    }
}
