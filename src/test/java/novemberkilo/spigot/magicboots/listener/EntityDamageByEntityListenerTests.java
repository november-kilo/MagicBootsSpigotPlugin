package novemberkilo.spigot.magicboots.listener;

import novemberkilo.spigot.magicboots.boots.MagicBootsTests;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntityDamageByEntityListenerTests {
    @Mock
    private EntityDamageByEntityEvent event;

    @Mock
    private EnderPearl enderPearl;

    @Mock
    private EntityEquipment equipment;

    private EntityDamageByEntityListener eventHandler;

    @BeforeEach
    public void runBeforeEachTest() {
        eventHandler = new EntityDamageByEntityListener();
    }

    @Test
    public void onEntityDamageByEntityShouldNotCancelWhenDamagerIsNotEnderPearl() {
        Entity damager = mock(Entity.class);
        when(event.getDamager()).thenReturn(damager);

        eventHandler.onEntityDamageByEntity(event);

        verify(event, never()).setCancelled(true);
    }

    @Test
    public void onEntityDamageByEntityShouldNotCancelWhenDamageeIsNotPlayer() {
        Entity entity = mock(Entity.class);
        when(event.getDamager()).thenReturn(enderPearl);
        when(event.getEntity()).thenReturn(entity);

        eventHandler.onEntityDamageByEntity(event);

        verify(event, never()).setCancelled(true);
    }

    @Test
    public void onEntityDamageByEntityShouldNotCancelWhenDamageeIsNotWearingFeatherFallingBoots() {
        Player entity = mock(Player.class);
        when(event.getDamager()).thenReturn(enderPearl);
        when(event.getEntity()).thenReturn(entity);
        MagicBootsTests.setupBootsWithoutFeatherFalling(equipment);

        eventHandler.onEntityDamageByEntity(event);

        verify(event, never()).setCancelled(true);
    }

    @Test
    public void onEntityDamageByEntityShouldCancelWhenDamageeIsWearingFeatherFallingBoots() {
        Player entity = mock(Player.class);
        when(event.getDamager()).thenReturn(enderPearl);
        when(event.getEntity()).thenReturn(entity);
        when(entity.getEquipment()).thenReturn(equipment);
        MagicBootsTests.setupBootsWithFeatherFalling(equipment);

        eventHandler.onEntityDamageByEntity(event);

        verify(event).setCancelled(true);
    }
}
