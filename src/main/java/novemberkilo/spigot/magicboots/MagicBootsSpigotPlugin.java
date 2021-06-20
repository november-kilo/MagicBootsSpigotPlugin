package novemberkilo.spigot.magicboots;

import novemberkilo.spigot.magicboots.eventhandler.EntityDamageByEntityListener;
import novemberkilo.spigot.magicboots.eventhandler.PlayerInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class MagicBootsSpigotPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Arrays.asList(
                new EntityDamageByEntityListener(),
                new PlayerInteractListener()
        ).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
    }
}
