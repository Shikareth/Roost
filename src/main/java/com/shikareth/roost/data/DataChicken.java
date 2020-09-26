package com.shikareth.roost.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.shikareth.roost.RoostItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class DataChicken {

	public static final String CHICKEN_ID_KEY = "Chicken";

	private static boolean isChicken(ItemStack stack) {
		return stack.getItem() == RoostItems.ITEM_CHICKEN.get();
	}
	
	public static List<DataChicken> getAllChickens() {
		List<DataChicken> chickens = new LinkedList<DataChicken>();
		DataChickenVanilla.addAllChickens(chickens);

		return null;
	}
	public static DataChicken getDataFromStack(ItemStack stack) {
		if (!isChicken(stack)) return null;

		DataChicken data = null;

		//if (ModList.get().isLoaded("")) return null;
		if (data == null) data = DataChickenVanilla.getDataFromStack(stack);

		return data;
	}
	public static DataChicken getDataFromEntity(LivingEntity target) {
		if (target == null) return null;
		
		DataChicken data = null;
		
		//if (ModList.get().isLoaded("")) return null;
		if (data == null) data = DataChickenVanilla.getDataFromEntity(target);
		
		return data;
	}
	public static DataChicken getDataFromTooltipNBT(CompoundNBT tag) {
		if (tag == null) return null;

		DataChicken data = null;

		//if (Loader.isModLoaded("chickens")) data = DataChickenModded.getDataFromTooltipNBT(tag);

		if (data == null) data = DataChickenVanilla.getDataFromTooltipNBT(tag);

		return data;
	}
	public static DataChicken getDataFromName(String name) {
		DataChicken data = null;

		//if (Loader.isModLoaded("chickens")) data = DataChickenModded.getDataFromName(name);

		if (data == null) data = DataChickenVanilla.getDataFromName(name);

		return data;
	}

	private String name;
	protected String i18nName;
	protected Random rand = new Random();

	public DataChicken(String name) {
		super();
		this.name = name;
	}
	
	public ItemStack getItemStack() {
		return null;
	}
	
//	public void addInfoToTooltip(List<String> tooltip) {
//	}
//	public boolean hasParents() {
//		return false;
//	}
//	public List<ItemStack> buildParentChickenStack() {
//		return null;
//	}
//	public ItemStack buildChickenStack() {
//		return ItemStack.EMPTY;
//	}
//	public ItemStack buildCaughtFromStack() {
//		return ItemStack.EMPTY;
//	}
//	public ChickenEntity buildEntity(World world) {
//		return null;
//	}
//	public CompoundNBT buildTooltipNBT() {
//		return null;
//	}
//	public void spawnEntity(World world, BlockPos pos) {
//	}
//	public String getChickenType() {
//		return null;
//	}
//	public String getTextureName() {
//		return null;
//	}
//	private static ItemStack createChildStack(DataChicken chickenA, DataChicken chickenB, World world) {
//		if (chickenA.getClass() != chickenB.getClass()) return chickenA.buildChickenStack();
//		ChickenEntity parentA = chickenA.buildEntity(world);
//		ChickenEntity parentB = chickenB.buildEntity(world);
//		if (parentA == null || parentB == null) return ItemStack.EMPTY;
//		DataChicken childData = DataChicken.getDataFromEntity(parentA.createChild(parentB));
//		if (childData == null) return ItemStack.EMPTY;
//		return childData.buildChickenStack();
//	}
//	public ItemStack createChildStack(DataChicken other, World world) {
//		if (rand.nextBoolean()) return createChildStack(this, other, world);
//		return createChildStack(other, this, world);
//	}
//	public ItemStack createDropStack() {
//		return ItemStack.EMPTY;
//	}
//	public int getAddedTime(ItemStack stack) {
//		return Math.max(0, stack.getCount());
//	}
//	public int getLayTime() {
//		return 6000 + rand.nextInt(6000);
//	}
//	public String getName() {
//		return name;
//	}
//	public String getDisplayName() {
//		return I18n.format(i18nName);
//	}
//	public boolean isEqual(DataChicken other) {
//		return true;
//	}
//	public String getDisplaySummary() {
//		return getDisplayName();
//	}
}
