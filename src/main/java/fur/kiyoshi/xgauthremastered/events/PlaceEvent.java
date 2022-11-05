package fur.kiyoshi.xgauthremastered.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static fur.kiyoshi.xgauthremastered.Main.authlocked;

public class PlaceEvent implements Listener {

    @EventHandler
    public void BlockPlace(final BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (authlocked.contains(p.getUniqueId())) {
            e.setCancelled(true);
        }
    }
}
