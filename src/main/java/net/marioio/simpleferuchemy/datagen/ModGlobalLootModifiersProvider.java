package net.marioio.simpleferuchemy.datagen;

import net.marioio.simpleferuchemy.SimpleFeruchemy;
import net.marioio.simpleferuchemy.item.ModItems;
import net.marioio.simpleferuchemy.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, SimpleFeruchemy.MOD_ID);
    }

    @Override
    protected void start() {
        // Tin
        add("tin_ingot_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build()
        }, ModItems.TIN_INGOT.get(), 0.8f, 2));
        add("tin_ingot_from_village_toolsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_toolsmith")).build()
        }, ModItems.TIN_INGOT.get(), 0.5f, 3));
        add("tin_ingot_from_village_weaponsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build()
        }, ModItems.TIN_INGOT.get(), 0.3f, 4));
        // Pewter
        add("pewter_ingot_from_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build()
        }, ModItems.PEWTER_INGOT.get(), 0.3f, 3));
        add("pewter_ingot_from_village_toolsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_toolsmith")).build()
        }, ModItems.PEWTER_INGOT.get(), 0.5f, 2));
        add("pewter_ingot_from_village_weaponsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build()
        }, ModItems.PEWTER_INGOT.get(), 0.8f, 2));
        add("pewter_ingot_from_pillager_outpost", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build()
        }, ModItems.PEWTER_INGOT.get(), 0.8f, 2));
        add("tin_ingot_from_mineshafts", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build()
        }, ModItems.TIN_INGOT.get(), 0.6f, 3));
        add("pewter_ring_from_pillager_outpost", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build()
        }, ModItems.PEWTER_RING.get(), 0.2f, 1));
        // Zinc
        add("zinc_ingot_from_dungeons", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build()
        }, ModItems.ZINC_INGOT.get(), 0.8f, 1));
        add("zinc_ingot_from_mineshafts", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build()
        }, ModItems.ZINC_INGOT.get(), 0.3f, 2));
        add("zinc_ingot_from_pillager_outpost", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build()
        }, ModItems.ZINC_INGOT.get(), 0.3f, 1));
        add("zinc_ingot_from_shipwrecks", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build()
        }, ModItems.ZINC_INGOT.get(), 0.8f, 2));
        add("zinc_ring_from_mineshafts", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build()
        }, ModItems.ZINC_RING.get(), 0.2f, 1));
        // Brass
        add("brass_ingot_from_bastions", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_other")).build()
        }, ModItems.BRASS_INGOT.get(), 0.4f, 1));
        add("brass_ingot_from_nether_fortress", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/nether_bridge")).build()
        }, ModItems.BRASS_INGOT.get(), 0.3f, 3));
        add("brass_ring_from_nether_fortress", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/nether_bridge")).build()
        }, ModItems.BRASS_RING.get(), 0.1f, 1));
        // Bendalloy
        add("bendalloy_ingot_from_bastions", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_bridge")).build()
        }, ModItems.BENDALLOY_INGOT.get(), 0.2f, 2));
        add("bendalloy_nugget_from_bastions", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_bridge")).build()
        }, ModItems.BENDALLOY_NUGGET.get(), 0.5f, 4));
        add("bendalloy_ingot_from_ancient_cities", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build()
        }, ModItems.BENDALLOY_INGOT.get(), 0.3f, 2));
        add("bendalloy_ring_from_ancient_cities", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build()
        }, ModItems.BENDALLOY_RING.get(), 0.2f, 1));
        add("bendalloy_ingot_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build()
        }, ModItems.BENDALLOY_INGOT.get(), 0.5f, 2));
        add("bendalloy_ingot_from_stronghold", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_crossing")).build()
        }, ModItems.BENDALLOY_INGOT.get(), 0.2f, 1));
        // Copper
        add("copper_ring_from_stronghold", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_library")).build()
        }, ModItems.COPPER_RING.get(), 0.5f, 1));
        // Nicrosil
        add("nicrosil_ingot_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build()
        }, ModItems.NICROSIL_INGOT.get(), 0.3f, 3));
        add("nicrosil_ring_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build()
        }, ModItems.NICROSIL_RING.get(), 0.2f, 1));
        add("nicrosil_ingot_from_stronghold", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_corridor")).build()
        }, ModItems.NICROSIL_INGOT.get(), 0.2f, 1));
    }
}
