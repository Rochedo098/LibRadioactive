package com.github.rochedo098.libradioactive.api;

import com.github.rochedo098.libradioactive.LibRadioactive;
import com.github.rochedo098.libradioactive.impl.RadiationState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class AdvancedRadiationType extends RadiationType {
    public Float damage;

    public AdvancedRadiationType(Float damage) {
        super(3);
        this.damage = damage;
    }

    @Override
    public void affectedEntity(LivingEntity entity) {
        entity.damage(LibRadioactive.RADIOACTIVE_DAMAGE_SOURCE, damage);
    }

    @Override
    public void affectedWorld(World world, ChunkPos pos) {
        new RadiationState().setRadiation(pos, new RadiationArea());
    }
}
