package engineeringmod.miningrefined;

import engineeringmod.common.CreativeTabEngineeringMod;
import net.minecraft.init.Items;

public class CreativeTabMiningRefined {
	public static final CreativeTabEngineeringMod tabMiningRefined = new CreativeTabEngineeringMod("engineeringmod_miningrefined");
	
	public static final void initCreativeTabs() {
		tabMiningRefined.iconItem = Items.iron_pickaxe;
	}
}
