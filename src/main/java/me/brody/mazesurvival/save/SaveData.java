package me.brody.mazesurvival.save;

import me.brody.mazesurvival.bounds.AreaProtectionManager;
import me.brody.mazesurvival.bounds.CollisionManager;
import me.brody.mazesurvival.daynightcycle.DayNightCycle;
import me.brody.mazesurvival.game.GameState;
import me.brody.mazesurvival.listener.RespawnManager;
import me.brody.mazesurvival.maze.GladeDoorListener;
import me.brody.mazesurvival.maze.MazeManager;
import me.brody.mazesurvival.mob.MobManager;
import me.brody.mazesurvival.player.ProfileManager;
import me.brody.mazesurvival.wanderingtrader.WanderingTraderManager;

import java.io.Serializable;

public class SaveData implements Serializable {
    public MazeManager mazeManager;
    public ProfileManager profileManager;
    public DayNightCycle dayNightCycle;
    public MobManager mobManager;
    public WanderingTraderManager wanderingTraderManager;
    public AreaProtectionManager areaProtectionManager;
    public CollisionManager collisionManager;
    public RespawnManager respawnManager;
    public GladeDoorListener gladeDoorListener;
    public GameState gameState;
    public boolean isDebugModeEnabled;

    public SaveData() {}

}
