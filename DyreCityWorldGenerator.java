package dyrewulf.citybiome;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import dyrewulf.citybiome.citymap.BasicBuildings;
import dyrewulf.citybiome.citymap.CityMapPoint;
import dyrewulf.citybiome.citymap.FullChunkPoint;
import dyrewulf.citybiome.structures.fullchunk.Ampitheatre;
import dyrewulf.citybiome.structures.fullchunk.Chapel;
import dyrewulf.citybiome.structures.fullchunk.FullChunkStructure;

public class DyreCityWorldGenerator implements IWorldGenerator
{
	
	private int maxBldgHt;
	private int chance;
	private ArrayList<BuildingMaterials> materials;
	
	private static final FullChunkStructure[] fullChunkStructures =
		{
		new Ampitheatre(),
		new Chapel()
		};
	
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
		 BiomeGenBase biome = world.getBiomeGenForCoords(chunkX << 4, chunkZ << 4);
		 if (biome.biomeName.equals("Basic City"))
		 {
			 DyrewulfCity.cityMap.mapIt(chunkX, chunkZ);
			 CityMapPoint mapPoint = DyrewulfCity.cityMap.getMapPoint(chunkX, chunkZ);
			 if(mapPoint.streetsDirection != 0)
			 {
				 BasicBuildings buildings = (BasicBuildings) new BasicBuildings(chunkX, chunkZ).setDirection(mapPoint.streetsDirection);
				 DyrewulfCity.cityMap.setMapPoint(buildings);
				 buildings.generate(world, maxBldgHt, chance, materials.get(random.nextInt(materials.size())));
			 } else if(mapPoint.streetsDirection == 0)
			 {
				 FullChunkPoint fullChunkBldg = new FullChunkPoint(chunkX, chunkZ);
				 DyrewulfCity.cityMap.setMapPoint(fullChunkBldg);
				 fullChunkStructures[random.nextInt(fullChunkStructures.length)].generate(world, fullChunkBldg.xmin, fullChunkBldg.zmin, fullChunkBldg.xmax, fullChunkBldg.zmax);
			 }
			 
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
