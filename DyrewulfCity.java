package dyrewulf.citybiome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import dyrewulf.citybiome.blocks.CityBlock;
import dyrewulf.citybiome.citymap.CityMap;
import dyrewulf.citybiome.help.Reference;
import dyrewulf.citybiome.world.DyreCityWorldType;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class DyrewulfCity
{	
	public static BiomeGenBase basicCity;
	public static DyreCityWorldGenerator worldGen = new DyreCityWorldGenerator().setMaxHeight(4).setChance(6).setBuildingMaterials(BiomeDyrewulfCity.buildingStyles);
	public static CityMap cityMap = new CityMap();
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		GameRegistry.registerBlock(CityBlock.brushedsteel, "brushedsteel");
		GameRegistry.registerBlock(CityBlock.cobblestonepattern, "cobblestonepattern");
		GameRegistry.registerBlock(CityBlock.fieldstone, "fieldstone");
		GameRegistry.registerBlock(CityBlock.mosque, "mosque");
		GameRegistry.registerBlock(CityBlock.redbrick, "redbrick");
		GameRegistry.registerBlock(CityBlock.stucco, "stucco");
		GameRegistry.registerBlock(CityBlock.wiltern, "wiltern");
		GameRegistry.registerBlock(CityBlock.fadedwoodTile, "fadedwoodtile");
		GameRegistry.registerBlock(CityBlock.redwoodTile, "redwoodtile");
		GameRegistry.registerBlock(CityBlock.tilefloor, "tilefloor");
		GameRegistry.registerBlock(CityBlock.woodTile, "woodtile");
		GameRegistry.registerBlock(CityBlock.asphalt, "asphalt");
		GameRegistry.registerBlock(CityBlock.weedytiles, "weedytiles");
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(worldGen, 1);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		basicCity = new BiomeDyrewulfCity(Reference.BIOMEID, Blocks.grass, Blocks.stone).setBiomeName("Basic City").setTemperatureRainfall(1.0F, 0.5F);
		BiomeDictionary.registerBiomeType(basicCity, Type.SPARSE, Type.PLAINS);
		BiomeDictionary.registerAllBiomes();
		DyreCityWorldType.addCustomWorldTypes();
		
	}
}
