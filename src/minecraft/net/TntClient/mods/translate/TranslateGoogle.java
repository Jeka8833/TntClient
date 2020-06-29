package net.TntClient.mods.translate;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import net.TntClient.Config;
import net.TntClient.mods.Language;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TranslateGoogle {

    private static final Minecraft mc = Minecraft.getMinecraft();
    private static String inText = "";
    private static String outText = "";

    public static void startTime() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (mc.currentScreen instanceof GuiChat && !inText.isEmpty()) {
                    try {
                        outText = translate(Config.config.googleLang.getCode(), inText);
                    } catch (Exception ignored) {
                        System.out.println("error");
                    }
                }
            }
        }, 0, 700);
    }

    public static void setText(final String text) {
        inText = text.trim();
    }

    public static String getText() {
        return outText;
    }

    private static String generateURL(String sourceLanguage, String targetLanguage, String text) throws UnsupportedEncodingException {
        return "http://translate.google.com/translate_a/single?client=webapp&hl=en&sl=" + sourceLanguage + "&tl=" + targetLanguage + "&q=" + URLEncoder.encode(text, "UTF-8") +
                "&multires=1&otf=0&pc=0&trs=1&ssel=0&tsel=0&kc=1&dt=t&ie=UTF-8&oe=UTF-8&tk=" + generateToken(text);
    }

    public static String detectLanguage(String text) throws IOException {
        String urlText = generateURL("auto", "en", text);
        URL url = new URL(urlText); //Generates URL
        String rawData = urlToText(url);//Gets text from Google
        return findLanguage(rawData);
    }

    public static String translate(String text) throws IOException {
        return translate(Locale.getDefault().getLanguage(), text);
    }

    public static String translate(String targetLanguage, String text) throws IOException {
        return translate("auto", targetLanguage, text);
    }

    public static String translate(String sourceLanguage, String targetLanguage, String text) throws IOException {
        String rawData = urlToText(new URL(generateURL(sourceLanguage, targetLanguage, text)));//Gets text from Google
        String[] raw = rawData.split("\"");//Parses the JSON
        if (raw.length < 2) {
            return null;
        }
        JsonArray arr = (JsonArray) ((JsonArray) new JsonParser().parse(rawData)).get(0);
        StringBuilder trans = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            JsonArray arr2 = (JsonArray) arr.get(i);
            trans.append(arr2.get(0).getAsString());
        }
        return trans.toString();
    }

    private static String urlToText(URL url) throws IOException {
        final URLConnection urlConn = url.openConnection();
        urlConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0) Gecko/20100101 Firefox/4.0");
        Reader r = new java.io.InputStreamReader(urlConn.getInputStream(), StandardCharsets.UTF_8);
        StringBuilder buf = new StringBuilder();
        while (true) {
            int ch = r.read();
            if (ch < 0) {
                break;
            }
            buf.append((char) ch);
        }
        return buf.toString();
    }

    private static String findLanguage(String rawData) {
        for (int i = 0; i + 5 < rawData.length(); i++) {
            boolean dashDetected = rawData.charAt(i + 4) == '-';
            if (rawData.charAt(i) == ',' && rawData.charAt(i + 1) == '"' && ((rawData.charAt(i + 4) == '"' && rawData.charAt(i + 5) == ',') || dashDetected)) {
                if (dashDetected) {
                    int lastQuote = rawData.substring(i + 2).indexOf('"');
                    if (lastQuote > 0) {
                        return rawData.substring(i + 2, i + 2 + lastQuote);
                    }
                } else {
                    String possible = rawData.substring(i + 2, i + 4);
                    if (containsLettersOnly(possible)) {//Required due to Google's inconsistent formatting.
                        return possible;
                    }
                }
            }
        }
        return null;
    }

    private static boolean containsLettersOnly(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isLetter(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int[] TKK() {
        return new int[]{0x6337E, 0x217A58DC + 0x5AF91132};
    }

    private static int shr32(int x, int bits) {
        if (x < 0) {
            long x_l = 0xffffffffL + x + 1;
            return (int) (x_l >> bits);
        }
        return x >> bits;
    }

    private static int RL(int a, String b) {//I am not entirely sure what this magic does.
        for (int c = 0; c < b.length() - 2; c += 3) {
            int d = b.charAt(c + 2);
            d = d >= 65 ? d - 87 : d - 48;
            d = b.charAt(c + 1) == '+' ? shr32(a, d) : (a << d);
            a = b.charAt(c) == '+' ? (a + (d)) : a ^ d;
        }
        return a;
    }

    private static String generateToken(String text) {
        int[] tkk = TKK();
        int b = tkk[0];
        int e = 0;
        int f = 0;
        List<Integer> d = new ArrayList<>();
        for (; f < text.length(); f++) {
            int g = text.charAt(f);
            if (0x80 > g) {
                d.add(e++, g);
            } else {
                if (0x800 > g) {
                    d.add(e++, g >> 6 | 0xC0);
                } else {
                    if (0xD800 == (g & 0xFC00) && f + 1 < text.length() && 0xDC00 == (text.charAt(f + 1) & 0xFC00)) {
                        g = 0x10000 + ((g & 0x3FF) << 10) + (text.charAt(++f) & 0x3FF);
                        d.add(e++, g >> 18 | 0xF0);
                        d.add(e++, g >> 12 & 0x3F | 0x80);
                    } else {
                        d.add(e++, g >> 12 | 0xE0);
                        d.add(e++, g >> 6 & 0x3F | 0x80);
                    }
                }
                d.add(e++, g & 63 | 128);
            }
        }

        int a_i = b;
        for (e = 0; e < d.size(); e++) {
            a_i += d.get(e);
            a_i = RL(a_i, "+-a^+6");
        }
        a_i = RL(a_i, "+-3^+b+-f");
        a_i ^= tkk[1];
        long a_l;
        if (0 > a_i) {
            a_l = 0x80000000L + (a_i & 0x7FFFFFFF);
        } else {
            a_l = a_i;
        }
        a_l %= Math.pow(10, 6);
        return String.format(Locale.US, "%d.%d", a_l, a_l ^ b);
    }

    public static final Language[] lang = {
            new Language("Azerbaijanera", "az"), new Language("Albanian", "sq"), new Language("Amharic", "am"),
            new Language("English", "en"), new Language("Arabiera", "ar"), new Language("Armeniako", "hy"), new Language("Afrikaans", "af"),
            new Language("Euskal", "eu"), new Language("Belorussian", "be"), new Language("Bengali", "bn"), new Language("Burmatarra", "my"),
            new Language("Bulgarian", "bg"), new Language("Bosniako", "bs"), new Language("Welsh", "cy"), new Language("Hungariako", "hu"),
            new Language("Vietnamese", "vi"), new Language("Oiasso", "haw"), new Language("Galiziako", "gl"), new Language("Greziako", "el"),
            new Language("Georgian", "ka"), new Language("Gujarati", "gu"), new Language("Danimarkako", "da"), new Language("Zulu", "zu"),
            new Language("Ivrit", "iw"), new Language("Igbo", "ig"), new Language("Joan zara", "yi"), new Language("Indonesian", "id"),
            new Language("Irish", "ga"), new Language("Icelandic", "is"), new Language("Espainiako", "es"), new Language("Italiako", "it"),
            new Language("Yoruba", "yo"), new Language("Kazakh", "kk"), new Language("Kannada", "kn"), new Language("Katalanez", "ca"),
            new Language("Kyrgyz", "ky"), new Language("Txinako", "zh-CN"), new Language("Korean", "ko"), new Language("Korsikera", "co"),
            new Language("Creole (gaiti)", "ht"), new Language("Kurmandjan", "ku"), new Language("Khmer", "km"), new Language("Khosa", "xh"),
            new Language("Lao", "lo"), new Language("Latin", "la"), new Language("Letoniako", "lv"), new Language("Euskara", "lt"),
            new Language("Luxembourgish", "lb"), new Language("Gaztelania", "mk"), new Language("Malagasiyskiy", "mg"), new Language("Malay", "ms"),
            new Language("Malayalam", "ml"), new Language("Maltese", "mt"), new Language("Maoriera", "mi"), new Language("Marathi", "mr"),
            new Language("Mongolski", "mn"), new Language("Alemaniako", "de"), new Language("Nepali", "ne"), new Language("Holandako", "nl"),
            new Language("Norvegiako", "no"), new Language("Punjabi", "pa"), new Language("Persian", "fa"), new Language("Poloniako", "pl"),
            new Language("Portugesa", "pt"), new Language("Saiheskiak", "ps"), new Language("Rumaniera", "ro"), new Language("Russian", "ru"),
            new Language("Samoanskii", "sm"), new Language("Cebuano", "ceb"), new Language("Serbian", "sr"), new Language("Osoa", "st"),
            new Language("Singaliskii", "si"), new Language("Sindhi", "sd"), new Language("Eslovakiako", "sk"), new Language("Esloveniako", "sl"),
            new Language("Somaliako", "so"), new Language("Swahili", "sw"), new Language("Sudango", "su"), new Language("Tajik", "tg"),
            new Language("Thai", "th"), new Language("Tamil'skij", "ta"), new Language("Telugu", "te"), new Language("Turkish", "tr"),
            new Language("Uzbek", "uz"), new Language("Ukrainian", "uk"), new Language("Urdu", "ur"), new Language("Filipinetako", "tl"),
            new Language("Finlandiako", "fi"), new Language("Frantziako", "fr"), new Language("Euskara", "fy"), new Language("Hausa", "ha"),
            new Language("Hindi", "hi"), new Language("Hmong", "hmn"), new Language("Kroaziako", "hr"), new Language("Chevanne", "ny"),
            new Language("Txekiar", "cs"), new Language("Suediako", "sv"), new Language("Shona", "sn"), new Language("Eskoziako gaeloa", "gd"),
            new Language("Esperantoa", "eo"), new Language("Estonian", "et"), new Language("Javera", "jw"), new Language("Japoniako", "ja"),
    };

}