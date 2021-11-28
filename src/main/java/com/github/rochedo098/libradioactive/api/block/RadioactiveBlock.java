package com.github.rochedo098.libradioactive.api.block;

import com.github.rochedo098.libradioactive.api.RadiationArea;
import com.github.rochedo098.libradioactive.api.SimpleRadiationType;
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
    public int spreadRadiationRate;

    public RadioactiveBlock(Block.Settings blockSettings, Item.Settings itemSettings, Float damageItem, int radiationAmount, int spreadRadiationRate) {
        super(blockSettings);
        this.itemSettings = itemSettings;
        this.damageItem = damageItem;
        this.radiationAmount = radiationAmount;
        this.spreadRadiationRate = spreadRadiationRate;
    }

    @Override
    public Item asItem() {
        return new RadioactiveItem(itemSettings, damageItem, spreadRadiationRate);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        RadiationArea.radiate(new SimpleRadiationType(damageItem, spreadRadiationRate), radiationAmount);
    }
}
