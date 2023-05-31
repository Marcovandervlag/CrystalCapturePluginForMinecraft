package marcovdvlag.net.crystalcapture.eventlisteners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Setspawnpoint implements Listener {
    @EventHandler
    public void setSpawnOn(PlayerMoveEvent event){

        // Get the player that just moved
        Player p = event.getPlayer();

        // Get the Block right below the player
        Block a = p.getLocation().getBlock().getRelative(BlockFace.DOWN);

        // Create an explosion of power 5 on the player's location
        if (a.getType() == Material.GOLD_BLOCK){
            Player player = event.getPlayer();
            Location location = player.getLocation();

            // Set the spawnpoint to the player's current position
            player.setBedSpawnLocation(location, true);

            System.out.println("Spawnpoint has been set for" + player + "at" + location);

        }

    }


}


