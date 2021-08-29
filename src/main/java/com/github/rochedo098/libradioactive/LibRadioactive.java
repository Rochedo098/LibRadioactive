package com.github.rochedo098.libradioactive;

import com.github.rochedo098.libradioactive.api.RadiationType;
import com.github.rochedo098.libradioactive.api.SimpleRadiationType;
import com.github.rochedo098.libradioactive.impl.RadioactiveMeter;
import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;

public class LibRadioactive implements ModInitializer {
    // DamageSource
    public static DamageSource RADIOACTIVE_DAMAGE_SOURCE = new RadioactiveDamageSource("radioactive");

    // Items
    public static Item RADIOACTIVE_METER = new RadioactiveMeter(new Item.Settings().group(ItemGroup.MISC));

    public static SimpleRegistry<RadiationType> registry = FabricRegistryBuilder.createSimple(RadiationType.class, new Identifier("libradioactive", "radiation"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, "radioactive_meter", RADIOACTIVE_METER);
        Registry.register(registry, new Identifier("libradioactive", "simpleradiation"), new SimpleRadiationType(1f, 3));
    }
}
