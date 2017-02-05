package engineeringmod.core;

import net.minecraft.launchwrapper.IClassTransformer;

public class EngineeringCoreClassTransformer implements IClassTransformer {
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		return basicClass;
	}
}
