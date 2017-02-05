package engineeringmod.common.multipartstructure.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class TileEntityRendererMPSPartBlock extends TileEntitySpecialRenderer {
	
	@Override
	public void renderTileEntityAt(TileEntity parTileEntity, double parX, double parY, double parZ, float parPartialTicks) {
		GL11.glPushMatrix();
		
//		GL11.glTranslatef(-MathHelper.floor_double(parX), -MathHelper.floor_double(parY), -MathHelper.floor_double(parZ));
		
		RenderBlocks renderBlocks = RenderBlocks.getInstance();
		renderBlocks.blockAccess = parTileEntity.getWorldObj();
		renderBlocks.renderStandardBlock(Blocks.dirt, MathHelper.floor_double(parX), MathHelper.floor_double(parY), MathHelper.floor_double(parZ));
		
//		GL11.glDisable(GL11.GL_CULL_FACE);
//		GL11.glDisable(GL11.GL_ALPHA_TEST);
//		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
//		
//		GL11.glTranslatef((float)parX, (float)parY, (float)parZ);
//		
//		GL11.glColor4f(1f, 0f, 0f, 1f);
//		GL11.glBegin(GL11.GL_TRIANGLES);
//			GL11.glNormal3f(0f, 1f, 0f);
//			GL11.glVertex3f(0f, 1f, 0f);
//			
//			GL11.glNormal3f(0f, 1f, 0f);
//			GL11.glVertex3f(1f, 1f, 1f);
//			
//			GL11.glNormal3f(0f, 1f, 0f);
//			GL11.glVertex3f(0f, 1f, 1f);
//		GL11.glEnd();
//		
//		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		
		GL11.glPopMatrix();
	}
}
