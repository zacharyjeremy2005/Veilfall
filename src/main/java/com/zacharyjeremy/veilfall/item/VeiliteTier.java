package com.zacharyjeremy.veilfall.item;
import com.zacharyjeremy.veilfall.registry.ModItems;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
public final class VeiliteTier implements Tier {
 public static final VeiliteTier INSTANCE=new VeiliteTier();private VeiliteTier(){}
 public int getUses(){return 4060;} public float getSpeed(){return 12.5F;} public float getAttackDamageBonus(){return 5.0F;} public int getLevel(){return 5;} public int getEnchantmentValue(){return 22;}
 public Ingredient getRepairIngredient(){return Ingredient.of(ModItems.VEILITE_INGOT.get());}
}