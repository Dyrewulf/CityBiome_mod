package dyrewulf.citybiome.citymap;

public class FullChunkPoint extends CityMapPoint
{
	public int xmin;
	public int xmax;
	public int zmin;
	public int zmax;

	public FullChunkPoint(int chunkX, int chunkZ)
	{
		super(chunkX, chunkZ);
		int actualX = chunkX * 16;
		int actualZ = chunkZ * 16;
		
		xmin = actualX + 1;
		xmax = actualX + 15;
		zmin = actualZ + 1;
		zmax = actualZ + 15;
	}


}
