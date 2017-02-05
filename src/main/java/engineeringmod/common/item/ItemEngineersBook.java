package engineeringmod.common.item;

import engineeringmod.client.gui.GuiEngineersBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEngineersBook extends ItemBase {
	
	public ItemEngineersBook(String parBaseName) {
		super(parBaseName);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack parItemStack, World parWorld, EntityPlayer parPlayer) {
		if(parWorld.isRemote && parPlayer instanceof EntityPlayerSP) {
//			EntityPlayerSP playerSP = (EntityPlayerSP)parPlayer;
			
			System.out.println("ItemEngineersToolbox.onItemRightClick()");
			
			// open book gui
			Minecraft mc = Minecraft.getMinecraft();
			mc.displayGuiScreen(new GuiEngineersBook(parItemStack, parPlayer));
		}
		return parItemStack;
	}
}
