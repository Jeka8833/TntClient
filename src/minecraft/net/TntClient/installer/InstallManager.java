package net.TntClient.installer;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

public class InstallManager {

    public static final String name = "TntClientD";

    public static void copyFile(final Path root, final boolean isExport) throws IOException, URISyntaxException {
        final Path path = root.resolve(isExport ? name : "versions/" + name);
        Files.createDirectories(path);
        Files.copy(InstallManager.class.getResourceAsStream("/net/TntClient/installer/files/TntClientD.json"),
                path.resolve(name + ".json"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get(InstallManager.class.getProtectionDomain().getCodeSource().getLocation().toURI()),
                path.resolve(name + ".jar"), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void modifyProfiles(final Path root) throws IOException {
        final Path file = root.resolve("launcher_profiles.json");
        final JSONObject json = new JSONObject(new String(Files.readAllBytes(file)));
        final JSONObject profiles = json.getJSONObject("profiles");
        if (profiles.optJSONObject(name) == null) {
            final JSONObject prof = new JSONObject();
            prof.put("name", name);
            profiles.put(name, prof);
        }
        final JSONObject prof = profiles.getJSONObject(name);
        prof.put("name", name);
        prof.put("created", Util.formatDate());
        prof.put("type", "custom");
        prof.put("lastVersionId", name);
        prof.put("lastUsed", Util.formatDate());
        try {
            prof.put("icon", "data:image/png;base64," +
                    Base64.getEncoder().encodeToString(net.TntClient.Util.toByteArray(InstallManager.class
                            .getResourceAsStream("/net/TntClient/installer/files/icon.png"))));
        } catch (Exception e) {
            prof.put("icon", "Furnace");
        }
        Files.write(file, json.toString().getBytes(StandardCharsets.UTF_8));
    }
}
