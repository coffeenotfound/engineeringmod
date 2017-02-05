package engineeringmod.common.item;

import engineeringmod.common.entity.EntityDart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDart extends ItemBase {
	
	public ItemDart(String parBaseName) {
		super(parBaseName);
		this.setFull3D();
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack parItemStack, World parWorld, EntityPlayer parPlayer) {
		if(parItemStack != null && parItemStack.stackSize > 0) {
			parItemStack.stackSize--;
			
			// spawn entity
			if(!parWorld.isRemote) {
				EntityDart.throwDart(parWorld, parPlayer);
			}
		}
		return parItemStack;
	}
}
