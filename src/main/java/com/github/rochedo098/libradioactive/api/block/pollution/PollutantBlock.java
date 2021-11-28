package com.github.rochedo098.libradioactive.api.block.pollution;

import com.github.rochedo098.libradioactive.api.pollution.PollutionArea;
import com.github.rochedo098.libradioactive.api.pollution.SimplePollutionType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PollutantBlock extends Block {
    public Item.Settings itemSettings;
    public int pollutionAmount;
    public int spreadRate;

    public PollutantBlock(Block.Settings blockSettings, Item.Settings itemSettings, int pollutionAmount, int spreadRate) {
        super(blockSettings);
        this.itemSettings = itemSettings;
        this.pollutionAmount = pollutionAmount;
        this.spreadRate = spreadRate;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        PollutionArea.pollute(new SimplePollutionType(spreadRate), pollutionAmount);
    }
}