package novemberkilo.spigot.magicboots;

import novemberkilo.spigot.magicboots.eventhandler.EnderBootsOnEntityDamageByEntity;
import novemberkilo.spigot.magicboots.eventhandler.FarmerBootsOnPlayerInteract;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class MagicBootsSpigotPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Arrays.asList(
                new EnderBootsOnEntityDamageByEntity(),
                new FarmerBootsOnPlayerInteract()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }
}
