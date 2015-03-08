package dyrewulf.citybiome.structures.fullchunk;

import net.minecraft.world.World;

public abstract interface FullChunkStructure
{	
	public void generate(World world, int xstart, int zstart, int xend, int zend);
}
