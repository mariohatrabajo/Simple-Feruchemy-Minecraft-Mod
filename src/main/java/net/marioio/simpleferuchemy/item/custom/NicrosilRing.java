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

public class NicrosilRing extends FeruchemicRing {

    public NicrosilRing(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (super.use(pLevel, pPlayer, pUsedHand).getResult() == InteractionResult.SUCCESS  && !pLevel.isClientSide()){
            // Show current mode
            int current_mode = getMode(stack);
            MutableComponent msg = Component.translatable(modes[current_mode+2]);
            if (current_mode != 0) {
                msg = msg.append(Component.literal(" ").append(Component.translatable("text.simpleferuchemy.investiture")));
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
                applyEffect(Math.abs(current_mode), living, pStack, false);

                if (this.getDamage(pStack) == 0) {
                    setMode(pStack, 0);
                }
            } else if (current_mode > 0){
                applyEffect(Math.abs(current_mode), living, pStack, true);
                if (this.getDamage(pStack) >= this.getMaxDamage()-1 - Math.abs(current_mode)) {
                    setMode(pStack, 0);
                }
            }
        }
    }

    protected void applyEffect(int level, LivingEntity entity, ItemStack pStack, boolean damageItem){
        int unbreakingLevel = pStack.getEnchantmentLevel(Enchantments.UNBREAKING);
        if (damageItem){
            if (entity instanceof ServerPlayer player){
                player.giveExperiencePoints(level);
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
            if (entity instanceof ServerPlayer player && this.getDamage(pStack) > 0 && player.totalExperience > 0) {
                player.giveExperiencePoints(-level);
                // We heal the item
                pStack.setDamageValue(Math.max(0, pStack.getDamageValue() - level));
            } else { // if the item is full health, we change to idle
                setMode(pStack, 0);
            }
        }
    }
}
