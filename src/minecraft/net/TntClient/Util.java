package net.TntClient;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Util {

    public static int getRainbow() {
        return Color.HSBtoRGB((float) ((System.currentTimeMillis() / 4000D) % 1), 1, 1);
    }

    public static String readSite(final String url) throws IOException {
        try (final InputStream inputStream = new URL(url).openStream()) {
            return new String(toByteArray(inputStream), StandardCharsets.UTF_8);
        }
    }

    public static byte[] toByteArray(final InputStream inputStream) throws IOException {
        try (final ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            final byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toByteArray();
        }
    }
}
