package engineeringmod.miningrefined.common.item;

import engineeringmod.miningrefined.common.block.BlockMetalOreDeposit;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockMetalOreDeposit extends ItemBlockWithMetadata {
	protected BlockMetalOreDeposit block;
	
	public ItemBlockMetalOreDeposit(Block parBlock) {
		super(parBlock, parBlock);
		block = (BlockMetalOreDeposit)parBlock;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack parItemStack) {
		return block.getBlockItemUnlocalizedName(parItemStack);
	}
}
