package engineeringmod.common.multipartstructure.block;

import engineeringmod.common.block.BlockBase;
import engineeringmod.common.multipartstructure.tile.TileEntityMPSPartBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockMPSPartBlock extends BlockBase implements ITileEntityProvider {
	
	public BlockMPSPartBlock(String parBaseName, Material parMaterial) {
		super(parBaseName, parMaterial);
	}
	
	@Override
	public void onBlockPlacedBy(World parWorld, int parX, int parY, int parZ, EntityLivingBase parPlayer, ItemStack parItemStack) {
		int l = determineOrientation(parWorld, parX, parY, parZ, parPlayer);
        parWorld.setBlockMetadataWithNotify(parX, parY, parZ, l, 2);
        
        if(!parWorld.isRemote) {
//            this.updatePistonState(parWorld, parX, parY, parZ);
        }
	}
	
	@Override
	public void onBlockAdded(World parWorld, int parX, int parY, int parZ) {
		
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World parWorld, int parX, int parY, int parZ, int parMeta) {
		
	}
	
	@Override
	public void onBlockPreDestroy(World parWorld, int parX, int parY, int parZ, int parMeta) {
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World parWorld, int parMeta) {
		return new TileEntityMPSPartBlock();
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return createNewTileEntity(world, metadata);
	}
	
	@Override
	public boolean isOpaqueCube() {
	    return false;
	}
	
	@Override
	public int getRenderType() {
	    return -1;
	}
	
	public static int determineOrientation(World parWorld, int parX, int parY, int parZ, EntityLivingBase parPlayer) {
		if (MathHelper.abs((float)parPlayer.posX - (float)parX) < 2.0F && MathHelper.abs((float)parPlayer.posZ - (float)parZ) < 2.0F) {
			double d0 = parPlayer.posY + 1.82D - (double)parPlayer.yOffset;
			
			if (d0 - (double)parY > 2.0D) {
				return 1;
			}
			
			if ((double)parY - d0 > 0.0D) {
				return 0;
			}
		}
		
		int l = MathHelper.floor_double((double)(parPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
	}
}
