package net.marioio.simpleferuchemy.item.custom;

import net.marioio.simpleferuchemy.screen.CoppermindScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WritableBookItem;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class CopperRing extends WritableBookItem {

    protected static final int COOLDOWN = 5;


    public CopperRing(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (getCooldown(stack) < COOLDOWN){
            return InteractionResultHolder.fail(stack);
        }

        if (!hasOwner(stack)){
            setOwner(stack, pPlayer.getUUID());
        }
        if (!getOwner(stack).equals(pPlayer.getUUID())){
            setCooldown(stack, 0);
            if (pLevel.isClientSide()) pPlayer.sendSystemMessage(Component.translatable("text.simpleferuchemy.not_the_owner"));
            return InteractionResultHolder.fail(stack);
        }
        if (pLevel.isClientSide()) {
            Minecraft.getInstance().setScreen(new CoppermindScreen(stack, pPlayer.getInventory().selected));
        }
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        // Increase cooldown
        setCooldown(pStack, getCooldown(pStack)+1);
        if (pStack.hasTag() && pStack.getTag().contains("pages", Tag.TAG_LIST)){
            ListTag pages = pStack.getTag().getList("pages", Tag.TAG_STRING);
            StringBuilder fullText = new StringBuilder();
            for (int i = 0; i < pages.size(); i++) {
                fullText.append(pages.getString(i));
            }
            String text = fullText.toString();
            pStack.setDamageValue(pStack.getMaxDamage()-text.length());
        } else {
            pStack.setDamageValue(pStack.getMaxDamage());
        }
    }

    // HELPERS
    protected boolean hasOwner(ItemStack stack) {
        return !stack.isEmpty() &&
                stack.hasTag() &&
                stack.getTag().hasUUID("owner");
    }
    protected UUID getOwner(ItemStack stack) {
        if (stack.isEmpty() || !stack.hasTag()) return null;
        CompoundTag tag = stack.getTag();
        if (!tag.hasUUID("owner")) return null;
        return tag.getUUID("owner");
    }
    protected void setOwner(ItemStack stack, UUID val) {
        if (stack.isEmpty()) return;
        stack.getOrCreateTag().putUUID("owner", val);
    }
    protected int getCooldown(ItemStack stack) {
        return stack.getOrCreateTag().getInt("cooldown");
    }
    protected void setCooldown(ItemStack stack, int val) {
        stack.getOrCreateTag().putInt("cooldown", val);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }


}
