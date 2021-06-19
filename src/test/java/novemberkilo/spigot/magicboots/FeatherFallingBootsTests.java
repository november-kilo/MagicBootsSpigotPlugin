package novemberkilo.spigot.magicboots;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeatherFallingBootsTests {
    @Mock
    private EntityEquipment equipment;

    private FeatherFallingBoots boots;

    @BeforeEach
    public void runBeforeEachTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void hasFeatherFallingShouldIndicateFalseWhenEquipmentIsNull() {
        boots = new FeatherFallingBoots(null);

        assertFalse(boots.hasFeatherFalling());
    }

    @Test
    public void hasFeatherFallingShouldIndicateFalseWhenBootsIsNull() {
        when(equipment.getBoots()).thenReturn(null);

        boots = new FeatherFallingBoots(equipment);

        assertFalse(boots.hasFeatherFalling());
    }

    @Test
    public void hasFeatherFallingShouldIndicateFalseWhenBootsMetaIsNull() {
        ItemStack equipmentItem = mock(ItemStack.class);
        when(equipment.getBoots()).thenReturn(null);
        when(equipmentItem.getItemMeta()).thenReturn(null);

        boots = new FeatherFallingBoots(equipment);

        assertFalse(boots.hasFeatherFalling());
    }

    @Test
    public void hasFeatherFallingShouldIndicateFalseWhenBootsMetaHasNoFeatherFallingEnchantment() {
        setupBootsWithoutFeatherFalling(equipment);

        boots = new FeatherFallingBoots(equipment);

        assertFalse(boots.hasFeatherFalling());
    }

    @Test
    public void hasFeatherFallingShouldIndicateTrueWhenBootsMetaHasFeatherFallingEnchantment() {
        setupBootsWithFeatherFalling(equipment);

        boots = new FeatherFallingBoots(equipment);

        assertTrue(boots.hasFeatherFalling());
    }

    public static void setupBootsWithFeatherFalling(EntityEquipment equipment) {
        ItemStack equipmentItem = mock(ItemStack.class);
        ItemMeta equipmentItemMeta = mock(ItemMeta.class);
        when(equipment.getBoots()).thenReturn(equipmentItem);
        when(equipmentItem.getItemMeta()).thenReturn(equipmentItemMeta);
        when(equipmentItemMeta.hasEnchant(Enchantment.PROTECTION_FALL)).thenReturn(true);
        when(equipmentItemMeta.getEnchantLevel(Enchantment.PROTECTION_FALL)).thenReturn(3);
    }

    public static void setupBootsWithoutFeatherFalling(EntityEquipment equipment) {
        ItemStack equipmentItem = mock(ItemStack.class);
        ItemMeta equipmentItemMeta = mock(ItemMeta.class);
        when(equipment.getBoots()).thenReturn(equipmentItem);
        when(equipmentItem.getItemMeta()).thenReturn(equipmentItemMeta);
        when(equipmentItemMeta.hasEnchant(Enchantment.PROTECTION_FALL)).thenReturn(false);
    }
}
