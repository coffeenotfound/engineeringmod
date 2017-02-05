package engineeringmod.common;

import engineeringmod.common.item.EngineeringItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabEngineeringMod extends CreativeTabs {
	public static final CreativeTabEngineeringMod tabEngineeringMod = new CreativeTabEngineeringMod("engineeringmod");
	
	public String label;
	public Item iconItem;
	
	public CreativeTabEngineeringMod(String parLabel) {
		super(parLabel);
		label = parLabel;
	}
	
	@Override
	public Item getTabIconItem() {
		return iconItem;
	}
	
	public static final void initCreativeTabs() {
		tabEngineeringMod.iconItem = EngineeringItems.engineersScrewdriver;
	}
}
