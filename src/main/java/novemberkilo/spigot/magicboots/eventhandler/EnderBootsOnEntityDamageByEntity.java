package novemberkilo.spigot.magicboots.eventhandler;

import novemberkilo.spigot.magicboots.FeatherFallingBoots;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EnderBootsOnEntityDamageByEntity implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof EnderPearl && event.getEntity() instanceof Player damagee) {
            FeatherFallingBoots boots = new FeatherFallingBoots(damagee.getEquipment());
            event.setCancelled(boots.hasFeatherFalling());
        }
    }
}
