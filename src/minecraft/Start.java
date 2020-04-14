import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.TntClient.TntClient;
import net.minecraft.client.main.Main;

import java.net.Proxy;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Start {

    /**
     * To start the license, the -email and -pass option are required. Example: -license true -email yourEmail -pass yourPass
     * The -name option is required to start the pirate. Example: -license false -name User228
     */
    public static void main(String[] args) throws AuthenticationException {
        TntClient.isDebug = true;
        final Path path = Paths.get(System.getenv("AppData"), ".minecraft");
        if (getValue("-license", args).equalsIgnoreCase("true")) {
            final YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) (new YggdrasilAuthenticationService(Proxy.NO_PROXY, "")).createUserAuthentication(Agent.MINECRAFT);
            auth.setUsername(getValue("-email", args));
            auth.setPassword(getValue("-pass", args));
            auth.logIn();
            Main.main(new String[]{"--username", auth.getSelectedProfile().getName(), "--version", "TntClient", "--gameDir", path.toString(), "--assetsDir", path.resolve("assets").toString(), "--assetIndex", "1.8", "--uuid", auth.getSelectedProfile().getId().toString(), "--accessToken", auth.getAuthenticatedToken(), "--userType", "mojang"});
        } else {
            Main.main(new String[]{"--username", getValue("-name", args), "--version", "TntClient", "--gameDir", path.toString(), "--assetsDir", path.resolve("assets").toString(), "--assetIndex", "1.8", "--uuid", "--accessToken", "0", "--userType", "mojang"});
        }
    }

    private static String getValue(final String key, final String[] array) {
        for (int i = 0; i < array.length; i++)
            if (array[i].equalsIgnoreCase(key) && i + 1 < array.length)
                return array[i + 1];
        throw new NullPointerException("Key(" + key + ") not found");
    }
}