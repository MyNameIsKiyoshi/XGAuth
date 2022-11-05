package fur.kiyoshi.xgauthremastered.events;

import fur.kiyoshi.xgauthremastered.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;

import static fur.kiyoshi.xgauthremastered.Main.authlocked;

public class QuitEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        File datafiles = new File(Main.getInstance().getDataFolder() + "/data/saved.yml");
        FileConfiguration datasets = YamlConfiguration.loadConfiguration(datafiles);
        final String secretkey = datasets.getString("Data."+player.getName());
        if (secretkey != null) {
            authlocked.remove(player.getUniqueId());
            player.getInventory().clear();
        }
    }
}
