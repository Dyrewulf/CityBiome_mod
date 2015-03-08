package dyrewulf.citybiome;

import dyrewulf.citybiome.blocks.CityBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class BuildingMaterials
{
	public Block floor = Blocks.planks;
	public Block foundation = Blocks.cobblestone;
	public Block corner = Blocks.stonebrick;
	public Block window = Blocks.glass_pane;
	public Block stairs = Blocks.stone_brick_stairs;
	public Block door = Blocks.wooden_door;
	public Block light = Blocks.glowstone;
	public Block wall = Blocks.stonebrick;
	public Block sidewalk = Blocks.stonebrick;
	public Block street = CityBlock.asphalt;
	
	public static final BuildingMaterials skyscraper = new BuildingMaterials().setCorner(CityBlock.brushedsteel).setWall(CityBlock.brushedsteel);
	public static final BuildingMaterials artdeco = new BuildingMaterials().setFoundation(CityBlock.tilefloor).setCorner(CityBlock.wiltern).setFloor(CityBlock.tilefloor).setWall(CityBlock.wiltern);
	public static final BuildingMaterials morroccan = new BuildingMaterials().setWall(CityBlock.mosque).setCorner(Blocks.sandstone).setStairs(Blocks.sandstone_stairs).setFloor(CityBlock.redwoodTile);
	public static final BuildingMaterials oldworld = new BuildingMaterials().setCorner(Blocks.log).setWall(CityBlock.stucco).setFloor(CityBlock.fadedwoodTile).setStairs(Blocks.birch_stairs);
	public static final BuildingMaterials brickandStone = new BuildingMaterials().setWall(CityBlock.redbrick).setFloor(CityBlock.woodTile);
	public static final BuildingMaterials rustic = new BuildingMaterials().setCorner(Blocks.log).setWall(CityBlock.fieldstone).setFoundation(Blocks.hardened_clay);
	public static final BuildingMaterials whitewashed = new BuildingMaterials().setCorner(CityBlock.stucco).setWall(CityBlock.stucco);
	public static final BuildingMaterials allbrick = new BuildingMaterials().setCorner(CityBlock.redbrick).setFoundation(CityBlock.redbrick).setWall(CityBlock.redbrick).setStairs(Blocks.oak_stairs);
	public static final BuildingMaterials logCabin = new BuildingMaterials().setCorner(Blocks.log2).setWall(Blocks.log).setStairs(Blocks.spruce_stairs);
	public static final BuildingMaterials desert = new BuildingMaterials().setCorner(Blocks.sandstone).setWall(Blocks.sandstone).setStairs(Blocks.sandstone_stairs).setFloor(Blocks.sandstone);
	public static final BuildingMaterials classicMinecraft = new BuildingMaterials().setCorner(Blocks.log).setWall(Blocks.planks);
	public static final BuildingMaterials nether = new BuildingMaterials().setCorner(Blocks.nether_brick).setWall(Blocks.nether_brick).setFloor(Blocks.netherrack).setFoundation(Blocks.nether_brick).setDoor(Blocks.air).setWindow(Blocks.nether_brick_fence).setStairs(Blocks.nether_brick_stairs);
	
	
	public BuildingMaterials()
	{
		super();
	}
	
	//the following methods return this so you can declare the changes
	public BuildingMaterials setFoundation(Block block)
	{
		foundation = block;
		return this;
	}
	
	public BuildingMaterials setFloor(Block block)
	{
		floor = block;
		return this;
	}
	
	public BuildingMaterials setCorner(Block block)
	{
		corner = block;
		return this;
	}
	
	public BuildingMaterials setWindow(Block block)
	{
		window = block;
		return this;
	}
	
	public BuildingMaterials setStairs(Block block)
	{
		stairs = block;
		return this;
	}
	
	public BuildingMaterials setDoor(Block block)
	{
		door = block;
		return this;
	}
	
	public BuildingMaterials setLight(Block block)
	{
		light = block;
		return this;
	}
	
	public BuildingMaterials setWall(Block block)
	{
		wall = block;
		return this;
	}
	
	public BuildingMaterials setSidewalk(Block block)
	{
		sidewalk = block;
		return this;
	}
	
	public BuildingMaterials setStreet(Block block)
	{
		street = block;
		return this;
	}
}
