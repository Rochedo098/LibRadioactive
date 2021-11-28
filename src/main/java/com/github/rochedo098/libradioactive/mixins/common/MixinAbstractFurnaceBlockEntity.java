package com.github.rochedo098.libradioactive.mixins.common;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class )
public abstract class MixinAbstractFurnaceBlockEntity extends LockableContainerBlockEntity {
    @Shadow protected abstract boolean isBurning();

    protected MixinAbstractFurnaceBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    static void tick(World world, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity, CallbackInfo ci) {
        // TODO: Fix it later
        //if (this.isBurning()) {
        //    int ticks = 0;
        //    while (ticks != 7) {
        //        if (ticks == 5) PollutionArea.pollute(new SimplePollutionType(3), 1);
        //        if (ticks == 6) ticks = 0;
        //        ticks++;
        //    }
        //}
    }
}
