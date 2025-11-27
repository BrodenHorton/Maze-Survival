package me.brody.mazesurvival;

import me.brody.mazesurvival.bounds.AreaProtectionManager;
import me.brody.mazesurvival.bounds.CollisionManager;
import me.brody.mazesurvival.brewing.BrewingMenu;
import me.brody.mazesurvival.daynightcycle.DayNightCycle;
import me.brody.mazesurvival.enchantment.EnchantingController;
import me.brody.mazesurvival.game.GameManager;
import me.brody.mazesurvival.game.GameState;
import me.brody.mazesurvival.item.recipe.CustomRecipeCompendium;
import me.brody.mazesurvival.listener.*;
import me.brody.mazesurvival.listener.boss.BossListener;
import me.brody.mazesurvival.listener.boss.MarkerTeleportListener;
import me.brody.mazesurvival.listener.enchantment.CrusaderEnchantmentListener;
import me.brody.mazesurvival.listener.enchantment.LingeringShotEnchantmentListener;
import me.brody.mazesurvival.listener.enchantment.SoulBoundEnchantmentListener;
import me.brody.mazesurvival.listener.recipe.CraftingRecipeListener;
import me.brody.mazesurvival.listener.setbonus.*;
import me.brody.mazesurvival.listener.enchantment.MazeRunnerEnchantmentManager;
import me.brody.mazesurvival.maze.MazeManager;
import me.brody.mazesurvival.mob.MobManager;
import me.brody.mazesurvival.player.ProfileManager;
import me.brody.mazesurvival.wanderingtrader.WanderingTraderManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.brody.mazesurvival.command.CommandManager;

import java.io.IOException;
import java.nio.file.Files;

public class Main extends JavaPlugin {
	private GameManager gameManager;

	@Override
	public void onEnable() {
		getLogger().info("****************************************");
		getLogger().info("MazeSurvival has been enabled");
		getLogger().info("****************************************");

		createDataFolder();

		gameManager = new GameManager(this);
		gameManager.init();

		getCommand("ms").setExecutor(new CommandManager(this));
	}
	
	@Override
	public void onDisable() {
		gameManager.saveGameData();

		getLogger().info("****************************************");
		getLogger().info("MazeSurvival has been disabled");
		getLogger().info("****************************************");
	}

	private void createDataFolder() {
		try {
			if(!getDataFolder().exists())
				Files.createDirectory(getDataFolder().toPath());
		}
		catch(IOException e) {
			getLogger().severe("Data Folder could not be created");
			e.printStackTrace();
		}
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public MazeManager getMazeManager() {
		return gameManager.getMazeManager();
	}

	public ProfileManager getProfileManager() {
		return gameManager.getProfileManager();
	}

	public DayNightCycle getDayNightCycle() {
		return gameManager.getDayNightCycle();
	}

	public EnchantingController getEnchantingController() {
		return gameManager.getEnchantingController();
	}

	public MobManager getMobManager() {
		return gameManager.getMobManager();
	}

	public WanderingTraderManager getWanderingTraderManager() {
		return gameManager.getWanderingTraderManager();
	}

	public AreaProtectionManager getAreaProtectionManager() {
		return gameManager.getAreaProtectionManager();
	}

	public CollisionManager getCollisionManager() {
		return gameManager.getCollisionManager();
	}

	public RespawnManager getRespawnManager() {
		return gameManager.getRespawnManager();
	}

	public GameState getGameState() {
		return gameManager.getGameState();
	}

	public CustomRecipeCompendium getCustomRecipeCompendium() {
		return gameManager.getCustomRecipeCompendium();
	}

	public boolean isGameRunning() {
		return gameManager.isGameRunning();
	}
}
