package net.TntClient.installer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String formatDate() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());
    }

    public static Path getWorkingDirectory() {
        final Path dir;
        switch (getPlatform()) {
            case LINUX:
            case SOLARIS:
                dir = Paths.get(System.getProperty("user.home"), ".minecraft");
                break;
            case WINDOWS:
                final String appdata = System.getenv("APPDATA");
                dir = appdata == null ? Paths.get(System.getProperty("user.home"), ".minecraft") :
                        Paths.get(System.getenv("APPDATA"), ".minecraft");
                break;
            case MACOS:
                dir = Paths.get(System.getProperty("user.home"), "Library", "Application Support", "minecraft");
                break;
            default:
                dir = Paths.get(System.getProperty("user.home"), "minecraft");
        }
        if (Files.exists(dir))
            return dir;
        return null;
    }

    public static OS getPlatform() {
        final String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return OS.WINDOWS;
        }
        if (osName.contains("mac")) {
            return OS.MACOS;
        }
        if (osName.contains("solaris")) {
            return OS.SOLARIS;
        }
        if (osName.contains("sunos")) {
            return OS.SOLARIS;
        }
        if (osName.contains("linux")) {
            return OS.LINUX;
        }
        if (osName.contains("unix")) {
            return OS.LINUX;
        }
        return OS.UNKNOWN;
    }

    public enum OS {
        LINUX,
        SOLARIS,
        WINDOWS,
        MACOS,
        UNKNOWN
    }
}
