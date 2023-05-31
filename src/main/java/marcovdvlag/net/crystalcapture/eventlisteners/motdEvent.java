package marcovdvlag.net.crystalcapture.eventlisteners;


import marcovdvlag.net.crystalcapture.CrystalCapture;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class motdEvent implements Listener {

    private String motd = "This is the server!";
    private ItemStack motdItem;

    public motdEvent() {
        motdItem = createGUIItem(Material.PAPER, "MOTD", motd);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setPlayerListHeaderFooter(motd, "");
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        // Check if the clicked inventory is the MOTD GUI
        if (inventory.getType().equals("MOTD GUI")) {
            event.setCancelled(true);

            // Check if the clicked item is the MOTD item
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null && clickedItem.isSimilar(motdItem)) {
                // Open a text input GUI for the player to enter the MOTD
                player.closeInventory();
                player.sendMessage(ChatColor.YELLOW + "Please enter the new MOTD in chat:");
                player.setMetadata("MOTDInput", new FixedMetadataValue(JavaPlugin.getPlugin(CrystalCapture.class), true));
            }
        }
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();

        // Check if the player was previously prompted for MOTD input
        if (player.hasMetadata("MOTDInput")) {
            event.setCancelled(true);
            player.removeMetadata("MOTDInput", JavaPlugin.getPlugin(CrystalCapture.class));

            // Set the new MOTD
            String newMotd = event.getMessage();
            setMOTD(newMotd);

            // Update the MOTD item in the GUI
            ItemMeta itemMeta = motdItem.getItemMeta();
            itemMeta.setLore(null);
            itemMeta.setDisplayName(ChatColor.GREEN + "MOTD");
            motdItem.setItemMeta(itemMeta);

            player.sendMessage(ChatColor.GREEN + "MOTD updated successfully!");
            player.openInventory(createMOTDGUI());
        }
    }

    private void setMOTD(String newMotd) {
        motd = newMotd;
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setPlayerListHeaderFooter(ChatColor.translateAlternateColorCodes('&', motd), "");
        }
    }

    private Inventory createMOTDGUI() {
        Inventory gui = Bukkit.createInventory(null, 9, "MOTD GUI");
        gui.setItem(4, motdItem);
        return gui;
    }

    private ItemStack createGUIItem(Material material, String name, String... lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + name);
        itemMeta.setLore(Arrays.asList(ChatColor.GRAY + lore[0])); // Convert the lore string to a list
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
