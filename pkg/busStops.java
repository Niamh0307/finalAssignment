import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class busStops {
	public int stopID ;
	public int stopCode ;
	public String stopName ;
	public String stopDesc ;
	public double stopLat ;
	public double stopLon ;
	public String stopZone ;
	public String stopURL;
	public int stopLocType ;
	public int stopParZone ;
	
	
	busStops(int stopID, int stopCode, String stopName, String stopDesc, double stopLat, double stopLon, 
			String stopZone, String stopURL, int stopLocType, int stopParZone)
	{
		this.stopID = stopID;
		this.stopCode = stopCode;
		this.stopName = stopName;
		this.stopDesc = stopDesc;
		this.stopLat = stopLat;
		this.stopLon = stopLon;
		this.stopZone = stopZone;
		this.stopURL = stopURL;
		this.stopLocType = stopLocType;
		//System.out.print("Hello");
		this.stopParZone = stopParZone;
		
	}
	public static void main(String[] args)
	{
		System.out.print("well");
		//busStops bus = new busStops("stops.txt");
		//System.out.println(bus);
		
	}
}
