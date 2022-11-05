package ru.enelson.total.pets.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import ru.enelson.total.pets.TotalPets;
import ru.enelson.total.pets.data.Pet;

public class TestHandler implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		TotalPets.pm.createPet(e.getPlayer());
	}
	

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Pet pet = TotalPets.pm.getPet(e.getPlayer());
		if(pet == null)
			return;
		//double length = pet.getLastPlayerLocation().distance(e.getPlayer().getLocation());
		double distance = e.getPlayer().getLocation().distance(pet.getLocation());
		
		if(distance <= 5)
			return;
		
		if(distance > 10) {
			pet.getNPC().teleport(e.getPlayer().getLocation(), TeleportCause.PLUGIN);
			pet.setLastPlayerLocation();
		}
		else {
			pet.getNPC().getNavigator().setTarget(e.getPlayer().getLocation());
			pet.setLastPlayerLocation();
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		TotalPets.pm.removePet(e.getPlayer());
	}
}
