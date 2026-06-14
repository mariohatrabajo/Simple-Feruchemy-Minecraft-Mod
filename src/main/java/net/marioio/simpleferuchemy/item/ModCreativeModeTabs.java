package net.marioio.simpleferuchemy.item;

import net.marioio.simpleferuchemy.SimpleFeruchemy;
import net.marioio.simpleferuchemy.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimpleFeruchemy.MOD_ID);

    // ADD CREATIVE TABS
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.STEEL_RING.get()))
                    .title(Component.translatable("creativetab.simpleferuchemy_tab"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        // ADD ITEMS TO THE TAB
                        output.accept(ModItems.STEEL_RING.get());
                        output.accept(ModItems.STEEL_BRACELET.get());
                        output.accept(ModItems.TIN_RING.get());
                        output.accept(ModItems.TIN_BRACELET.get());
                        output.accept(ModItems.PEWTER_RING.get());
                        output.accept(ModItems.PEWTER_BRACELET.get());
                        output.accept(ModItems.ZINC_RING.get());
                        output.accept(ModItems.ZINC_BRACELET.get());
                        output.accept(ModItems.GOLD_RING.get());
                        output.accept(ModItems.GOLD_BRACELET.get());
                        output.accept(ModItems.BENDALLOY_RING.get());
                        output.accept(ModItems.BENDALLOY_BRACELET.get());
                        output.accept(ModItems.COPPER_RING.get());
                        output.accept(ModItems.COPPER_BRACELET.get());
                        output.accept(ModItems.BRASS_RING.get());
                        output.accept(ModItems.BRASS_BRACELET.get());
                        output.accept(ModItems.NICROSIL_RING.get());
                        output.accept(ModItems.NICROSIL_BRACELET.get());
                        // Raw Ores
                        output.accept(ModItems.RAW_TIN.get());
                        output.accept(ModItems.RAW_ZINC.get());
                        output.accept(ModItems.RAW_BENDALLOY.get());
                        output.accept(ModItems.RAW_NICROSIL.get());
                        output.accept(ModBlocks.TIN_ORE.get());
                        output.accept(ModBlocks.ZINC_ORE.get());
                        output.accept(ModBlocks.BENDALLOY_ORE.get());
                        output.accept(ModBlocks.NETHER_BENDALLOY_ORE.get());
                        output.accept(ModBlocks.NICROSIL_ORE.get());
                        // Ingots
                        output.accept(ModItems.TIN_INGOT.get());
                        output.accept(ModItems.PEWTER_INGOT.get());
                        output.accept(ModItems.ZINC_INGOT.get());
                        output.accept(ModItems.BRASS_INGOT.get());
                        output.accept(ModItems.BENDALLOY_INGOT.get());
                        output.accept(ModItems.BENDALLOY_NUGGET.get());
                        output.accept(ModItems.NICROSIL_INGOT.get());
                    }))
                    .build());
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
