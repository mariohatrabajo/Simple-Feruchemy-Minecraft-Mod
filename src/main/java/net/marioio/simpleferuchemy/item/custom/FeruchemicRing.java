package net.marioio.simpleferuchemy.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FeruchemicRing extends Item {
    protected static final int COOLDOWN = 5;
    protected static final String[] modes = {"text.simpleferuchemy.storing_a_lot", "text.simpleferuchemy.storing", "text.simpleferuchemy.idle", "text.simpleferuchemy.extracting", "text.simpleferuchemy.extracting_a_lot"};

    public FeruchemicRing(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        int current_mode = getMode(stack);
        if (getCooldown(stack) >= COOLDOWN && !pLevel.isClientSide()){
            setCooldown(stack, 0);

            int increment = 1;
            if (pPlayer.isShiftKeyDown()) { increment = -1; }
            if((increment > 0 && this.getDamage(stack) < this.getMaxDamage()-1)
                || (increment < 0 && this.getDamage(stack) > 0)) {
                // Change mode
                current_mode = (current_mode + increment) % modes.length;
                current_mode = Math.max(Math.min(current_mode, 2), -2);
                setMode(stack, current_mode);
                return InteractionResultHolder.success(stack);
            }
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
        // Increase cooldown
        setCooldown(pStack, getCooldown(pStack)+1);
    }

    protected void applyEffect(MobEffect effect, int level, LivingEntity entity, ItemStack pStack, boolean damageItem){
        int unbreakingLevel = pStack.getEnchantmentLevel(Enchantments.UNBREAKING);
        if (!entity.hasEffect(effect)) {
            // Unbreaking gives a chance to not use durability
            int duration = this.getMaxDamage() / level;
            if (damageItem){
                duration +=  duration * (unbreakingLevel+1);
            }
            // Apply effect
            entity.addEffect(new MobEffectInstance(effect, duration, level - 1, false, true));
        }

        if (damageItem){
            // We damage the item if unbreaking lets us
            float damageChance = (float) 1 /(unbreakingLevel+1);
            if (Math.random() <= damageChance && this.getDamage(pStack) < this.getMaxDamage() - level+1) {
                pStack.setDamageValue(Math.min(pStack.getMaxDamage(), pStack.getDamageValue()+level));
            }
        } else if (this.getDamage(pStack) > 0) {
            // Heal the item
            pStack.setDamageValue(Math.max(0, pStack.getDamageValue() - level));
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.simpleferuchemy.ring_tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    // HELPERS
    protected int getCooldown(ItemStack stack) {
        return stack.getOrCreateTag().getInt("cooldown");
    }
    protected void setCooldown(ItemStack stack, int val) {
        stack.getOrCreateTag().putInt("cooldown", val);
    }

    protected int getMode(ItemStack stack) {
        return stack.getOrCreateTag().getInt("mode");
    }
    protected void setMode(ItemStack stack, int val) {
        stack.getOrCreateTag().putInt("mode", val);
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
