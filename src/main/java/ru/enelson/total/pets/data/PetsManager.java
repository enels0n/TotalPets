package ru.enelson.total.pets.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import net.citizensnpcs.api.npc.NPC;

public class PetsManager {
	
	private List<Pet> pets;
	
	public PetsManager() {
		this.pets = new ArrayList<Pet>();
	}
	
	public Pet createPet(Player player) {
		Pet pet = this.getPet(player);
		if(pet != null)
			return pet;
		
		pet = new Pet(player);
		this.pets.add(pet);
		return pet;
	}
	
	public Pet getPet(Player player) {
		return this.pets.stream().filter(p -> p.getPlayer().equals(player)).findFirst().orElse(null);
	}
	
	public Pet getPet(NPC npc) {
		return this.pets.stream().filter(p -> p.getNPC().equals(npc)).findFirst().orElse(null);
	}
	
	public void removePet(Pet pet) {
		if(pet == null)
			return;
		pet.remove();
		this.pets.remove(pet);
	}
	
	public void removePet(NPC npc) {
		this.removePet(this.getPet(npc));
	}
	
	public void removePet(Player player) {
		this.removePet(this.getPet(player));
	}
	
	public void deInit() {
		this.pets.forEach(p -> p.remove());
	}
}
