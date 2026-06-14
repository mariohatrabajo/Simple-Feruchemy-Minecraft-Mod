package net.marioio.simpleferuchemy.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class BendalloyRing extends FeruchemicRing {
    private static MobEffect inEffect = MobEffects.HUNGER;
    private static MobEffect outEffect = MobEffects.SATURATION;
    protected static final int FOOD_COOLDOWN = 25;

    public BendalloyRing(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (super.use(pLevel, pPlayer, pUsedHand).getResult() == InteractionResult.SUCCESS  && !pLevel.isClientSide()){
            // Remove previous effect
            pPlayer.removeEffect(inEffect);
            pPlayer.removeEffect(outEffect);
            // Show current mode
            int current_mode = getMode(stack);
            MutableComponent msg = Component.translatable(modes[current_mode+2]);
            if (current_mode != 0) {
                msg = msg.append(Component.literal(" ").append(Component.translatable("text.simpleferuchemy.energy")));
            }
            pPlayer.sendSystemMessage(msg);
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        int current_mode = getMode(pStack);
        // Apply effect
        if(!pLevel.isClientSide() && pEntity instanceof LivingEntity living){
            if (current_mode < 0){
                applyEffect(inEffect, Math.abs(current_mode), living, pStack, false);

                if (this.getDamage(pStack) == 0) {
                    setToIdle(pStack, living);
                }
            } else if (current_mode > 0){
                applyEffect(outEffect, Math.abs(current_mode), living, pStack, true);
                if (this.getDamage(pStack) >= this.getMaxDamage()-1 - Math.abs(current_mode)) {
                    setToIdle(pStack, living);
                }
            }
        }
    }
    @Override
    protected void applyEffect(MobEffect effect, int level, LivingEntity entity, ItemStack pStack, boolean damageItem){
        int unbreakingLevel = pStack.getEnchantmentLevel(Enchantments.UNBREAKING);
        if (damageItem){
            // Fake saturation
            if (entity instanceof ServerPlayer player && getCooldown(pStack) % Math.ceil((double) FOOD_COOLDOWN /level) == 0) {
                int food = player.getFoodData().getFoodLevel();
                float saturation = player.getFoodData().getSaturationLevel();
                player.getFoodData().setFoodLevel(food + 1);
                player.getFoodData().setSaturation(Math.max(saturation, 2));
            }

            // We damage the item if unbreaking lets us
            float damageChance = (float) 1 /(unbreakingLevel+1);
            if (Math.random() <= damageChance) {
                if (this.getDamage(pStack) < this.getMaxDamage() - level) {
                    pStack.hurtAndBreak(level, entity, e -> {});
                } else { // If theres not enough durability, change to idle before it breaks
                    setMode(pStack, 0);
                }
            }
        } else {
            if (this.getDamage(pStack) > 0) {
                // Fake hunger
                if (entity instanceof ServerPlayer player && getCooldown(pStack) % Math.ceil((FOOD_COOLDOWN*1.5)/level) == 0) {
                    int food = player.getFoodData().getFoodLevel();
                    player.getFoodData().setFoodLevel(food - 1);
                }

                if (!entity.hasEffect(effect)) {
                    // Unbreaking gives a chance to not use durability
                    int duration = this.getMaxDamage() / level;
                    // Apply effect
                    entity.addEffect(new MobEffectInstance(effect, duration, level - 1, false, true));
                }

                // We heal the item
                pStack.setDamageValue(Math.max(0, pStack.getDamageValue() - level));
            } else { // if the item is full health, we change to idle
                setMode(pStack, 0);
            }
        }
    }

    private void setToIdle(ItemStack pStack, LivingEntity pPlayer){
        setMode(pStack, 0);
        // Remove previous effect
        pPlayer.removeEffect(inEffect);
        pPlayer.removeEffect(outEffect);
    }
}
