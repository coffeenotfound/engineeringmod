package engineeringmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import engineeringmod.common.CommonProxy;
import loretips.Loretips;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = ModEngineering.MODID, version = ModEngineering.MOD_VERSION, dependencies = "", canBeDeactivated = false, guiFactory = "engineeringmod.SettingsGuiFactory", name = ModEngineering.MOD_NAME)
public final class ModEngineering {
	public static final String MODID = "engineeringmod";
	public static final String MOD_VERSION = "0.0_indev";
	public static final String MOD_NAME = "Unnamed Engineering Mod / Complete Overhaul Thing / Mod With A Long Working Title";
	
	@Instance(ModEngineering.MODID)
	public static ModEngineering instance;
	
	@SidedProxy(modId = ModEngineering.MODID, serverSide = "engineeringmod.common.CommonProxy", clientSide = "engineeringmod.client.ClientProxy")
	public static CommonProxy proxy;
	
	/** The config */
	public static Configuration CONFIG;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent parEvent) {
		// load config file
		// TODO:
		
		// init loretips
		Loretips.init();
		
		// call proxy
		proxy.preInit(parEvent);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent parEvent) {
		// call proxy
		proxy.init(parEvent);
	}
	
	public static final String getTexture(String parTexture) {
		return (MODID + ":" + parTexture);
	}
}
