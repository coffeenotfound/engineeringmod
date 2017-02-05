package engineeringmod.miningrefined.common.item;

import engineeringmod.miningrefined.ModMiningRefinedAddon;
import net.minecraft.item.Item;

public class ItemRawOre extends Item {
	
	public ItemRawOre(String parBaseName) {
		this.setUnlocalizedName(parBaseName).setTextureName(ModMiningRefinedAddon.getTexture(parBaseName));
	}
}
