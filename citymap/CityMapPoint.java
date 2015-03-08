package dyrewulf.citybiome.citymap;

import net.minecraft.world.World;


public class CityMapPoint
{
	public int chunkX;
	public int chunkZ;
	public int streetsDirection;
	
	private static final int NORTH = 1;
	private static final int EAST = 2;
	private static final int SOUTH = 4;
	private static final int WEST = 8;
	
	public CityMapPoint(int chunkX, int chunkZ)
	{
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
		this.streetsDirection = 0;
	}

	public CityMapPoint setDirection(int direction)
	{
		this.streetsDirection = direction;
		return this;
	}
	
	public int goesNorth()
	{
		if((streetsDirection & NORTH) == NORTH) return SOUTH;
		return 0;
	}
	
	public int goesEast()
	{
		if((streetsDirection & EAST) == EAST) return WEST;
		return 0;
	}
	
	public int goesSouth()
	{
		if((streetsDirection & SOUTH) == SOUTH) return NORTH;
		return 0;
	}
	
	public int goesWest()
	{
		if((streetsDirection & WEST) == WEST) return EAST;
		return 0;
	}
}


/*	    W S E N
 * 	 0  0 0 0 0
 * 	 1  0 0 0 1
 * 	 2  0 0 1 0
 * 	 3  0 0 1 1
 * 	 4  0 1 0 0
 * 	 5  0 1 0 1
 * 	 6  0 1 1 0
 * 	 7  0 1 1 1
 * 	 8  1 0 0 0
	 9  1 0 0 1
	10  1 0 1 0
	11  1 0 1 1
	12  1 1 0 0
	13  1 1 0 1
	14  1 1 1 0
	15  1 1 1 1
*/	