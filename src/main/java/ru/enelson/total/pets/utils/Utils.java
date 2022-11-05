package ru.enelson.total.pets.utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Utils {
	@SuppressWarnings("deprecation")
	public static ItemStack getHead(String value) {
		UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
		return Bukkit.getUnsafe().modifyItemStack(new ItemStack(Material.PLAYER_HEAD, 1, (short) 3), "{SkullOwner:{Id:\"" + hashAsId
				+ "\",Properties:{textures:[{Value:\"" + value + "\"}]},Name: \"test\"}}");
	}
}
