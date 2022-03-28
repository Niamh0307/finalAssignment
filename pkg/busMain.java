import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class busMain {

	public static void main(String[] args) {
		ArrayList<busStops> stops = new ArrayList<busStops>();
		ArrayList<busStopTimes> stopTimes = new ArrayList<busStopTimes>();
		ArrayList<busTransfers> transfers = new ArrayList<busTransfers>();
		BufferedReader reader = null;
			 try
			 {
				reader = new BufferedReader(new FileReader("C:\\Users\\35387\\git\\finalAssignment\\pkg\\stops.txt"));
				Scanner scanner = new Scanner(reader);
				scanner.nextLine();
	            //while (scanner.hasNextLine()) 
	            {
	            	//String lineOfInfo = scanner.nextLine();
	            	//String lines = scanner.nextLine();
	            	String [] line = scanner.nextLine().split(",");
	            	//for (int i = 0; i < line.length; i++)
	            	//{
	            		String lineID = (line[1]);
	            	//}
	    			int stopID = Integer.parseInt(line[0]);
	    			//System.out.print(stopID);
	    			//System.out.println(line[1]);
	    			int stopCode = Integer.parseInt(lineID);
	    			String stopName = line[2];
	    			String stopDesc = line[3];
	    			double stopLat = Double.parseDouble(line[4]);
	    			double stopLon = Double.parseDouble(line[5]);
	    			String stopZone = line[6];
	    			String stopURL = line[7];
	    			int stopLocType = Integer.parseInt(line[8]);
	    			int stopParZone = 0;
	    			stops.add(new busStops(stopID, stopCode, stopName, stopDesc, stopLat, stopLon, 
	    					stopZone, stopURL, stopLocType, stopParZone));
	    			insertionSort(stops);
	    			System.out.print(stopID);
	    		}
		}
		
		catch(FileNotFoundException e)
		{
			System.out.print("hi");
		}
	            
		
		/*	 
		try
		{
			reader = new BufferedReader(new FileReader("C:\\Users\\35387\\git\\finalAssignment\\pkg\\stop_times.txt"));
			Scanner scanner = new Scanner(reader);
			scanner.nextLine();
            while (scanner.hasNextLine())
	    	{
	    		String [] line = scanner.nextLine().split(",");
	    		{	
	    			int tripID = Integer.parseInt(line[0]);
	    			String TimeInString = line[1];
	    			String timeArriv = timeCheck(line[2])
	  				if (timeArriv != null)
	  				{
	    				LocalTime arrivalTime = LocalTime.parse(timeArriv);		
					
						String timeDep = timeCheck(line[3])
	  					if (timeDep != null)
	  					{
	    					LocalTime departureTime = LocalTime.parse(timeDep);		
	    					int stopID = Integer.parseInt(line[3]);
	    					int stopSeq = Integer.parseInt(line[4]);
	    					int stopHead = Integer.parseInt(line[5]);
	    					int pickUpType = Integer.parseInt(line[6]);
	    					int dropOffType = Integer.parseInt(line[7]);
	    					double distTrav = Double.parseDouble(line[8]);	
	    					stopTimes.add(new busStopTimes(tripID, arrivalTime, departureTime, stopID,
	    							stopSeq, stopHead, pickUpType, dropOffType,distTrav))
	    			}
	    		}
	    		
	    	}
		}
		catch(FileNotFoundException e)
		{
			System.out.print("Hi");
		}
		try
		{
			reader = new BufferedReader(new FileReader("C:\\Users\\35387\\git\\finalAssignment\\pkg\\transfers.txt"));
			Scanner scanner = new Scanner(reader);
			scanner.nextLine();
            while (scanner.hasNextLine())
	    	{
	    		String [] line = scanner.nextLine().split(",");
	    		{	
	    			int fromStopID = Integer.parseInt(line[0]);
	    			int toStopID = Integer.parseInt(line[1]);
	    			int transferType = Integer.parseInt(line[2]);
	    			String TimeInString = line[3];
	    			String minTranTime1 = timeCheck(line[2])
	  				if (minTranTime1 != null)
	  				{
	    				LocalTime minTranTime = LocalTime.parse(minTranTime1);		
	    				transfers.add(new busTransfers(fromStopID,toStopID, transferType, minTranTime));
	    			}
	    		}
	    		
	    	}
		}
		catch(FileNotFoundException e)
		{
			System.out.print("Hi");
		}
		

*/
	}	 //Use quickosrt to sort the stops by stop id
	public static ArrayList<busStops> insertionSort (ArrayList<busStops> stops)   
	{
		for (int j = 1; j < stops.size(); j++) 
		{
			int current = stops.get(j).stopID;
			int i = j-1;
			while ((i > -1) && (stops.get(i).stopID > current)) 
				{
			    	stops.get(i+1).stopID = stops.get(i).stopID;
			    	i--;
			    }
			stops.get(i+1).stopID = current;
		}
		return stops;
	}
	public static String timeCheck (String timeInputted)
	{
		String updatedTime;
		if (timeInputted.length() < 7)
		{
			return null;
		}
		else if (timeInputted.length() == 7)
		{
			updatedTime = addChar(timeInputted, (char) 0, 0);
			if (isValidTime(updatedTime))
			{
				return updatedTime;
			}
			else return null;
		}
		else if (timeInputted.length() == 8)
		{
			if (isValidTime(timeInputted))
			{
				return timeInputted;
			}
			else return null;
		}
		else
			return null;
		
	}
	public static String addChar(String str, char ch, int position) {
	    StringBuilder sb = new StringBuilder(str);
	    sb.insert(position, ch);
	    return sb.toString();
	}
	
	public static boolean isValidTime(String time)
	{
		try {
	        LocalTime.parse(time);
	        return true;
	    } catch (DateTimeParseException | NullPointerException e) {
	        return false;
	    }
	}
}
