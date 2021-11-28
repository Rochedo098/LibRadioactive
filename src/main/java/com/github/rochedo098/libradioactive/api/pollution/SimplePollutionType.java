package com.github.rochedo098.libradioactive.api.pollution;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

/**
 *  A simple setting PollutionType can be used as a template for custom PollutionType;
 *
 *  To use this:
 *    The damage value (a floating value), being 20f is full health player;
 *    and
 *    The spreadRate value is the pollution propagation rate;
 */
public class SimplePollutionType extends PollutionType {

    public SimplePollutionType(int spreadRate) {
        super(spreadRate);
    }

    @Override
    public void affectedEntity(LivingEntity entity) {
        int ticks = 0;
        while (ticks != 1202) {
            if (ticks == 1200) entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 10, 1, true, false));
            if (ticks == 1201) ticks = 0;
            ticks++;
        }
    }

    @Override
    public void affectedWorld(World world, ChunkPos pos) {}
}
