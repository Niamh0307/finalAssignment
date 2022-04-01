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

public class busMain <Value> {

	public static void main(String[] args) {
		ArrayList<busStops> stops = new ArrayList<busStops>();
		ArrayList<busStopTimes> stopTimes = new ArrayList<busStopTimes>();
		ArrayList<busTransfers> transfers = new ArrayList<busTransfers>();
		//TST ternarySearchTree = new TST();
		BufferedReader reader = null;
		
			 try
			 {
				int i = 0;
				reader = new BufferedReader(new FileReader("C:\\Users\\35387\\git\\finalAssignment\\pkg\\stops.txt"));
				Scanner scanner = new Scanner(reader);
				scanner.nextLine();
	            while (scanner.hasNextLine()) 
	            {
	            	String [] line = scanner.nextLine().split(",");
	            	int stopID = Integer.parseInt(line[0]);
	            	String lineID = (line[1]);
	            	if(!line[1].contains(" "))
	            	{
	            		int stopCode = Integer.parseInt(line[1]);	
	            		String unsortedName = line[2];
	            		String stopName = renameAddress(unsortedName);
	            		//ternarySearchTree.put(stopName, i);
	            		String stopDesc = line[3];
	            		double stopLat = Double.parseDouble(line[4]);
	            		double stopLon = Double.parseDouble(line[5]);
	            		String stopZone = line[6];
	            		String stopURL = line[7];
	            		int stopLocType = Integer.parseInt(line[8]);
	            		stops.add(new busStops(stopID, stopCode, stopName, stopDesc, stopLat, stopLon, 
	            				stopZone, stopURL, stopLocType));
	            		insertionSort(stops);		
	            	}
	            	else 
	            	{
	            		int stopCode = 0;	
	            		String unsortedName = line[2];
	            		String stopName = renameAddress(unsortedName);
	            		String stopDesc = line[3];
	            		double stopLat = Double.parseDouble(line[4]);
	            		double stopLon = Double.parseDouble(line[5]);
	            		String stopZone = line[6];
	            		String stopURL = line[7];
	            		int stopLocType = Integer.parseInt(line[8]);
	            		stops.add(new busStops(stopID, stopCode, stopName, stopDesc, stopLat, stopLon, 
	            				stopZone, stopURL, stopLocType));
	            		insertionSort(stops);	
	            	}
	            	
	            } 

	            
		}
		catch(FileNotFoundException e)
		{
			System.out.print("hello");
		}
		
		try
		{
			reader = new BufferedReader(new FileReader("C:\\Users\\35387\\git\\finalAssignment\\pkg\\stop_times.txt"));
			Scanner scanner = new Scanner(reader);
			scanner.nextLine();
            while (scanner.hasNextLine())
	    	{
	    		String [] line = scanner.nextLine().split(",");
	    		int tripID = Integer.parseInt(line[0]);
	    		String timeArriv = timeCheck(line[1]);
	  			if (timeArriv != "-1")
	  			{
	    				LocalTime arrivalTime = LocalTime.parse(timeArriv);		
					
						String timeDep = timeCheck(line[2]);
	  					if (timeDep != "-1")
	  					{
	    					LocalTime departureTime = LocalTime.parse(timeDep);		
	    					int stopID = Integer.parseInt(line[3]);
	    					int stopSeq = Integer.parseInt(line[4]);
	    					String lineID = (line[5]);
	    	            	if(!line[5].contains(""))
	    	            	{
	    	            		int stopHead = Integer.parseInt(line[5]);
	    	            		int pickUpType = Integer.parseInt(line[6]);
	    	            		int dropOffType = Integer.parseInt(line[7]);
	    	            		stopTimes.add(new busStopTimes(tripID, arrivalTime, departureTime, stopID,
	    									stopSeq, stopHead, pickUpType, dropOffType));
	    					}
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
	    		int fromStopID = Integer.parseInt(line[0]);
	    		int toStopID = Integer.parseInt(line[1]);
	    		int transferType = Integer.parseInt(line[2]);
	    		int minTranTime;
	    		if (line.length == 3)
	    		{	
	    			minTranTime = 0; 	
	    		}
	    		else
	    		{
	    			minTranTime = Integer.parseInt(line[2]);
	    			transfers.add(new busTransfers(fromStopID,toStopID, transferType, minTranTime));
	    		}	
	    	}
		}
		catch(FileNotFoundException e)
		{
			System.out.print("Hi");
		}
        

		
	}	 //Use Insertionosrt to sort the stops by stop id
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
		if (timeInputted.length() < 8 || timeInputted.length()>8)
		{
			System.out.print("error 1");
			return "-1";
		}
		else if (timeInputted.charAt(0) == 1 || timeInputted.charAt(0) == 1)
		{
			if (isValidTime(timeInputted))
			{
				return timeInputted;
			}
			else 
				System.out.print("error 2");
				return "-1";
		}
		else //if (timeInputted.length() == )
		{
			updatedTime = addChar(timeInputted);
			if (isValidTime(updatedTime))
			{
				return updatedTime;
			}
			else 
				System.out.print("error 3");
				return "-1";
		}
		
	}
	public static String addChar(String str) {
		String replacement = "0";
	    String result = replacement + str.substring(1);
	    return result;
	    
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
	
	public static String renameAddress(String str)
	{
		String firstThreeChars = str.substring(0, 3);
		String removeFirstThree = str.substring(3);
		String newString = removeFirstThree + " " + firstThreeChars;
		return newString;
	}
	
	public static edgeWeightedDigraph edgeWeightedDigraph(ArrayList<busStops> stops, ArrayList<busStopTimes> stopTimes, ArrayList<busTransfers> transfers)
	{
		Bag nodes = new Bag();
		for (int i = 0; i < stops.size(); i ++)
		{
			int current = stops.get(i).stopID;
			nodes.add(current);
		}
		edgeWeightedDigraph digraph = new edgeWeightedDigraph(nodes.size());
		for (int i = 0; i < stopTimes.size(); i ++)
		{
			if (stopTimes.get(i).tripID == stopTimes.get(i+1 ).tripID)
			{
			int current = stopTimes.get(i).stopID;
			int future = stopTimes.get(i+1).stopID;
			double weight = 1.0;
			DirectedEdge newEdge = new DirectedEdge(current, future, weight);
			}
			
		}
	}
}
