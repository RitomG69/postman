package me.srgantmoomoo.postman.backend.util.render;

import me.srgantmoomoo.Main;
import me.srgantmoomoo.postman.impl.modules.pvp.Surround;
import me.srgantmoomoo.postman.impl.modules.render.Esp;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL32;

import me.srgantmoomoo.postman.backend.util.Wrapper;
import me.srgantmoomoo.postman.backend.util.font.FontUtils;
import me.srgantmoomoo.postman.backend.util.world.GeometryMasks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

//Credit 086 for Kami base Tessellator, heavily rewrote/modified by lukflug and others

public class JTessellator {
	private static final Minecraft mc = Wrapper.getMinecraft();
	
	public static void drawBox(BlockPos blockPos, double height, JColor color, int sides) {
		drawBox(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1, height, 1, color, sides);
	}

	public static void drawBox(double x, double y, double z, double w, double h, double d, JColor color, int sides) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		color.glColor();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		if ((sides & GeometryMasks.Quad.DOWN) != 0) {
			vertex(x+w,y,z,  bufferbuilder);
			vertex(x+w,y,z+d,bufferbuilder);
			vertex(x,  y,z+d,bufferbuilder);
			vertex(x,  y,z,  bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.UP) != 0) {
			vertex(x+w,y+h,z,  bufferbuilder);
			vertex(x,  y+h,z,  bufferbuilder);
			vertex(x,  y+h,z+d,bufferbuilder);
			vertex(x+w,y+h,z+d,bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.NORTH) != 0) {
			vertex(x+w,y,  z,bufferbuilder);
			vertex(x,  y,  z,bufferbuilder);
			vertex(x,  y+h,z,bufferbuilder);
			vertex(x+w,y+h,z,bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.SOUTH) != 0) {
			vertex(x,  y,  z+d,bufferbuilder);
			vertex(x+w,y,  z+d,bufferbuilder);
			vertex(x+w,y+h,z+d,bufferbuilder);
			vertex(x,  y+h,z+d,bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.WEST) != 0) {
			vertex(x,y,  z,  bufferbuilder);
			vertex(x,y,  z+d,bufferbuilder);
			vertex(x,y+h,z+d,bufferbuilder);
			vertex(x,y+h,z,  bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.EAST) != 0) {
			vertex(x+w,y,  z+d,bufferbuilder);
			vertex(x+w,y,  z,  bufferbuilder);
			vertex(x+w,y+h,z,  bufferbuilder);
			vertex(x+w,y+h,z+d,bufferbuilder);
		}
		tessellator.draw();
	}
	
	public static void drawStorageBox(BlockPos blockPos, double height, JColor color, int sides) {
		drawStorageBox(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1, height, 1, color, sides);
	}

	public static void drawStorageBox(double x, double y, double z, double w, double h, double d, JColor color, int sides) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		color.glColor();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		if ((sides & GeometryMasks.Quad.DOWN) != 0) {
			vertex(x+w - 0.06,y,z + 0.06,  bufferbuilder);
			vertex(x+w - 0.06,y,z+d - 0.06,bufferbuilder);
			vertex(x + 0.06,  y,z+d - 0.06,bufferbuilder);
			vertex(x + 0.06,  y,z + 0.06,  bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.UP) != 0) {
			vertex(x+w - 0.06,y+h,z + 0.06,  bufferbuilder);
			vertex(x + 0.06,  y+h,z + 0.06,  bufferbuilder);
			vertex(x + 0.06,  y+h,z+d - 0.06,bufferbuilder);
			vertex(x+w - 0.06,y+h,z+d - 0.06,bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.NORTH) != 0) {
			vertex(x+w - 0.06,y,  z + 0.06,bufferbuilder);
			vertex(x + 0.06,  y,  z + 0.06,bufferbuilder);
			vertex(x + 0.06,  y+h,z + 0.06,bufferbuilder);
			vertex(x+w - 0.06,y+h,z + 0.06,bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.SOUTH) != 0) {
			vertex(x + 0.06,  y,  z+d - 0.06,bufferbuilder);
			vertex(x+w - 0.06,y,  z+d - 0.06,bufferbuilder);
			vertex(x+w - 0.06,y+h,z+d - 0.06,bufferbuilder);
			vertex(x + 0.06,  y+h,z+d - 0.06,bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.WEST) != 0) {
			vertex(x + 0.06,y,  z + 0.06,  bufferbuilder);
			vertex(x + 0.06,y,  z+d - 0.06,bufferbuilder);
			vertex(x + 0.06,y+h,z+d - 0.06,bufferbuilder);
			vertex(x + 0.06,y+h,z + 0.06,  bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.EAST) != 0) {
			vertex(x+w - 0.06,y,  z+d - 0.06,bufferbuilder);
			vertex(x+w - 0.06,y,  z + 0.06,  bufferbuilder);
			vertex(x+w - 0.06,y+h,z + 0.06,  bufferbuilder);
			vertex(x+w - 0.06,y+h,z+d - 0.06,bufferbuilder);
		}
		tessellator.draw();
	}

	public static void drawBoundingBox(BlockPos bp, double height, float width, JColor color) {
		drawBoundingBox(getBoundingBox(bp,1, height,1),width,color);
	}

	public static void drawBoundingBox(AxisAlignedBB bb, float width, JColor color) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		GlStateManager.glLineWidth(width);
		color.glColor();
		bufferbuilder.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
		vertex(bb.minX,bb.minY,bb.minZ,bufferbuilder);
		vertex(bb.minX,bb.minY,bb.maxZ,bufferbuilder);
		vertex(bb.maxX,bb.minY,bb.maxZ,bufferbuilder);
		vertex(bb.maxX,bb.minY,bb.minZ,bufferbuilder);
		vertex(bb.minX,bb.minY,bb.minZ,bufferbuilder);
		vertex(bb.minX,bb.maxY,bb.minZ,bufferbuilder); //
		vertex(bb.minX,bb.maxY,bb.maxZ,bufferbuilder);
		vertex(bb.minX,bb.minY,bb.maxZ,bufferbuilder);
		vertex(bb.maxX,bb.minY,bb.maxZ,bufferbuilder); //
		vertex(bb.maxX,bb.maxY,bb.maxZ,bufferbuilder);
		vertex(bb.minX,bb.maxY,bb.maxZ,bufferbuilder);
		vertex(bb.maxX,bb.maxY,bb.maxZ,bufferbuilder);
		vertex(bb.maxX,bb.maxY,bb.minZ,bufferbuilder);
		vertex(bb.maxX,bb.minY,bb.minZ,bufferbuilder);
		vertex(bb.maxX,bb.maxY,bb.minZ,bufferbuilder);
		vertex(bb.minX,bb.maxY,bb.minZ,bufferbuilder);
		tessellator.draw();
	}
	
	public static void drawFillBox(AxisAlignedBB bb, float width, JColor color, int sides) {
		drawFillBox(bb.minX,bb.minY,bb.minZ,bb.maxX-bb.minX, bb.maxY-bb.minY,bb.maxZ-bb.minZ,color,sides);
	}
	
	public static void drawFillBox(double x, double y, double z, double w, double h, double d, JColor color, int sides) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		color.glColor();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		if ((sides & GeometryMasks.Quad.DOWN) != 0) {
			vertex(x+w,y,z,  bufferbuilder);
			vertex(x+w,y,z+d,bufferbuilder);
			vertex(x,  y,z+d,bufferbuilder);
			vertex(x,  y,z,  bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.UP) != 0) {
			vertex(x+w,y+h,z,  bufferbuilder);
			vertex(x,  y+h,z,  bufferbuilder);
			vertex(x,  y+h,z+d,bufferbuilder);
			vertex(x+w,y+h,z+d,bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.NORTH) != 0) {
			vertex(x+w,y,  z,bufferbuilder);
			vertex(x,  y,  z,bufferbuilder);
			vertex(x,  y+h,z,bufferbuilder);
			vertex(x+w,y+h,z,bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.SOUTH) != 0) {
			vertex(x,  y,  z+d,bufferbuilder);
			vertex(x+w,y,  z+d,bufferbuilder);
			vertex(x+w,y+h,z+d,bufferbuilder);
			vertex(x,  y+h,z+d,bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.WEST) != 0) {
			vertex(x,y,  z,  bufferbuilder);
			vertex(x,y,  z+d,bufferbuilder);
			vertex(x,y+h,z+d,bufferbuilder);
			vertex(x,y+h,z,  bufferbuilder);
		}
		if ((sides & GeometryMasks.Quad.EAST) != 0) {
			vertex(x+w,y,  z+d,bufferbuilder);
			vertex(x+w,y,  z,  bufferbuilder);
			vertex(x+w,y+h,z,  bufferbuilder);
			vertex(x+w,y+h,z+d,bufferbuilder);
		}
		tessellator.draw();
	}

	public static void draw2dEsp(Entity e, float viewerYaw, float lineWidth, JColor color) {
		JTessellator.prepare();
		GlStateManager.pushMatrix();
		Vec3d pos = Surround.getInterpolatedPos(e, mc.getRenderPartialTicks());
		GlStateManager.translate(pos.x - (mc.getRenderManager()).renderPosX, pos.y - (mc.getRenderManager()).renderPosY, pos.z - (mc.getRenderManager()).renderPosZ);
		GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-viewerYaw, 0.0F, 1.0F, 0.0F);
		GL11.glEnable(2848);
		if (e instanceof net.minecraft.entity.player.EntityPlayer) {
			GlStateManager.glLineWidth((float) lineWidth);
			color.glColor();
			GL11.glBegin(2);
			GL11.glVertex2d(-e.width, 0.0D);
			GL11.glVertex2d(-e.width, (e.height / 4.0F));
			GL11.glVertex2d(-e.width, 0.0D);
			GL11.glVertex2d((-e.width / 4.0F * 2.0F), 0.0D);
			GL11.glEnd();
			GL11.glBegin(2);
			GL11.glVertex2d(-e.width, e.height);
			GL11.glVertex2d((-e.width / 4.0F * 2.0F), e.height);
			GL11.glVertex2d(-e.width, e.height);
			GL11.glVertex2d(-e.width, (e.height / 2.5F * 2.0F));
			GL11.glEnd();
			GL11.glBegin(2);
			GL11.glVertex2d(e.width, e.height);
			GL11.glVertex2d((e.width / 4.0F * 2.0F), e.height);
			GL11.glVertex2d(e.width, e.height);
			GL11.glVertex2d(e.width, (e.height / 2.5F * 2.0F));
			GL11.glEnd();
			GL11.glBegin(2);
			GL11.glVertex2d(e.width, 0.0D);
			GL11.glVertex2d((e.width / 4.0F * 2.0F), 0.0D);
			GL11.glVertex2d(e.width, 0.0D);
			GL11.glVertex2d(e.width, (e.height / 4.0F));
			GL11.glEnd();
		}
		JTessellator.release();
		GlStateManager.popMatrix();
	}

	public static void drawLine(double posx, double posy, double posz, double posx2, double posy2, double posz2, float red, float green, float blue, float alpha){
		GlStateManager.glLineWidth(1.0f);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		GL11.glColor4f(red, green, blue, alpha);
		bufferbuilder.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
		vertex(posx,posy,posz,bufferbuilder);
		vertex(posx2,posy2,posz2,bufferbuilder);
		tessellator.draw();
	}

	public static void drawNametag (double x, double y, double z, String[] text, JColor color, int type) {
		double dist=mc.player.getDistance(x,y,z);
		double scale = 1, offset = 0;
		int start=0;
		switch (type) {
			case 0:
				scale=dist/20*Math.pow(1.2589254,0.1/(dist<25?0.5:2));
				scale=Math.min(Math.max(scale,.5),5);
				offset=scale>2?scale/2:scale;
				scale/=40;
				start=10;
				break;
			case 1:
				scale=-((int)dist)/6.0;
				if (scale<1) scale=1;
				scale*=2.0/75.0;
				break;
			case 2:
				scale=0.0018+0.003*dist;
				if (dist<=8.0) scale=0.0245;
				start=-8;
				break;
		}
		GlStateManager.pushMatrix();
		GlStateManager.translate(x-mc.getRenderManager().viewerPosX,y+offset-mc.getRenderManager().viewerPosY,z-mc.getRenderManager().viewerPosZ);
		GlStateManager.rotate(-mc.getRenderManager().playerViewY,0,1,0);
		GlStateManager.rotate(mc.getRenderManager().playerViewX,mc.gameSettings.thirdPersonView==2?-1:1,0,0);
		GlStateManager.scale(-scale,-scale,scale);
		if (type == 2) {
			double width = 0;
			JColor bcolor = new JColor(0,0,0,51);
			/*if (Nametags.customColor.getValue()) {
				bcolor = Nametags.borderColor.getValue();
			}*/
			for (int i = 0; i < text.length; i++) {
				double w= FontUtils.getStringWidth(false,text[i])/2;
				if (w > width) {
					width = w;
				}
			}
			drawBorderedRect(-width - 1, -mc.fontRenderer.FONT_HEIGHT, width + 2,1,1.8f, new JColor(0,4,0,85), bcolor);
		}
		GlStateManager.enableTexture2D();
		for (int i=0;i<text.length;i++) {
			FontUtils.drawStringWithShadow(false,text[i],-FontUtils.getStringWidth(false,text[i])/2,i*(mc.fontRenderer.FONT_HEIGHT+1)+start,color);
		}
		GlStateManager.disableTexture2D();
		if (type!=2) {
			GlStateManager.popMatrix();
		}
	}

	private static void drawBorderedRect (double x, double y, double x1, double y1, float lineWidth, JColor inside, JColor border) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		inside.glColor();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		bufferbuilder.pos(x,y1,0).endVertex();
		bufferbuilder.pos(x1,y1,0).endVertex();
		bufferbuilder.pos(x1,y,0).endVertex();
		bufferbuilder.pos(x,y,0).endVertex();
		tessellator.draw();
		border.glColor();
		GlStateManager.glLineWidth(lineWidth);
		bufferbuilder.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
		bufferbuilder.pos(x,y,0).endVertex();
		bufferbuilder.pos(x,y1,0).endVertex();
		bufferbuilder.pos(x1,y1,0).endVertex();
		bufferbuilder.pos(x1,y,0).endVertex();
		bufferbuilder.pos(x,y,0).endVertex();
		tessellator.draw();
	}

	private static void vertex (double x, double y, double z, BufferBuilder bufferbuilder) {
		bufferbuilder.pos(x-mc.getRenderManager().viewerPosX,y-mc.getRenderManager().viewerPosY,z-mc.getRenderManager().viewerPosZ).endVertex();
	}

	private static AxisAlignedBB getBoundingBox (BlockPos bp, double width, double height, double depth) {
		double x=bp.getX();
		double y=bp.getY();
		double z=bp.getZ();
		return new AxisAlignedBB(x,y,z,x+width,y+height,z+depth);
	}

	public static void prepare() {
		GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
		GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ZERO, GL11.GL_ONE);
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();
		GlStateManager.disableDepth();
		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();
		GlStateManager.disableCull();
		GlStateManager.enableAlpha();
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL32.GL_DEPTH_CLAMP);
	}

	public static void release() {
		GL11.glDisable(GL32.GL_DEPTH_CLAMP);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GlStateManager.enableAlpha();
		GlStateManager.enableCull();
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
		GlStateManager.enableDepth();
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
		GlStateManager.glLineWidth(1.0f);
		GlStateManager.shadeModel(GL11.GL_FLAT);
		GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_DONT_CARE);
	}
}