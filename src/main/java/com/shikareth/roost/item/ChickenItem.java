package com.shikareth.roost.item;

import java.util.Objects;

import javax.annotation.Nullable;

import com.shikareth.roost.RoostItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;

public class ChickenItem extends ItemBase {

//	private static String I18N_NAME = "entity.Chicken.name";
	private final EntityType<?> typeIn = EntityType.CHICKEN;
	
	public  ChickenItem() {
		super(RoostItems.DEFAULT_PROPERTIES.maxDamage(0).defaultMaxDamage(0).maxStackSize(16));
	}
	
	   /**
	* Called when this item is used when targetting a Block
	*/
	public ActionResultType onItemUse(ItemUseContext context) {
	      World world = context.getWorld();
	      if (world.isRemote) {
	         return ActionResultType.SUCCESS;
	      } else {
	         ItemStack itemstack = context.getItem();
	         BlockPos blockpos = context.getPos();
	         Direction direction = context.getFace();
	         BlockState blockstate = world.getBlockState(blockpos);
	         if (blockstate.isIn(Blocks.SPAWNER)) {
	            TileEntity tileentity = world.getTileEntity(blockpos);
	            if (tileentity instanceof MobSpawnerTileEntity) {
	               AbstractSpawner abstractspawner = ((MobSpawnerTileEntity)tileentity).getSpawnerBaseLogic();
	               EntityType<?> entitytype1 = this.getType(itemstack.getTag());
	               abstractspawner.setEntityType(entitytype1);
	               tileentity.markDirty();
	               world.notifyBlockUpdate(blockpos, blockstate, blockstate, 3);
	               itemstack.shrink(1);
	               return ActionResultType.CONSUME;
	            }
	         }
	
	         BlockPos blockpos1;
	         if (blockstate.getCollisionShape(world, blockpos).isEmpty()) {
	            blockpos1 = blockpos;
	         } else {
	            blockpos1 = blockpos.offset(direction);
	         }
	
	         EntityType<?> entitytype = this.getType(itemstack.getTag());
	         if (entitytype.spawn(world, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) {
	            itemstack.shrink(1);
	         }
	
	         return ActionResultType.CONSUME;
	      }
	   }
	
	   /**
	* Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
	* {@link #onItemUse}.
	*/
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
		if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
			return ActionResult.resultPass(itemstack);
		} else if (worldIn.isRemote) {
			return ActionResult.resultSuccess(itemstack);
		} else {
			BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
			BlockPos blockpos = blockraytraceresult.getPos();
			if (!(worldIn.getBlockState(blockpos).getBlock() instanceof FlowingFluidBlock)) {
				return ActionResult.resultPass(itemstack);
			} else if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, blockraytraceresult.getFace(), itemstack)) {
				EntityType<?> entitytype = this.getType(itemstack.getTag());
				if (entitytype.spawn(worldIn, itemstack, playerIn, blockpos, SpawnReason.SPAWN_EGG, false, false) == null) {
	               return ActionResult.resultPass(itemstack);
	            } else {
	            	if (!playerIn.abilities.isCreativeMode) {
	            		itemstack.shrink(1);
	            	}
	            	playerIn.addStat(Stats.ITEM_USED.get(this));
	            	return ActionResult.resultConsume(itemstack);
	            }
			} else {
				return ActionResult.resultFail(itemstack);
			}
	      }
	   }
	
	public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
	   if (p_208076_1_ != null && p_208076_1_.contains("EntityTag", 10)) {
		   CompoundNBT compoundnbt = p_208076_1_.getCompound("EntityTag");
		   if (compoundnbt.contains("id", 8)) 
			   return EntityType.byKey(compoundnbt.getString("id")).orElse(this.typeIn);
	   }
	
      return this.typeIn;
   }

}
