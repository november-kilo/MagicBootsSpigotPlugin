package novemberkilo.spigot.magicboots.eventhandler;

import novemberkilo.spigot.magicboots.FeatherFallingBoots;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Optional;
import java.util.function.Consumer;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL) {
            Optional.ofNullable(event.getClickedBlock()).ifPresent(new BlockConsumer(event));
        }
    }

    private record BlockConsumer(PlayerInteractEvent event) implements Consumer<Block> {
        @Override
        public void accept(Block block) {
            if (block.getType() == Material.FARMLAND) {
                FeatherFallingBoots boots = new FeatherFallingBoots(event.getPlayer().getEquipment());
                event.setCancelled(boots.hasFeatherFalling());
            }
        }
    }
}
