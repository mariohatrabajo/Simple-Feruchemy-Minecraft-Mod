package net.marioio.simpleferuchemy.datagen;

import net.marioio.simpleferuchemy.SimpleFeruchemy;
import net.marioio.simpleferuchemy.block.ModBlocks;
import net.marioio.simpleferuchemy.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> TIN_SMELTABLES = List.of(ModItems.RAW_TIN.get(), ModBlocks.TIN_ORE.get());
    private static final List<ItemLike> ZINC_SMELTABLES = List.of(ModItems.RAW_ZINC.get(), ModBlocks.ZINC_ORE.get());
    private static final List<ItemLike> BENDALLOY_SMELTABLES = List.of(ModItems.RAW_BENDALLOY.get(), ModBlocks.BENDALLOY_ORE.get(), ModBlocks.NETHER_BENDALLOY_ORE.get());
    private static final List<ItemLike> NICROSIL_SMELTABLES = List.of(ModItems.RAW_NICROSIL.get(), ModBlocks.NICROSIL_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        // Ore smelting
        oreSmelting(pWriter, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25f, 200, "tin");
        oreBlasting(pWriter, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT.get(), 0.25F, 100, "tin");
        oreSmelting(pWriter, ZINC_SMELTABLES, RecipeCategory.MISC, ModItems.ZINC_INGOT.get(), 0.25f, 200, "zinc");
        oreBlasting(pWriter, ZINC_SMELTABLES, RecipeCategory.MISC, ModItems.ZINC_INGOT.get(), 0.25F, 100, "zinc");
        oreSmelting(pWriter, BENDALLOY_SMELTABLES, RecipeCategory.MISC, ModItems.BENDALLOY_INGOT.get(), 0.25f, 200, "bendalloy");
        oreBlasting(pWriter, BENDALLOY_SMELTABLES, RecipeCategory.MISC, ModItems.BENDALLOY_INGOT.get(), 0.25F, 100, "bendalloy");
        oreSmelting(pWriter, NICROSIL_SMELTABLES, RecipeCategory.MISC, ModItems.NICROSIL_INGOT.get(), 0.25f, 200, "nicrosil");
        oreBlasting(pWriter, NICROSIL_SMELTABLES, RecipeCategory.MISC, ModItems.NICROSIL_INGOT.get(), 0.25F, 100, "nicrosil");

        // Rings
        craftRing(pWriter, ModItems.STEEL_RING.get(), Items.IRON_INGOT);
        craftBracelet(pWriter, ModItems.STEEL_BRACELET.get(), ModItems.STEEL_RING.get());
        craftRing(pWriter, ModItems.TIN_RING.get(), ModItems.TIN_INGOT.get());
        craftBracelet(pWriter, ModItems.TIN_BRACELET.get(), ModItems.TIN_RING.get());
        craftRing(pWriter, ModItems.PEWTER_RING.get(), ModItems.PEWTER_INGOT.get());
        craftBracelet(pWriter, ModItems.PEWTER_BRACELET.get(), ModItems.PEWTER_RING.get());
        craftRing(pWriter, ModItems.ZINC_RING.get(), ModItems.ZINC_INGOT.get());
        craftBracelet(pWriter, ModItems.ZINC_BRACELET.get(), ModItems.ZINC_RING.get());
        craftRing(pWriter, ModItems.GOLD_RING.get(), Items.GOLD_INGOT);
        craftBracelet(pWriter, ModItems.GOLD_BRACELET.get(), ModItems.GOLD_RING.get());
        craftRing(pWriter, ModItems.BENDALLOY_RING.get(), ModItems.BENDALLOY_INGOT.get());
        craftBracelet(pWriter, ModItems.BENDALLOY_BRACELET.get(), ModItems.BENDALLOY_RING.get());
        craftRing(pWriter, ModItems.COPPER_RING.get(), Items.COPPER_INGOT);
        craftBracelet(pWriter, ModItems.COPPER_BRACELET.get(), ModItems.COPPER_RING.get());
        craftRing(pWriter, ModItems.BRASS_RING.get(), ModItems.BRASS_INGOT.get());
        craftBracelet(pWriter, ModItems.BRASS_BRACELET.get(), ModItems.BRASS_RING.get());
        craftRing(pWriter, ModItems.NICROSIL_RING.get(), ModItems.NICROSIL_INGOT.get());
        craftBracelet(pWriter, ModItems.NICROSIL_BRACELET.get(), ModItems.NICROSIL_RING.get());

        // Alloys
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PEWTER_INGOT.get(), 2).requires(ModItems.TIN_INGOT.get()).requires(Items.COPPER_INGOT).unlockedBy(getHasName(ModItems.TIN_INGOT.get()), has(ModItems.TIN_INGOT.get())).save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BRASS_INGOT.get(), 2).requires(ModItems.ZINC_INGOT.get()).requires(Items.COPPER_INGOT).unlockedBy(getHasName(ModItems.ZINC_INGOT.get()), has(ModItems.ZINC_INGOT.get())).save(pWriter);

        // Nuggets
        nineBlockStorageRecipes(pWriter, RecipeCategory.MISC, ModItems.BENDALLOY_NUGGET.get(), RecipeCategory.MISC, ModItems.BENDALLOY_INGOT.get());
    }

    private void craftRing(Consumer<FinishedRecipe> pWriter, Item result, Item ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result)
                .pattern(" i ")
                .pattern("i i")
                .pattern(" i ")
                .define('i', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pWriter);
    }

    private void craftBracelet(Consumer<FinishedRecipe> pWriter, Item result, Item ingredient){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result)
                .requires(ingredient, 3)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, SimpleFeruchemy.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
