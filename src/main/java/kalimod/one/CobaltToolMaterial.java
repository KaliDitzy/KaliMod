package kalimod.one;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CobaltToolMaterial implements ToolMaterial {
	
    @Override
    public int getDurability() {
        return 200;
    }
    
    @Override
    public float getMiningSpeedMultiplier() {
        return 7F;
    }
    
    @Override
    public float getAttackDamage() {
        return 3.0F;
    }
    
    @Override
    public int getMiningLevel() {
        return 2;
    }
    
    @Override
    public int getEnchantability() {
        return 14;
    }
    
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(KaliMod.REFINED_COBALT);
    }
    
    public static final CobaltToolMaterial INSTANCE = new CobaltToolMaterial();
}