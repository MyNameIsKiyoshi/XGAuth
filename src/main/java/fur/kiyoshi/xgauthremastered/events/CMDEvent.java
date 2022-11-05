package fur.kiyoshi.xgauthremastered.events;

import fur.kiyoshi.xgauthremastered.utils.Messages;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import static fur.kiyoshi.xgauthremastered.Main.authlocked;

public class CMDEvent implements Listener {

    @EventHandler
    public void BlockCMD(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        if (authlocked.contains(player.getUniqueId())) {
            e.setCancelled(true);
            player.sendMessage(Messages.authBefore().replace("{player}", player.getName()));
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 50.0f, 50.0f);
        }
    }

}
