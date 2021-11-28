package com.github.rochedo098.libradioactive.api.pollution;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

/**
 *  Extends this class to create a PollutionType;
 *
 *  spreadRate is the speed at which the pollution spreads;
 *
 *  The affectedEntity method you write what will happen to the affected entity;
 *  and
 *  The affectedWorld method you write what will happen to the affected world;
 */
public abstract class PollutionType {
    protected int spreadRate;

    public PollutionType(int spreadRate) {
        this.spreadRate = spreadRate;
    }

    public int getPollutionSpreadRate() {
        return spreadRate;
    }

    public abstract void affectedEntity(LivingEntity entity);
    public abstract void affectedWorld(World world, ChunkPos pos);
}