package novemberkilo.spigot.magicboots.boots;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

public class MagicBoots {
    private ItemStack boots;
    private int featherFallingLevel;

    public MagicBoots(EntityEquipment equipment) {
        this.boots = null;
        this.featherFallingLevel = 0;

        if (equipment != null) {
            Optional.ofNullable(equipment.getBoots()).ifPresent(this::makeBoots);
        }
    }

    public boolean hasFeatherFalling() {
        return boots != null && featherFallingLevel > 0;
    }

    private void makeBoots(ItemStack boots) {
        this.boots = boots;
        Optional.ofNullable(boots.getItemMeta()).ifPresent(this::configureLevels);
    }

    private void configureLevels(ItemMeta itemMeta) {
        if (itemMeta.hasEnchant(Enchantment.PROTECTION_FALL)) {
            this.featherFallingLevel = itemMeta.getEnchantLevel(Enchantment.PROTECTION_FALL);
        }
    }
}
