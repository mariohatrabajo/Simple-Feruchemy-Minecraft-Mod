package net.marioio.simpleferuchemy.fluid;

import net.marioio.simpleferuchemy.SimpleFeruchemy;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, SimpleFeruchemy.MOD_ID);

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties, ResourceLocation overlay, int tint, float r, float g, float b){
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, overlay,
                tint, new Vector3f(r/255f, g/255f, b/255f), properties));
    }

    public static void register(IEventBus eventBus){
        FLUID_TYPES.register(eventBus);
    }

}
