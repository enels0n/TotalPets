package ru.enelson.total.pets.data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import ru.enelson.total.pets.utils.Utils;

public class Pet {
	
	private Player player;
	private NPC npc;
	private Location lastPlayerLocation;
	
	Pet(Player player) {
		this.player = player;
		this.lastPlayerLocation = player.getLocation().clone();
		this.npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.HUSK, player.getName() + "'s Pet");
		this.npc.spawn(this.lastPlayerLocation);
		
		if(this.npc.getEntity() instanceof LivingEntity) {
			EntityEquipment eq = ((LivingEntity)this.npc.getEntity()).getEquipment();
			eq.setHelmet(Utils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTk5YTU4YjE1ZGVjY2Y3MzdjMWM1NWQzMmEyYWEwYTc5MjM5NmRmNzM3OWIwMTAzMzkzOTkwZWRjZWU4MzM0OSJ9fX0="));
			//eq.setBoots(new ItemStack(Material.LEATHER_BOOTS));
			//eq.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
			//eq.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
		}
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public NPC getNPC() {
		return this.npc;
	}
	
	public Location getLocation() {
		return this.npc.getStoredLocation();
	}
	
	public Location getLastPlayerLocation() {
		return this.lastPlayerLocation;
	}
	
	public void setLastPlayerLocation() {
		this.lastPlayerLocation = this.player.getLocation().clone();
	}
	
	public void remove() {
		this.npc.despawn();
		CitizensAPI.getNPCRegistry().deregister(this.npc);
	}
}
