package novemberkilo.spigot.magicboots.eventhandler;

import novemberkilo.spigot.magicboots.FeatherFallingBootsTests;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerInteractListenerTests {
    @Mock
    private PlayerInteractEvent event;

    @Mock
    private Block block;

    @Mock
    private Player player;

    @Mock
    private EntityEquipment equipment;

    private PlayerInteractListener eventHandler;

    @BeforeEach
    public void runBeforeEachTest() {
        eventHandler = new PlayerInteractListener();
    }

    @Test
    public void onPlayerInteractShouldNotCancelWhenActionIsNotPhysical() {
        when(event.getAction()).thenReturn(Action.LEFT_CLICK_BLOCK);

        eventHandler.onPlayerInteract(event);

        verify(event, never()).setCancelled(true);
    }

    @Test
    public void onPlayerInteractShouldNotCancelWhenBlockIsNull() {
        when(event.getAction()).thenReturn(Action.PHYSICAL);
        when(event.getClickedBlock()).thenReturn(null);

        eventHandler.onPlayerInteract(event);

        verify(event, never()).setCancelled(true);
    }

    @Test
    public void onPlayerInteractShouldNotCancelWhenBlockIsNotFarmland() {
        when(event.getAction()).thenReturn(Action.PHYSICAL);
        when(block.getType()).thenReturn(Material.DIRT);
        when(event.getClickedBlock()).thenReturn(block);

        eventHandler.onPlayerInteract(event);

        verify(event, never()).setCancelled(true);
    }

    @Test
    public void onPlayerInteractShouldNotCancelWhenBootsNotHasFeatherFalling() {
        when(event.getAction()).thenReturn(Action.PHYSICAL);
        when(block.getType()).thenReturn(Material.FARMLAND);
        when(event.getClickedBlock()).thenReturn(block);
        FeatherFallingBootsTests.setupBootsWithoutFeatherFalling(equipment);
        when(player.getEquipment()).thenReturn(equipment);
        when(event.getPlayer()).thenReturn(player);

        eventHandler.onPlayerInteract(event);

        verify(event, never()).setCancelled(true);
    }

    @Test
    public void onPlayerInteractShouldNotCancelWhenBootsHasFeatherFalling() {
        when(event.getAction()).thenReturn(Action.PHYSICAL);
        when(block.getType()).thenReturn(Material.FARMLAND);
        when(event.getClickedBlock()).thenReturn(block);
        FeatherFallingBootsTests.setupBootsWithFeatherFalling(equipment);
        when(player.getEquipment()).thenReturn(equipment);
        when(event.getPlayer()).thenReturn(player);

        eventHandler.onPlayerInteract(event);

        verify(event).setCancelled(true);
    }
}
