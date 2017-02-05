package engineeringmod.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import engineeringmod.common.CreativeTabEngineeringMod;
import net.minecraft.item.Item;

public final class EngineeringItems {
	public static final Item engineersBook = new ItemEngineersBook("engineers_book").setTextureName("book_normal").setCreativeTab(CreativeTabEngineeringMod.tabEngineeringMod);
	
	public static final Item engineersWrench = new ItemEngineersWrench("engineers_wrench").setCreativeTab(CreativeTabEngineeringMod.tabEngineeringMod);
	public static final Item engineersScrewdriver = new ItemEngineersScrewdriver("engineers_screwdriver").setCreativeTab(CreativeTabEngineeringMod.tabEngineeringMod);
	public static final Item engineersScrewdriverRare = new ItemEngineersScrewdriver("engineers_screwdriver_rare").setCreativeTab(CreativeTabEngineeringMod.tabEngineeringMod);
	public static final Item engineersScrewdriverRusty = new ItemEngineersScrewdriver("engineers_screwdriver_rusty").setCreativeTab(CreativeTabEngineeringMod.tabEngineeringMod);
	public static final Item engineersToolbox = new ItemEngineersToolbox("engineers_toolbox").setCreativeTab(CreativeTabEngineeringMod.tabEngineeringMod);
	
	public static final Item dart = new ItemDart("dart").setCreativeTab(CreativeTabEngineeringMod.tabEngineeringMod);
	
	public static final void registerItems() {
		// engineers books
		reg(engineersBook, "engineers_book");
		
		// tools
		reg(engineersWrench, "engineers_wrench");
		reg(engineersScrewdriver, "engineers_screwdriver");
		reg(engineersScrewdriverRare, "engineers_screwdriver_rare");
		reg(engineersScrewdriverRusty, "engineers_screwdriver_rusty");
		reg(engineersToolbox, "engineers_toolbox");
		
		// dart
		reg(dart, "dart");
	}
	
	private static final void reg(Item parItem, String parID) {
		GameRegistry.registerItem(parItem, parID);
	}
}
