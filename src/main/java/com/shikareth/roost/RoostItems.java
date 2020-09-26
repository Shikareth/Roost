package com.shikareth.roost;

import com.shikareth.roost.item.CatcherItem;
import com.shikareth.roost.item.ChickenItem;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RoostItems {

	public static final Properties DEFAULT_PROPERTIES = new Properties().group(Roost.TAB);
	
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Roost.MODID);
	
	public static final RegistryObject<Item> ITEM_CATCHER = REGISTRY.register("catcher", () -> new CatcherItem());
	public static final RegistryObject<Item> ITEM_CHICKEN = REGISTRY.register("chicken", () -> new ChickenItem());
}
