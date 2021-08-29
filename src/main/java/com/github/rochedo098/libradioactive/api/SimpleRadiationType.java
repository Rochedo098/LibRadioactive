package com.github.rochedo098.libradioactive.api;

import com.github.rochedo098.libradioactive.LibRadioactive;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

/**
 *  A simple setting RadiationType can be used as a template for custom RadiationType;
 *
 *  To use this:
 *    The damage value (a floating value), being 20f is full health player;
 *    and
 *    The spreadRate value is the radiation propagation rate;
 */
public class SimpleRadiationType extends RadiationType {
    public Float damage;

    public SimpleRadiationType(Float damage, int spreadRate) {
        super(spreadRate);
        this.damage = damage;
    }

    @Override
    public void affectedEntity(LivingEntity entity) {
        entity.damage(LibRadioactive.RADIOACTIVE_DAMAGE_SOURCE, damage);
    }

    @Override
    public void affectedWorld(World world, ChunkPos pos) {

    }
}
