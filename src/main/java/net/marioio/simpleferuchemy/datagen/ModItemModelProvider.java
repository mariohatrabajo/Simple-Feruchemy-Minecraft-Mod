package net.marioio.simpleferuchemy.datagen;

import net.marioio.simpleferuchemy.SimpleFeruchemy;
import net.marioio.simpleferuchemy.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SimpleFeruchemy.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.STEEL_RING);
        simpleItem(ModItems.STEEL_BRACELET);
        simpleItem(ModItems.TIN_RING);
        simpleItem(ModItems.TIN_BRACELET);
        simpleItem(ModItems.PEWTER_RING);
        simpleItem(ModItems.PEWTER_BRACELET);
        simpleItem(ModItems.ZINC_RING);
        simpleItem(ModItems.ZINC_BRACELET);
        simpleItem(ModItems.GOLD_RING);
        simpleItem(ModItems.GOLD_BRACELET);
        simpleItem(ModItems.BENDALLOY_RING);
        simpleItem(ModItems.BENDALLOY_BRACELET);
        simpleItem(ModItems.COPPER_RING);
        simpleItem(ModItems.COPPER_BRACELET);
        simpleItem(ModItems.BRASS_RING);
        simpleItem(ModItems.BRASS_BRACELET);
        simpleItem(ModItems.NICROSIL_RING);
        simpleItem(ModItems.NICROSIL_BRACELET);
        // Raw Ores
        simpleItem(ModItems.RAW_TIN);
        simpleItem(ModItems.RAW_ZINC);
        simpleItem(ModItems.RAW_BENDALLOY);
        simpleItem(ModItems.RAW_NICROSIL);
        // Ingots
        simpleItem(ModItems.TIN_INGOT);
        simpleItem(ModItems.PEWTER_INGOT);
        simpleItem(ModItems.ZINC_INGOT);
        simpleItem(ModItems.BRASS_INGOT);
        simpleItem(ModItems.BENDALLOY_INGOT);
        simpleItem(ModItems.BENDALLOY_NUGGET);
        simpleItem(ModItems.NICROSIL_INGOT);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(), // Nombre del json
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SimpleFeruchemy.MOD_ID, "item/" + item.getId().getPath())); // Ruta a la textura
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(SimpleFeruchemy.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(SimpleFeruchemy.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(SimpleFeruchemy.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(SimpleFeruchemy.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SimpleFeruchemy.MOD_ID,"item/" + item.getId().getPath()));
    }
}
