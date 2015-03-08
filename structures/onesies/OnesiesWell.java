package dyrewulf.citybiome.structures.onesies;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class OnesiesWell implements Onesies
{

	@Override
	public void generate(World world, int xstart, int zstart, int xend, int zend)
	{		
		int y = world.getTopSolidOrLiquidBlock(xstart + 3, xstart + 3);
		
		for(int i = -2; i < 2; i++)
		{
		world.setBlock(xstart + 2, y + i, zstart + 2, Blocks.stonebrick);
		world.setBlock(xstart + 2, y + i, zstart + 3, Blocks.stonebrick);
		world.setBlock(xstart + 2, y + i, zstart + 4, Blocks.stonebrick);
		world.setBlock(xstart + 2, y + i, zstart + 5, Blocks.stonebrick);
		world.setBlock(xstart + 3, y + i, zstart + 5, Blocks.stonebrick);
		world.setBlock(xstart + 4, y + i, zstart + 5, Blocks.stonebrick);
		world.setBlock(xstart + 3, y + i, zstart + 2, Blocks.stonebrick);
		world.setBlock(xstart + 4, y + i, zstart + 2, Blocks.stonebrick);
		world.setBlock(xstart + 5, y + i, zstart + 2, Blocks.stonebrick);
		world.setBlock(xstart + 5, y + i, zstart + 3, Blocks.stonebrick);
		world.setBlock(xstart + 5, y + i, zstart + 4, Blocks.stonebrick);
		world.setBlock(xstart + 5, y + i, zstart + 5, Blocks.stonebrick);
		
		world.setBlock(xstart + 3, y + i - 1, zstart + 3, Blocks.water);
		world.setBlock(xstart + 3, y + i - 1, zstart + 4, Blocks.water);
		world.setBlock(xstart + 4, y + i - 1, zstart + 3, Blocks.water);
		world.setBlock(xstart + 4, y + i - 1, zstart + 4, Blocks.water);
		
		}
	}

}
