package com.github.rochedo098.libradioactive;

import com.github.rochedo098.libradioactive.api.RadiationType;
import com.github.rochedo098.libradioactive.api.SimpleRadiationType;
import com.github.rochedo098.libradioactive.impl.RadioactiveMeter;
import com.mojang.serialization.Lifecycle;
import net.fabricmc.api.ModInitializer;
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

    public static final SimpleRegistry<RadiationType> registry = new SimpleRegistry<RadiationType>(RegistryKey.ofRegistry(new Identifier("libradioactive:radiation")), Lifecycle.experimental());

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, "radioactive_meter", RADIOACTIVE_METER);

        Registry.register((MutableRegistry<MutableRegistry<?>>)Registry.REGISTRIES,new Identifier("libradioactive","radiation"), registry);
        Registry.register(registry, new Identifier("libradioactive", "simpleradiation"), new SimpleRadiationType(1f));
    }
}
