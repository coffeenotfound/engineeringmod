package engineeringmod.miningrefined.common.item;

import engineeringmod.miningrefined.ModMiningRefinedAddon;
import net.minecraft.item.Item;

public class ItemRock extends Item {
	
	public ItemRock(String parBaseName) {
		this.setUnlocalizedName(parBaseName).setTextureName(ModMiningRefinedAddon.getTexture(parBaseName));
	}
}
