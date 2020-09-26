package com.shikareth.roost.data;

import java.util.List;

import com.shikareth.roost.Roost;
import com.shikareth.roost.RoostItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class DataChickenVanilla extends DataChicken {

	public static final String VANILLA_TYPE = "minecraft:chicken";

	public static void addAllChickens(List<DataChicken> chickens) {
		chickens.add(new DataChickenVanilla());
	}
	public static DataChicken getDataFromEntity(LivingEntity target) {
		if (target instanceof ChickenEntity) return new DataChickenVanilla();
		return null;
	}
	public static DataChicken getDataFromStack(ItemStack stack) {
		CompoundNBT tagCompound = stack.getTag();
		if (tagCompound == null || !tagCompound.getString(CHICKEN_ID_KEY).equals(VANILLA_TYPE)) return null;
		return new DataChickenVanilla();
	}
	public static DataChicken getDataFromTooltipNBT(CompoundNBT tagCompound) {
		if (tagCompound == null || !tagCompound.getString(CHICKEN_ID_KEY).equals(VANILLA_TYPE)) return null;
		return new DataChickenVanilla();
	}
	public static DataChicken getDataFromName(String name) {
		if (name.equals("minecraft:vanilla")) return new DataChickenVanilla();
		return null;
	}
	
	public DataChickenVanilla() {
		super("entity.chicken.name");
	}
	
	@Override
	public ItemStack getItemStack() {
		Roost.LOGGER.debug("creating vanilla chicken itemStack...");
		return new ItemStack(RoostItems.ITEM_CHICKEN.get());
	}
	
//	@Override
//	public boolean isEqual(DataChicken other) {
//		return (other instanceof DataChickenVanilla);
//	}
//	@Override
//	public ItemStack createDropStack() {
//		Item item = rand.nextInt(3) > 0 && !RoostConfig.disableEggLaying ? Items.EGG : Items.FEATHER;
//		return new ItemStack(item, 1);
//	}
//	@Override
//	public ChickenEntity buildEntity(World world) {
//		return new ChickenEntity(null, world);
//	}
//	@Override
//	public void spawnEntity(World world, BlockPos pos) {
//		ChickenEntity chicken = new ChickenEntity(null, world);
//		chicken.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
//		chicken.onInitialSpawn(world, world.getDifficultyForLocation(pos), null, null, null);
//		chicken.setGrowingAge(getLayTime());
//		world.addEntity(chicken);
//	}
//	@Override
//	public ItemStack buildChickenStack() {
//		ItemStack stack = new ItemStack(RoostItems.ITEM_CHICKEN.get());
//		CompoundNBT tagCompound = new CompoundNBT();
//		tagCompound.putString(CHICKEN_ID_KEY, VANILLA_TYPE);
//		stack.setTag(tagCompound);
//		return stack;
//	}
//	@Override
//	public CompoundNBT buildTooltipNBT() {
//		CompoundNBT tagCompound = new CompoundNBT();
//		tagCompound.putString(CHICKEN_ID_KEY, VANILLA_TYPE);
//		return tagCompound;
//	}
//	@Override
//	public boolean hasParents() {
//		return true;
//	}
//	@Override
//	public List<ItemStack> buildParentChickenStack() {
//		return Arrays.asList(buildChickenStack(), buildChickenStack());
//	}
//	@Override
//	public ItemStack buildCaughtFromStack() {
//		ItemStack stack = new ItemStack(Items.CHICKEN_SPAWN_EGG);
//		
//		//ItemMonsterPlacer.applyEntityIdToItemStack(stack, new ResourceLocation("chicken"));
//		return stack;
//	}
//	@Override
//	public String toString() {
//		return "DataChickenVanilla [name=" + getName() + "]";
//	}
//	public String getChickenType() {
//		return "minecraft:vanilla";
//	}

}
