package fur.kiyoshi.xgauthremastered.events;

import fur.kiyoshi.xgauthremastered.utils.Authentication;
import fur.kiyoshi.xgauthremastered.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static fur.kiyoshi.xgauthremastered.Main.authlocked;

public class CheckEvent implements Listener {

    @EventHandler
    public void Check(final AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        final String message = event.getMessage();;
        if (authlocked.contains(player.getUniqueId())) {
            try {
                final int code = Integer.parseInt(message);
                if (Authentication.playerInputCode(player, code)) {
                    authlocked.remove(player.getUniqueId());
                    player.sendMessage(Messages.authSuccess().replace("{player}", player.getName()));
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 50.0f, 50.0f);
                    Bukkit.broadcast(Messages.authSuccessStaffNotification().replace("{player}", player.getName()), "XGAuth.Notify");
                }
                else {
                    player.sendMessage(Messages.wrongCode().replace("{player}", player.getName()));
                    player.playSound(player.getLocation(), Sound.BLOCK_CHEST_CLOSE, 50.0f, 50.0f);
                    Bukkit.broadcast(Messages.wrongCodeStaffNotification().replace("{player}", player.getName()), "XGAuth.Notify");
                }
            }
            catch (Exception e) {
                player.sendMessage(Messages.wrongCode().replace("{player}", player.getName()));
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_CLOSE, 50.0f, 50.0f);
                Bukkit.broadcast(Messages.wrongCodeStaffNotification().replace("{player}", player.getName()), "XGAuth.Notify");
            }
            event.setCancelled(true);
        }
    }

}
