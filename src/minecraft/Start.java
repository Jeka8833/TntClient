import java.io.File;
import java.lang.reflect.Field;
import java.net.Proxy;
import java.util.Arrays;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.client.main.Main;

public class Start
{

    private final static String email = "email";
    private final static String password = "password";

    public static void main(String[] args) throws AuthenticationException {
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) (new YggdrasilAuthenticationService(Proxy.NO_PROXY, "")).createUserAuthentication(Agent.MINECRAFT);
        auth.setUsername(email);
        auth.setPassword(password);
        auth.logIn();
        Main.main(concat(new String[]{"--username", auth.getSelectedProfile().getName(), "--version", "1.8.8-HackChi3", "--gameDir", "C:\\Users\\eader\\AppData\\Roaming\\.minecraft", "--assetsDir", "C:\\Users\\eader\\AppData\\Roaming\\.minecraft//assets", "--assetIndex", "1.8", "--uuid", auth.getSelectedProfile().getId().toString(), "--accessToken", auth.getAuthenticatedToken(), "--userType", "mojang", "--versionType", "release"}, args));

        //Main.main(concat(new String[]{"--username", "Jeka8833Bot", "--version", "1.8.8-HackChi3", "--gameDir", "C:\\Users\\eader\\AppData\\Roaming\\.minecraft", "--assetsDir", "C:\\Users\\eader\\AppData\\Roaming\\.minecraft//assets", "--assetIndex", "1.8", "--accessToken", "0", "--versionType", "release"}, args));

    }


    private static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
