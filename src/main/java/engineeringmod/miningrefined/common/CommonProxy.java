package engineeringmod.miningrefined.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import engineeringmod.miningrefined.CreativeTabMiningRefined;
import engineeringmod.miningrefined.common.block.BlocksMiningRefined;
import engineeringmod.miningrefined.common.item.ItemsMiningRefined;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent parEvent) {
		// register blocks
		BlocksMiningRefined.registerBlocks();
		
		// register items
		ItemsMiningRefined.registerItems();
		
		// register recipes
		
		// init creative tabs
		CreativeTabMiningRefined.initCreativeTabs();
	}
	
	public void init(FMLInitializationEvent parEvent) {
		// override vanilla objects
		overrideObjects();
	}
	
	protected void overrideObjects() {
		
	}
}
