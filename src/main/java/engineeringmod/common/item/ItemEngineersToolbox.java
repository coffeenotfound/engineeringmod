package engineeringmod.common.item;

import engineeringmod.ModEngineering;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemEngineersToolbox extends ItemBase {
	public IIcon iconOpen, iconClosed;
	
	public ItemEngineersToolbox(String parBaseName) {
		super(parBaseName);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack parItemStack, World parWorld, EntityPlayer parPlayer) {		
		/*
		if(!parWorld.isRemote) {
//			System.out.println("ItemEngineersToolbox.onItemRightClick()");
//			parPlayer.openGui(mod, modGuiId, world, x, y, z);
			
//			Minecraft.getMinecraft().thePlayer.openGui(mod, modGuiId, world, x, y, z);
		}
		*/
		return parItemStack;
	}
	
	@Override
	public void registerIcons(IIconRegister parRegister) {
		iconClosed = parRegister.registerIcon(ModEngineering.getTexture("engineers_toolbox_closed"));
		iconOpen = parRegister.registerIcon(ModEngineering.getTexture("engineers_toolbox_open"));
	}
	
	@Override
	public IIcon getIconFromDamage(int parDamage) {
//		return (parDamage == 1 ? iconOpen : iconClosed);
		return iconClosed;
	}
}
