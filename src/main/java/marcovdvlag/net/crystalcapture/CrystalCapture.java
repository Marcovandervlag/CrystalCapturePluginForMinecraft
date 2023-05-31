package marcovdvlag.net.crystalcapture;

import marcovdvlag.net.crystalcapture.eventlisteners.GUIOpener;
import marcovdvlag.net.crystalcapture.eventlisteners.PlayerMovementListener;
import marcovdvlag.net.crystalcapture.eventlisteners.Setspawnpoint;
import marcovdvlag.net.crystalcapture.eventlisteners.motdEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public final class CrystalCapture extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger logger = getLogger();
        logger.info("Plug-in is made by Marco van der Vlag");
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Setspawnpoint(), this);
        getServer().getPluginManager().registerEvents(new PlayerMovementListener(), this);
        getServer().getPluginManager().registerEvents(new motdEvent(), this);
        getServer().getPluginManager().registerEvents(new GUIOpener(this), this);



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
