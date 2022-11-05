package fur.kiyoshi.xgauthremastered.utils;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import fr.xephi.authme.api.v3.AuthMeApi;
import fur.kiyoshi.xgauthremastered.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

import static fur.kiyoshi.xgauthremastered.Main.authlocked;

public class Authentication {


    public static File datafiles = new File(Main.getInstance().getDataFolder() + "/data/saved.yml");
    public static FileConfiguration datasets = YamlConfiguration.loadConfiguration(datafiles);

    public static boolean playerInputCode(Player player, int code) {
        final String secretkey = datasets.getString("Secret."+player.getName());
        final GoogleAuthenticator gauth = new GoogleAuthenticator();
        if(secretkey != null) {
            final boolean codeisvalid = gauth.authorize(secretkey, code);
            if (codeisvalid) {
                if(Main.getInstance().getConfig().getBoolean("Addons.AuthMeSupport")){
                    if(AuthMeApi.getInstance().isAuthenticated(player)){
                        authlocked.remove(player.getUniqueId());
                        return true;
                    }
                } else {
                    authlocked.remove(player.getUniqueId());
                    return true;
                }
            }
        }
        return false;
    }

}
