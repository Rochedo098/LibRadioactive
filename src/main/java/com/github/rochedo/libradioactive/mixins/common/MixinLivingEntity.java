package com.github.rochedo.libradioactive.mixins.common;

import com.github.rochedo.libradioactive.LibRadioactive;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends LivingEntity {
    public static Double RADIOACTIVITY_LEVEL = 0.0;
    public static Boolean RADIOACTIVITY_IMMUNE = false;

    protected MixinLivingEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putDouble("radioactivityLevel", RADIOACTIVITY_LEVEL);
        nbt.putBoolean("radioactivityImmune", RADIOACTIVITY_IMMUNE);
    }

    @Inject(method = "readCustomDataToNbt", at = @At("RETURN"))
    public void readCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        RADIOACTIVITY_LEVEL = nbt.getDouble("radioactivityLevel");
        RADIOACTIVITY_IMMUNE = nbt.getBoolean("radioactivityImmune");
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick() {
        if (!RADIOACTIVITY_IMMUNE || RADIOACTIVITY_LEVEL < 25) {
            this.damage(LibRadioactive.RADIOACTIVE_DAMAGE_SOURCE, 1f);
        }
    }
}
