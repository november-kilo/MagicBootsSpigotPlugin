package novemberkilo.spigot.magicboots;

import novemberkilo.spigot.magicboots.listener.EntityDamageByEntityListener;
import novemberkilo.spigot.magicboots.listener.PlayerInteractListener;
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
