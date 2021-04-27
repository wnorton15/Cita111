package com.wnorton.willsmod.lists;

import net.minecraft.item.Food;

public class FoodList {

	public static final Food PEPPER = (new Food.Builder().hunger(6).saturation(0.2F).build());
	public static final Food ICE_BERRY = (new Food.Builder().hunger(4).saturation(0.5f).setAlwaysEdible()).build();
}
