package net.marioio.simpleferuchemy.item;

import net.marioio.simpleferuchemy.SimpleFeruchemy;
import net.marioio.simpleferuchemy.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SimpleFeruchemy.MOD_ID);

    // ADD ITEMS
    public static final RegistryObject<Item> STEEL_RING = ITEMS.register("steel_ring",
            () -> new SteelRing(new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> STEEL_BRACELET = ITEMS.register("steel_bracelet",
            () -> new SteelRing(new Item.Properties().durability(4800)));
    public static final RegistryObject<Item> TIN_RING = ITEMS.register("tin_ring",
            () -> new TinRing(new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> TIN_BRACELET = ITEMS.register("tin_bracelet",
            () -> new TinRing(new Item.Properties().durability(4800)));
    public static final RegistryObject<Item> PEWTER_RING = ITEMS.register("pewter_ring",
            () -> new PewterRing(new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> PEWTER_BRACELET = ITEMS.register("pewter_bracelet",
            () -> new PewterRing(new Item.Properties().durability(4800)));
    public static final RegistryObject<Item> ZINC_RING = ITEMS.register("zinc_ring",
            () -> new ZincRing(new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> ZINC_BRACELET = ITEMS.register("zinc_bracelet",
            () -> new ZincRing(new Item.Properties().durability(4800)));
    public static final RegistryObject<Item> GOLD_RING = ITEMS.register("gold_ring",
            () -> new GoldRing(new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> GOLD_BRACELET = ITEMS.register("gold_bracelet",
            () -> new GoldRing(new Item.Properties().durability(4800)));
    public static final RegistryObject<Item> BENDALLOY_RING = ITEMS.register("bendalloy_ring",
            () -> new BendalloyRing(new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> BENDALLOY_BRACELET = ITEMS.register("bendalloy_bracelet",
            () -> new BendalloyRing(new Item.Properties().durability(4800)));
    public static final RegistryObject<Item> COPPER_RING = ITEMS.register("copper_ring",
            () -> new CopperRing(new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> COPPER_BRACELET = ITEMS.register("copper_bracelet",
            () -> new CopperRing(new Item.Properties().durability(4800)));
    public static final RegistryObject<Item> BRASS_RING = ITEMS.register("brass_ring",
            () -> new BrassRing(new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> BRASS_BRACELET = ITEMS.register("brass_bracelet",
            () -> new BrassRing(new Item.Properties().durability(4800)));
    public static final RegistryObject<Item> NICROSIL_RING = ITEMS.register("nicrosil_ring",
            () -> new NicrosilRing(new Item.Properties().durability(1200)));
    public static final RegistryObject<Item> NICROSIL_BRACELET = ITEMS.register("nicrosil_bracelet",
            () -> new NicrosilRing(new Item.Properties().durability(4800)));
    // Raw ores
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ZINC = ITEMS.register("raw_zinc",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_BENDALLOY = ITEMS.register("raw_bendalloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_NICROSIL = ITEMS.register("raw_nicrosil",
            () -> new Item(new Item.Properties()));
    // Ingots
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PEWTER_INGOT = ITEMS.register("pewter_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRASS_INGOT = ITEMS.register("brass_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BENDALLOY_INGOT = ITEMS.register("bendalloy_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BENDALLOY_NUGGET = ITEMS.register("bendalloy_nugget",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NICROSIL_INGOT = ITEMS.register("nicrosil_ingot",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
