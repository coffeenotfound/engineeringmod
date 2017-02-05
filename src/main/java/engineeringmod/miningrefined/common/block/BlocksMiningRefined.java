package engineeringmod.miningrefined.common.block;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.registry.GameRegistry;
import engineeringmod.miningrefined.CreativeTabMiningRefined;
import engineeringmod.miningrefined.ModMiningRefinedAddon;
import engineeringmod.miningrefined.common.fluid.FluidCrudeOil;
import engineeringmod.miningrefined.common.item.ItemBlockMetalOreDeposit;
import engineeringmod.miningrefined.common.item.ItemBucketCrudeOil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public final class BlocksMiningRefined {
	public static final BlockMetalOreDeposit ironOreDeposit = (BlockMetalOreDeposit)new BlockMetalOreDeposit("iron_ore_deposit", Material.rock).setCreativeTab(CreativeTabMiningRefined.tabMiningRefined);
	
	public static final Fluid fluidOil = new FluidCrudeOil();
	public static Block flowingOil;
//	public static final Block stillOil = new BlockStaticOil(Material.water).setHardness(100.0F).setLightOpacity(3).setBlockName("oil").setBlockTextureName(ModMiningRefinedAddon.getTexture("oil_still"));
	public static Item oilBucket;
	
	public static final void registerBlocks() {
		// iron ore deposit
		GameRegistry.registerBlock(ironOreDeposit, ItemBlockMetalOreDeposit.class, "iron_ore_deposit");
		
		// init fluid
		fluidOil.setUnlocalizedName("crude_oil").setViscosity(1500).setDensity(900);
		FluidRegistry.registerFluid(fluidOil);
		
		// oil [to be moved to Oil Fever]
		flowingOil = new BlockFluidCrudeOil(fluidOil, Material.water).setBlockTextureName(ModMiningRefinedAddon.getTexture("oil_flow")).setBlockName("crude_oil").setHardness(100.0f).setLightOpacity(3).setCreativeTab(CreativeTabMiningRefined.tabMiningRefined);
		GameRegistry.registerBlock(flowingOil, "flowing_oil");
		
		// oil bucket
//		oilBucket = new ItemBucket(flowingOil).setUnlocalizedName("oil_bucket").setTextureName(ModMiningRefinedAddon.getTexture("oil_bucket")).setCreativeTab(CreativeTabMiningRefined.tabMiningRefined);
//		oilBucket = new ItemFluidContainer(itemID, capacity)
		oilBucket = new ItemBucketCrudeOil(flowingOil).setUnlocalizedName("oil_bucket").setTextureName(ModMiningRefinedAddon.getTexture("oil_bucket")).setCreativeTab(CreativeTabMiningRefined.tabMiningRefined);
		GameRegistry.registerItem(oilBucket, "oil_bucket");
		
		FluidContainerRegistry.registerFluidContainer(fluidOil, new ItemStack(oilBucket), FluidContainerRegistry.EMPTY_BUCKET);
		MinecraftForge.EVENT_BUS.register(new TestBucketFillHandler());
	}
	
	public static class TestBucketFillHandler {
		
		@SubscribeEvent
		public void onBucketFill(FillBucketEvent parEvent) {
			// try fill bucket
			ItemStack result = fillBucket(parEvent.world, parEvent.target, parEvent.current);
			
			if(result != null) {
				parEvent.result = result;
				parEvent.setResult(Result.ALLOW);
			}
		}
		
		private ItemStack fillBucket(World parWorld, MovingObjectPosition parPos, ItemStack parItemStack) {
			Block block = parWorld.getBlock(parPos.blockX, parPos.blockY, parPos.blockZ);
			
//			if(block instanceof BlockFluidBase) {
			if(block == BlocksMiningRefined.flowingOil) {
				BlockFluidBase fluidBlock = (BlockFluidBase)block;
				
				// simulate drain
				FluidStack drainedFluid = fluidBlock.drain(parWorld, parPos.blockX, parPos.blockY, parPos.blockZ, false);
				
				// try fill container
				ItemStack filledItemStack = FluidContainerRegistry.fillFluidContainer(drainedFluid, parItemStack);
				
				// actually drain the fluid
				if(filledItemStack != null) {
					fluidBlock.drain(parWorld, parPos.blockX, parPos.blockY, parPos.blockZ, true);
				}
				return filledItemStack;
			}
			return null;
		}
	}
}
