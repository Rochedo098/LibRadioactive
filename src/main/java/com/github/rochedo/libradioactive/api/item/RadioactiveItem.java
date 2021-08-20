package com.github.rochedo.libradioactive.api.item;

import com.github.rochedo.libradioactive.LibRadioactive;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RadioactiveItem extends Item {
    // Variables
    public float damage;

    public RadioactiveItem(Settings settings, Float damage) {
        super(settings);
        this.damage = damage;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        // Simples Damage for Radioactive Item
        // TODO: Make a more complete radioactive damage
        entity.damage(LibRadioactive.RADIOACTIVE_DAMAGE_SOURCE, damage);
    }
}
