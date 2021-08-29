package com.github.rochedo098.libradioactive.impl;

import com.github.rochedo098.libradioactive.api.RadiationArea;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * A simple item to show the radiation in a entity or chunk
 */
public class RadioactiveMeter extends Item {
    public RadioactiveMeter(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText("This item shows the radiation on an entity or chunk, for that click on:"));
        tooltip.add(new LiteralText("Air: to show the radiation level on your player"));
        tooltip.add(new LiteralText("Entity: to show the radiation level in this entity"));
        tooltip.add(new LiteralText("Block: to show the radiation level on the chunk where your player is located."));
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

        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        final ChunkPos chunkPos = context.getWorld().getChunk(context.getBlockPos()).getPos();
        RadiationArea radiation = new RadiationState().getRadiation(chunkPos);
        Objects.requireNonNull(context.getPlayer()).sendMessage(new LiteralText("The current radioactivity level on chunk " + chunkPos.toString() + " is " + radiation.toString()).formatted(Formatting.BOLD), true);
        return ActionResult.SUCCESS;
    }
}
