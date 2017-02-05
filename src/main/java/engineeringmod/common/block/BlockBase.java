package engineeringmod.common.block;

import engineeringmod.ModEngineering;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {
	public String baseName;
	
	public BlockBase(String parName, Material parMaterial) {
		super(parMaterial);
		baseName = parName;
		
		this.setBlockName(baseName).setBlockTextureName(ModEngineering.getTexture(baseName));
	}
}
