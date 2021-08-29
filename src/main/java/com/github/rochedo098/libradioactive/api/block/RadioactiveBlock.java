package com.github.rochedo098.libradioactive.api.block;

import com.github.rochedo098.libradioactive.api.AdvancedRadiationType;
import com.github.rochedo098.libradioactive.api.RadiationArea;
import com.github.rochedo098.libradioactive.api.item.RadioactiveItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RadioactiveBlock extends Block {
    public Item.Settings itemSettings;
    public Float damageItem;
    public int radiationAmount;

    public RadioactiveBlock(Block.Settings blockSettings, Item.Settings itemSettings, Float damageItem, int radiationAmount) {
        super(blockSettings);
        this.itemSettings = itemSettings;
        this.damageItem = damageItem;
        this.radiationAmount = radiationAmount;
    }

    @Override
    public Item asItem() {
        return new RadioactiveItem(itemSettings, damageItem);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        RadiationArea.radiate(new AdvancedRadiationType(damageItem), radiationAmount);
    }
}
