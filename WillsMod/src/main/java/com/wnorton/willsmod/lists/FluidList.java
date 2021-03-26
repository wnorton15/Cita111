package com.wnorton.willsmod.lists;

import com.wnorton.willsmod.events.RegistryEvents;
import com.wnorton.willsmod.objects.fluids.FluidOil;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraftforge.event.RegistryEvent;

public class FluidList {
	public static FluidOil.Source oil = null;
	public static FluidOil.Flowing flowing_oil = null;
	
	public static class Tags {
		public static final Tag<Fluid> OIL = new FluidTags.Wrapper(RegistryEvents.location("oil"));
	}
}
