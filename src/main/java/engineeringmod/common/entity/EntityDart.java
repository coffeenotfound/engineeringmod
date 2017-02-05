package engineeringmod.common.entity;

import java.util.List;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import engineeringmod.common.item.EngineeringItems;
import engineeringmod.common.util.VectorUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityDart extends Entity implements IProjectile {
	private int field_145791_d = -1;
	private int field_145792_e = -1;
	private int field_145789_f = -1;
	private Block field_145790_g;
	private int inData;
	private boolean inGround;
	/** 1 if the player can pick up the arrow */
	public int canBePickedUp;
	/** Seems to be some sort of timer for animating an arrow. */
	public int arrowShake;
	/** The owner of this arrow. */
	public Entity shootingEntity;
	private int ticksInGround;
	private int ticksInAir;
	private double damage = 0.5d;
	/** The amount of knockback an arrow applies when it hits a mob. */
	private int knockbackStrength;
	
	public EntityDart(World parWorld, EntityLivingBase parShootingEntity) {
		super(parWorld);
		shootingEntity = parShootingEntity;
		
		this.renderDistanceWeight = 10.0d;
		this.setSize(0.25f, 0.25f);
		
		canBePickedUp = 1;
	}
	
	/** IMPORTANT: this constructor is assumed to exist by internal fml code */
	public EntityDart(World parWorld) {
		this(parWorld, null);
	}
	
	protected void entityInit() {
//		this.dataWatcher.addObject(16, 0f);
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@SuppressWarnings("rawtypes")
	public void onUpdate() {
		super.onUpdate();
		
		if(this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f) * 180.0D / Math.PI);
		}
		
		Block block = this.worldObj.getBlock(this.field_145791_d, this.field_145792_e, this.field_145789_f);
		
		if(block.getMaterial() != Material.air) {
			block.setBlockBoundsBasedOnState(this.worldObj, this.field_145791_d, this.field_145792_e, this.field_145789_f);
			AxisAlignedBB axisalignedbb = block.getCollisionBoundingBoxFromPool(this.worldObj, this.field_145791_d, this.field_145792_e, this.field_145789_f);
			
			if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ))) {
				this.inGround = true;
			}
		}
		
		if(this.arrowShake > 0) {
			--this.arrowShake;
		}
		
		if(this.inGround) {
			int j = this.worldObj.getBlockMetadata(this.field_145791_d, this.field_145792_e, this.field_145789_f);
			
			if(block == this.field_145790_g && j == this.inData) {
				++this.ticksInGround;
				
				if(this.ticksInGround == 1200) {
					this.setDead();
				}
			}
			else {
				this.inGround = false;
				this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
				this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
				this.ticksInGround = 0;
				this.ticksInAir = 0;
			}
		}
		else {
			++this.ticksInAir;
			Vec3 vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec31, vec3, false, true, false);
			vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			
			if (movingobjectposition != null) {
				vec3 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
			}
			
			Entity entity = null;
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			int i;
			float f1;
			
			for(i = 0; i < list.size(); ++i) {
				Entity entity1 = (Entity)list.get(i);
				
				if(entity1.canBeCollidedWith() && (entity1 != this.shootingEntity || this.ticksInAir >= 5)) {
					f1 = 0.3F;
					AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand((double)f1, (double)f1, (double)f1);
					MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);
					
					if(movingobjectposition1 != null) {
						double d1 = vec31.distanceTo(movingobjectposition1.hitVec);
						
						if(d1 < d0 || d0 == 0.0D) {
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}
			
			if(entity != null) {
				movingobjectposition = new MovingObjectPosition(entity);
			}
			
			if(movingobjectposition != null && movingobjectposition.entityHit != null && movingobjectposition.entityHit instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.entityHit;
				
				if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer)) {
					movingobjectposition = null;
				}
			}
			
			float f2;
			float f4;
			
			if(movingobjectposition != null) {
				if(movingobjectposition.entityHit != null) {
					f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					int k = MathHelper.ceiling_double_int((double)f2 * this.damage);
					
//					if (this.getIsCritical()) {
//						k += this.rand.nextInt(k / 2 + 2);
//					}
					
					DamageSource damagesource = null;
					
					if(this.shootingEntity == null) {
						damagesource = DamageSource.causeThrownDamage(this, this);
					}
					else {
						damagesource = DamageSource.causeThrownDamage(this, this.shootingEntity);
					}
					
					if(this.isBurning() && !(movingobjectposition.entityHit instanceof EntityEnderman)) {
						movingobjectposition.entityHit.setFire(5);
					}
					
					if(movingobjectposition.entityHit.attackEntityFrom(damagesource, (float)k)) {
						if(movingobjectposition.entityHit instanceof EntityLivingBase) {
							EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.entityHit;
							
							if(!this.worldObj.isRemote) {
								entitylivingbase.setArrowCountInEntity(entitylivingbase.getArrowCountInEntity() + 1);
							}
							
							if(this.knockbackStrength > 0) {
								f4 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
								
								if(f4 > 0.0F) {
									movingobjectposition.entityHit.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)f4, 0.1D, this.motionZ * (double)this.knockbackStrength * 0.6000000238418579D / (double)f4);
								}
							}
							
							if(this.shootingEntity != null && this.shootingEntity instanceof EntityLivingBase) {
								EnchantmentHelper.func_151384_a(entitylivingbase, this.shootingEntity);
								EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, entitylivingbase);
							}
							
							if(this.shootingEntity != null && movingobjectposition.entityHit != this.shootingEntity && movingobjectposition.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
								((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
							}
						}
						
						this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
						// TODO: play sound
						
						if(!(movingobjectposition.entityHit instanceof EntityEnderman)) {
							this.setDead();
						}
					}
					else {
						this.motionX *= -0.10000000149011612D;
						this.motionY *= -0.10000000149011612D;
						this.motionZ *= -0.10000000149011612D;
						this.rotationYaw += 180.0F;
						this.prevRotationYaw += 180.0F;
						this.ticksInAir = 0;
					}
				}
				else {
					this.field_145791_d = movingobjectposition.blockX;
					this.field_145792_e = movingobjectposition.blockY;
					this.field_145789_f = movingobjectposition.blockZ;
					this.field_145790_g = this.worldObj.getBlock(this.field_145791_d, this.field_145792_e, this.field_145789_f);
					this.inData = this.worldObj.getBlockMetadata(this.field_145791_d, this.field_145792_e, this.field_145789_f);
					this.motionX = (double)((float)(movingobjectposition.hitVec.xCoord - this.posX));
					this.motionY = (double)((float)(movingobjectposition.hitVec.yCoord - this.posY));
					this.motionZ = (double)((float)(movingobjectposition.hitVec.zCoord - this.posZ));
					f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					this.posX -= this.motionX / (double)f2 * 0.05000000074505806D;
					this.posY -= this.motionY / (double)f2 * 0.05000000074505806D;
					this.posZ -= this.motionZ / (double)f2 * 0.05000000074505806D;
					
					this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
					// TODO: play sound
					
					this.inGround = true;
					this.arrowShake = 7;
//					this.setIsCritical(false);
					
					if(this.field_145790_g.getMaterial() != Material.air) {
						this.field_145790_g.onEntityCollidedWithBlock(this.worldObj, this.field_145791_d, this.field_145792_e, this.field_145789_f, this);
					}
				}
			}
			
