package net.marioio.simpleferuchemy.fluid;

import net.marioio.simpleferuchemy.SimpleFeruchemy;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, SimpleFeruchemy.MOD_ID);

    public static void register(IEventBus eventBus){
        FLUIDS.register(eventBus);
    }
}
