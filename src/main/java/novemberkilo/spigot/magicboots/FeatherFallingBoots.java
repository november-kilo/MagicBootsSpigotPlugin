package novemberkilo.spigot.magicboots;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FeatherFallingBoots {
    private ItemStack boots;
    private int level;

    public FeatherFallingBoots(EntityEquipment equipment) {
        boots = null;
        level = 0;

        if (equipment != null) {
            boots = equipment.getBoots();
            if (boots != null) {
                ItemMeta bootsItemMeta = boots.getItemMeta();
                if (bootsItemMeta != null && bootsItemMeta.hasEnchant(Enchantment.PROTECTION_FALL)) {
                    level = bootsItemMeta.getEnchantLevel(Enchantment.PROTECTION_FALL);
                }
            }
        }
    }

    public boolean hasFeatherFalling() {
        return boots != null && level > 0;
    }
}
