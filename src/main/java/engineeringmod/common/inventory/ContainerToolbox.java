package engineeringmod.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerToolbox extends Container {
	
	@Override
	public boolean canInteractWith(EntityPlayer parPlayer) {
		return true;
	}
}
