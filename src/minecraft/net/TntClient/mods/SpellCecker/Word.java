package net.TntClient.mods.SpellCecker;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private final int index;
    private final int length;
    private final List<String> words = new ArrayList<>();

    public Word(final int index, final int length) {
        this.index = index;
        this.length = length;
    }

    public void addWord(final String word) {
        words.add(word);
    }

    public List<String> getWords() {
        return words;
    }

    public int getIndex() {
        return index;
    }

    public int getLength(){
        return length;
    }
}
