package engineeringmod.client.gui;

import engineeringmod.common.item.EngineeringItems;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GuiEngineersBook extends GuiScreenBook {
	public ItemStack itemStack;
	public EntityPlayer player;
	
	public GuiEngineersBook(ItemStack parItemStack, EntityPlayer parPlayer) {
		super(parPlayer, new ItemStack(EngineeringItems.engineersBook), true);
		itemStack = parItemStack;
		player = parPlayer;
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
