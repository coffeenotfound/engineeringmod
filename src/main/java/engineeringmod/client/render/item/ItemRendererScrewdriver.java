package engineeringmod.client.render.item;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererScrewdriver implements IItemRenderer {
	public Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return (type == ItemRenderType.EQUIPPED_FIRST_PERSON);
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		EntityLivingBase player = (EntityLivingBase)data[1];
		
//		Tessellator tessellator = Tessellator.instance;
		
//		renderItemActualImpl(player, item, 0);
		
		double time = ((double)System.currentTimeMillis() / 100d);
		
		//boolean doTurnAnimation = true;
		boolean doTurnAnimation = player.isSneaking();
		
//		GL11.glRotatef(-90f, 0f, 0f, 1f);
		
		// push matrix
		GL11.glPushMatrix();
		
		// reset inherited transform
		GL11.glTranslatef(0.59375F, 0.0625F, 0.0F);
		GL11.glRotatef(-320.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(-100.0F, 0.0F, 1.0F, 0.0F);
		
		// do transform
		if(doTurnAnimation) {
			GL11.glTranslatef(0.1f, 0.5f, -0.3f);
			
			GL11.glRotatef((float)Math.sin(time) * 10f, 0f, 0f, 1f);
			GL11.glRotatef(-90f, 1f, 0f, 0f);
		}
		else {
			GL11.glTranslatef(0.1f, 0.0f, -0.3f);
		}
		
		// restore inherited transform
		GL11.glRotatef(100.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(320.0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(-0.59375F, -0.0625F, 0.0F);
		
		// scale
		float f6 = 0.75f;
		GL11.glScalef(f6, f6, f6);
		
		// draw the item
		IIcon iicon = player.getItemIcon(item, 0);
		Tessellator tessellator = Tessellator.instance;
		float f = iicon.getMinU();
		float f1 = iicon.getMaxU();
		float f2 = iicon.getMinV();
		float f3 = iicon.getMaxV();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625f);
		
		// reset and pop matrix
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}
	
	// ItemRenderer
	private void renderItemActualImpl(EntityLivingBase parEntity, ItemStack parItemstack, int parRenderpass) {
		GL11.glPushMatrix();
		TextureManager texturemanager = this.mc.getTextureManager();
		Item item = parItemstack.getItem();
		Block block = Block.getBlockFromItem(item);

		if (parItemstack != null && block != null && block.getRenderBlockPass() != 0)
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_CULL_FACE);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		}
		
		IIcon iicon = parEntity.getItemIcon(parItemstack, parRenderpass);
		
		texturemanager.bindTexture(texturemanager.getResourceLocation(parItemstack.getItemSpriteNumber()));
		TextureUtil.func_152777_a(false, false, 1.0F);
		Tessellator tessellator = Tessellator.instance;
		float f = iicon.getMinU();
		float f1 = iicon.getMaxU();
		float f2 = iicon.getMinV();
		float f3 = iicon.getMaxV();
		float f4 = 0.0F;
		float f5 = 0.3F;
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef(-f4, -f5, 0.0F);
		float f6 = 1.5F;
		GL11.glScalef(f6, f6, f6);
		GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
		
		ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625F);
		
		if (parItemstack.hasEffect(parRenderpass))
		{
			GL11.glDepthFunc(GL11.GL_EQUAL);
			GL11.glDisable(GL11.GL_LIGHTING);
//			texturemanager.bindTexture(RES_ITEM_GLINT);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(768, 1, 1, 0);
			float f7 = 0.76F;
			GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
			GL11.glMatrixMode(GL11.GL_TEXTURE);
			GL11.glPushMatrix();
			float f8 = 0.125F;
			GL11.glScalef(f8, f8, f8);
			float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
			GL11.glTranslatef(f9, 0.0F, 0.0F);
			GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
			
			ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
			
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(f8, f8, f8);
			f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
			GL11.glTranslatef(-f9, 0.0F, 0.0F);
			GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
			
			ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
			
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDepthFunc(GL11.GL_LEQUAL);
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		texturemanager.bindTexture(texturemanager.getResourceLocation(parItemstack.getItemSpriteNumber()));
		TextureUtil.func_147945_b();

		if (parItemstack != null && block != null && block.getRenderBlockPass() != 0)
		{
			GL11.glDisable(GL11.GL_BLEND);
		}

		GL11.glPopMatrix();
	}
}
