import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.TntClient.Config;
import net.TntClient.TntClient;
import net.TntClient.mods.hypixel.HypixelPlayers;
import net.TntClient.util.WebUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.Main;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Proxy;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Start {

    private static final JsonParser parser = new JsonParser();

    /**
     * To start the license, the -email and -pass option are required. Example: -license true -email yourEmail -pass yourPass
     * The -name option is required to start the pirate. Example: -license false -name User228
     */
    public static void main(String[] args) throws AuthenticationException {
        TntClient.isDebug = true;
        final Path path = Paths.get(System.getenv("AppData"), ".minecraft");
        if (true) {
            try {
                final JsonObject out = parser.parse(WebUtil.postJson("https://auth.mcleaks.net/v1/redeem", "{" + "  \"token\": \"4phqEyDxyIxKcaca\"" + "}")).getAsJsonObject().getAsJsonObject("result");
                System.out.println(out.get("mcname").getAsString());
                String uuid = parser.parse(readSite("https://api.mojang.com/users/profiles/minecraft/" + out.get("mcname").getAsString())).getAsJsonObject().get("id").getAsString();
                Main.main(new String[]{"--username", out.get("mcname").getAsString(), "--version", "TntClient", "--gameDir", path.toString(), "--assetsDir", path.resolve("assets").toString(), "--assetIndex", "1.8", "--uuid", uuid, "--accessToken", out.get("session").getAsString(), "--userType", "mojang"});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (getValue("-license", args).equalsIgnoreCase("true")) {
            final YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) (new YggdrasilAuthenticationService(Proxy.NO_PROXY, "")).createUserAuthentication(Agent.MINECRAFT);
            auth.setUsername(getValue("-email", args));
            auth.setPassword(getValue("-pass", args));
            auth.logIn();
            Main.main(new String[]{"--username", auth.getSelectedProfile().getName(), "--version", "TntClient", "--gameDir", path.toString(), "--assetsDir", path.resolve("assets").toString(), "--assetIndex", "1.8", "--uuid", auth.getSelectedProfile().getId().toString(), "--accessToken", auth.getAuthenticatedToken(), "--userType", "mojang"});
        } else {
            Main.main(new String[]{"--username", getValue("-name", args), "--version", "TntClient", "--gameDir", path.toString(), "--assetsDir", path.resolve("assets").toString(), "--assetIndex", "1.8", "--uuid", "--accessToken", "0", "--userType", "mojang"});
        }
    }

    private static boolean isValue(final String key, final String[] array) {
        for (int i = 0; i < array.length; i++)
            if (array[i].equalsIgnoreCase(key) && i + 1 < array.length)
                return true;
        return false;
    }

    private static String getValue(final String key, final String[] array) {
        for (int i = 0; i < array.length; i++)
            if (array[i].equalsIgnoreCase(key) && i + 1 < array.length)
                return array[i + 1];
        throw new NullPointerException("Key(" + key + ") not found");
    }

    private static String readSite(final String url) throws IOException {
        URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        StringBuilder sb = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            sb.append(inputLine);
        in.close();
        return sb.toString();
    }
}