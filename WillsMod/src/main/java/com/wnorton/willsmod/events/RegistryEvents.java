package com.wnorton.willsmod.events;

import org.apache.logging.log4j.Logger;

import com.wnorton.willsmod.Main;
import com.wnorton.willsmod.lists.BlockList;
import com.wnorton.willsmod.lists.ItemList;
import com.wnorton.willsmod.objects.blocks.CustomStairsBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
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
				ItemList.tutorial_stair = new BlockItem(BlockList.tutorial_stair, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.tutorial_stair.getRegistryName())
		);
	}
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				BlockList.tutorial_block = new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2, 6)).setRegistryName(location("tutorial_block")), 
				BlockList.tutorial_slab = new SlabBlock(Block.Properties.from(BlockList.tutorial_block)).setRegistryName(location("tutorial_slab")),
				BlockList.tutorial_stair = new CustomStairsBlock(BlockList.tutorial_block.getDefaultState(), Block.Properties.from(BlockList.tutorial_block)).setRegistryName(location("tutorial_stairs"))
		);
	}
	
	public static ResourceLocation location(String name) {
		return new ResourceLocation(MOD_ID, name);
	}
}
