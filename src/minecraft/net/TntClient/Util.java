package net.TntClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Util {

    public static int getRainbow() {
        return hsvToRgb((float) ((System.currentTimeMillis() / 4000D) % 1), 1, 1);
    }

    public static int hsvToRgb(float hue, float saturation, float value) {
        final int h = (int) (hue * 6);
        final float f = hue * 6 - h;
        final float p = value * (1 - saturation);
        final float q = value * (1 - f * saturation);
        final float t = value * (1 - (1 - f) * saturation);
        switch (h) {
            case 0:
                return rgbToInt(value, t, p);
            case 1:
                return rgbToInt(q, value, p);
            case 2:
                return rgbToInt(p, value, t);
            case 3:
                return rgbToInt(p, q, value);
            case 4:
                return rgbToInt(t, p, value);
            case 5:
                return rgbToInt(value, p, q);
        }
        return 0;
    }

    public static int rgbToInt(float r, float g, float b) {
        return 0xFF000000 | ((int) (r * 255) & 0xFF) << 16 | ((int) (g * 255) & 0xFF) << 8 | ((int) (b * 255) & 0xFF);
    }

    public static String readSite(final String url) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        final StringBuilder buf = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buf.append(line).append('\n');
        }
        return buf.toString();
    }
}
