package novemberkilo.spigot.magicboots;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeatherFallingBootsTests {
    @Mock
    private EntityEquipment equipment;

    private FeatherFallingBoots boots;

    public static void setupBootsWithFeatherFalling(EntityEquipment equipment) {
        ItemStack equipmentItem = mock(ItemStack.class);
        ItemMeta equipmentItemMeta = mock(ItemMeta.class);
        when(equipment.getBoots()).thenReturn(equipmentItem);
        lenient().when(equipmentItem.getItemMeta()).thenReturn(equipmentItemMeta);
        lenient().when(equipmentItemMeta.hasEnchant(Enchantment.PROTECTION_FALL)).thenReturn(true);
        lenient().when(equipmentItemMeta.getEnchantLevel(Enchantment.PROTECTION_FALL)).thenReturn(3);
    }

    public static void setupBootsWithoutFeatherFalling(EntityEquipment equipment) {
        ItemStack equipmentItem = mock(ItemStack.class);
        ItemMeta equipmentItemMeta = mock(ItemMeta.class);
        lenient().when(equipment.getBoots()).thenReturn(equipmentItem);
        lenient().when(equipmentItem.getItemMeta()).thenReturn(equipmentItemMeta);
        lenient().when(equipmentItemMeta.hasEnchant(Enchantment.PROTECTION_FALL)).thenReturn(false);
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
}
