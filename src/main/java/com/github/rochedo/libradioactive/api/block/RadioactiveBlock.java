package com.github.rochedo.libradioactive.api.block;

import com.github.rochedo.libradioactive.api.item.RadioactiveItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class RadioactiveBlock extends Block {
    public Item.Settings itemSettings;
    public Float damageItem;

    public RadioactiveBlock(Block.Settings blockSettings, Item.Settings itemSettings, Float damageItem) {
        super(blockSettings);
        this.itemSettings = itemSettings;
        this.damageItem = damageItem;
    }

    @Override
    public Item asItem() {
        return new RadioactiveItem(itemSettings, damageItem);
    }


}
