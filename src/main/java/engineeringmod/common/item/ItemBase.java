package engineeringmod.common.item;

import engineeringmod.ModEngineering;
import loretips.CachedTooltip;
import net.minecraft.item.Item;

public class ItemBase extends Item {
	public String baseName;
	public CachedTooltip tooltip;
	
	public ItemBase(String parBaseName) {
		super();
		baseName = parBaseName;
		
		this.setUnlocalizedName(baseName).setTextureName(ModEngineering.getTexture(baseName));
	}
}
