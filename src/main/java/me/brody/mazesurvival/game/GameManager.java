package me.brody.mazesurvival.game;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.bounds.AreaProtectionManager;
import me.brody.mazesurvival.bounds.CollisionManager;
import me.brody.mazesurvival.brewing.BrewingMenu;
import me.brody.mazesurvival.daynightcycle.DayNightCycle;
import me.brody.mazesurvival.enchantment.EnchantingController;
import me.brody.mazesurvival.enchantment.MazeEnchantment;
import me.brody.mazesurvival.item.CustomItem;
import me.brody.mazesurvival.item.ItemGrade;
import me.brody.mazesurvival.item.recipe.CustomRecipeCompendium;
import me.brody.mazesurvival.item.recipe.CustomRecipes;
import me.brody.mazesurvival.listener.*;
import me.brody.mazesurvival.listener.boss.BossListener;
import me.brody.mazesurvival.listener.boss.MarkerTeleportListener;
import me.brody.mazesurvival.listener.enchantment.CrusaderEnchantmentListener;
import me.brody.mazesurvival.listener.enchantment.LingeringShotEnchantmentListener;
import me.brody.mazesurvival.listener.enchantment.MazeRunnerEnchantmentManager;
import me.brody.mazesurvival.listener.enchantment.SoulBoundEnchantmentListener;
import me.brody.mazesurvival.listener.mob.*;
import me.brody.mazesurvival.listener.recipe.CraftingRecipeListener;
import me.brody.mazesurvival.listener.setbonus.*;
import me.brody.mazesurvival.loot.chest.LootTable;
import me.brody.mazesurvival.maze.GladeDoorListener;
import me.brody.mazesurvival.maze.MazeManager;
import me.brody.mazesurvival.maze.grid.MazeGridBase;
import me.brody.mazesurvival.maze.region.MazeRegionBase;
import me.brody.mazesurvival.mob.MobManager;
import me.brody.mazesurvival.mob.MobSpawnPool;
import me.brody.mazesurvival.mob.custom.CustomMob;
import me.brody.mazesurvival.player.ProfileManager;
import me.brody.mazesurvival.save.SaveData;
import me.brody.mazesurvival.wanderingtrader.WanderingTraderManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;

public class GameManager {
    private final Main plugin;
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
    private boolean isGameRunning;
    private boolean isDebugModeEnabled;
    private boolean shouldSaveGameData;

    public GameManager(Main plugin) {
        this.plugin = plugin;
        isGameRunning = false;
        isDebugModeEnabled = false;
        shouldSaveGameData = true;
    }

    public void init() {
        MazeEnchantment.init(plugin);
        LootTable.init(plugin);
        CustomMob.init(plugin);
        MobSpawnPool.init();
        ItemGrade.register();
        CustomItem.register();
        CustomMob.register();
        CustomRecipes.register();
        MazeRegionBase.initMazeBases();
        MazeGridBase.register();

        if(hasSaveFile())
            loadSaveFile();
        else
            plugin.getLogger().info("No Save File Found.");
    }

    public void startGame(MazeGridBase gridBase, Location gridOrigin, boolean shouldEnableDebugMode) {
        if(gridBase == null || gridOrigin == null) {
            plugin.getLogger().warning("Invalid arguments when starting game. gridBase: " + gridBase + ", gridOrigin: " + gridOrigin);
            return;
        }

        setGameRules(gridOrigin.getWorld());
        newSaveFile(shouldEnableDebugMode);
        mazeManager.generateMaze(gridBase, gridOrigin);
    }

    public void newSaveFile(boolean shouldEnableDebugMode) {
        plugin.getLogger().info("New game created");
        plugin.getServer().getScheduler().cancelTasks(plugin);
        HandlerList.unregisterAll(plugin);
        mazeManager = new MazeManager(plugin);
        profileManager = new ProfileManager(plugin);
        dayNightCycle = new DayNightCycle(plugin, mazeManager);
        mobManager = new MobManager(plugin, dayNightCycle);
        wanderingTraderManager = new WanderingTraderManager(plugin, dayNightCycle);
        areaProtectionManager = new AreaProtectionManager();
        collisionManager = new CollisionManager();
        respawnManager = new RespawnManager();
        gladeDoorListener = new GladeDoorListener(plugin, dayNightCycle);
        gameState = new GameState();
        isGameRunning = false;
        isDebugModeEnabled = shouldEnableDebugMode;

        enchantingController = new EnchantingController(plugin);
        customRecipeCompendium = new CustomRecipeCompendium(plugin);
        initializePlayersListener = new InitializePlayersListener(plugin, mazeManager);

        registerListeners();
        runScheduledTasks();
        mazeManager.onMazeConstructionFinished.addListener((o, e) -> isGameRunning = true);
    }

