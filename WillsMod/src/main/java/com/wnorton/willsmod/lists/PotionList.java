package com.wnorton.willsmod.lists;

import java.lang.reflect.Method;

import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class PotionList {
	public static Potion more_health_potion = null;
	public static Effect more_health_effect = null;
	
	private static Method brewing_mixes;
	
	private static void addMix(Potion start, Item ingredient, Potion result) {
		if(brewing_mixes == null) {
			brewing_mixes = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "addMix", Potion.class, Item.class, Potion.class);
			brewing_mixes.setAccessible(true);
		}
		try {
			brewing_mixes.invoke(null, start, ingredient, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addBrewingRecipes() {
		addMix(Potions.AWKWARD, ItemList.ice_berry, PotionList.more_health_potion);
	}
	
	public static class MoreHealthEffect extends Effect {

		public MoreHealthEffect(EffectType typeIn, int liquidColorIn) {
			super(typeIn, liquidColorIn);
		}
		
	}
	
}
