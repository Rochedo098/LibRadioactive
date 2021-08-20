package com.github.rochedo.libradioactive.impl;

import com.github.rochedo.libradioactive.api.RadiationArea;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.PersistentState;

public class RadiationState extends PersistentState {
    private Long2ObjectMap<RadiationArea> chunkMap = new Long2ObjectOpenHashMap<>();

    public RadiationState() {
        super();
    }

    public void readNbt(NbtCompound tag) {
        chunkMap.clear();
        for (String key : tag.getKeys()) {
            long chunk = Long.parseLong(key);
            RadiationArea area = new RadiationArea();
            area.fromTag(tag.getCompound(key));
            chunkMap.put(chunk, area);
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        for (long key : chunkMap.keySet()) {
            RadiationArea area = chunkMap.get(key);
            tag.put(Long.toString(key), area.toTag());
        }
        return tag;
    }

    public RadiationArea getRadiation(ChunkPos pos) {
        long key = pos.toLong();
        if (!chunkMap.containsKey(key)) {
            chunkMap.put(key, new RadiationArea());
            markDirty();
        }
        return chunkMap.get(key);
    }

    public void setRadiation(ChunkPos pos, RadiationArea pollution) {
        chunkMap.put(pos.toLong(), pollution);
        markDirty();
    }
}
