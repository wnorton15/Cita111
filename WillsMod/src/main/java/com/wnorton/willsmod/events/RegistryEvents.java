package com.wnorton.willsmod.events;

import org.apache.logging.log4j.Logger;

import com.wnorton.willsmod.Main;
import com.wnorton.willsmod.lists.BlockList;
import com.wnorton.willsmod.lists.FluidList;
import com.wnorton.willsmod.lists.FoodList;
import com.wnorton.willsmod.lists.ItemList;
import com.wnorton.willsmod.objects.blocks.CustomStairsBlock;
import com.wnorton.willsmod.objects.fluids.FluidOil;
import com.wnorton.willsmod.objects.fluids.FluidOil.Flowing;
import com.wnorton.willsmod.objects.fluids.FluidOil.Source;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
	public static final Logger LOGGER = Main.LOGGER;
	public static final String MOD_ID = Main.MOD_ID;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				ItemList.tutorial_item = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("tutorial_item")),
				ItemList.tutorial_block = new BlockItem(BlockList.tutorial_block, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.tutorial_block.getRegistryName()), 
				ItemList.tutorial_slab = new BlockItem(BlockList.tutorial_slab, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.tutorial_slab.getRegistryName()), 
				ItemList.tutorial_stair = new BlockItem(BlockList.tutorial_stair, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.tutorial_stair.getRegistryName()),
				ItemList.tutorial_wall = new BlockItem(BlockList.tutorial_wall, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.tutorial_wall.getRegistryName()),
				ItemList.oil_bucket = new BucketItem(() -> FluidList.oil, new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)).setRegistryName(location("oil_bucket")),
				ItemList.pepper = new Item(new Item.Properties().group(ItemGroup.MISC).food(FoodList.PEPPER)).setRegistryName(location("pepper"))
		);
	}
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				BlockList.tutorial_block = new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2, 6)).setRegistryName(location("tutorial_block")), 
				BlockList.tutorial_slab = new SlabBlock(Block.Properties.from(BlockList.tutorial_block)).setRegistryName(location("tutorial_slab")),
				BlockList.tutorial_stair = new CustomStairsBlock(BlockList.tutorial_block.getDefaultState(), Block.Properties.from(BlockList.tutorial_block)).setRegistryName(location("tutorial_stairs")),
				BlockList.tutorial_wall = new WallBlock(Block.Properties.from(BlockList.tutorial_block)).setRegistryName(location("tutorial_wall")),
				BlockList.oil = new FlowingFluidBlock(() -> FluidList.oil, Block.Properties.create(Material.WATER).doesNotBlockMovement().noDrops()).setRegistryName(location("oil"))
				
		);
	}
	
	@SubscribeEvent
	public static void registerFluids(final RegistryEvent.Register<Fluid> event) {
		event.getRegistry().registerAll(
				FluidList.flowing_oil = (Flowing) new FluidOil.Flowing().setRegistryName(location("flowing_oil")),
				FluidList.oil = (Source) new FluidOil.Source().setRegistryName(location("oil"))
		);
	}
	
	public static ResourceLocation location(String name) {
		return new ResourceLocation(MOD_ID, name);
	}
}
