package engineeringmod.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import engineeringmod.ModEngineering;
import engineeringmod.common.achievement.AchievementsEngineering;
import engineeringmod.common.block.EngineeringBlocks;
import engineeringmod.common.entity.EntityDart;
import engineeringmod.common.item.EngineeringItems;
import engineeringmod.common.multipartstructure.MultipartStructures;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent parEvent) {
		// register blocks
		registerBlocks();
		
		// register items
		registerItems();
		
		// init creativetabs
		CreativeTabEngineeringMod.initCreativeTabs();
		
		// register entities
		registerEntities();
		
		// register achievements
		AchievementsEngineering.registerAchievements();
		
		// register mps
		MultipartStructures.registerBlocksItems();
		
//		GameRegistry.addSubstitutionAlias();
	}
	
	public void init(FMLInitializationEvent parEvent) {
//		// init oredict
//		OreDictionary.initVanillaEntries();
//		
//		OreDictionary.registerOre("oreIron", Blocks.bookshelf);
////		System.out.println(Arrays.toString(OreDictionary.getOreNames()));
//		
//		OreDictionary.rebakeMap();
	}
	
	protected void registerBlocks() {
		// register blocks
		EngineeringBlocks.registerBlocks();
	}
	
	protected void registerItems() {
		// register items
		EngineeringItems.registerItems();
	}
	
	protected void registerEntities() {
		// dart
		EntityRegistry.registerModEntity(EntityDart.class, "dart", 0, ModEngineering.instance, 64, 1, true);
	}
}
