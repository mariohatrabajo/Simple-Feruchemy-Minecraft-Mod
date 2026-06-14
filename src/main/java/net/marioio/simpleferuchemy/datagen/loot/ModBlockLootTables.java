package net.marioio.simpleferuchemy.datagen.loot;

import net.marioio.simpleferuchemy.block.ModBlocks;
import net.marioio.simpleferuchemy.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.add(ModBlocks.TIN_ORE.get(), block -> this.createOreDrops(ModBlocks.TIN_ORE.get(), ModItems.RAW_TIN.get(), 1f, 1f));
        this.add(ModBlocks.ZINC_ORE.get(), block -> this.createOreDrops(ModBlocks.ZINC_ORE.get(), ModItems.RAW_ZINC.get(), 1f, 1f));
        this.add(ModBlocks.BENDALLOY_ORE.get(), block -> this.createOreDrops(ModBlocks.BENDALLOY_ORE.get(), ModItems.RAW_BENDALLOY.get(), 1f, 1f));
        this.add(ModBlocks.NETHER_BENDALLOY_ORE.get(), block -> this.createOreDrops(ModBlocks.NETHER_BENDALLOY_ORE.get(), ModItems.BENDALLOY_NUGGET.get(), 2f, 4f));
        this.add(ModBlocks.NICROSIL_ORE.get(), block -> this.createOreDrops(ModBlocks.NICROSIL_ORE.get(), ModItems.RAW_NICROSIL.get(), 1f, 2f));
    }

    // Custom function para que suelte entre 2 y 5 items, en este caso
    // click central en createOreDrop para ver todas las funciones vanilla
    protected LootTable.Builder createOreDrops(Block pBlock, Item item, float min, float max) {
        return createSilkTouchDispatchTable(pBlock,
                (LootPoolEntryContainer.Builder)this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }


    // Todos los bloques de ModBlocks.BLOCKS que no tengan .noLootTable() tendrán loot table
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