//			if (this.getIsCritical()) {
//				for (i = 0; i < 4; ++i) {
//					this.worldObj.spawnParticle("crit", this.posX + this.motionX * (double)i / 4.0D, this.posY + this.motionY * (double)i / 4.0D, this.posZ + this.motionZ * (double)i / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
//				}
//			}
			
			// DEBUG:
//			if(!worldObj.isRemote) {
				this.posX += this.motionX;
				this.posY += this.motionY;
				this.posZ += this.motionZ;
//			}
			
			
			f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			
			for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f2) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
				;
			}
			
			while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
				this.prevRotationPitch += 360.0F;
			}
			
			while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
				this.prevRotationYaw -= 360.0F;
			}
			
			while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
				this.prevRotationYaw += 360.0F;
			}
			
			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			float f3 = 0.99F;
			f1 = 0.05F;
			
			if(this.isInWater()) {
				for(int l = 0; l < 4; ++l) {
					f4 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)f4, this.posY - this.motionY * (double)f4, this.posZ - this.motionZ * (double)f4, this.motionX, this.motionY, this.motionZ);
				}
				
				f3 = 0.8F;
			}
			
			if (this.isWet()) {
				this.extinguish();
			}
			
			this.motionX *= (double)f3;
			this.motionY *= (double)f3;
			this.motionZ *= (double)f3;
			this.motionY -= (double)f1;
			this.setPosition(this.posX, this.posY, this.posZ);
			this.func_145775_I();
			
			// force send updates to client to avoid desync
