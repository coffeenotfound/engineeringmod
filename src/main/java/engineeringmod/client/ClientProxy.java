package engineeringmod.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import engineeringmod.ModEngineering;
import engineeringmod.client.render.entity.RenderDart;
import engineeringmod.client.render.item.ItemRendererScrewdriver;
import engineeringmod.client.render.tile.SpecialMPSPartBlockRenderer;
import engineeringmod.common.CommonProxy;
import engineeringmod.common.entity.EntityDart;
import engineeringmod.common.item.EngineeringItems;
import engineeringmod.common.multipartstructure.tile.TileEntityMPSPartBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent parEvent) {
		super.preInit(parEvent);
		
		// register tile renderers
		registerTileRenderer();
		
		// register entity renderer
		registerEntityRenderer();
		
		// register item renderer
		registerItemRenderer();
		
		// DEBUG:
		NetworkRegistry.INSTANCE.registerGuiHandler(ModEngineering.instance, new IGuiHandler() {
			@Override
			public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
				return null;
			}
			
			@Override
			public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
				return null;
			}
		});
	}
	
	protected void registerTileRenderer() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMPSPartBlock.class, new SpecialMPSPartBlockRenderer());
	}
	
	protected void registerEntityRenderer() {
		// dart
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart());
	}
	
	protected void registerItemRenderer() {
		// screwdriver
		MinecraftForgeClient.registerItemRenderer(EngineeringItems.engineersScrewdriver, new ItemRendererScrewdriver());
	}
}
