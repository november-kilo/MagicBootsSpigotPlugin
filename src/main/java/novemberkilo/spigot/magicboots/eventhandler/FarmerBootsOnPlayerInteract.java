package novemberkilo.spigot.magicboots.eventhandler;

import novemberkilo.spigot.magicboots.FeatherFallingBoots;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FarmerBootsOnPlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL) {
            Block block = event.getClickedBlock();

            if (block != null && block.getType() == Material.FARMLAND) {
                FeatherFallingBoots boots = new FeatherFallingBoots(event.getPlayer().getEquipment());
                event.setCancelled(boots.hasFeatherFalling());
            }
        }
    }
}
