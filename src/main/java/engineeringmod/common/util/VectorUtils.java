package engineeringmod.common.util;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public final class VectorUtils {
	
	public static final Vec3 normalizeVec(Vec3 parVec) {
		double d0 = (double)MathHelper.sqrt_double(parVec.xCoord * parVec.xCoord + parVec.yCoord * parVec.yCoord + parVec.zCoord * parVec.zCoord);
		if(d0 < 1.0E-4D) {
			parVec.xCoord = 0d;
			parVec.yCoord = 0d;
			parVec.zCoord = 0d;
		}
		else {
			parVec.xCoord = parVec.xCoord / d0;
			parVec.yCoord = parVec.yCoord / d0;
			parVec.zCoord = parVec.zCoord / d0;
		}
		return parVec;
	}
}
