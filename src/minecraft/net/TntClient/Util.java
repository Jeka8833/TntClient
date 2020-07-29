package net.TntClient;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Util {

    public static int getRainbow() {
        return Color.HSBtoRGB((float) ((System.currentTimeMillis() / 4000D) % 1), 1, 1);
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
