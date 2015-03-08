package dyrewulf.citybiome.structures.fullchunk;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Ampitheatre implements FullChunkStructure
{

	@Override
	public void generate(World world, int xstart, int zstart, int xend, int zend)
	{
		int y = world.getTopSolidOrLiquidBlock(xstart + 8, zstart + 8) - 1;
		
		setStairWest(world, xstart + 1, y + 5, zstart + 7);
		setStairWest(world, xstart + 2, y + 4, zstart + 7);
		setStairWest(world, xstart + 3, y + 3, zstart + 7);
		setStairWest(world, xstart + 4, y + 2, zstart + 7);
		setStairWest(world, xstart + 5, y + 1, zstart + 7);
		
		setStairWest(world, xstart + 1, y + 5, zstart + 6);
		setStairWest(world, xstart + 2, y + 4, zstart + 6);
		setStairWest(world, xstart + 3, y + 3, zstart + 6);
		setStairWest(world, xstart + 4, y + 2, zstart + 6);
		setStairWest(world, xstart + 5, y + 1, zstart + 6);
		
		setStairWest(world, xstart + 1, y + 5, zstart + 5);
		setStairWest(world, xstart + 2, y + 4, zstart + 5);
		setStairWest(world, xstart + 3, y + 3, zstart + 5);
		setStairWest(world, xstart + 4, y + 2, zstart + 5);
		setStairWest(world, xstart + 5, y + 1, zstart + 5);
		
		setStairWest(world, xstart + 1, y + 5, zstart + 4);
		setStairWest(world, xstart + 2, y + 4, zstart + 4);
		setStairWest(world, xstart + 3, y + 3, zstart + 4);
		setStairWest(world, xstart + 4, y + 2, zstart + 4);
		
		setStairNorth(world, xstart + 6, y + 1, zstart + 5);
		setStairNorth(world, xstart + 7, y + 1, zstart + 5);
		setStairNorth(world, xstart + 8, y + 1, zstart + 5);
		setStairNorth(world, xstart + 9, y + 1, zstart + 5);
		
		setStairNorth(world, xstart + 5, y + 2, zstart + 4);
		setStairNorth(world, xstart + 6, y + 2, zstart + 4);
		setStairNorth(world, xstart + 7, y + 2, zstart + 4);
		setStairNorth(world, xstart + 8, y + 2, zstart + 4);
		setStairNorth(world, xstart + 9, y + 2, zstart + 4);
		setStairNorth(world, xstart + 10, y + 2, zstart + 4);
		
		setStairNorth(world, xstart + 4, y + 3, zstart + 3);
		setStairNorth(world, xstart + 5, y + 3, zstart + 3);
		setStairNorth(world, xstart + 6, y + 3, zstart + 3);
		setStairNorth(world, xstart + 7, y + 3, zstart + 3);
		setStairNorth(world, xstart + 8, y + 3, zstart + 3);
		setStairNorth(world, xstart + 9, y + 3, zstart + 3);
		setStairNorth(world, xstart + 10, y + 3, zstart + 3);
		setStairNorth(world, xstart + 11, y + 3, zstart + 3);
		
		setStairNorth(world, xstart + 3, y + 4, zstart + 2);
		setStairNorth(world, xstart + 4, y + 4, zstart + 2);
		setStairNorth(world, xstart + 5, y + 4, zstart + 2);
		setStairNorth(world, xstart + 6, y + 4, zstart + 2);
		setStairNorth(world, xstart + 7, y + 4, zstart + 2);
		setStairNorth(world, xstart + 8, y + 4, zstart + 2);
		setStairNorth(world, xstart + 9, y + 4, zstart + 2);
		setStairNorth(world, xstart + 10, y + 4, zstart + 2);
		setStairNorth(world, xstart + 11, y + 4, zstart + 2);
		setStairNorth(world, xstart + 12, y + 4, zstart + 2);
		
		setStairNorth(world, xstart + 2, y + 5, zstart + 1);
		setStairNorth(world, xstart + 3, y + 5, zstart + 1);
		setStairNorth(world, xstart + 4, y + 5, zstart + 1);
		setStairNorth(world, xstart + 5, y + 5, zstart + 1);
		setStairNorth(world, xstart + 6, y + 5, zstart + 1);
		setStairNorth(world, xstart + 7, y + 5, zstart + 1);
		setStairNorth(world, xstart + 8, y + 5, zstart + 1);
		setStairNorth(world, xstart + 9, y + 5, zstart + 1);
		setStairNorth(world, xstart + 10, y + 5, zstart + 1);
		setStairNorth(world, xstart + 11, y + 5, zstart + 1);
		setStairNorth(world, xstart + 12, y + 5, zstart + 1);
		setStairNorth(world, xstart + 13, y + 5, zstart + 1);
		
		setStairWest(world, xstart + 1, y + 5, zstart + 3);
		setStairWest(world, xstart + 2, y + 4, zstart + 3);
		setStairWest(world, xstart + 3, y + 3, zstart + 3);
		
		
		setStairWest(world, xstart + 1, y + 5, zstart + 2);
		setStairWest(world, xstart + 2, y + 4, zstart + 2);
		
		
		setStairWest(world, xstart + 1, y + 5, zstart + 1);
		
		
		setStairEast(world, xend - 1, y + 5, zstart + 7);
		setStairEast(world, xend - 2, y + 4, zstart + 7);
		setStairEast(world, xend - 3, y + 3, zstart + 7);
		setStairEast(world, xend - 4, y + 2, zstart + 7);
		setStairEast(world, xend - 5, y + 1, zstart + 7);
		
		setStairEast(world, xend - 1, y + 5, zstart + 6);
		setStairEast(world, xend - 2, y + 4, zstart + 6);
		setStairEast(world, xend - 3, y + 3, zstart + 6);
		setStairEast(world, xend - 4, y + 2, zstart + 6);
		setStairEast(world, xend - 5, y + 1, zstart + 6);
		
		setStairEast(world, xend - 1, y + 5, zstart + 5);
		setStairEast(world, xend - 2, y + 4, zstart + 5);
		setStairEast(world, xend - 3, y + 3, zstart + 5);
		setStairEast(world, xend - 4, y + 2, zstart + 5);
		setStairEast(world, xend - 5, y + 1, zstart + 5);
		
		setStairEast(world, xend - 1, y + 5, zstart + 4);
		setStairEast(world, xend - 2, y + 4, zstart + 4);
		setStairEast(world, xend - 3, y + 3, zstart + 4);
		setStairEast(world, xend - 4, y + 2, zstart + 4);
		
		setStairEast(world, xend - 1, y + 5, zstart + 3);
		setStairEast(world, xend - 2, y + 4, zstart + 3);
		setStairEast(world, xend - 3, y + 3, zstart + 3);
		
		setStairEast(world, xend - 1, y + 5, zstart + 2);
		setStairEast(world, xend - 2, y + 4, zstart + 2);
		
		setStairEast(world, xend - 1, y + 5, zstart + 1);
		
	}
	
	private void setStairEast(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.stone_stairs, 0, 2);
		buildDown(world, x, y - 1, z);
		clearUp(world, x, y + 1, z);
	}
	private void setStairWest(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.stone_stairs, 1, 2);
		buildDown(world, x, y - 1, z);
		clearUp(world, x, y + 1, z);
	}
	private void setStairNorth(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.stone_stairs, 3, 2);
		buildDown(world, x, y - 1, z);
		clearUp(world, x, y + 1, z);
	}
	private void setStairSouth(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.stone_stairs, 2, 2);
		buildDown(world, x, y - 1, z);
		clearUp(world, x, y + 1, z);
	}
	
	private void buildDown(World world, int x, int y, int z)
	{
		for(int i = 0; i < 3; i++)
		{
			world.setBlock(x, y - i, z, Blocks.cobblestone);
		}
	}
	
	private void clearUp(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.air);
		if(world.getBlock(x, y + 1, z) != Blocks.air) clearUp(world, x, y + 1, z);
	}

}
