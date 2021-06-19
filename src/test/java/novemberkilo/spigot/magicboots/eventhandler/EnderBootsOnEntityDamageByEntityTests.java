package novemberkilo.spigot.magicboots.eventhandler;

import novemberkilo.spigot.magicboots.FeatherFallingBoots;
import novemberkilo.spigot.magicboots.FeatherFallingBootsTests;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EnderBootsOnEntityDamageByEntityTests {
    @Mock
    private EntityDamageByEntityEvent event;

    @Mock
    private EnderPearl enderPearl;

    @Mock
    private EntityEquipment equipment;

    private EnderBootsOnEntityDamageByEntity eventHandler;

    @BeforeEach
    public void runBeforeEachTest() {
        MockitoAnnotations.initMocks(this);
        eventHandler = new EnderBootsOnEntityDamageByEntity();
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
        FeatherFallingBootsTests.setupBootsWithoutFeatherFalling(equipment);

        eventHandler.onEntityDamageByEntity(event);

        verify(event, never()).setCancelled(true);
    }

    @Test
    public void onEntityDamageByEntityShouldCancelWhenDamageeIsWearingFeatherFallingBoots() {
        Player entity = mock(Player.class);
        when(event.getDamager()).thenReturn(enderPearl);
        when(event.getEntity()).thenReturn(entity);
        when(entity.getEquipment()).thenReturn(equipment);
        FeatherFallingBootsTests.setupBootsWithFeatherFalling(equipment);

        eventHandler.onEntityDamageByEntity(event);

        verify(event).setCancelled(true);
    }
}
