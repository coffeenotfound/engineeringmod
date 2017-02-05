package engineeringmod.miningrefined.common.block;

import java.util.List;
import engineeringmod.common.block.BlockBase;
import engineeringmod.miningrefined.ModMiningRefinedAddon;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMetalOreDeposit extends BlockBase {
	public static final String[] gradeNames = new String[]{"rich", "average", "lean"};
	
	public IIcon[] gradeIcons = new IIcon[3];
	
	protected String[] cachedItemBlockName = null;
	
	public BlockMetalOreDeposit(String parName, Material parMaterial) {
		super(parName, parMaterial);
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item parItem, CreativeTabs parCreativeTab, List parList) {
		for(int i = 0; i < 3; i++) {
			parList.add(new ItemStack(parItem, 1, i));
		}
	}
	
	@Override
	public void registerBlockIcons(IIconRegister parRegister) {
		gradeIcons[0] = parRegister.registerIcon(ModMiningRefinedAddon.getTexture(baseName + "_" + gradeNames[0]));
		gradeIcons[1] = parRegister.registerIcon(ModMiningRefinedAddon.getTexture(baseName + "_" + gradeNames[1]));
		gradeIcons[2] = parRegister.registerIcon(ModMiningRefinedAddon.getTexture(baseName + "_" + gradeNames[2]));
	}
	
	@Override
	public IIcon getIcon(int parSide, int parMeta) {
		if(parMeta > 2) return null;
		return gradeIcons[parMeta];
	}
	
	@Override
	public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_) {
		return this.getIcon(p_149673_5_, p_149673_1_.getBlockMetadata(p_149673_2_, p_149673_3_, p_149673_4_));
	}
	
	@Override
	public void onBlockClicked(World parWorld, int parX, int parY, int parZ, EntityPlayer parPlayer) {
		System.out.println(parWorld.getBlockMetadata(parX, parY, parZ));
	}
	
	public boolean canSilkHarvest() {
        return true;
    }
	
	public String getBlockItemUnlocalizedName(ItemStack parItemStack) {
		if(cachedItemBlockName == null) {
			cachedItemBlockName = new String[3];
			cachedItemBlockName[0] = getUnlocalizedName() + "." + gradeNames[0];
			cachedItemBlockName[1] = getUnlocalizedName() + "." + gradeNames[1];
			cachedItemBlockName[2] = getUnlocalizedName() + "." + gradeNames[2];
		}
		int meta = parItemStack.getItemDamage();
		if(meta < 0 || meta >= cachedItemBlockName.length) meta = 0;
		return cachedItemBlockName[meta];
	}
}
