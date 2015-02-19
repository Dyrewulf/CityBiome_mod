package dyrewulf.citybiome;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeDyrewulfCity extends BiomeGenBase
{
	public static int MxBldgHt = 5;
	public static int chanceInTen = 6;
	public static ArrayList<BuildingMaterials> buildingStyles = new ArrayList<BuildingMaterials>();
	
	public BiomeDyrewulfCity(int id, Block top, Block filler)
	{
		super(id);
		setBiomeName("dyreCityBiome");
		this.theBiomeDecorator = this.getModdedBiomeDecorator(new DyreCityBiomeDecorator());
		this.heightVariation = 0.0F;
		this.rootHeight = 0.0F;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.deadBushPerChunk = 0;
	    this.theBiomeDecorator.reedsPerChunk = 0;
	    this.theBiomeDecorator.cactiPerChunk = 0;
		this.topBlock = top;
		this.fillerBlock = filler;
		this.spawnableCreatureList.clear();
		
		buildingStyles.add(new BuildingMaterials());
		buildingStyles.add(BuildingMaterials.allbrick);
		buildingStyles.add(BuildingMaterials.artdeco);
		buildingStyles.add(BuildingMaterials.brickandStone);
		buildingStyles.add(BuildingMaterials.classicMinecraft);
		buildingStyles.add(BuildingMaterials.desert);
		buildingStyles.add(BuildingMaterials.logCabin);
		buildingStyles.add(BuildingMaterials.morroccan);
		buildingStyles.add(BuildingMaterials.nether);
		buildingStyles.add(BuildingMaterials.oldworld);
		buildingStyles.add(BuildingMaterials.rustic);
		buildingStyles.add(BuildingMaterials.skyscraper);
		buildingStyles.add(BuildingMaterials.whitewashed);
		
		}
	 
	public BiomeGenBase setBuildingHeight(int height)
	{
		this.MxBldgHt = height;
		return this;
	}
	
	public int getBuildingHt()
	{
		return(MxBldgHt);
	}
	
	public BiomeGenBase setChanceInTen(int chance)
	{
		this.chanceInTen = chance;
		return this;
	}
	
	public int getChanceInTen()
	{
		return(chanceInTen);
	}
	
}
