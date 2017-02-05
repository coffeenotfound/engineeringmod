package engineeringmod.common.item;

import engineeringmod.common.CreativeTabEngineeringMod;

public class ItemEngineersTool extends ItemBase {
	
	public ItemEngineersTool(String parBaseName) {
		super(parBaseName);
		
		this.setMaxStackSize(1).setFull3D().setCreativeTab(CreativeTabEngineeringMod.tabEngineeringMod);
	}
}
