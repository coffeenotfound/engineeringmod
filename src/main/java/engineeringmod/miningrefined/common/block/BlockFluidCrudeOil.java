package engineeringmod.miningrefined.common.block;

import engineeringmod.miningrefined.ModMiningRefinedAddon;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidCrudeOil extends BlockFluidFinite {
	public IIcon iconFlowing, iconStill;
	
	public BlockFluidCrudeOil(Fluid fluid, Material material) {
		super(fluid, material);
		this.setQuantaPerBlock(8);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister parRegister) {
		iconFlowing = parRegister.registerIcon(ModMiningRefinedAddon.getTexture("oil_flow"));
		iconStill = parRegister.registerIcon(ModMiningRefinedAddon.getTexture("oil_still"));
		
//		BlocksMiningRefined.fluidOil.setIcons(iconStill, iconFlowing);
	}
	
	@Override
	public IIcon getIcon(int parSide, int parMeta) {
		return (parSide == 1 ? iconStill : iconFlowing);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
//		System.out.println(world.getBlockMetadata(x, y, z));
//		world.setBlockMetadataWithNotify(x, y, z, 3, 2);
	}
	
//	@Override
//	public void onBlockAdded(World world, int x, int y, int z) {
//		world.setBlockMetadataWithNotify(x, y, z, world.rand.nextInt(8), 2);
//	}
	
//	@Override
//	public int onBlockPlaced(World world, int x, int y, int z, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
////		world.setBlockMetadataWithNotify(x, y, z, world.rand.nextInt(8), 2);
////		world.setBlockMetadataWithNotify(x, y, z, world.rand.nextInt(8), 2);
//		return 7;
//	}
}
