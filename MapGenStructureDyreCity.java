package dyrewulf.citybiome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;



public class MapGenStructureDyreCity
{
	public World currentWorld;
	public int chunkX;
	public int chunkZ;
	public int[][] skylineXZ;
	private int[][] Xmins;
	private int[][] Zmins;
	private int[][] Xmaxs;
	private int[][] Zmaxs;
	private int maxBuildingHeight;
	Random ran = new Random();
	int baseY = 0;
	private int currentY;
	BuildingMaterials mats = new BuildingMaterials();
	boolean isXaxis;
	
	public MapGenStructureDyreCity(World world, int chunkX, int chunkZ, int maxHeight)
	{
		this.currentWorld = world;
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
		this.maxBuildingHeight = maxHeight;
		/*
			when this is instantiated, the chunk is divided into 4 parts, each part given a building height, stored in skylineXZ
			Xmins, Xmaxs, Zmins, Zmaxs stores the XZ block coordinates for each building
			each building is 7 x 7 blocks, leaving a 1 block alley between buildings
			I may get around to writing code to connect buildings. I intentionally made sure that this is aware of the height of other buildings in this chunk for that purpose
			The Y value is static, because this is designed for a flat biome.
			
		*/
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				skylineXZ[i][j] = ran.nextInt(maxBuildingHeight);
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
				Zmins[i][j] = (chunkX * 16) - 15 + (i * 8);
				Zmaxs[i][j] = Zmins[i][j] + 7;
				} else if (chunkZ < 0)
				{
					Zmaxs[i][j] = (chunkX * 16) + 15 - (i * 8);
					Zmins[i][j] = Zmaxs[i][j] - 7;
				}
			}
		}
		this.baseY = currentWorld.getTopSolidOrLiquidBlock(Xmaxs[0][0], Zmaxs[0][0]);
			
	}
	
	public void generate()
	{
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, ran, chunkX, chunkZ));
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				if (skylineXZ[i][j] > 0)
					generateBuilding(i, j);
				
			}
		}
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, ran, chunkX, chunkZ));
	}
	
	private void generateBuilding(int x, int z)
	{
		generateFoundation(Xmins[x][z], Zmins[x][z], Xmaxs[x][z], Zmaxs[x][z]);
		for (int i = 0; i <= skylineXZ[x][z]; i++)
		{
			currentY = baseY + (i * 4);
			generateFloor(plusOne(Xmins[x][z]), plusOne(Zmins[x][z]), minusOne(Xmaxs[x][z]), minusOne(Zmaxs[x][z]), currentY + 4);
			generateEdge(Xmins[x][z], Zmins[x][z], Xmaxs[x][z], Zmaxs[x][z], currentY + 1);
			
			generateWall(Xmins[x][z], Zmins[x][z], Xmaxs[x][z], Zmins[x][z], currentY + 1, randomFromHeight(i));
			generateWall(Xmins[x][z], Zmaxs[x][z], Xmaxs[x][z], Zmaxs[x][z], currentY + 1, randomFromHeight(i));
			generateWall(Xmaxs[x][z], Zmins[x][z], Xmaxs[x][z], Zmaxs[x][z], currentY + 1, randomFromHeight(i));
			generateWall(Xmins[x][z], Zmins[x][z], Xmins[x][z], Zmaxs[x][z], currentY + 1, randomFromHeight(i));
					
		}
	}
	
	private void generateFloor(int xstart, int zstart, int xend, int zend, int y)
	{
		for(int i=xstart; i<=xend;i++)
		{
			for(int j=zstart;j<=zend;j++)
			{
				currentWorld.setBlock(i, j, y, mats.floor);
			}
		}
		currentWorld.setBlock(getCenter(xend, xstart), getCenter(zend, zstart), y, mats.light);
	}
	
	private void generateEdge(int xstart, int zstart, int xend, int zend, int y)
	{
		for(int i = y; i <= y + 3; i++)
		{
			if(i < y +3)
			{
				currentWorld.setBlock(xstart, zstart, i, mats.corner);
				currentWorld.setBlock(xstart, zend, i, mats.corner);
				currentWorld.setBlock(xend, zstart, i, mats.corner);
				currentWorld.setBlock(xend, zend, i, mats.corner);
			}
			if(i == y + 3)
			{
				for(int j = xstart; j <= xend; j++)
				{
					currentWorld.setBlock(j, zstart, i, mats.corner);
					currentWorld.setBlock(j, zend, i, mats.corner);
				}
				for(int j = zstart; j <= zend; j++)
				{
					currentWorld.setBlock(j, xstart, i, mats.corner);
					currentWorld.setBlock(j, xend, i, mats.corner);
				}
			}
			
		}
		
		
		
		for(int i=xstart; i<=xend;i++)
		{
			for(int j=zstart;j<=zend;j++)
			{
				currentWorld.setBlock(i, j, baseY, mats.corner);
			}
		}
	}
	
	private void generateFoundation(int xstart, int zstart, int xend, int zend)
	{
		for(int i=xstart; i<=xend;i++)
		{
			for(int j=zstart;j<=zend;j++)
			{
				currentWorld.setBlock(i, j, baseY, mats.foundation);
			}
		}
	}

