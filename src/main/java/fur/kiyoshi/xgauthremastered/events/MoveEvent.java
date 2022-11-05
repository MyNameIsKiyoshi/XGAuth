package fur.kiyoshi.xgauthremastered.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static fur.kiyoshi.xgauthremastered.Main.authlocked;

public class MoveEvent implements Listener {

    @EventHandler
    public void move(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if(authlocked.contains(player.getUniqueId())) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }
}
