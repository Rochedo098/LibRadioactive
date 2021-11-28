package com.github.rochedo098.libradioactive.api;

import com.github.rochedo098.libradioactive.LibRadioactive;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

/**
 *  The class for RadiationArea;
 *
 *  The radiate method you create a radiation area for Radioactives parameter;
 *  and
 *  The getRadiation @return the quantity of radiation in Radioactives parameter;
 */
public class RadiationArea {
    private static final Object2IntMap<RadiationType> radioactives = new Object2IntOpenHashMap<>();

    public RadiationArea() { }

    public static void radiate(RadiationType type, int amount) {
        if (!radioactives.containsKey(type)) radioactives.put(type, amount);
        else {
            int existing = radioactives.getInt(type);
            radioactives.put(type, existing + amount);
        }
    }

    public static int getRadiation(RadiationType type) {
        return radioactives.getOrDefault(type, 0);
    }

    public static NbtCompound toTag() {
        NbtCompound tag = new NbtCompound();
        for (RadiationType type : radioactives.keySet()) {
            String key = LibRadioactive.radiationRegistry.getId(type).toString();
            tag.putInt(key, radioactives.getInt(type));
        }
        return tag;
    }

    public static void fromTag(NbtCompound tag) {
        radioactives.clear();
        for (String key : tag.getKeys()) {
            RadiationType type = LibRadioactive.radiationRegistry.get(new Identifier(key));
            int value = tag.getInt(key);
            radioactives.put(type, value);
        }
    }
}
