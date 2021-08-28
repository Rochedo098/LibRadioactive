package com.github.rochedo098.libradioactive.api;

import com.github.rochedo098.libradioactive.LibRadioactive;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class RadiationArea {
    private final Object2IntMap<RadiationType> radioactives = new Object2IntOpenHashMap<>();

    public RadiationArea() {

    }

    public void radiate(RadiationType type, int amount) {
        if (!radioactives.containsKey(type)) radioactives.put(type, amount);
        else {
            int existing = radioactives.getInt(type);
            radioactives.put(type, existing + amount);
        }
    }

    public int getRadiation(RadiationType type) {
        return radioactives.getOrDefault(type, 0);
    }

    public NbtCompound toTag() {
        NbtCompound tag = new NbtCompound();
        for (RadiationType type : radioactives.keySet()) {
            String key = LibRadioactive.registry.getId(type).toString();
            tag.putInt(key, radioactives.getInt(type));
        }
        return tag;
    }

    public void fromTag(NbtCompound tag) {
        radioactives.clear();
        for (String key : tag.getKeys()) {
            RadiationType type = LibRadioactive.registry.get(new Identifier(key));
            int value = tag.getInt(key);
            radioactives.put(type, value);
        }
    }
}