//			this.velocityChanged = true;
		}
	}
	
	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
	 */
	public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
		float f2 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
		p_70186_1_ /= (double)f2;
		p_70186_3_ /= (double)f2;
		p_70186_5_ /= (double)f2;
		p_70186_1_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
		p_70186_3_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
		p_70186_5_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
		p_70186_1_ *= (double)p_70186_7_;
		p_70186_3_ *= (double)p_70186_7_;
		p_70186_5_ *= (double)p_70186_7_;
		this.motionX = p_70186_1_;
		this.motionY = p_70186_3_;
		this.motionZ = p_70186_5_;
		float f3 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
		this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_70186_3_, (double)f3) * 180.0D / Math.PI);
		this.ticksInGround = 0;
	}
	
	/**
	 * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
		this.setPosition(p_70056_1_, p_70056_3_, p_70056_5_);
		this.setRotation(p_70056_7_, p_70056_8_);
	}
	
	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public void setVelocity(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
		this.motionX = p_70016_1_;
		this.motionY = p_70016_3_;
		this.motionZ = p_70016_5_;
		
		if(this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_70016_3_, (double)f) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}
	
	public void writeEntityToNBT(NBTTagCompound parTag) {
		parTag.setShort("xTile", (short)this.field_145791_d);
		parTag.setShort("yTile", (short)this.field_145792_e);
		parTag.setShort("zTile", (short)this.field_145789_f);
		parTag.setShort("life", (short)this.ticksInGround);
		parTag.setByte("inTile", (byte)Block.getIdFromBlock(this.field_145790_g));
		parTag.setByte("inData", (byte)this.inData);
		parTag.setByte("shake", (byte)this.arrowShake);
		parTag.setByte("inGround", (byte)(this.inGround ? 1 : 0));
		parTag.setByte("pickup", (byte)this.canBePickedUp);
		parTag.setDouble("damage", this.damage);
	}
	
	public void readEntityFromNBT(NBTTagCompound parTag) {
		this.field_145791_d = parTag.getShort("xTile");
		this.field_145792_e = parTag.getShort("yTile");
		this.field_145789_f = parTag.getShort("zTile");
		this.ticksInGround = parTag.getShort("life");
		this.field_145790_g = Block.getBlockById(parTag.getByte("inTile") & 255);
		this.inData = parTag.getByte("inData") & 255;
		this.arrowShake = parTag.getByte("shake") & 255;
		this.inGround = parTag.getByte("inGround") == 1;
		
		if(parTag.hasKey("damage", 99)) {
			this.damage = parTag.getDouble("damage");
		}
		
		if(parTag.hasKey("pickup", 99)) {
			this.canBePickedUp = parTag.getByte("pickup");
		}
		else if(parTag.hasKey("player", 99)) {
			this.canBePickedUp = parTag.getBoolean("player") ? 1 : 0;
		}
	}
	
	/**
	 * Called by a player entity when they collide with an entity
	 */
	public void onCollideWithPlayer(EntityPlayer p_70100_1_) {
		if(!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0) {
			boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && p_70100_1_.capabilities.isCreativeMode;
			
			if(this.canBePickedUp == 1 && !p_70100_1_.inventory.addItemStackToInventory(new ItemStack(EngineeringItems.dart, 1))) {
				flag = false;
			}
			
			if(flag) {
				this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				p_70100_1_.onItemPickup(this, 1);
				this.setDead();
			}
		}
	}
	
	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}
	
	public void setDamage(double p_70239_1_) {
		this.damage = p_70239_1_;
	}
	
	public double getDamage() {
		return this.damage;
	}
	
	/**
	 * Sets the amount of knockback the arrow applies when it hits a mob.
	 */
	public void setKnockbackStrength(int p_70240_1_) {
		this.knockbackStrength = p_70240_1_;
	}
	
	/**
	 * If returns false, the item will not inflict any damage against entities.
	 */
	public boolean canAttackWithItem() {
		return false;
	}
	
//	/**
//	 * Whether the arrow has a stream of critical hit particles flying behind it.
//	 */
//	public void setIsCritical(boolean p_70243_1_) {
//		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
//		
//		if (p_70243_1_) {
//			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
//		}
//		else {
//			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
//		}
//	}
//	
//	/**
//	 * Whether the arrow has a stream of critical hit particles flying behind it.
//	 */
//	public boolean getIsCritical() {
//		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
//		return (b0 & 1) != 0;
//	}
	
	public static EntityDart throwDart(World parWorld, EntityLivingBase parThrowingEntity) {
		final float positionOffset = 0.1f;
		final float velocity = 2.0f;
		final float dirVariation = 0.01f;
		final float eyeHeightOffset = -0.2f;
		
		float heightOffset = parThrowingEntity.getEyeHeight() + eyeHeightOffset;
		Vec3 lookDir = parThrowingEntity.getLookVec();
		Vec3 velocityVec = Vec3.createVectorHelper(lookDir.xCoord, lookDir.yCoord, lookDir.zCoord);
		
		// calc dir variation
		velocityVec.xCoord += MathHelper.randomFloatClamp(parWorld.rand, -dirVariation, dirVariation);
		velocityVec.yCoord += MathHelper.randomFloatClamp(parWorld.rand, -dirVariation, dirVariation);
		velocityVec.zCoord += MathHelper.randomFloatClamp(parWorld.rand, -dirVariation, dirVariation);
		VectorUtils.normalizeVec(velocityVec);
		
		velocityVec.xCoord *= velocity;
		velocityVec.yCoord *= velocity;
		velocityVec.zCoord *= velocity;
		
		// spawn dart
		EntityDart dart = new EntityDart(parWorld, parThrowingEntity);
		dart.setPosition(parThrowingEntity.posX + lookDir.xCoord*positionOffset, parThrowingEntity.posY + heightOffset + lookDir.yCoord*positionOffset, parThrowingEntity.posZ + lookDir.zCoord*positionOffset);
		dart.setVelocity(velocityVec.xCoord, velocityVec.yCoord, velocityVec.zCoord);
		parWorld.spawnEntityInWorld(dart);
		
		return dart;
	}
}
