package com.shikareth.roost.item;

import java.util.Random;

import com.shikareth.roost.Roost;
import com.shikareth.roost.RoostItems;
import com.shikareth.roost.data.DataChicken;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class CatcherItem extends ItemBase{

	public CatcherItem() {
		super(RoostItems.DEFAULT_PROPERTIES.maxDamage(64));
	}

	@Override
	public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
		World world = target.getEntityWorld();
		Vector3d pos = target.getPositionVec();
		DataChicken chickenData = DataChicken.getDataFromEntity(target);
		
		if (chickenData == null) {
			if (world.isRemote) {
				playerIn.sendMessage(new StringTextComponent("Couldn't get entity DataChicken"), PlayerEntity.getUUID(playerIn.getGameProfile()));
				Roost.LOGGER.error("Couldn't get entity DataChicken");
				spawnParticles(pos, world, ParticleTypes.SMOKE);
			}
			world.playSound(playerIn, pos.x, pos.y, pos.z, SoundEvents.BLOCK_FIRE_EXTINGUISH, target.getSoundCategory(), 1.0F, 1.0F);
			return ActionResultType.FAIL;
		} else if (target.isChild()) {
			if (world.isRemote) {
				playerIn.sendMessage(new StringTextComponent("Chicken must be an adult"), PlayerEntity.getUUID(playerIn.getGameProfile()));
				spawnParticles(pos, world, ParticleTypes.SMOKE);
			}
			world.playSound(playerIn, pos.x, pos.y, pos.z, SoundEvents.ENTITY_CHICKEN_HURT, target.getSoundCategory(), 1.0F, 1.0F);
		} else {
			if (world.isRemote) {
				spawnParticles(pos, world, ParticleTypes.EXPLOSION);
			} else {
				//Roost.LOGGER.info(chickenData.buildChickenStack());
				ItemEntity item = target.entityDropItem(chickenData.getItemStack(), 1.0F);
				item.setMotion(0, 0.2D, 0); 
				target.remove();
			}
			world.playSound(playerIn, pos.x, pos.y, pos.z, SoundEvents.ENTITY_CHICKEN_EGG, target.getSoundCategory(), 1.0F, 1.0F);
			stack.damageItem(1, playerIn, null);
		}
		
		return ActionResultType.CONSUME;
	}
	
	private void spawnParticles(Vector3d pos, World world, BasicParticleType particleType) {
		Random rand = new Random();
	
		for (int k = 0; k < 20; ++k) {
			double xCoord = pos.x + (rand.nextDouble() * 0.6D) - 0.3D;
			double yCoord = pos.y + (rand.nextDouble() * 0.6D);
			double zCoord = pos.z + (rand.nextDouble() * 0.6D) - 0.3D;
			double xSpeed = rand.nextGaussian() * 0.02D;
			double ySpeed = rand.nextGaussian() * 0.2D;
			double zSpeed = rand.nextGaussian() * 0.02D;
			world.addParticle(particleType, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
		}
	}
}
