package net.marioio.simpleferuchemy.worldgen;

import net.marioio.simpleferuchemy.SimpleFeruchemy;
import net.marioio.simpleferuchemy.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> TIN_ORE_KEY = registerKey("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> ZINC_ORE_KEY = registerKey("zinc_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> BENDALLOY_ORE_KEY = registerKey("bendalloy_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> NETHER_BENDALLOY_ORE_KEY = registerKey("nether_bendalloy_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> NICROSIL_ORE_KEY = registerKey("nicrosil_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context){
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        register(context, TIN_ORE_KEY, Feature.ORE, new OreConfiguration(stoneReplaceables, ModBlocks.TIN_ORE.get().defaultBlockState(), 5)); // pSize es el tamaño de las menas
        register(context, ZINC_ORE_KEY, Feature.ORE, new OreConfiguration(deepslateReplaceables, ModBlocks.ZINC_ORE.get().defaultBlockState(), 6));
        register(context, BENDALLOY_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables, ModBlocks.BENDALLOY_ORE.get().defaultBlockState(), 7));
        register(context, NETHER_BENDALLOY_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables, ModBlocks.NETHER_BENDALLOY_ORE.get().defaultBlockState(), 4));
        register(context, NICROSIL_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables, ModBlocks.NICROSIL_ORE.get().defaultBlockState(), 5));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(SimpleFeruchemy.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
