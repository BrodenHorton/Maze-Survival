package me.brody.mazesurvival;

import me.brody.mazesurvival.bounds.AreaProtectionManager;
import me.brody.mazesurvival.brewing.BrewingMenu;
import me.brody.mazesurvival.bounds.CollisionManager;
import me.brody.mazesurvival.daynightcycle.DayNightCycle;
import me.brody.mazesurvival.enchantment.EnchantingController;
import me.brody.mazesurvival.enchantment.MazeEnchantment;
import me.brody.mazesurvival.gamestate.GameState;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.recipe.CustomRecipeCompendium;
import me.brody.mazesurvival.item.recipe.CustomRecipes;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.listener.*;
import me.brody.mazesurvival.listener.boss.BossListener;
import me.brody.mazesurvival.listener.boss.MarkerTeleportListener;
import me.brody.mazesurvival.listener.enchantment.CrusaderEnchantmentListener;
import me.brody.mazesurvival.listener.enchantment.LingeringShotEnchantmentListener;
import me.brody.mazesurvival.listener.enchantment.SoulBoundEnchantmentListener;
import me.brody.mazesurvival.listener.recipe.CraftingRecipeListener;
import me.brody.mazesurvival.listener.setbonus.*;
import me.brody.mazesurvival.loot.chest.LootTable;
import me.brody.mazesurvival.maze.GladeDoorListener;
import me.brody.mazesurvival.maze.grid.MazeGridBase;
import me.brody.mazesurvival.maze.region.MazeRegionBase;
import me.brody.mazesurvival.mob.MobSpawnPool;
import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.mob.MobManager;
import me.brody.mazesurvival.player.ProfileManager;
import me.brody.mazesurvival.save.SaveData;
import me.brody.mazesurvival.wanderingtrader.WanderingTraderManager;
import me.brody.mazesurvival.listener.enchantment.MazeRunnerEnchantmentManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.brody.mazesurvival.command.CommandManager;
import me.brody.mazesurvival.maze.MazeManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends JavaPlugin {
	private MazeManager mazeManager;
	private ProfileManager profileManager;
	private DayNightCycle dayNightCycle;
	private MobManager mobManager;
	private WanderingTraderManager wanderingTraderManager;
	private EnchantingController enchantingController;
	private AreaProtectionManager areaProtectionManager;
	private CollisionManager collisionManager;
	private GladeDoorListener gladeDoorListener;
	private RespawnManager respawnManager;
	private GameState gameState;
	private InitializePlayersListener initializePlayersListener;
	private CustomRecipeCompendium customRecipeCompendium;

	@Override
	public void onEnable() {
		getLogger().info("****************************************");
		getLogger().info("MazeSurvival has been enabled");
		getLogger().info("****************************************");

		MazeEnchantment.init(this);
		LootTable.init(this);
		CustomMob.init(this);
		MobSpawnPool.init();
		ItemGrade.register();
		CustomItem.register();
		CustomMob.register();
		CustomRecipes.register();
		MazeRegionBase.initMazeBases();
		MazeGridBase.register();

		if(hasSaveFile()) {
			getLogger().info("Loading MazeSurvival Save File ...");
			loadSaveFile();
		}
		else {
			getLogger().info("No Save File Found.");
			mazeManager = new MazeManager(this);
			profileManager = new ProfileManager(this);
			dayNightCycle = new DayNightCycle(this, mazeManager);
			mobManager = new MobManager(this, dayNightCycle);
			wanderingTraderManager = new WanderingTraderManager(this, dayNightCycle);
			areaProtectionManager = new AreaProtectionManager();
			collisionManager = new CollisionManager();
			respawnManager = new RespawnManager();
			gameState = new GameState();
		}
		enchantingController = new EnchantingController(this);
		gladeDoorListener = new GladeDoorListener(this, dayNightCycle);
		customRecipeCompendium = new CustomRecipeCompendium(this);
		initializePlayersListener = new InitializePlayersListener(this, mazeManager);

		// Registering the executor for the "ms" command
		getCommand("ms").setExecutor(new CommandManager(this));
		// Registering Listeners
		getServer().getPluginManager().registerEvents(mobManager, this);
		getServer().getPluginManager().registerEvents(wanderingTraderManager, this);
		getServer().getPluginManager().registerEvents(enchantingController, this);
		getServer().getPluginManager().registerEvents(enchantingController.getNavigationMenu(), this);
		getServer().getPluginManager().registerEvents(areaProtectionManager, this);
		getServer().getPluginManager().registerEvents(collisionManager, this);
		getServer().getPluginManager().registerEvents(respawnManager, this);
		getServer().getPluginManager().registerEvents(customRecipeCompendium, this);
		getServer().getPluginManager().registerEvents(initializePlayersListener, this);
		getServer().getPluginManager().registerEvents(new BossListener(this), this);
		getServer().getPluginManager().registerEvents(new CrusaderEnchantmentListener(this), this);
		getServer().getPluginManager().registerEvents(new LingeringShotEnchantmentListener(this), this);
		getServer().getPluginManager().registerEvents(new SoulBoundEnchantmentListener(this), this);
		getServer().getPluginManager().registerEvents(new LapisSetBonusListener(this), this);
		getServer().getPluginManager().registerEvents(new MithrilSetBonusListener(this), this);
		getServer().getPluginManager().registerEvents(new OrichalcumSetBonusListener(this), this);
		getServer().getPluginManager().registerEvents(new AdamantiteSetBonusListener(this), this);
		getServer().getPluginManager().registerEvents(new ChestListener(this), this);
		getServer().getPluginManager().registerEvents(new TrapListener(this), this);
		getServer().getPluginManager().registerEvents(new TrapChestListener(this), this);
		getServer().getPluginManager().registerEvents(new BlockLightningFireListener(this), this);
		getServer().getPluginManager().registerEvents(new InventoryEnchantmentListener(this), this);
		getServer().getPluginManager().registerEvents(new BrewingMenu(this), this);
		getServer().getPluginManager().registerEvents(new CraftingRecipeListener(this), this);
		getServer().getPluginManager().registerEvents(new MobDropListener(this), this);
		getServer().getPluginManager().registerEvents(new MarkerTeleportListener(this), this);
		getServer().getPluginManager().registerEvents(new OreDropListener(this), this);
		getServer().getPluginManager().registerEvents(new MobCaptureListener(this), this);

		PlayerHealthManager.getInstance().run(this);
		MazeRunnerEnchantmentManager.getInstance().run(this);
		AmethystSetBonusManager.getInstance().run(this);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Saving Game Data ...");
		saveGameData();

		getLogger().info("****************************************");
		getLogger().info("MazeSurvival has been disabled");
		getLogger().info("****************************************");
	}

	public void saveGameData() {
		SaveData saveData = new SaveData();
		saveData.mazeManager = mazeManager;
		saveData.profileManager = profileManager;
		saveData.dayNightCycle = dayNightCycle;
		saveData.mobManager = mobManager;
		saveData.wanderingTraderManager = wanderingTraderManager;
		saveData.areaProtectionManager = areaProtectionManager;
		saveData.collisionManager = collisionManager;
		saveData.respawnManager = respawnManager;
		saveData.gameState = gameState;

		try {
			FileOutputStream fileOutputSteam = new FileOutputStream(getDataFolder() + "/save.dat");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputSteam);
			objectOutputStream.writeObject(saveData);
			fileOutputSteam.close();
			objectOutputStream.close();
			System.out.println("File Saved");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Saving game data failed ...");
		}
	}

	public void loadSaveFile() {
		try {
			FileInputStream fileInputStream = new FileInputStream(getDataFolder() + "/save.dat");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			SaveData saveData = (SaveData) objectInputStream.readObject();
			mazeManager = saveData.mazeManager;
			profileManager = saveData.profileManager;
			dayNightCycle = saveData.dayNightCycle;
			mobManager = saveData.mobManager;
			Field dayNightCycleField = mobManager.getClass().getField("dayNightCycle");
			dayNightCycleField.setAccessible(true);
			dayNightCycleField.set(mobManager, dayNightCycle);
			wanderingTraderManager = saveData.wanderingTraderManager;
			areaProtectionManager = saveData.areaProtectionManager;
			collisionManager = saveData.collisionManager;
			respawnManager = saveData.respawnManager;
			gameState = saveData.gameState;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Loading game data failed ...");
			System.exit(1);
		}
	}

	private boolean hasSaveFile() {
		return Files.exists(Paths.get(getDataFolder() + "/save.dat"));
	}
	
	public MazeManager getMazeManager() {
		return mazeManager;
	}

	public ProfileManager getProfileManager() {
		return profileManager;
	}

	public DayNightCycle getDayNightCycle() {
		return dayNightCycle;
	}

	public EnchantingController getEnchantingController() {
		return enchantingController;
	}

	public MobManager getMobManager() {
		return mobManager;
	}

	public WanderingTraderManager getWanderingTraderManager() {
		return wanderingTraderManager;
	}

	public AreaProtectionManager getAreaProtectionManager() {
		return areaProtectionManager;
	}

	public CollisionManager getCollisionManager() {
		return collisionManager;
	}

	public RespawnManager getRespawnManager() {
		return respawnManager;
	}

	public GameState getGameState() {
		return gameState;
	}

	public CustomRecipeCompendium getCustomRecipeCompendium() {
		return customRecipeCompendium;
	}
}
