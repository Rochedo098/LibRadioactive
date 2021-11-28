package com.github.rochedo098.libradioactive.api.pollution;

import com.github.rochedo098.libradioactive.LibRadioactive;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

/**
 *  The class for PollutionArea;
 *
 *  The pollute method you create a pollution area for Pollution's parameter;
 *  and
 *  The getPollution @return the quantity of pollution in Pollution Area;
 */
public class PollutionArea {
    private static final Object2IntMap<PollutionType> pollutions = new Object2IntOpenHashMap<>();

    public PollutionArea() { }

    public static void pollute(PollutionType type, int amount) {
        if (!pollutions.containsKey(type)) pollutions.put(type, amount);
        else {
            int existing = pollutions.getInt(type);
            pollutions.put(type, existing + amount);
        }
    }

    public static int getPollution(PollutionType type) {
        return pollutions.getOrDefault(type, 0);
    }

    public static NbtCompound toTag() {
        NbtCompound tag = new NbtCompound();
        for (PollutionType type : pollutions.keySet()) {
            String key = LibRadioactive.pollutionRegistry.getId(type).toString();
            tag.putInt(key, pollutions.getInt(type));
        }
        return tag;
    }

    public static void fromTag(NbtCompound tag) {
        pollutions.clear();
        for (String key : tag.getKeys()) {
            PollutionType type = LibRadioactive.pollutionRegistry.get(new Identifier(key));
            int value = tag.getInt(key);
            pollutions.put(type, value);
        }
    }
}
