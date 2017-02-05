package engineeringmod.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import engineeringmod.common.CreativeTabEngineeringMod;
import engineeringmod.common.tileentity.TestTileEntity;

public final class EngineeringBlocks {
	public static final TestBlock testBlock = (TestBlock)new TestBlock("testblock").setBlockTextureName("stone").setCreativeTab(CreativeTabEngineeringMod.tabEngineeringMod);
	
	public static final void registerBlocks() {
		GameRegistry.registerBlock(testBlock, testBlock.baseName);
		GameRegistry.registerTileEntity(TestTileEntity.class, "testblock");
	}
}
