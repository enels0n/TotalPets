package ru.enelson.total.pets;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.reflect.ClassPath;

import ru.enelson.total.pets.data.PetsManager;

public class TotalPets extends JavaPlugin {
	public static PetsManager pm;
	
	public void onEnable() {
		pm = new PetsManager();
		
		PluginManager pluginManager = Bukkit.getPluginManager();
		try {
			for (ClassPath.ClassInfo clazzInfo : ClassPath.from(getClassLoader()).getTopLevelClasses("ru.enelson.total.pets.listeners")) {
				Class<?> clazz = Class.forName(clazzInfo.getName());
				if (Listener.class.isAssignableFrom(clazz)) {
					pluginManager.registerEvents((Listener)clazz.getDeclaredConstructor().newInstance(), this);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onDisable() {
		pm.deInit();
	}
}
