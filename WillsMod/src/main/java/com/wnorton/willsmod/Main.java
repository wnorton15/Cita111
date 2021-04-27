package com.wnorton.willsmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wnorton.willsmod.lists.BlockList;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MOD_ID)
public class Main {
	public static Main instance;
	public static final String MOD_ID = "willsmod";
	public static final Logger LOGGER = LogManager.getLogger();
	
	public Main() {
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::Setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::ClientSetup);
	}
	
	private void Setup(final FMLCommonSetupEvent event) {
		
	}
	
	private void ClientSetup(final FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(BlockList.pepper_crop, RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockList.ice_berry_bush, RenderType.getCutout());
	}
	
	public void OnServerStarting(FMLServerStartingEvent event) {
		
	}
}
