package me.brody.mazesurvival.maze;

import me.brody.mazesurvival.maze.region.CellOrientation;

public class MazeCell {
	public boolean visited;
	public boolean[] walls;
	
	public MazeCell() {
		visited = false;
		walls = new boolean[4];
		for(int i = 0; i < walls.length; i++)
			walls[i] = true;
	}

	public int getNumberOfStandingWalls() {
		int count = 0;
		for(int i = 0; i < walls.length; i++) {
			if(walls[i])
				count++;
		}

		return count;
	}

	public CellOrientation getCellOrientation() {
		CellOrientation orientation = CellOrientation.INTERSECTION;
		int standingWallCount = getNumberOfStandingWalls();
		if(standingWallCount >= 3)
			orientation = CellOrientation.DEADEND;
		else if(standingWallCount == 2) {
			orientation = walls[0] && walls[2] || walls[1] && walls[3] ? CellOrientation.CORRIDOR : CellOrientation.BEND;
		}
		else if(standingWallCount == 1)
			orientation = CellOrientation.T_INTERSECTION;

		return orientation;
	}
}
