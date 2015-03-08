package dyrewulf.citybiome.structures.onesies;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class OnesiesRubble implements Onesies
{
	private static final Block[] topBlocks =
		{
		Blocks.cobblestone,
		Blocks.log,
		Blocks.log2,
		Blocks.stone,
		Blocks.stonebrick,
		Blocks.sand,
		Blocks.gravel
		};
	private static final Block[] bottomBlocks =
		{
		Blocks.iron_ore,
		Blocks.coal_ore,
		Blocks.gold_ore
		};
	private Random ran = new Random();
	
	@Override
	public void generate(World world, int xstart, int zstart, int xend, int zend)
	{
		for(int i = xstart + 1; i <= xend - 1; i++)
		{
			for(int j = zstart + 1; j <= zend - 1; j++)
			{
				int baseY = world.getTopSolidOrLiquidBlock(i, j);
				if(ran.nextInt(9) < 4)
				{
					world.setBlock(i, baseY - 1, j, bottomBlocks[ran.nextInt(bottomBlocks.length)]);
				} else world.setBlock(i, baseY, j, topBlocks[ran.nextInt(topBlocks.length)]);
				
				for(int k = 0; k < ran.nextInt(5); k++)
				{
					world.setBlock(i, baseY + k, j, topBlocks[ran.nextInt(topBlocks.length)]);
				}
			}
		}
	}
}
