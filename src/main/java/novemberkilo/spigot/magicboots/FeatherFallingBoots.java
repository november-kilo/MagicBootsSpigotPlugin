package novemberkilo.spigot.magicboots;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class FeatherFallingBoots {
    private ItemStack boots;
    private int level;

    public FeatherFallingBoots(EntityEquipment equipment) {
        this.boots = null;
        this.level = 0;

        if (equipment != null) {
            Optional.ofNullable(equipment.getBoots()).ifPresent(this::makeBoots);
        }
    }

    public boolean hasFeatherFalling() {
        return boots != null && level > 0;
    }

    private void makeBoots(ItemStack boots) {
        this.boots = boots;
        Optional.ofNullable(boots.getItemMeta()).ifPresent(this::configureLevel);
    }

    private void configureLevel(ItemMeta itemMeta) {
        if (itemMeta.hasEnchant(Enchantment.PROTECTION_FALL)) {
            this.level = itemMeta.getEnchantLevel(Enchantment.PROTECTION_FALL);
        }
    }
}
