package dyrewulf.citybiome.citymap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CityMap
{
	private static Map<Long, CityMapPoint> mapPoints = new HashMap<Long, CityMapPoint>();
	private Random ran = new Random();
	
	private static final int[] NORTH = {0, 3, 5, 7, 9, 11, 13, 15};
	private static final int[] EAST = {0, 3, 6, 7, 10, 11, 14, 15};
	private static final int[] SOUTH = {0, 5, 6, 7, 12, 13, 14, 15};
	private static final int[] WEST = {0, 9, 10, 11, 12, 13, 14, 15};
	private static final int[] NW = {0, 9, 11, 13, 15};
	private static final int[] NS = {0, 5, 7, 13, 15};
	private static final int[] NE = {0, 3, 7, 13, 15};
	private static final int[] WS = {0, 12, 13, 14, 15};
	private static final int[] WE = {0, 10, 11, 14, 15};
	private static final int[] SE = {0, 6, 7, 14, 15};
	private static final int[] NWS = {0, 1, 13, 15};
	private static final int[] NWE = {0, 1, 11, 15};
	private static final int[] NSE = {0, 1, 7, 15};
	private static final int[] WSE = {0, 1, 14, 15};
	
	public CityMap()
	{
	}
	
	public CityMapPoint addMapPoint(int chunkx, int chunkz)
	{
		long n = (chunkx << 32) | (chunkz & 0XFFFFFFFFL);
		CityMapPoint newPoint = new CityMapPoint(chunkx, chunkz);
		mapPoints.put(n, newPoint);
		return newPoint;
	}
	
	public CityMapPoint getMapPoint(int chunkx, int chunkz)
	{
		long n = (chunkx << 32) | (chunkz & 0XFFFFFFFFL);
		if(mapPoints.containsKey(n)) {
			CityMapPoint getPoint = mapPoints.get(n);
			return getPoint;
		} else return new CityMapPoint(chunkx, chunkz);
	}
	
	public void setMapPoint(CityMapPoint mapPoint)
	{
		long n = (mapPoint.chunkX << 32) | (mapPoint.chunkZ & 0XFFFFFFFFL);
		mapPoints.put(n, mapPoint);
	}
	
	public boolean hasStructure(int chunkx, int chunkz)
	{
		long n = (chunkx << 32) | (chunkz & 0XFFFFFFFFL);
		
		return false;
	}
	
	public void mapIt(int X, int Z)
	{
		if(!hasStructure(X, Z))
		{
			int direction = (getMapPoint(X, Z - 1).goesNorth() | getMapPoint(X, Z + 1).goesSouth() | getMapPoint(X + 1, Z).goesEast() | getMapPoint(X - 1, Z).goesWest());
			switch(direction)
			{
			case 3: direction = NE[ran.nextInt(NE.length)];
					break;
			case 5:	direction = NS[ran.nextInt(NS.length)];
					break;
			case 6:	direction = SE[ran.nextInt(SE.length)];
					break;
			case 7:	direction = NSE[ran.nextInt(NSE.length)];
					break;
			case 9:	direction = NW[ran.nextInt(NW.length)];
					break;
			case 10:direction = WE[ran.nextInt(WE.length)];
					break;
			case 11:direction = NWE[ran.nextInt(NWE.length)];
					break;
			case 12:direction = WS[ran.nextInt(WS.length)];
					break;
			case 13:direction = NWS[ran.nextInt(NWS.length)];
					break;
			case 14:direction = WSE[ran.nextInt(WSE.length)];
					break;
			default:direction = ran.nextInt(16);
			}
			addMapPoint(X, Z).setDirection(direction);
		}
	}

}
