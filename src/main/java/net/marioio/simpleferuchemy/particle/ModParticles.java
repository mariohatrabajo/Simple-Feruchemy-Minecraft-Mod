package net.marioio.simpleferuchemy.particle;

import net.marioio.simpleferuchemy.SimpleFeruchemy;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SimpleFeruchemy.MOD_ID);

    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }
}
