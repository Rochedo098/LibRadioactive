package com.github.rochedo098.libradioactive.impl.pollution;

import com.github.rochedo098.libradioactive.api.pollution.PollutionArea;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.PersistentState;

/**
 *  The setPollution method sets a pollution value for a Chunk;
 *  The getPollution method returns the value of the pollution in Chunk;
 */
public class PollutionState extends PersistentState {
    private final Long2ObjectMap<PollutionArea> chunkMap = new Long2ObjectOpenHashMap<>();

    public PollutionState() {
        super();
    }

    public void readNbt(NbtCompound tag) {
        chunkMap.clear();
        for (String key : tag.getKeys()) {
            long chunk = Long.parseLong(key);
            PollutionArea area = new PollutionArea();
            PollutionArea.fromTag(tag.getCompound(key));
            chunkMap.put(chunk, area);
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        for (long key : chunkMap.keySet()) {
            PollutionArea area = chunkMap.get(key);
            tag.put(Long.toString(key), PollutionArea.toTag());
        }
        return tag;
    }

    public PollutionArea getPollution(ChunkPos pos) {
        long key = pos.toLong();
        if (!chunkMap.containsKey(key)) {
            chunkMap.put(key, new PollutionArea());
            markDirty();
        }
        return chunkMap.get(key);
    }

    public void setPollution(ChunkPos pos, PollutionArea pollution) {
        chunkMap.put(pos.toLong(), pollution);
        markDirty();
    }
}
