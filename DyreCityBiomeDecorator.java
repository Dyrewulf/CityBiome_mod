package dyrewulf.citybiome;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;

public class DyreCityBiomeDecorator extends BiomeDecorator
{
	
	public DyreCityBiomeDecorator()
	{
		this.sandGen = new WorldGenSand(Blocks.sand, 0);
	    this.gravelAsSandGen = new WorldGenSand(Blocks.gravel, 0);
	    this.dirtGen = new WorldGenMinable(Blocks.dirt, 0);
	    this.gravelGen = new WorldGenMinable(Blocks.gravel, 0);
	    this.coalGen = new WorldGenMinable(Blocks.coal_ore, 0);
	    this.ironGen = new WorldGenMinable(Blocks.iron_ore, 0);
	    this.goldGen = new WorldGenMinable(Blocks.gold_ore, 0);
	    this.redstoneGen = new WorldGenMinable(Blocks.redstone_ore, 0);
	    this.diamondGen = new WorldGenMinable(Blocks.diamond_ore, 0);
	    this.lapisGen = new WorldGenMinable(Blocks.lapis_ore, 0);
	    this.yellowFlowerGen = new WorldGenFlowers(Blocks.yellow_flower);
	    this.mushroomBrownGen = new WorldGenFlowers(Blocks.brown_mushroom);
	    this.mushroomRedGen = new WorldGenFlowers(Blocks.red_mushroom);
	    this.bigMushroomGen = new WorldGenBigMushroom();
	    this.reedGen = new WorldGenReed();
	    this.cactusGen = new WorldGenCactus();
	    this.waterlilyGen = new WorldGenWaterlily();
	    this.flowersPerChunk = 0;
	    this.grassPerChunk = 0;
	    this.sandPerChunk = 0;
	    this.sandPerChunk2 = 0;
	    this.clayPerChunk = 0;
	    this.generateLakes = false;
	}
	
	
	@Override
	public void decorateChunk(World world, Random ran, BiomeGenBase biomegenbase, int chunkX, int chunkZ)
    {
        if (this.currentWorld != null)
        {
            throw new RuntimeException("Already decorating!!");
        }
        else
        {
            this.currentWorld = world;
            this.randomGenerator = ran;
            this.chunk_X = chunkX;
            this.chunk_Z = chunkZ;
            
 //         this.genDecorations(biomegenbase);
            this.currentWorld = null;
            this.randomGenerator = null;
        }
    }
}
