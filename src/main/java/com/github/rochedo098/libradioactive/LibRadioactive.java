package com.github.rochedo098.libradioactive;

import com.github.rochedo098.libradioactive.api.RadiationType;
import com.github.rochedo098.libradioactive.api.SimpleRadiationType;
import com.github.rochedo098.libradioactive.api.pollution.PollutionType;
import com.github.rochedo098.libradioactive.api.pollution.SimplePollutionType;
import com.github.rochedo098.libradioactive.impl.GeigerCounter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;

public class LibRadioactive implements ModInitializer {
    // DamageSource
    public static DamageSource RADIOACTIVE_DAMAGE_SOURCE = new RadioactiveDamageSource("radioactive");
    public static DamageSource SUFFOCATED_DAMAGE_SOURCE = new SuffocatedDamageSource("suffocated");


    // Items
    public static Item RADIOACTIVE_METER = new GeigerCounter(new Item.Settings().group(ItemGroup.MISC));

    public static SimpleRegistry<RadiationType> radiationRegistry = FabricRegistryBuilder.createSimple(RadiationType.class, new Identifier("libradioactive", "radiation"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();

    public static SimpleRegistry<PollutionType> pollutionRegistry = FabricRegistryBuilder.createSimple(PollutionType.class, new Identifier("libradioactive", "pollution"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, "radioactive_meter", RADIOACTIVE_METER);
        Registry.register(radiationRegistry, new Identifier("libradioactive", "simpleradiation"), new SimpleRadiationType(1f, 3));
        Registry.register(pollutionRegistry, new Identifier("libradioactive", "simplepollution"), new SimplePollutionType(3));
    }
}
