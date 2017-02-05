package engineeringmod.miningrefined;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import engineeringmod.miningrefined.common.CommonProxy;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = ModMiningRefinedAddon.MODID, version = ModMiningRefinedAddon.MOD_VERSION, dependencies = "after:engineeringmod", canBeDeactivated = false, name = ModMiningRefinedAddon.MOD_NAME)
public final class ModMiningRefinedAddon {
	public static final String MODID = "engineeringmod_miningrefined";
	public static final String MOD_VERSION = "0.0_indev";
	public static final String MOD_NAME = "Unnamed Engineering Mod: Mining Refined";
	
	@Instance(ModMiningRefinedAddon.MODID)
	public static ModMiningRefinedAddon instance;
	
	@SidedProxy(modId = ModMiningRefinedAddon.MODID, serverSide = "engineeringmod.miningrefined.common.CommonProxy", clientSide = "engineeringmod.miningrefined.client.ClientProxy")
	public static CommonProxy proxy;
	
	/** The config */
	public static Configuration CONFIG;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent parEvent) {
		// load config file
		// TODO:
		
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
