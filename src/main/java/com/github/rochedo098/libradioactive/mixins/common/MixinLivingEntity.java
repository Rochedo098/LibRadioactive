package com.github.rochedo098.libradioactive.mixins.common;

import com.github.rochedo098.libradioactive.LibRadioactive;
import com.github.rochedo098.libradioactive.api.RadiationArea;
import com.github.rochedo098.libradioactive.impl.RadiationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.ChunkPos;
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
        ChunkPos chunk = this.getChunkPos();
        RadiationArea radiation = new RadiationState().getRadiation(chunk);

    }
}
