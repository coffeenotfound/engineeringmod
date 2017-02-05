package engineeringmod.common.block;

import engineeringmod.common.tileentity.TestTileEntity;
import engineeringmod.common.util.VectorUtils;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TestBlock extends BlockBase implements ITileEntityProvider {
	
	public TestBlock(String parName) {
		super(parName, Material.rock);
	}
	
	@Override
	public TileEntity createNewTileEntity(World parWorld, int parMeta) {
		return new TestTileEntity();
	}
	
	@Override
	public TileEntity createTileEntity(World parWorld, int parMeta) {
		return createNewTileEntity(parWorld, parMeta);
	}
	
	@Override
	public boolean onBlockActivated(World parWorld, int parX, int parY, int parZ, EntityPlayer parPlayer, int parFace, float parHitX, float parHitY, float parHitZ) {
		// on right click
		
		if(!parWorld.isRemote) {
			Vec3 eyeVec = Vec3.createVectorHelper(parPlayer.posX, parPlayer.posY + parPlayer.eyeHeight, parPlayer.posZ);
			
//			EntityArrow arrow = new EntityArrow(parWorld, eyeVec.xCoord + lookVec.xCoord, eyeVec.yCoord + lookVec.yCoord, eyeVec.zCoord + lookVec.zCoord);
//			arrow.setVelocity(lookVec.xCoord * 4f, lookVec.yCoord * 4f, lookVec.zCoord * 4f);
//			parWorld.spawnEntityInWorld(arrow);
			
			Vec3 hitVec = Vec3.createVectorHelper(parHitX, parHitY, parHitZ);
			Vec3 hitDir = VectorUtils.normalizeVec(Vec3.createVectorHelper(hitVec.xCoord - eyeVec.xCoord, hitVec.yCoord - eyeVec.yCoord, hitVec.zCoord - eyeVec.zCoord));
			
			return onPokedMPS(parWorld, parX, parY, parZ, parPlayer, hitVec, hitDir);
		}
		return true;
	}
	
	@Override
	public void onBlockClicked(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_) {
		// on left click
	}
	
	public boolean onPokedMPS(World parWorld, int parX, int parY, int parZ, EntityPlayer parPlayer, Vec3 parHitVec, Vec3 parHitDir) {
		return true;
	}
}
