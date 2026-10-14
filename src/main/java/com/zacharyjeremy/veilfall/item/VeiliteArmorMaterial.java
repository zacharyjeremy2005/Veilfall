package com.zacharyjeremy.veilfall.item;
import com.zacharyjeremy.veilfall.Veilfall;
import com.zacharyjeremy.veilfall.registry.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
public final class VeiliteArmorMaterial implements ArmorMaterial {
 public static final VeiliteArmorMaterial INSTANCE=new VeiliteArmorMaterial();private static final int[] DURABILITY={41,47,44,35};private static final int[] DEFENSE={4,12,9,5};private VeiliteArmorMaterial(){}
 public int getDurabilityForSlot(EquipmentSlot slot){return DURABILITY[slot.getIndex()];}
 public int getDefenseForSlot(EquipmentSlot slot){return DEFENSE[slot.getIndex()];}
 public int getEnchantmentValue(){return 22;} public SoundEvent getEquipSound(){return SoundEvents.ARMOR_EQUIP_NETHERITE;}
 public Ingredient getRepairIngredient(){return Ingredient.of(ModItems.VEILITE_INGOT.get());}
 public String getName(){return Veilfall.MOD_ID+":veilite";} public float getToughness(){return 5.0F;} public float getKnockbackResistance(){return 0.2F;}
}