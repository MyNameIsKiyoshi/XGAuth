package fur.kiyoshi.xgauthremastered.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import static fur.kiyoshi.xgauthremastered.Main.authlocked;

public class BreakEvent implements Listener {

    @EventHandler
    public void blockbreak(final BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (authlocked.contains(player.getUniqueId())) {
            e.setCancelled(true);
        }
    }

}
