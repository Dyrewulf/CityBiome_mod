package dyrewulf.citybiome.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DyreCityWorldType extends WorldType
{
private boolean hasNotificationData;

public static WorldType dyreCityOverland;

public DyreCityWorldType(String name) {
	super(name);
}

public static void addCustomWorldTypes(){
	dyreCityOverland = new DyreCityWorldType("Dyrewulf City").setNotificationData();
}

@Override
public WorldChunkManager getChunkManager(World world) {
	return new WorldChunkManagerDyreCity(world);
}

@Override
public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
	return new ChunkProviderDyreCity(world, world.getSeed(), true);
}

@Override
public int getMinimumSpawnHeight(World world) {
	return 64;
} 

/**
 * enables the display of generator.[worldtype].info message on the customize world menu
 */
private WorldType setNotificationData()
{
    this.hasNotificationData = true;
    return this;
}

/**
 * returns true if selecting this worldtype from the customize menu should display the generator.[worldtype].info
 * message
 */
@SideOnly(Side.CLIENT)
@Override
public boolean showWorldInfoNotice()
{
    return this.hasNotificationData;
}
}