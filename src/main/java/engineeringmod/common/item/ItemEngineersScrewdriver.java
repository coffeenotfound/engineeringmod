package engineeringmod.common.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemEngineersScrewdriver extends ItemEngineersTool {
	
	public ItemEngineersScrewdriver(String parBaseName) {
		super(parBaseName);
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack parItemStack, EntityPlayer parPlayer, List parTooltip, boolean parAdvancedTooltips) {
//		parTooltip.add("§7Made in the '80s§r");
//		parTooltip.add("§7Old but loyal§r");
		parTooltip.add("Some tooltip");
	}
}
