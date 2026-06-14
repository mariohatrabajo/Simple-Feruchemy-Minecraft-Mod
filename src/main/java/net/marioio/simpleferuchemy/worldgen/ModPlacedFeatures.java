package net.marioio.simpleferuchemy.worldgen;

import net.marioio.simpleferuchemy.SimpleFeruchemy;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> TIN_PLACED_KEY = registerKey("tin_ore_placed");
    public static final ResourceKey<PlacedFeature> ZINC_PLACED_KEY = registerKey("zinc_ore_placed");
    public static final ResourceKey<PlacedFeature> BENDALLOY_PLACED_KEY = registerKey("bendalloy_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_BENDALLOY_PLACED_KEY = registerKey("nether_bendalloy_ore_placed");
    public static final ResourceKey<PlacedFeature> NICROSIL_PLACED_KEY = registerKey("nicrosil_ore_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?,?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, TIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TIN_ORE_KEY),
                ModOrePlacement.commonOrePlacement(6, // menas por chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(1), VerticalAnchor.absolute(74)))); // Generadas uniformemente desde la capa 80 a la -64. usa .triangle para que creen un rombo
        register(context, ZINC_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZINC_ORE_KEY),
                ModOrePlacement.commonOrePlacement(6,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(0))));
        register(context, BENDALLOY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BENDALLOY_ORE_KEY),
                ModOrePlacement.commonOrePlacement(7,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, NETHER_BENDALLOY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_BENDALLOY_ORE_KEY),
                ModOrePlacement.commonOrePlacement(25,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(250))));
        register(context, NICROSIL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NICROSIL_ORE_KEY),
                ModOrePlacement.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(SimpleFeruchemy.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
