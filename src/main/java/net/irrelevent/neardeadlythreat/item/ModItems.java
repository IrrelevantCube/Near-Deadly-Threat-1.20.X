package net.irrelevent.neardeadlythreat.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.irrelevent.neardeadlythreat.entity.ModEntities;
import net.irrelevent.neardeadlythreat.item.custom.ModArmorItem;
import net.irrelevent.neardeadlythreat.potion.ModPotions;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item DOMINITE = registerItem ("dominite", new Item(new FabricItemSettings()));
    public static final Item ALUMINUM_INGOT = registerItem ("aluminum_ingot", new Item(new FabricItemSettings()));
    public static final Item RAW_ALUMINUM = registerItem ("raw_aluminum", new Item(new FabricItemSettings()));
    public static final Item REFINED_DOMINITE = registerItem ("refined_dominite", new Item(new FabricItemSettings()));
    public static final Item DOMINITE_ISOTOPE = registerItem ("dominite_isotope", new Item(new FabricItemSettings()));
    public static final Item RAW_CHRODOMACH = registerItem ("raw_chrodomach", new Item(new FabricItemSettings ()));
    public static final Item REFINED_CHRODOMACH = registerItem ("refined_chrodomach", new Item(new FabricItemSettings ()));
    public static final Item TRIDYMITE_DUST = registerItem ("tridymite_dust", new Item(new FabricItemSettings ()));
    public static final Item BRACHYTUS_SHELL = registerItem ("brachytus_shell", new Item(new FabricItemSettings ()));
    public static final Item BRACHYTUS_STEEL = registerItem ("brachytus_steel", new Item(new FabricItemSettings ()));



    public static final Item CRAB_LEGS = registerItem ("crab_legs", new Item(new FabricItemSettings().food(ModFoodComponents.CRAB_LEGS)));
    public static final Item COOKED_CRAB_LEGS = registerItem ("cooked_crab_legs", new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_CRAB_LEGS)));

    public static final Item BRACHYTUS_SPAWN_EGG = registerItem ("brachytus_spawn_egg", new SpawnEggItem (ModEntities.BRACHYTUS, 0xff70388, 0x00008b, new FabricItemSettings ()));
    public static final Item BRACHYMITE_SPAWN_EGG = registerItem ("brachymite_spawn_egg", new SpawnEggItem (ModEntities.BRACHYMITE, 0x00008b,0xff70388 , new FabricItemSettings ()));

    public static final Item BRACHYTUS_STEEL_CHESTPLATE = registerItem ("brachytus_steel_chestplate",
            new ArmorItem (ModArmorMaterials.BRACHYTUS_STEEL, ArmorItem.Type.CHESTPLATE, new FabricItemSettings ()));
    public static final Item BRACHYTUS_STEEL_HELMET = registerItem ("brachytus_steel_helmet",
            new ArmorItem (ModArmorMaterials.BRACHYTUS_STEEL, ArmorItem.Type.HELMET, new FabricItemSettings ()));
    public static final Item BRACHYTUS_STEEL_LEGGINGS = registerItem ("brachytus_steel_leggings",
            new ArmorItem (ModArmorMaterials.BRACHYTUS_STEEL, ArmorItem.Type.LEGGINGS, new FabricItemSettings ()));
    public static final Item BRACHYTUS_STEEL_BOOTS = registerItem ("brachytus_steel_boots",
            new ArmorItem (ModArmorMaterials.BRACHYTUS_STEEL, ArmorItem.Type.BOOTS, new FabricItemSettings ()));
    public static final Item ALUMINATE_CHESTPLATE = registerItem ("aluminate_chestplate",
            new ModArmorItem (ModArmorMaterials.ALUMINATE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings ()));
    public static final Item ALUMINATE_HELMET = registerItem ("aluminate_helmet",
            new ModArmorItem (ModArmorMaterials.ALUMINATE, ArmorItem.Type.HELMET, new FabricItemSettings ()));
    public static final Item ALUMINATE_LEGGINGS = registerItem ("aluminate_leggings",
            new ModArmorItem (ModArmorMaterials.ALUMINATE, ArmorItem.Type.LEGGINGS, new FabricItemSettings ()));
    public static final Item ALUMINATE_BOOTS = registerItem ("aluminate_boots",
            new ModArmorItem (ModArmorMaterials.ALUMINATE, ArmorItem.Type.BOOTS, new FabricItemSettings ()));


    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {

    }
    private static Item registerItem(String name, Item item) {
        return Registry.register (Registries.ITEM, new Identifier (NearDeadlyThreat.MOD_ID, name), item);
    }


    public static void registerModItems() {
        NearDeadlyThreat.LOGGER.info("Registering Mod Items for " + NearDeadlyThreat.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent (ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTabItemGroup);
    }
}
