package me.brody.mazesurvival.wanderingtrader;

import me.brody.mazesurvival.Main;
import me.brody.mazesurvival.daynightcycle.DayNightCycle;
import me.brody.mazesurvival.event.eventargs.EventArgs;
import me.brody.mazesurvival.maze.grid.MazeGrid;
import me.brody.mazesurvival.maze.region.MazeRegion;
import me.brody.mazesurvival.namespacekey.NamespacedKeys;
import me.brody.mazesurvival.utils.ChatUtils;
import me.brody.mazesurvival.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.function.Supplier;

public class WanderingTraderManager implements Listener, Serializable {
    private static final Random RNG = new Random();

    private transient Main plugin;

    private List<UUID> traderUuids;
    private Map<UUID, Integer> havenTraderAmountByRegionId;

    public WanderingTraderManager(Main plugin, DayNightCycle dayNightCycle) {
        this.plugin = plugin;

        traderUuids = new ArrayList<>();
        havenTraderAmountByRegionId = new HashMap<>();

        dayNightCycle.onStartOfDay.addListener(this::updateDayWanderingTraders);
        dayNightCycle.onStartOfNight.addListener(this::updateNightWanderingTraders);
    }

    private void updateDayWanderingTraders(Object sender, EventArgs e) {
        despawnWanderingTraders();
        havenTraderAmountByRegionId.clear();
        MazeGrid grid = plugin.getMazeManager().getGrid();
        for(int i = 0; i < grid.getRegions().size(); i++) {
            MazeRegion region = grid.getRegions().get(i);
            int traderCount = (int)Math.floor(Math.sqrt(region.getRegionDimensions().x * region.getRegionDimensions().y)) + 1;
            for(int j = 0; j < traderCount; j++) {
                int row, column;
                int count = 0;
                do {
                    row = RNG.nextInt(0, region.getMazeCells().length);
                    column = RNG.nextInt(0, region.getMazeCells()[0].length);
                    count++;
                }
                while(region.getMazeCells()[row][column] == null && count < 5);
                if(region.getMazeCells()[row][column] == null)
                    continue;

                Location traderLocation = LocationUtils.copy(grid.getRegionCellWorldCenter(region, row, column));
                traderLocation.setY(traderLocation.getY() + 1);
                Villager trader = (Villager) plugin.getServer().getWorld(traderLocation.getWorld().getUID()).spawnEntity(traderLocation, EntityType.VILLAGER);
                trader.setAI(false);
                trader.setInvulnerable(true);
                trader.setProfession(Villager.Profession.CARTOGRAPHER);
                trader.getPersistentDataContainer().set(NamespacedKeys.WANDERING_TRADER, PersistentDataType.STRING, "maze");
                traderUuids.add(trader.getUniqueId());
                plugin.getLogger().info("Spawned Wandering Trader into region index: " + i + ". At location: " + traderLocation.getX() + ", " + traderLocation.getZ());
            }
        }
    }

    private void updateNightWanderingTraders(Object sender, EventArgs e) {
        despawnWanderingTraders();
        MazeGrid grid = plugin.getMazeManager().getGrid();
        Location gladeCenter = grid.getGladeWorldCenter();
        gladeCenter.setY(gladeCenter.getY() + 1);
        for(Map.Entry<UUID, Integer> regionTraderCountEntry : havenTraderAmountByRegionId.entrySet()) {
            Location havenCenter;
            MazeRegion region = grid.getRegionByUuid(regionTraderCountEntry.getKey());
            if(region == null || region.getHaven() == null)
                havenCenter = gladeCenter;
            else {
                havenCenter = grid.getRegionHavenWorldCenter(region);
                havenCenter.setY(havenCenter.getY() + 1);
            }
            for(int i = 0; i < regionTraderCountEntry.getValue(); i++) {
                Villager trader = (Villager) plugin.getServer().getWorld(gladeCenter.getWorld().getUID()).spawnEntity(havenCenter, EntityType.VILLAGER);
                trader.setInvulnerable(true);
                trader.setProfession(Villager.Profession.CARTOGRAPHER);
                trader.getPersistentDataContainer().set(NamespacedKeys.WANDERING_TRADER, PersistentDataType.STRING, "haven");
                List<Supplier<MerchantRecipe>> recipeSuppliers = region.getTradeTable().getWeightedValuesNoRepeat(4);
                List<MerchantRecipe> recipes = new ArrayList<>();
                for(int j = 0; j < recipeSuppliers.size(); j++)
                    recipes.add(recipeSuppliers.get(j).get());
                trader.setRecipes(recipes);
                traderUuids.add(trader.getUniqueId());
            }
        }
    }

    private void despawnWanderingTraders() {
        MazeGrid grid = plugin.getMazeManager().getGrid();
        for(int i = traderUuids.size() - 1; i >= 0; i--) {
            for(Entity entity : plugin.getServer().getWorld(grid.getGridOrigin().getWorld().getUID()).getEntities()) {
                if(entity.getUniqueId().equals(traderUuids.get(i))) {
                    entity.remove();
                    traderUuids.remove(i);
                    break;
                }
            }
        }

        if(!traderUuids.isEmpty()) {
            plugin.getLogger().warning("Not all Wandering Traders were properly despawned in WanderingTraderManager#despawnWanderingTraders.");
            traderUuids.clear();
        }
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        plugin = JavaPlugin.getPlugin(Main.class);
    }

    @Override
    public String toString() {
        return "WanderingTraderManager{" +
                "traderUuids=" + traderUuids +
                ", havenTraderAmountByRegionId=" + havenTraderAmountByRegionId +
                '}';
    }

    @EventHandler
    public void mazeTraderInteraction(PlayerInteractEntityEvent e) {
        if(plugin.getMazeManager().getGrid() == null)
            return;
        MazeGrid grid = plugin.getMazeManager().getGrid();
        if(plugin.getProfileManager().getProfileOf(e.getPlayer()) == null)
            return;
        if(!(e.getRightClicked() instanceof Villager))
            return;
        Villager trader = (Villager) e.getRightClicked();
        if(!trader.getPersistentDataContainer().get(NamespacedKeys.WANDERING_TRADER, PersistentDataType.STRING).equals("maze"))
            return;
        if(grid.getRegionAt(trader.getLocation()) == null)
            return;

        MazeRegion region = grid.getRegionAt(trader.getLocation());
        int traderCount = havenTraderAmountByRegionId.get(region.getUuid()) != null ? havenTraderAmountByRegionId.get(region.getUuid()) + 1 : 1;
        havenTraderAmountByRegionId.put(region.getUuid(), traderCount);
        traderUuids.remove(trader.getUniqueId());
        trader.remove();
        e.setCancelled(true);
        e.getPlayer().playSound(e.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 0.8f);
        ChatUtils.msg(e.getPlayer(), "&aWandering Trader has been rescued in region: &f" + region.getUuid());
    }

    @EventHandler
    public void cancelVillageProfessionChange(VillagerCareerChangeEvent e) {
        if(!e.getEntity().getPersistentDataContainer().get(NamespacedKeys.WANDERING_TRADER, PersistentDataType.STRING).equals("haven"))
            return;

        e.setCancelled(true);
    }

}
