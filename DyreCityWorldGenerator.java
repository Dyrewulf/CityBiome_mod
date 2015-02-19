package dyrewulf.citybiome;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class DyreCityWorldGenerator implements IWorldGenerator
{
	
	private int maxBldgHt;
	private int chance;
	private ArrayList<BuildingMaterials> materials;
	
	public DyreCityWorldGenerator()
	{
		maxBldgHt = 5;
		chance = 6;
		materials = BiomeDyrewulfCity.buildingStyles;
	}
	
	public DyreCityWorldGenerator(int maxheight, int chanceInTen, ArrayList<BuildingMaterials> mats)
	{
		this.maxBldgHt = maxheight;
		this.chance = chanceInTen;
		this.materials = mats;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		 BiomeGenBase biome = world.getBiomeGenForCoords(chunkX * 16 + 8, chunkZ * 16 + 8);
		 if (biome.biomeName.equals("Basic City") && chunkX % 5 != 0 && chunkZ % 5 != 0)
		 {
			 GenerateBuildings builder = new GenerateBuildings(world, chunkX, chunkZ, maxBldgHt, chance, materials.get(random.nextInt(materials.size())));
			 builder.makeItWork();
		 }
	}
	
	public DyreCityWorldGenerator setMaxHeight(int value)
	{
		this.maxBldgHt = value;
		return this;
	}
	
	public DyreCityWorldGenerator setChance(int chanceInTen)
	{
		this.chance = chanceInTen;
		return this;
	}
	
	public DyreCityWorldGenerator setBuildingMaterials(ArrayList<BuildingMaterials> mats)
	{
		this.materials = mats;
		return this;
	}
	
	public DyreCityWorldGenerator addBuildingMaterials(BuildingMaterials mats)
	{
		materials.add(mats);
		return this;
	}
}
