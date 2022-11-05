package fur.kiyoshi.xgauthremastered.events;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import fur.kiyoshi.xgauthremastered.Main;
import fur.kiyoshi.xgauthremastered.utils.Messages;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import static fur.kiyoshi.xgauthremastered.Main.authlocked;

public class JoinEvent implements Listener {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void config(Player player, UUID uuid, String... key){
        Main.getInstance().getConfig().options().parseComments(true);
        Main.getInstance().getConfig().options().copyDefaults(true);
        Main.getInstance().saveDefaultConfig();
        File data = new File(Main.getInstance().getDataFolder(), "data");
        File datafiles = new File(Main.getInstance().getDataFolder() + "/data/saved.yml");
        if (!data.exists()) {
            data.mkdir();
        }
        FileConfiguration datasets = YamlConfiguration.loadConfiguration(datafiles);
        if (!datafiles.exists()) {
            try {
                datafiles.createNewFile();
                datasets.createSection("Staff");
                datasets.createSection("Data");
                datasets.createSection("Secret");
                if(!(datasets.contains("Staff."+player.getName()))){
                    datasets.set("Staff."+player.getName(), "Player - " + player.getName() + " UUID - " + uuid);
                }
                if(!(datasets.contains("Secret."+player.getName()))){
                    datasets.set("Secret."+player.getName(), key);
                }
                if(!(datasets.contains("Data."+player.getName()))){
                    datasets.set("Data."+player.getName(), "Player - " + uuid + " UUID - " + Arrays.toString(key));
                }
                datasets.save(datafiles);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        File datafiles = new File(Main.getInstance().getDataFolder() + "/data/saved.yml");
        FileConfiguration datasets = YamlConfiguration.loadConfiguration(datafiles);
        Player player = e.getPlayer();
        final String secretkey = datasets.getString("Data."+player.getName());

        if (secretkey == null) {
            final GoogleAuthenticator gAuth = new GoogleAuthenticator();
            final GoogleAuthenticatorKey key = gAuth.createCredentials();
            if (player.hasPermission("XGAuth.Authenticate")) {

                TextComponent code = new TextComponent(Messages.codeGenerated().replace("{player}", player.getName()).replace("{code}", key.getKey()));

                code.setFont("minecraft:uniform");
                code.setColor(ChatColor.of(new Color(39,77,100)));
                code.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(Messages.messageHoverCode())));
                code.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, key.getKey()));
                player.spigot().sendMessage(code);
                player.sendMessage(Messages.codeGeneratedInstructions().replace("{player}", player.getName()).replace("{code}", key.getKey()));
//                Main.getInstance().getConfig().set("staff." + player.getUniqueId(), player.getName());
//                Main.getInstance().getConfig().set("authcodes." + player.getUniqueId(), key.getKey());
                Main.getInstance().getConfig().options().parseComments(true);
                Main.getInstance().getConfig().options().copyDefaults(true);
                Main.getInstance().saveConfig();
                config(player, player.getUniqueId(), key.getKey());
            }
        }
        else {
            authlocked.add(player.getUniqueId());
            player.sendMessage(Messages.authRequired().replace("{player}", player.getName()));
        }
    }

}
