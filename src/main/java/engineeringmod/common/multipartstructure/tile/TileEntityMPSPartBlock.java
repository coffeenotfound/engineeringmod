package engineeringmod.common.multipartstructure.tile;

import engineeringmod.common.multipartstructure.StructureMaster;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMPSPartBlock extends TileEntity {
	public ItemStack[] partSlots;
	
	/** a reference to the master */
	public StructureMaster master;
	
	@Override
	public void writeToNBT(NBTTagCompound parCompound) {
		super.writeToNBT(parCompound);
		
		// write master ref
		parCompound.setBoolean("hasmaster", master != null);
		if(master != null) {
			parCompound.setShort("masteroffx", (short)(master.containingTileEntity.xCoord - xCoord));
			parCompound.setShort("masteroffy", (short)(master.containingTileEntity.yCoord - yCoord));
			parCompound.setShort("masteroffz", (short)(master.containingTileEntity.zCoord - zCoord));
		}
		
		// write part slots
		if(partSlots != null) {
			for(int i = 0; i < partSlots.length; i++) {
				if(partSlots[i] != null) {
					NBTTagCompound slotCompound = partSlots[i].writeToNBT(new NBTTagCompound());
					parCompound.setTag("partslot" + i, slotCompound);
				}
				else {
					parCompound.removeTag("partslot" + i);
				}
			}
		}
	}
}
