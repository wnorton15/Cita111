package com.wnorton.willsmod.events;

import org.apache.logging.log4j.Logger;

import com.wnorton.willsmod.Main;
import com.wnorton.willsmod.lists.BlockList;
import com.wnorton.willsmod.lists.FluidList;
import com.wnorton.willsmod.lists.FoodList;
import com.wnorton.willsmod.lists.ItemList;
import com.wnorton.willsmod.lists.PotionList;
import com.wnorton.willsmod.objects.blocks.CustomStairsBlock;
import com.wnorton.willsmod.objects.blocks.IceBerryBush;
import com.wnorton.willsmod.objects.blocks.PepperCropBlock;
import com.wnorton.willsmod.objects.fluids.FluidOil;
import com.wnorton.willsmod.objects.fluids.FluidOil.Flowing;
import com.wnorton.willsmod.objects.fluids.FluidOil.Source;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
	public static final Logger LOGGER = Main.LOGGER;
	public static final String MOD_ID = Main.MOD_ID;
	public static final ItemGroup TUTORIAL_GROUP = Main.TUTORIAL_TAB;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				ItemList.tutorial_item = new Item(new Item.Properties().group(TUTORIAL_GROUP)).setRegistryName(location("tutorial_item")),
				ItemList.tutorial_block = new BlockItem(BlockList.tutorial_block, new Item.Properties().group(TUTORIAL_GROUP)).setRegistryName(BlockList.tutorial_block.getRegistryName()), 
				ItemList.tutorial_slab = new BlockItem(BlockList.tutorial_slab, new Item.Properties().group(TUTORIAL_GROUP)).setRegistryName(BlockList.tutorial_slab.getRegistryName()), 
				ItemList.tutorial_stair = new BlockItem(BlockList.tutorial_stair, new Item.Properties().group(TUTORIAL_GROUP)).setRegistryName(BlockList.tutorial_stair.getRegistryName()),
				ItemList.tutorial_wall = new BlockItem(BlockList.tutorial_wall, new Item.Properties().group(TUTORIAL_GROUP)).setRegistryName(BlockList.tutorial_wall.getRegistryName()),
				ItemList.oil_bucket = new BucketItem(() -> FluidList.oil, new Item.Properties().group(TUTORIAL_GROUP).maxStackSize(1)).setRegistryName(location("oil_bucket")),
				ItemList.pepper = new BlockItem(BlockList.pepper_crop, new Item.Properties().group(TUTORIAL_GROUP).food(FoodList.PEPPER)).setRegistryName(location("pepper")),
				ItemList.ice_berry = new BlockItem(BlockList.ice_berry_bush, new Item.Properties().group(TUTORIAL_GROUP).food(FoodList.ICE_BERRY)).setRegistryName(BlockList.ice_berry_bush.getRegistryName())
		);
	}
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				BlockList.tutorial_block = new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2, 6)).setRegistryName(location("tutorial_block")), 
				BlockList.tutorial_slab = new SlabBlock(Block.Properties.from(BlockList.tutorial_block)).setRegistryName(location("tutorial_slab")),
				BlockList.tutorial_stair = new CustomStairsBlock(BlockList.tutorial_block.getDefaultState(), Block.Properties.from(BlockList.tutorial_block)).setRegistryName(location("tutorial_stairs")),
				BlockList.tutorial_wall = new WallBlock(Block.Properties.from(BlockList.tutorial_block)).setRegistryName(location("tutorial_wall")),
				BlockList.oil = new FlowingFluidBlock(() -> FluidList.oil, Block.Properties.create(Material.WATER).doesNotBlockMovement().noDrops()).setRegistryName(location("oil")),
				BlockList.pepper_crop = new PepperCropBlock(Block.Properties.create(Material.PLANTS).hardnessAndResistance(0f).doesNotBlockMovement().tickRandomly().sound(SoundType.PLANT)).setRegistryName("pepper_crop"),
				BlockList.ice_berry_bush = new IceBerryBush(Block.Properties.create(Material.PLANTS).hardnessAndResistance(0).doesNotBlockMovement().tickRandomly().sound(SoundType.PLANT)).setRegistryName(location("ice_berry_bush"))
		);
	}
	
	@SubscribeEvent
	public static void registerFluids(final RegistryEvent.Register<Fluid> event) {
		event.getRegistry().registerAll(
				FluidList.flowing_oil = (Flowing) new FluidOil.Flowing().setRegistryName(location("flowing_oil")),
				FluidList.oil = (Source) new FluidOil.Source().setRegistryName(location("oil"))
		);
	}
	
	@SubscribeEvent
	public static void registerPotions(final RegistryEvent.Register<Potion> event) {
		event.getRegistry().registerAll(
			PotionList.more_health_potion = new Potion(new EffectInstance(PotionList.more_health_effect, 3600)).setRegistryName(location("more_health"))
		);
	}
	
	@SubscribeEvent
	public static void registerEffects(final RegistryEvent.Register<Effect> event) {
		event.getRegistry().registerAll(
			PotionList.more_health_effect = new PotionList.MoreHealthEffect(EffectType.BENEFICIAL, 0xf7ec19).addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "123ADE48-3DBA-7392-1583-908C731B2630" , (double)1, AttributeModifier.Operation.MULTIPLY_TOTAL).setRegistryName(location("more_health"))
		);
	}
	
	public static ResourceLocation location(String name) {
		return new ResourceLocation(MOD_ID, name);
	}
}
