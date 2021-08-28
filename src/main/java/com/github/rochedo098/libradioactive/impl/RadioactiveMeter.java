package com.github.rochedo098.libradioactive.impl;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RadioactiveMeter extends Item {
    public RadioactiveMeter(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText("If this item is in your inventory, it will show the amount of radiation on you and blocks"));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // Read Entity NBT
        NbtCompound nbt = new NbtCompound();
        user.readCustomDataFromNbt(nbt);
        double radioactivityLevel = nbt.getDouble("radioactivityLevel");

        user.sendMessage(new LiteralText("The current radioactivity level is " + radioactivityLevel).formatted(Formatting.BOLD), true);
        return TypedActionResult.success(user.getMainHandStack());
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        // Read Entity NBT
        NbtCompound nbt = new NbtCompound();
        entity.readCustomDataFromNbt(nbt);
        double radioactivityLevel = nbt.getDouble("radioactivityLevel");

        // Send Message to User
        user.sendMessage(new LiteralText("The current radioactivity level on entity " + entity.getEntityName() + " is " + radioactivityLevel).formatted(Formatting.BOLD), true);

        return ActionResult.success(false);
    }
}
