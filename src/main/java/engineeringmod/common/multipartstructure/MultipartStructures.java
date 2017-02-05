package engineeringmod.common.multipartstructure;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import engineeringmod.common.multipartstructure.block.BlockMPSPartBlock;
import engineeringmod.common.multipartstructure.render.TileEntityRendererMPSPartBlock;
import engineeringmod.common.multipartstructure.tile.TileEntityMPSPartBlock;
import net.minecraft.block.material.Material;

public final class MultipartStructures {
	public static final BlockMPSPartBlock blockMPSPartBlock = new BlockMPSPartBlock("mpspartblock", Material.wood);
	
	public static void registerBlocksItems() {
		GameRegistry.registerBlock(blockMPSPartBlock, "mpspartblock");
//		GameRegistry.registerTileEntity(TileEntityMPSPartBlock.class, "tempspartblock");
		
//		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMPSPartBlock.class, new TileEntityRendererMPSPartBlock());
		ClientRegistry.registerTileEntity(TileEntityMPSPartBlock.class, "tempspartblock", new TileEntityRendererMPSPartBlock());
	}
	
	public static void registerStructure(Object[] parTemplate, Class<? extends StructureMaster> parMaster) {
		
	}
}
