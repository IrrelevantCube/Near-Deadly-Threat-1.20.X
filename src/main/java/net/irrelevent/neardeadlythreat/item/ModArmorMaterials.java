package net.irrelevent.neardeadlythreat.item;

import net.irrelevent.neardeadlythreat.NearDeadlyThreat;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial, ModArmorMaterial {
    BRACHYTUS_STEEL("brachytus_steel", 30, new int[] {2, 20, 5, 1}, 20,
            SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 5f, 0.0f, 1.0f, () -> Ingredient.ofItems (ModItems.BRACHYTUS_STEEL)),
    ALUMINATE("aluminate", 1, new int[] {1, 1, 1, 1}, 50,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1000000000000000000000f, 100000000000000000000000000000000000000.0f, -100000000.0f, () -> Ingredient.ofItems (ModItems.ALUMINUM_INGOT, Items.IRON_INGOT))

    ;

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float blastDamageReduction;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound,
                      float toughness, float blastDamageReduction, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.blastDamageReduction = blastDamageReduction;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal ()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal ()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get ();
    }

    @Override
    public String getName() {
        return NearDeadlyThreat.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }
    @Override
    public float getBlastDamageReduction() {
        return this.blastDamageReduction;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
