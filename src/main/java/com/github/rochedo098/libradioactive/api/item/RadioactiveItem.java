package com.github.rochedo098.libradioactive.api.item;

import com.github.rochedo098.libradioactive.LibRadioactive;
import com.github.rochedo098.libradioactive.api.RadiationArea;
import com.github.rochedo098.libradioactive.api.SimpleRadiationType;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RadioactiveItem extends Item {
    // Variables
    public float damage;
    public int spreadRate;

    public RadioactiveItem(Settings settings, float damage, int spreadRate) {
        super(settings);
        this.damage = damage;
        this.spreadRate = spreadRate;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        // Simples Damage for Radioactive Item
        // TODO: Make a more complete radioactive damage
        entity.damage(LibRadioactive.RADIOACTIVE_DAMAGE_SOURCE, damage);

        int ticks = 0;
        while (ticks != 52) {
            if (ticks == 50) RadiationArea.radiate(new SimpleRadiationType((damage / 2), 1), (spreadRate / 4));
            if (ticks == 51) ticks = 0;
            ticks++;
        }
    }
}
