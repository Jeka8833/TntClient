package net.TntClient.mods.SpellCecker;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.TntClient.Util;
import net.TntClient.mods.Language;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SpellChecker {

    private static final Minecraft mc = Minecraft.getMinecraft();
    private static List<Word> words = new ArrayList<>();
    private static String text = "";
    private static String lang = "auto";

    public static void startTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (mc.currentScreen instanceof GuiChat) {
                    if (!text.startsWith("/")) {
                        try {
                            if (text.startsWith("%")) {
                                List<Word> wordsMv = parseJson(Util.readSite("https://languagetool.org/api/v2/check?language=" + lang + "&text=" + URLEncoder.encode(text.substring(1), "UTF-8")));
                                words.clear();
                                for (Word w : wordsMv) {
                                    words.add(new Word(w.getIndex() + 1, w.getLength()));
                                }
                            } else
                                words = parseJson(Util.readSite("https://languagetool.org/api/v2/check?language=" + lang + "&text=" + URLEncoder.encode(text, "UTF-8")));
                        } catch (IOException ignored) {
                        }
                    } else {
                        words = new ArrayList<>();
                    }
                }
            }
        }, 0, 1525);
    }

    public static void setLang(final String lang) {
        SpellChecker.lang = lang;
    }

    public static String getLang() {
        return lang;
    }

    public static void setText(final String text) {
        SpellChecker.text = text;
    }

    public static List<Word> getWords() {
        return words;
    }


    private static List<Word> parseJson(final String json) {
        final List<Word> words = new ArrayList<>();
        final JsonArray arr = (JsonArray) ((JsonObject) new JsonParser().parse(json)).get("matches");
        final int len = arr.size();
        for (int i = 0; i < len; i++) {
            final JsonObject errors = (JsonObject) arr.get(i);
            final Word word = new Word(errors.get("offset").getAsInt(), errors.get("length").getAsInt());
            final JsonArray val = (JsonArray) errors.get("replacements");
            final int len2 = val.size();
            for (int a = 0; a < len2; a++) {
                word.addWord(((JsonObject) val.get(a)).get("value").getAsString());
            }
            words.add(word);
        }
        return words;
    }

    public static List<Language> getLanguage() throws IOException {
        final List<Language> languages = new ArrayList<>();
        languages.add(new Language("Auto", "auto"));
        final JsonArray arr = (JsonArray) new JsonParser().parse(Util.readSite("https://languagetool.org/api/v2/languages"));
        final int len = arr.size();
        for (int i = 0; i < len; i++) {
            final JsonObject json = (JsonObject) arr.get(i);
            languages.add(new Language(json.get("name").getAsString(), json.get("longCode").getAsString()));
        }
        return languages;
    }
}
