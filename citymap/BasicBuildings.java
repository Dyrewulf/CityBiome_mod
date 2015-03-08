package dyrewulf.citybiome.citymap;

import net.minecraft.world.World;
import dyrewulf.citybiome.BuildingMaterials;
import dyrewulf.citybiome.GenerateBuildings;

public class BasicBuildings extends CityMapPoint
{	
	public BasicBuildings(int chunkX, int chunkZ)
	{
		super(chunkX, chunkZ);
	}

	public void generate(World world, int maxHeight, int chanceInTen, BuildingMaterials mats)
	{
		GenerateBuildings builder = new GenerateBuildings(world, chunkX, chunkZ, maxHeight, chanceInTen, mats);
		builder.generateRoads(this.streetsDirection);
		builder.createBuildings();
		builder.createOnesies();
	}
	
}
