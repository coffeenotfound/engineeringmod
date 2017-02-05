package engineeringmod.miningrefined.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public final class ItemsMiningRefined {
	public static final Item rock = new ItemRock("rock");
	
	public static final Item rawIronOre = new ItemRock("raw_iron_ore");
	
//	public static final Item bucketOil = new ItemBucket(BlocksMiningRefined.stillOil).setUnlocalizedName("oil_bucket");
	
	public static final void registerItems() {
		GameRegistry.registerItem(rock, "rock");
		
		// raw ores
		GameRegistry.registerItem(rawIronOre, "raw_iron_ore");
		
		// oil bucket
//		GameRegistry.registerItem(bucketOil, "oil_bucket");
	}
}
