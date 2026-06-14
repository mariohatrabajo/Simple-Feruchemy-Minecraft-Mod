package net.marioio.simpleferuchemy.item.custom;

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
import net.minecraft.world.level.Level;

public class TinRing extends FeruchemicRing {
    private static MobEffect inEffect = MobEffects.BLINDNESS;
    private static MobEffect outEffect = MobEffects.NIGHT_VISION;
    protected static final String[] modes = {"text.simpleferuchemy.storing_a_lot_plural", "text.simpleferuchemy.storing", "text.simpleferuchemy.idle", "text.simpleferuchemy.extracting", "text.simpleferuchemy.extracting_a_lot_plural"};


    public TinRing(Properties pProperties) {
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
                msg = msg.append(Component.literal(" ").append(Component.translatable("text.simpleferuchemy.senses")));
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

    private void setToIdle(ItemStack pStack, LivingEntity pPlayer){
        setMode(pStack, 0);
        // Remove previous effect
        pPlayer.removeEffect(inEffect);
        pPlayer.removeEffect(outEffect);
    }
}
