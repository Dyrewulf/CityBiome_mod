package dyrewulf.citybiome.structures.onesies;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class OnesiesPark implements Onesies
{
	@Override
	public void generate(World world, int xstart, int zstart, int xend, int zend)
	{
		for(int i = xstart + 1; i < xend; i++)
		{
			for(int j = zstart + 1; j < zend; j++)
			{
				if(i == xstart + 1 || i == xend - 1 || j == zstart + 1 || j == zend - 1)
				{
					world.setBlock(i, world.getTopSolidOrLiquidBlock(i, j), j, Blocks.log);
				} else
				{
					world.setBlock(i, world.getTopSolidOrLiquidBlock(i, j), j, Blocks.sand);
				}
			}
		}
	}

	
	
}
