package com.github.rochedo098.libradioactive.mixins.common;

import com.github.rochedo098.libradioactive.LibRadioactive;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
    @Shadow public abstract boolean damage(DamageSource source, float amount);

    private static Double RADIOACTIVITY_LEVEL = 0.0; // MiliSieverts
    private static Boolean RADIOACTIVITY_IMMUNE = false;

    private static Double POLLUTION_LEVEL = 0.0;

    protected MixinLivingEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putDouble("radioactivityLevel", RADIOACTIVITY_LEVEL);
        nbt.putBoolean("radioactivityImmune", RADIOACTIVITY_IMMUNE);
        nbt.putDouble("pollutionLevel", POLLUTION_LEVEL);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void readCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        RADIOACTIVITY_LEVEL = nbt.getDouble("radioactivityLevel");
        RADIOACTIVITY_IMMUNE = nbt.getBoolean("radioactivityImmune");
        POLLUTION_LEVEL = nbt.getDouble("pollutionLevel");
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if (RADIOACTIVITY_LEVEL >= 20.0) {
            int ticks = 0;
            while (ticks != 7) {
                if (ticks == 5) this.damage(LibRadioactive.RADIOACTIVE_DAMAGE_SOURCE, 1f);
                if (ticks == 6) ticks = 0;
                ticks++;
            }
        }
    }
}