/*	When calling generateWall, use wallStyle to determine windows and doors.
	0 (or negatives) will result in an empty space.
	1 - 8 include doors
	9 - 14 are windows only
	15+ will result in a solid wall	
*/	
	private void generateWall(int xstart, int zstart, int xend, int zend, int y, int wallStyle)
	{
		if((Math.abs((Math.abs(zend) - Math.abs(xstart))) > Math.abs((Math.abs(zend) - Math.abs(zstart)))))
				{
			isXaxis = true;
				} else isXaxis = false;

		if (wallStyle > 0)
		{
		for(int i=xstart; i<=xend;i++)
		{
			for(int j=zstart;j<=zend;j++)
			{
				currentWorld.setBlock(i, j, y, mats.wall);
				currentWorld.setBlock(i, j, y + 1, mats.wall);
				currentWorld.setBlock(i, j, y + 2, mats.wall);
			}
		}
		}
		
		switch (wallStyle){
		//		XXXXXXX
		//  	X  XXXX
		//		X  XXXX
		case 1: currentWorld.setBlock(xOffset(xstart, 1), zOffset(zstart, 1), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 1), zOffset(zstart, 1), y+1, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 2), zOffset(zstart, 2), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 2), zOffset(zstart, 2), y+1, Blocks.air);
		//		XXXXXXX
		//  	XX  XXX
		//		XX  XXX
		case 2: currentWorld.setBlock(xOffset(xstart, 2), zOffset(zstart, 2), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 2), zOffset(zstart, 2), y+1, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 3), zOffset(zstart, 3), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 3), zOffset(zstart, 3), y+1, Blocks.air);				
		//		XXXXXXX
		//  	XXX  XX
		//		XXX  XX
		case 3: currentWorld.setBlock(xOffset(xstart, 3), zOffset(zstart, 3), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 3), zOffset(zstart, 3), y+1, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 4), zOffset(zstart, 4), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 4), zOffset(zstart, 4), y+1, Blocks.air);
		//		XXXXXXX
		//  	X  X  X
		//		X  X  X
		case 4: currentWorld.setBlock(xOffset(xstart, 4), zOffset(zstart, 4), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 4), zOffset(zstart, 4), y+1, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 5), zOffset(zstart, 5), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 5), zOffset(zstart, 5), y+1, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 1), zOffset(zstart, 1), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 1), zOffset(zstart, 1), y+1, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 2), zOffset(zstart, 2), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 2), zOffset(zstart, 2), y+1, Blocks.air);
		//		XXXX XX
		//  	XX X XX
		//		XX XXXX
		case 5: currentWorld.setBlock(xOffset(xstart, 3), zOffset(zstart, 2), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 3), zOffset(zstart, 2), y+1, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 5), zOffset(zstart, 5), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 5), zOffset(zstart, 5), y+2, mats.window);				
		//		XX XXXX
		//  	XX X XX
		//		XXXX XX
		case 6: currentWorld.setBlock(xOffset(xstart, 3), zOffset(zstart, 3), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 3), zOffset(zstart, 3), y+2, mats.window);
				currentWorld.setBlock(xOffset(xstart, 5), zOffset(zstart, 5), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 5), zOffset(zstart, 5), y+1, Blocks.air);
		//		XXXXXXX
		//  	XXX XXX
		//		XXX XXX
		case 7: currentWorld.setBlock(xOffset(xstart, 4), zOffset(zstart, 4), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 4), zOffset(zstart, 4), y+1, Blocks.air);
		//		X XXX X
		//  	X X X X
		//		XXX XXX
		case 8: currentWorld.setBlock(xOffset(xstart, 4), zOffset(zstart, 4), y, Blocks.air);
				currentWorld.setBlock(xOffset(xstart, 4), zOffset(zstart, 4), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 2), zOffset(zstart, 2), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 2), zOffset(zstart, 2), y+2, mats.window);
				currentWorld.setBlock(xOffset(xstart, 6), zOffset(zstart, 6), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 6), zOffset(zstart, 6), y+2, mats.window);
		//		XX X XX
		//  	XX X XX
		//		XXXXXXX
		case 9: currentWorld.setBlock(xOffset(xstart, 3), zOffset(zstart, 3), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 3), zOffset(zstart, 3), y+2, mats.window);
				currentWorld.setBlock(xOffset(xstart, 5), zOffset(zstart, 5), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 5), zOffset(zstart, 5), y+2, mats.window);
		//		X  X  X
		//  	X  X  X
		//		XXXXXXX
		case 10:currentWorld.setBlock(xOffset(xstart, 2), zOffset(zstart, 2), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 2), zOffset(zstart, 2), y+2, mats.window);
				currentWorld.setBlock(xOffset(xstart, 3), zOffset(zstart, 3), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 3), zOffset(zstart, 3), y+2, mats.window);
				currentWorld.setBlock(xOffset(xstart, 5), zOffset(zstart, 5), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 5), zOffset(zstart, 5), y+2, mats.window);
				currentWorld.setBlock(xOffset(xstart, 6), zOffset(zstart, 6), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 6), zOffset(zstart, 6), y+2, mats.window);
		//		XXX XXX
		//  	XXX XXX
		//		XXXXXXX
		case 11:currentWorld.setBlock(xOffset(xstart, 4), zOffset(zstart, 4), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 4), zOffset(zstart, 4), y+2, mats.window);
		//		X XXXXX
		//  	X XXXXX
		//		XXXXXXX
		case 12:currentWorld.setBlock(xOffset(xstart, 2), zOffset(zstart, 2), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 2), zOffset(zstart, 2), y+2, mats.window);
		//		XXXXX X
		//  	XXXXX X
		//		XXXXXXX
		case 13:currentWorld.setBlock(xOffset(xstart, 6), zOffset(zstart, 6), y+1, mats.window);
				currentWorld.setBlock(xOffset(xstart, 6), zOffset(zstart, 6), y+2, mats.window);
		default:
		}
		
		
		
		
	}

//	the offset methods are used when replacing blocks on a wall	
	private int xOffset(int x, int offset)
	{
		if(isXaxis) return x + offset;
		else return x;
	}
	
	private int zOffset(int z, int offset)
	{
		if(isXaxis) return z + offset;
		else return z;
	}
	
	private int getCenter(int min, int max)
	{
		return min + ((max - min) / 2);
	}

//	used to generate a number 1 smaller if positive or 1 bigger if negative	
	private int minusOne(int value)
	{
		if (value > 0) return value - 1;
		else return value +1;
	}

//	used to generate a number 1 bigger if positive, or 1 smaller if negative
	private int plusOne(int value)
	{
		if (value > 0) return value + 1;
		else return value - 1;
	}

//	controls the random number so that only ground level floors have doors, and upper levels always have windows	
	private int randomFromHeight(int buildingFloor)
	{
		if(buildingFloor == 0) return ran.nextInt(13) + 1;
		else return ran.nextInt(4) + 9;
	}
}
