package com.zacharyjeremy.veilfall.item;
import com.zacharyjeremy.veilfall.registry.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
public final class VeiliteArmorMaterial implements ArmorMaterial {
 public static final VeiliteArmorMaterial INSTANCE=new VeiliteArmorMaterial();
 private static final int[] DURABILITY={48,55,52,40};
 private static final int[] DEFENSE={5,10,13,5};
 private VeiliteArmorMaterial(){}
 public int getDurabilityForType(ArmorItem.Type type){return DURABILITY[type.ordinal()];}
 public int getDefenseForType(ArmorItem.Type type){return DEFENSE[type.ordinal()];}
 public int getEnchantmentValue(){return 28;}
 public SoundEvent getEquipSound(){return SoundEvents.ARMOR_EQUIP_NETHERITE;}
 public Ingredient getRepairIngredient(){return Ingredient.of(ModItems.VEILITE_INGOT.get());}
 public String getName(){return "veilfall:veilite";}
 public float getToughness(){return 6.0F;}
 public float getKnockbackResistance(){return 0.25F;}
}