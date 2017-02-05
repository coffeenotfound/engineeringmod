package engineeringmod.miningrefined.common.item;

import engineeringmod.miningrefined.common.block.BlockFluidCrudeOil;
import engineeringmod.miningrefined.common.block.BlocksMiningRefined;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBucket;
import net.minecraft.world.World;

public class ItemBucketCrudeOil extends ItemBucket {
	
	public ItemBucketCrudeOil(Block p_i45331_1_) {
		super(p_i45331_1_);
	}
	
	@Override
	public boolean tryPlaceContainedLiquid(World p_77875_1_, int p_77875_2_, int p_77875_3_, int p_77875_4_) {
		Material material = p_77875_1_.getBlock(p_77875_2_, p_77875_3_, p_77875_4_).getMaterial();
		boolean flag = !material.isSolid();
		
		if(!p_77875_1_.isAirBlock(p_77875_2_, p_77875_3_, p_77875_4_) && !flag) {
			return false;
		}
		else {
			if (!p_77875_1_.isRemote && flag && !material.isLiquid()) {
				p_77875_1_.func_147480_a(p_77875_2_, p_77875_3_, p_77875_4_, true);
			}
			
//			p_77875_1_.setBlock(p_77875_2_, p_77875_3_, p_77875_4_, this., 0, 3);
			p_77875_1_.setBlock(p_77875_2_, p_77875_3_, p_77875_4_, BlocksMiningRefined.flowingOil, ((BlockFluidCrudeOil)BlocksMiningRefined.flowingOil).getMaxRenderHeightMeta(), 3);
//			FluidOil
			return true;
		}
	}
}