    private void setGameRules(World world) {
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.DISABLE_RAIDS, true);
        world.setGameRule(GameRule.DO_INSOMNIA, false);
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
        world.setGameRule(GameRule.MOB_GRIEFING, false);
    }

    private void registerListeners() {
        registerListener(mobManager);
        registerListener(wanderingTraderManager);
        registerListener(enchantingController);
        registerListener(enchantingController.getNavigationMenu());
        registerListener(areaProtectionManager);
        registerListener(collisionManager);
        registerListener(respawnManager);
        registerListener(customRecipeCompendium);
        registerListener(initializePlayersListener);
        registerListener(new DurabilityListener());
        registerListener(new CrusaderEnchantmentListener(plugin));
        registerListener(new LingeringShotEnchantmentListener(plugin));
        registerListener(new SoulBoundEnchantmentListener(plugin));
        registerListener(new LapisSetBonusListener(plugin));
        registerListener(new MithrilSetBonusListener(plugin));
        registerListener(new OrichalcumSetBonusListener(plugin));
        registerListener(new AdamantiteSetBonusListener(plugin));
        registerListener(new ChestListener(plugin));
        registerListener(new TrapListener(plugin));
        registerListener(new TrapChestListener(plugin));
        registerListener(new BlockLightningFireListener(plugin));
        registerListener(new InventoryEnchantmentListener(plugin));
        registerListener(new BrewingMenu(plugin));
        registerListener(new CraftingRecipeListener(plugin));
        registerListener(new MobDropListener(plugin));
        registerListener(new BossListener(plugin));
        registerListener(new MarkerTeleportListener(plugin));
        registerListener(new OreDropListener(plugin));
        registerListener(new GoldenPickaxeBlockBreakListener());
        registerListener(new MobCaptureListener(plugin));
        registerListener(new SlimeSplitListener());
        registerListener(new SlimePoisonEffectListener());
        registerListener(new MagmaCubeFireEffectListener());
    }

    private void registerListener(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    private void runScheduledTasks() {
        PlayerHealthManager.getInstance().run(plugin);
        MazeRunnerEnchantmentManager.getInstance().run(plugin);
        AmethystSetBonusManager.getInstance().run(plugin);
    }

    public void saveGameData() {
        if(!isGameRunning || !shouldSaveGameData)
            return;

        plugin.getLogger().info("Saving Game Data ...");
        SaveData saveData = new SaveData();
        saveData.mazeManager = mazeManager;
        saveData.profileManager = profileManager;
        saveData.dayNightCycle = dayNightCycle;
        saveData.mobManager = mobManager;
        saveData.wanderingTraderManager = wanderingTraderManager;
        saveData.areaProtectionManager = areaProtectionManager;
        saveData.collisionManager = collisionManager;
        saveData.respawnManager = respawnManager;
        saveData.gladeDoorListener = gladeDoorListener;
        saveData.gameState = gameState;
        saveData.isDebugModeEnabled = isDebugModeEnabled;

        try {
            FileOutputStream fileOutputSteam = new FileOutputStream(getSaveFile());
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
        plugin.getLogger().info("Loading MazeSurvival Save File ...");
        try {
            FileInputStream fileInputStream = new FileInputStream(getSaveFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SaveData saveData = (SaveData) objectInputStream.readObject();
            mazeManager = saveData.mazeManager;
            profileManager = saveData.profileManager;
            dayNightCycle = saveData.dayNightCycle;
            mobManager = saveData.mobManager;
            Field dayNightCycleField = mobManager.getClass().getDeclaredField("dayNightCycle");
            dayNightCycleField.setAccessible(true);
            dayNightCycleField.set(mobManager, dayNightCycle);
            wanderingTraderManager = saveData.wanderingTraderManager;
            areaProtectionManager = saveData.areaProtectionManager;
            collisionManager = saveData.collisionManager;
            respawnManager = saveData.respawnManager;
            gladeDoorListener = saveData.gladeDoorListener;
            gameState = saveData.gameState;
            isDebugModeEnabled = saveData.isDebugModeEnabled;

            dayNightCycle.registerEvents(mazeManager);
            mobManager.registerEvents();
            wanderingTraderManager.registerEvents(dayNightCycle);
            gladeDoorListener.registerEvents(dayNightCycle);

            enchantingController = new EnchantingController(plugin);
            customRecipeCompendium = new CustomRecipeCompendium(plugin);
            initializePlayersListener = new InitializePlayersListener(plugin, mazeManager);
            shouldSaveGameData = true;
            isGameRunning = true;

            registerListeners();
            runScheduledTasks();
            mazeManager.onMazeConstructionFinished.addListener((o, e) -> isGameRunning = true);

            dayNightCycle.startDayNightCycle();
            mobManager.startMobSpawning();
        }
        catch(Exception e) {
            System.out.println("Loading game data failed ...");
            shouldSaveGameData = false;
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }

    private boolean hasSaveFile() {
        return Files.exists(getSaveFile().toPath());
    }

    private File getSaveFile() {
        return new File(plugin.getDataFolder() + "/save.dat");
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

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public boolean isDebugModeEnabled() {
        return isDebugModeEnabled;
    }
}
