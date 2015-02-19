package dyrewulf.citybiome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class GenerateBuildings
{
	public World currentWorld;
	public int chunkX;
	public int chunkZ;
	
	public int[][] skylineXZ = new int[2][2];
	private int[][] Xmins = new int[2][2];
	private int[][] Zmins = new int[2][2];
	private int[][] Xmaxs = new int[2][2];
	private int[][] Zmaxs = new int[2][2];
	private int maxHtAdjusted;
	private int chance;
	Random ran = new Random();
	int baseY = 0;
	private int currentY;
	boolean isXaxis;
	private BuildingMaterials materials;
	
	private int[] interiorwallset = {1, 2, 3, 7, 15, 16};
	
	public GenerateBuildings(World world, int chunkX, int chunkZ, int maxHeight, int chanceInTen, BuildingMaterials mats)
	{
		this.currentWorld = world;
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
		this.maxHtAdjusted = maxHeight - 1;
		this.chance = chanceInTen;
		this.materials = mats;
		
		this.baseY = currentWorld.getTopSolidOrLiquidBlock(chunkX * 16 - 8, chunkZ * 16 - 8);

		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				if(ran.nextInt(10) <= chance)
				{
				skylineXZ[i][j] = ran.nextInt(maxHtAdjusted) + 1;
				} else skylineXZ[i][j] = 0;
					
				if (chunkX > 0)
				{
				Xmins[i][j] = (chunkX * 16) - 15 + (i * 8);
				Xmaxs[i][j] = Xmins[i][j] + 6;
				} else if (chunkX < 0)
				{
					Xmaxs[i][j] = (chunkX * 16) + 15 - (i * 8);
					Xmins[i][j] = Xmaxs[i][j] - 6;
				}
				if (chunkZ > 0)
				{
				Zmins[i][j] = (chunkZ * 16) - 15 + (j * 8);
				Zmaxs[i][j] = Zmins[i][j] + 6;
				} else if (chunkZ < 0)
				{
					Zmaxs[i][j] = (chunkZ * 16) + 15 - (j * 8);
					Zmins[i][j] = Zmaxs[i][j] - 6;
				}
			}
		}

	}
	
	public void makeItWork()
	{	
		boolean ishighest = true;
		for(int k = maxHtAdjusted + 1; k > 0; k--)
		{
			for(int i = 0; i < 2; i++)
			{
				for(int j = 0; j < 2; j++)
				{
					if(skylineXZ[i][j] == k)
						{
						generateBuilding(i, j, ishighest);
						ishighest = false;
						}
				}
			}
		}
	}
	
	
	private void generateBuilding(int X, int Z, boolean isHighest)
	{
		createFoundation(Xmins[X][Z], Zmins[X][Z], Xmaxs[X][Z], Zmaxs[X][Z]);
		for(int k = 0; k < skylineXZ[X][Z]; k++)
		{
			int Xconnector = isXconnected(X, Z, k + 1);
			int Zconnector = isZconnected(X, Z, k + 1);
			
			int northWallStyle = randomFromHeight(k);
			int southWallStyle =  randomFromHeight(k);
			int eastWallStyle =  randomFromHeight(k);
			int westWallStyle =  randomFromHeight(k);
			
			createFloor(Xmins[X][Z], Zmins[X][Z], Xmaxs[X][Z], Zmaxs[X][Z], baseY + 3 +(k * 4));
			createEdges(Xmins[X][Z], Zmins[X][Z], Xmaxs[X][Z], Zmaxs[X][Z], baseY + (k * 4));
			
			if(isHighest)
			{
				if(k < skylineXZ[X][Z] - 1)
				{
					if(k == 0)
					{
						createStairsN(Xmins[X][Z], Zmins[X][Z], Xmaxs[X][Z], Zmaxs[X][Z], baseY + (k * 4));
						northWallStyle = ran.nextInt(5) + 11;
					} else if (k % 2 == 0)
					{
						createStairsN(Xmins[X][Z], Zmins[X][Z], Xmaxs[X][Z], Zmaxs[X][Z], baseY + (k * 4));
						northWallStyle = ran.nextInt(5) + 11;
					} else
					{
						createStairsS(Xmins[X][Z], Zmins[X][Z], Xmaxs[X][Z], Zmaxs[X][Z], baseY + (k * 4));
						southWallStyle = ran.nextInt(5) + 11;
					}
				}
			}
			
			switch (Xconnector) {
			case -1:
						if(k == 0) createFoundation(Xmins[X][Z] - 1, Zmins[X][Z], Xmins[X][Z] - 1, Zmaxs[X][Z]);
						createConnection(Xmins[X][Z] - 1, Zmins[X][Z], Xmins[X][Z] - 1, Zmaxs[X][Z], baseY + (k * 4));
						createEastWall(Xmaxs[X][Z], Zmins[X][Z], Xmaxs[X][Z], Zmaxs[X][Z], baseY + (k * 4), eastWallStyle);
						break;
			case  0:	createEastWall(Xmaxs[X][Z], Zmins[X][Z], Xmaxs[X][Z], Zmaxs[X][Z], baseY + (k * 4), eastWallStyle);
						createWestWall(Xmins[X][Z], Zmins[X][Z], Xmins[X][Z], Zmaxs[X][Z], baseY + (k * 4), westWallStyle);
						break;
			case  1:	if(k == 0) createFoundation(Xmaxs[X][Z] + 1, Zmins[X][Z], Xmaxs[X][Z] + 1, Zmaxs[X][Z]);
						createConnection(Xmaxs[X][Z] + 1, Zmins[X][Z], Xmaxs[X][Z] + 1, Zmaxs[X][Z], baseY + (k * 4));
						createWestWall(Xmins[X][Z], Zmins[X][Z], Xmins[X][Z], Zmaxs[X][Z], baseY + (k * 4), westWallStyle);
						break;
			}
			
			switch (Zconnector) {
			case -1:	if(k == 0) createFoundation(Xmins[X][Z], Zmins[X][Z] - 1, Xmaxs[X][Z], Zmins[X][Z] - 1);
						createConnection(Xmins[X][Z], Zmins[X][Z] - 1, Xmaxs[X][Z], Zmins[X][Z] - 1, baseY + (k * 4));
						createNorthWall(Xmins[X][Z], Zmaxs[X][Z], Xmaxs[X][Z], Zmaxs[X][Z], baseY + (k * 4), northWallStyle);
						break;
			case 0:		createNorthWall(Xmins[X][Z], Zmaxs[X][Z], Xmaxs[X][Z], Zmaxs[X][Z], baseY + (k * 4), northWallStyle);
						createSouthWall(Xmins[X][Z], Zmins[X][Z], Xmaxs[X][Z], Zmins[X][Z], baseY + (k * 4), southWallStyle);
						break;
			case 1:		if(k == 0) createFoundation(Xmins[X][Z], Zmaxs[X][Z] + 1, Xmaxs[X][Z], Zmaxs[X][Z] + 1);
						createConnection(Xmins[X][Z], Zmaxs[X][Z] + 1, Xmaxs[X][Z], Zmaxs[X][Z] + 1, baseY + (k * 4));
						createSouthWall(Xmins[X][Z], Zmins[X][Z], Xmaxs[X][Z], Zmins[X][Z], baseY + (k * 4), southWallStyle);
						break;
			}
		}
	}

	private void createStairsS(int xstart, int zstart, int xend, int zend, int y)
	{
		currentWorld.setBlock(xend - 2, y, zstart + 1, materials.stairs, 1, 2);
		currentWorld.setBlock(xend - 3, y, zstart + 1, materials.stairs, 4, 2);
		currentWorld.setBlock(xend - 3, y + 1, zstart + 1, materials.stairs, 1, 2);
		currentWorld.setBlock(xend - 4, y + 1, zstart + 1, materials.stairs, 4, 2);
		currentWorld.setBlock(xend - 4, y + 2, zstart + 1, materials.stairs, 1, 2);
		currentWorld.setBlock(xend - 5, y + 2, zstart + 1, materials.stairs, 4, 2);
		currentWorld.setBlock(xend - 5, y + 2, zstart + 2, materials.stairs, 5, 2);
		currentWorld.setBlock(xend - 5, y + 3, zstart + 2, materials.stairs, 2, 2);
	
		currentWorld.setBlock(xend - 1, y + 3, zstart + 1, Blocks.air);
		currentWorld.setBlock(xend - 2, y + 3, zstart + 1, Blocks.air);
		currentWorld.setBlock(xend - 3, y + 3, zstart + 1, Blocks.air);
		currentWorld.setBlock(xend - 4, y + 3, zstart + 1, Blocks.air);
		currentWorld.setBlock(xend - 5, y + 3, zstart + 1, Blocks.air);
	}

	private void createStairsN(int xstart, int zstart, int xend, int zend, int y)
	{
		currentWorld.setBlock(xstart + 2, y, zend - 1, materials.stairs, 0, 2);
		currentWorld.setBlock(xstart + 3, y, zend - 1, materials.stairs, 5, 2);
		currentWorld.setBlock(xstart + 3, y + 1, zend - 1, materials.stairs, 0, 2);
		currentWorld.setBlock(xstart + 4, y + 1, zend - 1, materials.stairs, 5, 2);
		currentWorld.setBlock(xstart + 4, y + 2, zend - 1, materials.stairs, 0, 2);
		currentWorld.setBlock(xstart + 5, y + 2, zend - 1, materials.stairs, 5, 2);
		currentWorld.setBlock(xstart + 5, y + 2, zend - 2, materials.stairs, 6, 2);
		currentWorld.setBlock(xstart + 5, y + 3, zend - 2, materials.stairs, 3, 2);
		
		
		currentWorld.setBlock(xstart + 1, y + 3, zend -1, Blocks.air);
		currentWorld.setBlock(xstart + 2, y + 3, zend -1, Blocks.air);
		currentWorld.setBlock(xstart + 3, y + 3, zend -1, Blocks.air);
		currentWorld.setBlock(xstart + 4, y + 3, zend -1, Blocks.air);
		currentWorld.setBlock(xstart + 5, y + 3, zend -1, Blocks.air);
	}

	private void createFoundation(int xstart, int zstart, int xend, int zend)
	{
		for(int i = xstart ; i <= xend; i++)
		{
			for(int j = zstart; j <= zend; j++)
			{
				currentWorld.setBlock(i, baseY - 1, j, materials.foundation);
			}
		}
	}
	
	private void createEdges(int xstart, int zstart, int xend, int zend, int ystart)
	{
		for(int i = ystart; i <= ystart + 3; i++)
		{
			if(i < ystart + 3)
			{
				currentWorld.setBlock(xstart, i, zstart, materials.corner);
				currentWorld.setBlock(xstart, i, zend, materials.corner);
				currentWorld.setBlock(xend, i, zstart, materials.corner);
				currentWorld.setBlock(xend, i, zend, materials.corner);
				
			}
			if(i == ystart + 3)
			{
				for(int j = xstart; j <= xend; j++)
				{
					currentWorld.setBlock(j, i, zstart, materials.corner);
					currentWorld.setBlock(j, i, zend, materials.corner);
				}
				for(int j = zstart; j <= zend; j++)
				{
					currentWorld.setBlock(xstart, i, j, materials.corner);
					currentWorld.setBlock(xend, i, j, materials.corner);
				}
			}
			
		}
	}
	
	private void createFloor(int xstart, int zstart, int xend, int zend, int y)
	{
		for(int i = xstart; i <= xend;i++)
		{
			for(int j = zstart; j <= zend;j++)
			{
				currentWorld.setBlock(i, y, j, materials.floor);
			}
		}
		currentWorld.setBlock(getCenter(xend, xstart), y, getCenter(zend, zstart), materials.light);
	}
	
	/*	When calling create X Wall, use wallStyle to determine windows and doors.
	0 (or negatives) will result in an empty space.
	1 - 8 include doors
	9 - 14 are windows only
	15+ will result in a solid wall	
	*/
	
	private void createWestWall(int xstart, int zstart, int xend, int zend, int y, int wallStyle) 
	{	
		if(wallStyle < 0) return;
		
		for(int j = zstart + 1; j < zend; j++)
		{
			currentWorld.setBlock(xstart, y, j, materials.wall);
			currentWorld.setBlock(xstart, y + 1, j, materials.wall);
			currentWorld.setBlock(xstart, y + 2, j, materials.wall);
		}
		
			switch (wallStyle){
			//		XXXXXXX
			//  	X  XXXX
			//		X  XXXX
			case 1: currentWorld.setBlock(xstart, y, zstart + 1, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 1, Blocks.air);
					currentWorld.setBlock(xstart, y, zstart + 2, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.air);
					currentWorld.setBlock(xstart, y, zstart + 1, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 1, Blocks.wooden_door, 8, 2);
					currentWorld.setBlock(xstart, y, zstart + 2, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.wooden_door, 9, 2);
					break;
			//		XXXXXXX
			//  	XX  XXX
			//		XX  XXX
			case 2: currentWorld.setBlock(xstart, y, zstart + 2, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.air);
					currentWorld.setBlock(xstart, y, zstart + 3, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.air);
					currentWorld.setBlock(xstart, y, zstart + 2, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.wooden_door, 8, 2);
					currentWorld.setBlock(xstart, y, zstart + 3, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.wooden_door, 9, 2);
					break;
			//		XXXXXXX
			//  	XXX  XX
			//		XXX  XX
			case 3: currentWorld.setBlock(xstart, y, zstart + 3, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.air);
					currentWorld.setBlock(xstart, y, zstart + 4, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 4, Blocks.air);
					currentWorld.setBlock(xstart, y, zstart + 3, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.wooden_door, 8, 2);
					currentWorld.setBlock(xstart, y, zstart + 4, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 4, Blocks.wooden_door, 9, 2);
					break;
			//		XXXXXXX
			//  	X  X  X
			//		X  X  X
			case 4: currentWorld.setBlock(xstart, y, zstart + 1, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 1, Blocks.air);
					currentWorld.setBlock(xstart, y, zstart + 2, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.air);
					currentWorld.setBlock(xstart, y, zstart + 4, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 4, Blocks.air);
					currentWorld.setBlock(xstart, y, zstart + 5, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 5, Blocks.air);
					currentWorld.setBlock(xstart, y, zstart + 1, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 1, Blocks.wooden_door, 8, 2);
					currentWorld.setBlock(xstart, y, zstart + 2, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.wooden_door, 9, 2);
					currentWorld.setBlock(xstart, y, zstart + 4, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 4, Blocks.wooden_door, 8, 2);
					currentWorld.setBlock(xstart, y, zstart + 5, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 5, Blocks.wooden_door, 9, 2);
					break;
			//		XXXX XX
			//  	XX X XX
			//		XX XXXX
			case 5: currentWorld.setBlock(xstart, y, zstart + 2, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 4, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 4, materials.window);
					currentWorld.setBlock(xstart, y, zstart + 2, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.wooden_door, 9, 2);
					break;
			//		XX XXXX
			//  	XX X XX
			//		XXXX XX
			case 6: currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.air);
					currentWorld.setBlock(xstart, y + 2, zstart + 2, Blocks.air);			
					currentWorld.setBlock(xstart, y, zstart + 4, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 4, Blocks.air);
					currentWorld.setBlock(xstart, y, zstart + 4, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 4, Blocks.wooden_door, 9, 2);
					break;
			//		XXXXXXX
			//  	XXX XXX
			//		XXX XXX
			case 7: currentWorld.setBlock(xstart, y, zstart + 3, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.air);
					currentWorld.setBlock(xstart, y, zstart + 3, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.wooden_door, 9, 2);
					break;
			//		X XXX X
			//  	X X X X
			//		XXX XXX
			case 8: currentWorld.setBlock(xstart, y + 1, zstart + 1, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 1, materials.window);	
					currentWorld.setBlock(xstart, y, zstart + 3, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.air);
					currentWorld.setBlock(xstart, y + 1, zstart + 5, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 5, materials.window);
					currentWorld.setBlock(xstart, y, zstart + 3, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.wooden_door, 9, 2);
					break;
			//		XX X XX
			//  	XX X XX
			//		XXXXXXX
			case 9: currentWorld.setBlock(xstart, y + 1, zstart + 2, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 2, materials.window);	
					currentWorld.setBlock(xstart, y + 1, zstart + 4, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 4, materials.window);
					break;
			//		X  X  X
			//  	X  X  X
			//		XXXXXXX
			case 10:currentWorld.setBlock(xstart, y + 1, zstart + 1, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 1, materials.window);	
					currentWorld.setBlock(xstart, y + 1, zstart + 2, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 2, materials.window);		
					currentWorld.setBlock(xstart, y + 1, zstart + 4, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 4, materials.window);
					currentWorld.setBlock(xstart, y + 1, zstart + 5, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 5, materials.window);
					break;
			//		XXX XXX
			//  	XXX XXX
			//		XXXXXXX
			case 11:currentWorld.setBlock(xstart, y + 1, zstart + 3, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 3, materials.window);
					break;
			//		X XXXXX
			//  	X XXXXX
			//		XXXXXXX
			case 12:currentWorld.setBlock(xstart, y + 1, zstart + 1, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 1, materials.window);
					break;
			//		XXXXX X
			//  	XXXXX X
			//		XXXXXXX
			case 13:currentWorld.setBlock(xstart, y + 1, zstart + 5, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 5, materials.window);
					break;
			//		XXXXX X
			//  	XXXXX X
			//		XXXXXXX
			case 14:currentWorld.setBlock(xstart, y + 1, zstart + 5, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 5, materials.window);
					currentWorld.setBlock(xstart, y + 1, zstart + 1, materials.window);
					currentWorld.setBlock(xstart, y + 2, zstart + 1, materials.window);
					break;
			//		XXXXXXX
			//  	X XXXXX
			//		X XXXXX
			case 15:currentWorld.setBlock(xstart, y, zstart + 1, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 1, Blocks.wooden_door, 8, 2);
					break;
			//		XXXXXXX
			//  	XXXXX X
			//		XXXXX X
			case 16:currentWorld.setBlock(xstart, y, zstart + 5, Blocks.wooden_door, 0, 2);
					currentWorld.setBlock(xstart, y + 1, zstart + 5, Blocks.wooden_door, 9, 2);
					break;
			default:
			}
			
		}

	private void createEastWall(int xstart, int zstart, int xend, int zend, int y, int wallStyle) 
	{
		if(wallStyle < 0) return;
		
		for(int j = zstart + 1; j < zend; j++)
		{
			currentWorld.setBlock(xstart, y, j, materials.wall);
			currentWorld.setBlock(xstart, y + 1, j, materials.wall);
			currentWorld.setBlock(xstart, y + 2, j, materials.wall);
		}
		
		switch (wallStyle){
		//		XXXXXXX
		//  	X  XXXX
		//		X  XXXX
		case 1: 
				currentWorld.setBlock(xstart, y, zstart + 1, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 1, Blocks.wooden_door, 8, 2);
				currentWorld.setBlock(xstart, y, zstart + 2, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.wooden_door, 9, 2);
				break;
		//		XXXXXXX
		//  	XX  XXX
		//		XX  XXX
		case 2: 
				currentWorld.setBlock(xstart, y, zstart + 2, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.wooden_door, 8, 2);
				currentWorld.setBlock(xstart, y, zstart + 3, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.wooden_door, 9, 2);
				break;
		//		XXXXXXX
		//  	XXX  XX
		//		XXX  XX
		case 3: 
				currentWorld.setBlock(xstart, y, zstart + 3, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.wooden_door, 8, 2);
				currentWorld.setBlock(xstart, y, zstart + 4, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 4, Blocks.wooden_door, 9, 2);
				break;
		//		XXXXXXX
		//  	X  X  X
		//		X  X  X
		case 4: 
				currentWorld.setBlock(xstart, y, zstart + 1, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 1, Blocks.wooden_door, 8, 2);
				currentWorld.setBlock(xstart, y, zstart + 2, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.wooden_door, 9, 2);
				currentWorld.setBlock(xstart, y, zstart + 4, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 4, Blocks.wooden_door, 8, 2);
				currentWorld.setBlock(xstart, y, zstart + 5, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 5, Blocks.wooden_door, 9, 2);
				break;
		//		XXXX XX
		//  	XX X XX
		//		XX XXXX
		case 5: currentWorld.setBlock(xstart, y, zstart + 2, Blocks.air);
				currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.air);
				currentWorld.setBlock(xstart, y + 1, zstart + 4, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 4, materials.window);
				currentWorld.setBlock(xstart, y, zstart + 2, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.wooden_door, 9, 2);
				break;
		//		XX XXXX
		//  	XX X XX
		//		XXXX XX
		case 6: currentWorld.setBlock(xstart, y + 1, zstart + 2, Blocks.air);
				currentWorld.setBlock(xstart, y + 2, zstart + 2, Blocks.air);			
				currentWorld.setBlock(xstart, y, zstart + 4, Blocks.air);
				currentWorld.setBlock(xstart, y + 1, zstart + 4, Blocks.air);
				currentWorld.setBlock(xstart, y, zstart + 4, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 4, Blocks.wooden_door, 9, 2);
				break;
		//		XXXXXXX
		//  	XXX XXX
		//		XXX XXX
		case 7: currentWorld.setBlock(xstart, y, zstart + 3, Blocks.air);
				currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.air);
				currentWorld.setBlock(xstart, y, zstart + 3, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.wooden_door, 9, 2);
				break;
		//		X XXX X
		//  	X X X X
		//		XXX XXX
		case 8: currentWorld.setBlock(xstart, y + 1, zstart + 1, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 1, materials.window);	
				currentWorld.setBlock(xstart, y, zstart + 3, Blocks.air);
				currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.air);
				currentWorld.setBlock(xstart, y + 1, zstart + 5, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 5, materials.window);
				currentWorld.setBlock(xstart, y, zstart + 3, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 3, Blocks.wooden_door, 9, 2);
				break;
		//		XX X XX
		//  	XX X XX
		//		XXXXXXX
		case 9: currentWorld.setBlock(xstart, y + 1, zstart + 2, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 2, materials.window);	
				currentWorld.setBlock(xstart, y + 1, zstart + 4, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 4, materials.window);
				break;
		//		X  X  X
		//  	X  X  X
		//		XXXXXXX
		case 10:currentWorld.setBlock(xstart, y + 1, zstart + 1, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 1, materials.window);	
				currentWorld.setBlock(xstart, y + 1, zstart + 2, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 2, materials.window);		
				currentWorld.setBlock(xstart, y + 1, zstart + 4, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 4, materials.window);
				currentWorld.setBlock(xstart, y + 1, zstart + 5, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 5, materials.window);
				break;
		//		XXX XXX
		//  	XXX XXX
		//		XXXXXXX
		case 11:currentWorld.setBlock(xstart, y + 1, zstart + 3, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 3, materials.window);
				break;
		//		X XXXXX
		//  	X XXXXX
		//		XXXXXXX
		case 12:currentWorld.setBlock(xstart, y + 1, zstart + 1, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 1, materials.window);
				break;
		//		XXXXX X
		//  	XXXXX X
		//		XXXXXXX
		case 13:currentWorld.setBlock(xstart, y + 1, zstart + 5, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 5, materials.window);
				break;
		//		X XXX X
		//  	X XXX X
		//		XXXXXXX
		case 14:currentWorld.setBlock(xstart, y + 1, zstart + 5, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 5, materials.window);
				currentWorld.setBlock(xstart, y + 1, zstart + 1, materials.window);
				currentWorld.setBlock(xstart, y + 2, zstart + 1, materials.window);
				break;
		//		XXXXXXX
		//  	XXXXX X
		//		XXXXX X			
		case 15:currentWorld.setBlock(xstart, y, zstart + 1, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 1, Blocks.wooden_door, 8, 2);
				break;
		//		XXXXXXX
		//  	XXXXX X
		//		XXXXX X
		case 16:currentWorld.setBlock(xstart, y, zstart + 5, Blocks.wooden_door, 2, 2);
				currentWorld.setBlock(xstart, y + 1, zstart + 5, Blocks.wooden_door, 9, 2);
				break;
		default:
		}
		
	}

	private void createSouthWall(int xstart, int zstart, int xend, int zend, int y, int wallStyle) 
	{
		if(wallStyle < 0) return;
		
		for(int j = xstart + 1; j < xend; j++)
		{
			currentWorld.setBlock(j, y, zend, materials.wall);
			currentWorld.setBlock(j, y + 1, zend, materials.wall);
			currentWorld.setBlock(j, y + 2, zend, materials.wall);
		}
		
		switch (wallStyle){
		//		XXXXXXX
		//  	X  XXXX
		//		X  XXXX
		case 1: currentWorld.setBlock(xstart + 1, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.door, 9, 2);
				currentWorld.setBlock(xstart + 2, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.door, 8, 2);
				break;
		//		XXXXXXX
		//  	XX  XXX
		//		XX  XXX
		case 2: currentWorld.setBlock(xstart + 2, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.door, 9, 2);
				currentWorld.setBlock(xstart + 3, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 3, y + 1, zstart, materials.door, 8, 2);
				break;
		//		XXXXXXX
		//  	XXX  XX
		//		XXX  XX
		case 3: currentWorld.setBlock(xstart + 3, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 3, y + 1, zstart, materials.door, 8, 2);
				currentWorld.setBlock(xstart + 4, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 4, y + 1, zstart, materials.door, 9, 2);
				break;
		//		XXXXXXX
		//  	X  X  X
		//		X  X  X
		case 4: currentWorld.setBlock(xstart + 1, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.door, 8, 2);
				currentWorld.setBlock(xstart + 2, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.door, 9, 2);
				currentWorld.setBlock(xstart + 4, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 4, y + 1, zstart, materials.door, 8, 2);
				currentWorld.setBlock(xstart + 5, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 5, y + 1, zstart, materials.door, 9, 2);
				break;
		//		XXXX XX
		//  	XX X XX
		//		XX XXXX
		case 5: currentWorld.setBlock(xstart + 2, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.door, 9, 2);
				currentWorld.setBlock(xstart + 4, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 4, y + 2, zstart, materials.window);
				break;
		//		XX XXXX
		//  	XX X XX
		//		XXXX XX
		case 6: currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 2, y + 2, zstart, materials.window);			
				currentWorld.setBlock(xstart + 4, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 4, y + 1, zstart, materials.door, 8, 2);
				break;
		//		XXXXXXX
		//  	XXX XXX
		//		XXX XXX
		case 7: currentWorld.setBlock(xstart + 3, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 3, y + 1, zstart, materials.door, 8, 2);
				break;
		//		X XXX X
		//  	X X X X
		//		XXX XXX
		case 8: currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 1, y + 2, zstart, materials.window);	
				currentWorld.setBlock(xstart + 3, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 3, y + 1, zstart, materials.door, 8, 2);
				currentWorld.setBlock(xstart + 5, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 5, y + 2, zstart, materials.window);
				break;
		//		XX X XX
		//  	XX X XX
		//		XXXXXXX
		case 9: currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 2, y + 2, zstart, materials.window);			
				currentWorld.setBlock(xstart + 4, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 4, y + 2, zstart, materials.window);
				break;
		//		X  X  X
		//  	X  X  X
		//		XXXXXXX
		case 10:currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 1, y + 2, zstart, materials.window);		
				currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 2, y + 2, zstart, materials.window);			
				currentWorld.setBlock(xstart + 4, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 4, y + 2, zstart, materials.window);
				currentWorld.setBlock(xstart + 5, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 5, y + 2, zstart, materials.window);
				break;
		//		XXX XXX
		//  	XXX XXX
		//		XXXXXXX
		case 11:currentWorld.setBlock(xstart + 3, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 3, y + 2, zstart, materials.window);
				break;
		//		X XXXXX
		//  	X XXXXX
		//		XXXXXXX
		case 12:currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 1, y + 2, zstart, materials.window);
				break;
		//		XXXXX X
		//  	XXXXX X
		//		XXXXXXX
		case 13:currentWorld.setBlock(xstart + 5, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 5, y + 2, zstart, materials.window);
				
				break;
		//		X XXX X
		//  	X XXX X
		//		XXXXXXX
		case 14:currentWorld.setBlock(xstart + 5, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 5, y + 2, zstart, materials.window);
				currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 1, y + 2, zstart, materials.window);
				break;
		//		XXXXXXX
		//  	X XXXXX
		//		X XXXXX				
		case 15:currentWorld.setBlock(xstart + 1, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.door, 9, 2);
				break;
		//		XXXXXXX
		//  	XXXXX X
		//		XXXXX X				
		case 16:currentWorld.setBlock(xstart + 5, y, zstart, materials.door, 1, 2);
				currentWorld.setBlock(xstart + 5, y + 1, zstart, materials.door, 9, 2);
				break;
		default:
		}
		
	}

	private void createNorthWall(int xstart, int zstart, int xend, int zend, int y, int wallStyle) 
	{
		if(wallStyle < 0) return;
		
		for(int j = xstart + 1; j < xend; j++)
		{
			currentWorld.setBlock(j, y, zstart, materials.wall);
			currentWorld.setBlock(j, y + 1, zstart, materials.wall);
			currentWorld.setBlock(j, y + 2, zstart, materials.wall);
		}
		
		
		switch (wallStyle){
		//		XXXXXXX
		//  	X  XXXX
		//		X  XXXX
		case 1: currentWorld.setBlock(xstart + 1, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.door, 8, 2);
				currentWorld.setBlock(xstart + 2, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.door, 9, 2);
				break;
		//		XXXXXXX
		//  	XX  XXX
		//		XX  XXX
		case 2: currentWorld.setBlock(xstart + 2, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.door, 8, 2);
				currentWorld.setBlock(xstart + 3, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 3, y + 1, zstart, materials.door, 9, 2);
				break;
		//		XXXXXXX
		//  	XXX  XX
		//		XXX  XX
		case 3: currentWorld.setBlock(xstart + 3, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 3, y + 1, zstart, materials.door, 8, 2);
				currentWorld.setBlock(xstart + 4, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 4, y + 1, zstart, materials.door, 9, 2);
				break;
		//		XXXXXXX
		//  	X  X  X
		//		X  X  X
		case 4: currentWorld.setBlock(xstart + 1, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.door, 8, 2);
				currentWorld.setBlock(xstart + 2, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.door, 9, 2);
				currentWorld.setBlock(xstart + 4, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 4, y + 1, zstart, materials.door, 8, 2);
				currentWorld.setBlock(xstart + 5, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 5, y + 1, zstart, materials.door, 9, 2);
				break;
		//		XXXX XX
		//  	XX X XX
		//		XX XXXX
		case 5: currentWorld.setBlock(xstart + 2, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.door, 9, 2);
				currentWorld.setBlock(xstart + 4, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 4, y + 2, zstart, materials.window);
				break;
		//		XX XXXX
		//  	XX X XX
		//		XXXX XX
		case 6: currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 2, y + 2, zstart, materials.window);			
				currentWorld.setBlock(xstart + 4, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 4, y + 1, zstart, materials.door, 8, 2);
				break;
		//		XXXXXXX
		//  	XXX XXX
		//		XXX XXX
		case 7: currentWorld.setBlock(xstart + 3, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 3, y + 1, zstart, materials.door, 8, 2);
				break;
		//		X XXX X
		//  	X X X X
		//		XXX XXX
		case 8: currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 1, y + 2, zstart, materials.window);	
				currentWorld.setBlock(xstart + 3, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 3, y + 1, zstart, materials.door, 8, 2);
				currentWorld.setBlock(xstart + 5, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 5, y + 2, zstart, materials.window);
				break;
		//		XX X XX
		//  	XX X XX
		//		XXXXXXX
		case 9: currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 2, y + 2, zstart, materials.window);			
				currentWorld.setBlock(xstart + 4, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 4, y + 2, zstart, materials.window);
				break;
		//		X  X  X
		//  	X  X  X
		//		XXXXXXX
		case 10:currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 1, y + 2, zstart, materials.window);		
				currentWorld.setBlock(xstart + 2, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 2, y + 2, zstart, materials.window);			
				currentWorld.setBlock(xstart + 4, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 4, y + 2, zstart, materials.window);
				currentWorld.setBlock(xstart + 5, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 5, y + 2, zstart, materials.window);
				break;
		//		XXX XXX
		//  	XXX XXX
		//		XXXXXXX
		case 11:currentWorld.setBlock(xstart + 3, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 3, y + 2, zstart, materials.window);
				break;
		//		X XXXXX
		//  	X XXXXX
		//		XXXXXXX
		case 12:currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 1, y + 2, zstart, materials.window);
				break;
		//		XXXXX X
		//  	XXXXX X
		//		XXXXXXX
		case 13:currentWorld.setBlock(xstart + 5, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 5, y + 2, zstart, materials.window);
				break;
		//		X XXX X
		//  	X XXX X
		//		XXXXXXX
		case 14:currentWorld.setBlock(xstart + 5, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 5, y + 2, zstart, materials.window);
				currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.window);
				currentWorld.setBlock(xstart + 1, y + 2, zstart, materials.window);
				break;				
		//		XXXXXXX
		//  	X XXXXX
		//		X XXXXX
		case 15:currentWorld.setBlock(xstart + 1, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 1, y + 1, zstart, materials.door, 8, 2);
				break;
		//		XXXXXXX
		//  	XXXXX X
		//		XXXXX X
		case 16:currentWorld.setBlock(xstart + 5, y, zstart, materials.door, 3, 2);
				currentWorld.setBlock(xstart + 5, y + 1, zstart, materials.door, 9, 2);
				break;
		default:
		}
		}
	
	private void createConnection(int xstart, int zstart, int xend, int zend, int y)
	{
		if(xstart == xend){
			currentWorld.setBlock(xstart, y, zstart, materials.wall);
			currentWorld.setBlock(xstart, y + 1, zstart, materials.wall);
			currentWorld.setBlock(xstart, y + 2, zstart, materials.wall);
			
			currentWorld.setBlock(xstart, y, zend, materials.wall);
			currentWorld.setBlock(xstart, y + 1, zend, materials.wall);
			currentWorld.setBlock(xstart, y + 2, zend, materials.wall);
		} else {
			currentWorld.setBlock(xstart, y, zstart, materials.wall);
			currentWorld.setBlock(xstart, y + 1, zstart, materials.wall);
			currentWorld.setBlock(xstart, y + 2, zstart, materials.wall);
			
			currentWorld.setBlock(xend, y, zstart, materials.wall);
			currentWorld.setBlock(xend, y + 1, zstart, materials.wall);
			currentWorld.setBlock(xend, y + 2, zstart, materials.wall);
		}
		
		
		for(int m = xstart ; m <= xend; m++)
		{
			for(int n = zstart; n <= zend; n++)
			{
				currentWorld.setBlock(m, y + 3, n, materials.floor);
			}
			
		}
	}
	

	private int getCenter(int min, int max)
	{
		return min + ((max - min) / 2);
	}

//	controls the random number so that only ground level floors have doors, and upper levels always have windows	
	private int randomFromHeight(int buildingFloor)
	{
		if(buildingFloor == 0) return ran.nextInt(8) + 1;
		else return ran.nextInt(6) + 9;
	}
	
	private int isZconnected(int x,int z, int k)
	{
		if(z == 0) {
			if(skylineXZ[x][1] >= k) return 1;
		} else if(z == 1) {
			if(skylineXZ[x][0] >= k) return -1;
		}
		return 0;
	}

	private int isXconnected(int x, int z, int k)
	{
		if(x == 0) {
			if(skylineXZ[1][z] >= k) return 1;
		} else if(x == 1) {
			if(skylineXZ[0][z] >= k) return -1;
		}
		return 0;
	}
	
}
