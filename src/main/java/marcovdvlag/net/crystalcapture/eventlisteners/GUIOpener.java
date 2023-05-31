package marcovdvlag.net.crystalcapture.eventlisteners;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class GUIOpener implements Listener {

    private final JavaPlugin plugin;

    public GUIOpener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Check if the player right-clicked
        if (event.getAction().toString().contains("RIGHT_CLICK")) {

            // Check if the player is holding an item
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item != null && item.getType() == Material.COMPASS) {

                // Prevent default interaction
                event.setCancelled(true);

                // Open the GUI for the player
                openCustomGUI(player);
            }
        }
    }

    private void openCustomGUI(Player player) {
        // Create the inventory for the GUI
        Inventory gui = plugin.getServer().createInventory(null, 9, "Custom GUI");

        // Add items to the GUI
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Custom Item");
        item.setItemMeta(meta);
        gui.setItem(4, item);
        player.sendMessage("kaas");

        // Open the GUI for the player
        player.openInventory(gui);
    }
}
