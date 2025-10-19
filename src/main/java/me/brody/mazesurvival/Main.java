package me.brody.mazesurvival;

import me.brody.mazesurvival.bounds.AreaProtectionManager;
import me.brody.mazesurvival.brewing.BrewingMenu;
import me.brody.mazesurvival.bounds.CollisionManager;
import me.brody.mazesurvival.daynightcycle.DayNightCycle;
import me.brody.mazesurvival.enchantment.EnchantingController;
import me.brody.mazesurvival.enchantment.MazeEnchantment;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.recipe.CustomRecipes;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.listener.*;
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
import me.brody.mazesurvival.wanderingtrader.WanderingTraderManager;
import me.brody.mazesurvival.listener.enchantment.MazeRunnerEnchantmentManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.brody.mazesurvival.command.CommandManager;
import me.brody.mazesurvival.maze.MazeManager;

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
	private BossListener bossListener;

	@Override
	public void onEnable() {
		getConfig().options().copyDefaults();
		saveDefaultConfig();

		MazeEnchantment.init(this);
		LootTable.init(this);
		CustomMob.init(this);
		MobSpawnPool.init();
		MazeRegionBase.initMazeBases();
		ItemGrade.register();
		CustomItem.register();
		CustomMob.register();
		CustomRecipes.register();
		MazeGridBase.register();
		mazeManager = new MazeManager(this);
		profileManager = new ProfileManager(this);
		dayNightCycle = new DayNightCycle(this, mazeManager);
		mobManager = new MobManager(this, dayNightCycle);
		wanderingTraderManager = new WanderingTraderManager(this, dayNightCycle);
		enchantingController = new EnchantingController(this);
		areaProtectionManager = new AreaProtectionManager(this);
		collisionManager = new CollisionManager(this);
		gladeDoorListener = new GladeDoorListener(this, dayNightCycle);
		respawnManager = new RespawnManager(this);
		bossListener = new BossListener(this);

		// Registering the executor for the "ms" command
		getCommand("ms").setExecutor(new CommandManager(this));
		// Registering Listeners
		getServer().getPluginManager().registerEvents(profileManager, this);
		getServer().getPluginManager().registerEvents(mobManager, this);
		getServer().getPluginManager().registerEvents(wanderingTraderManager, this);
		getServer().getPluginManager().registerEvents(enchantingController, this);
		getServer().getPluginManager().registerEvents(enchantingController.getNavigationMenu(), this);
		getServer().getPluginManager().registerEvents(areaProtectionManager, this);
		getServer().getPluginManager().registerEvents(collisionManager, this);
		getServer().getPluginManager().registerEvents(respawnManager, this);
		getServer().getPluginManager().registerEvents(bossListener, this);
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

		PlayerHealthManager.getInstance().run(this);
		MazeRunnerEnchantmentManager.getInstance().run(this);
		AmethystSetBonusManager.getInstance().run(this);

		getLogger().info("****************************************");
		getLogger().info("MazeSurvival has been enabled");
		getLogger().info("****************************************");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("****************************************");
		getLogger().info("MazeSurvival has been disabled");
		getLogger().info("****************************************");
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

	public AreaProtectionManager getAreaProtectionManager() {
		return areaProtectionManager;
	}

	public CollisionManager getCollisionManager() {
		return collisionManager;
	}

	public RespawnManager getRespawnManager() {
		return respawnManager;
	}
}
