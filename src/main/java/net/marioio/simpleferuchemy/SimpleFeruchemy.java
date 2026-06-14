package net.marioio.simpleferuchemy;

import com.mojang.logging.LogUtils;
import net.marioio.simpleferuchemy.block.ModBlocks;
import net.marioio.simpleferuchemy.fluid.ModFluidTypes;
import net.marioio.simpleferuchemy.fluid.ModFluids;
import net.marioio.simpleferuchemy.item.ModCreativeModeTabs;
import net.marioio.simpleferuchemy.item.ModItems;
import net.marioio.simpleferuchemy.loot.ModLootModifiers;
import net.marioio.simpleferuchemy.particle.ModParticles;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

import static org.antlr.runtime.debug.DebugEventListener.PROTOCOL_VERSION;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SimpleFeruchemy.MOD_ID)
public class SimpleFeruchemy
{
    /**TODO
     * Los anillos se craftean sin durabilidad
     */

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "simpleferuchemy";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SimpleFeruchemy()
    {
        registerPackets();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModParticles.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        // Add items to a vanilla creative tab
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.STEEL_RING);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }

        @SubscribeEvent
        public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
        }
    }

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MOD_ID, "main_channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    private void registerPackets() {
        int id = 0;

        CHANNEL.registerMessage(
                id++,
                net.marioio.simpleferuchemy.network.UpdateBookTextPacket.class,
                net.marioio.simpleferuchemy.network.UpdateBookTextPacket::encode,
                net.marioio.simpleferuchemy.network.UpdateBookTextPacket::decode,
                net.marioio.simpleferuchemy.network.UpdateBookTextPacket::handle
        );
    }
}
