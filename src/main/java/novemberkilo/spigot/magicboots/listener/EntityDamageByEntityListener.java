package novemberkilo.spigot.magicboots.listener;

import novemberkilo.spigot.magicboots.boots.MagicBoots;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof EnderPearl && event.getEntity() instanceof Player damagee) {
            MagicBoots boots = new MagicBoots(damagee.getEquipment());
            event.setCancelled(boots.hasFeatherFalling());
        }
    }
}
