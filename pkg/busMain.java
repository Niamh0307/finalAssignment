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
	            		System.out.println(stopName);
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
	            		System.out.println(stopName);
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
	    				String arrivalTime = timeArriv;		
					
						String timeDep = timeCheck(line[2]);
	  					if (timeDep != "-1")
	  					{
	    					String departureTime = timeDep;		
	    					int stopID = Integer.parseInt(line[3]);
	    					int stopSeq = Integer.parseInt(line[4]);
	    	            	int pickUpType = Integer.parseInt(line[6]);
	    	            	int dropOffType = Integer.parseInt(line[7]);
	    	            	stopTimes.add(new busStopTimes(tripID, arrivalTime, departureTime, stopID,
	    									stopSeq, pickUpType, dropOffType));
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
        
		//edgeWeightedDigraph(stops, stopTimes, transfers);
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the bus network information system. Please select option 1, 2, 3"
				+ " or type quit to exit \n");
		String answer = input.nextLine();
		boolean validInput = false;
		boolean finished = false;
		//while (!finished)
		{
			if (answer.equals("1"))
			{
				edgeWeightedDigraph digraph = edgeWeightedDigraph(stops, stopTimes, transfers);
				System.out.println("Thank you for selecting option 1");
				System.out.println("Please enter your starting stop");
				int startingStop = input.nextInt();
				if (stopExists(stops, startingStop))
				{
					System.out.println("Please enter your end stop");
					int finalStop = input.nextInt();
					if (stopExists(stops, finalStop))
					{
						System.out.print("We will now calculate the shortest path\n");
						DijkstraSP dijkstra = new DijkstraSP(digraph, startingStop);
						if (dijkstra.distTo(finalStop) != Double.POSITIVE_INFINITY)
						{
						System.out.println("The distance to the distance between these points is " + dijkstra.distTo(finalStop));
						System.out.print("\nThe path to this stop is" + dijkstra.pathTo(finalStop));
						}
						else
							System.out.print("There is no path between those stops");
					}
					else
						System.out.print("invalid stop");
				}
				
			}
			else if (answer.equals("2"))
			{
				System.out.println("Thank you for choosing option 2");
				TST ternaryBusStops = new TST();
				for(int i = 0; i < stops.size(); i ++)
				{
					busStops currentStop = stops.get(i);
					String key = String.valueOf(stops.get(i).stopID);
					ternaryBusStops.put(currentStop.stopName, currentStop );	
				}
				System.out.print(ternaryBusStops.size());
				
				System.out.print("\nWhat stop would you like to find? \n");
				String searchStop = input.nextLine();
				stopFindByPrefix(searchStop.toUpperCase(), ternaryBusStops);
			}
			else if (answer.equals("quit"))
			{
				System.out.print("goodbye");
				finished = true;
				input.close();
			}
			
			else if (answer.equals("3"))
			{
				boolean correctStartTime = false;
				boolean correctEndTime = false;
				System.out.print("You have selected to find all trips within a given arrival time\n"
					+ "please input the begining of this time interval in the format hh:mm:ss \nor type quit to end the program "
					+ "\nor type mainPage to return to the homescreen \n");
				String startTime = input.nextLine();
				while (!correctStartTime)
				{
					//String startTime = input.nextLine();
					if (startTime.equals("quit"))
					{
						finished = true;
						System.out.print("goodbye");	
					}
					else
					{
						String startTimeCheck = timeCheck(startTime);
						if (startTimeCheck == "-1")
						{
							System.out.print("Please enter a valid start time or type quit or mainPage");
							input.nextLine();
						}
						else 
						{
							correctStartTime = true;	
							System.out.print("Thank you for selecting a start time. Below are the trips with this arrival time \n");
							ArrayList<busStopTimes> hello = arrivalBusStops(stopTimes, startTime);
							if (hello.size() == 0)
							{
								System.out.print("\nSorry there are no trips with this arrival time");
							}
							else
							{
								for (int i = 0; i < hello.size(); i ++)
								{
									System.out.println("Trip ID: " + hello.get(i).tripID);
									System.out.println("Arrival Time: "+ hello.get(i).arrivalTime);
									System.out.println("Departure Time:" + hello.get(i).departureTime);
									System.out.println("Stop ID: " + hello.get(i).stopID);
									System.out.println("");
									
								}
							}
								

						}
					}	
				}
				
				
				
				//String endTime = input.next();
				//while (!correctEndTime)
				/*{	
					//System.out.print("Please enter the end of the time interval \n");
					String endTime = input.nextLine();
					String endTimeCheck = timeCheck(endTime);
					if (endTime.equals("quit"))
					{
						finished = true;
						System.out.print("goodbye");	
					}
					else if (!endTimeCheck.equals("-1") && isValidInterval(startTime, endTime))
					{
						System.out.print("endtime check working");
						ArrayList<busStopTimes> tripsInInterval = new ArrayList<busStopTimes>();
						tripsInInterval.addAll(intervalList(startTime, endTime, stopTimes));
						insertionSortStopTimes(tripsInInterval);
						correctEndTime = true;
						for (int i = 0; i < tripsInInterval.size(); i ++)
						{
							System.out.println(tripsInInterval.get(i).tripID);
						}
					}
					else
					{
						System.out.println("Please enter a valid start time or type quit or mainPage");
						
					}
					
				}
				
				*/
			}
			else if (input.next() == "quit")
			{
				System.out.print("goodbye");
				finished = true;
			}
			else if (input.next() == "mainPage")
			{
				
			}
			

		
			else
			{
				System.out.print("Please enter a valid option");
				input.next();
			}
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
	public static ArrayList<busStopTimes> insertionSortStopTimes (ArrayList<busStopTimes> stopTimes)   
	{
		for (int j = 1; j < stopTimes.size(); j++) 
		{
			int current = stopTimes.get(j).tripID;
			int i = j-1;
			while ((i > -1) && (stopTimes.get(i).tripID > current)) 
				{
			    	stopTimes.get(i+1).tripID = stopTimes.get(i).tripID;
			    	i--;
			    }
			stopTimes.get(i+1).tripID = current;
		}
		return stopTimes;
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
		String firstNineChars = str.substring(0, 9);
		if (firstThreeChars.equals("WB ")|| firstThreeChars.equals("NB ")|| firstThreeChars.equals("SB ")|| firstThreeChars.equals("EB "))
		{
		String removeFirstThree = str.substring(3);
		String newString = removeFirstThree + " " + firstThreeChars;
		return newString;
		}
		else if (firstNineChars.equals("FLAGSTOP "))
		{
			String removeFirstNine = str.substring(9);
			String newString = removeFirstNine + " " + firstNineChars;
			String newCheck = newString.substring(0, 3);
			if (newCheck.equals("WB ")|| newCheck.equals("NB ")|| newCheck.equals("SB ")|| newCheck.equals("EB "))
			{
				String removeFirstThree = newString.substring(3);
				String doubleUpdate = removeFirstThree + newCheck;
				return doubleUpdate;
			}
			else return newString;	
		}
		else return str;
	}
	
	public static edgeWeightedDigraph edgeWeightedDigraph(ArrayList<busStops> stops, ArrayList<busStopTimes> stopTimes, ArrayList<busTransfers> transfers)
	{	
		edgeWeightedDigraph digraph = new edgeWeightedDigraph(stopTimes.size() + transfers.size());
		for (int i = 1; i < stopTimes.size(); i ++)
		{
			if (stopTimes.get(i).tripID == stopTimes.get(i-1).tripID)
			{
				int current = i-1;
				int future = i;
				String currentName = String.valueOf(stopTimes.get(i-1).stopID);
				String futureName = String.valueOf(stopTimes.get(i).stopID);
				double weight = 1.0;
				DirectedEdge newEdge = new DirectedEdge(stopTimes.get(i-1).stopID, stopTimes.get(i).stopID, weight);
				digraph.addEdge(newEdge);
			}
		}
		for (int i = 1; i < transfers.size(); i ++)
		{
			if (transfers.get(i).toStopID == transfers.get(i-1).fromStopID)
			{
				int current = i-1;
				int future = i;
				String currentName = String.valueOf(transfers.get(i-1).fromStopID);
				String futureName = String.valueOf(transfers.get(i).toStopID);
				double weight = 2.0;
				DirectedEdge newEdge = new DirectedEdge(transfers.get(i-1).fromStopID, transfers.get(i-1).toStopID, weight);
				digraph.addEdge(newEdge);
			}
			
		}
		return digraph;
	}


	
	public static void stopFindByPrefix(String searchName, TST stopsTree)
	{
		Iterable<String> stopsWithPrefix = stopsTree.keysWithPrefix(searchName);
		ArrayList<busStops> stopsInPrefix = new ArrayList<busStops>();
        boolean notEmpty = false;
        for (String key : stopsWithPrefix) 
        {
            if(key != null) 
            {
                notEmpty = true;
            }
        }
        if (notEmpty) 
        {
            for (String key : stopsWithPrefix) 
            {
            	busStops newBusStop = stopsTree.get(key);
                stopsInPrefix.add(newBusStop);
            }
            for (int i = 0; i < stopsInPrefix.size(); i++ )
            {
            System.out.println("Stop ID: " + stopsInPrefix.get(i).stopID);
            System.out.println("Stop Name: " + stopsInPrefix.get(i).stopName);
            System.out.println("Stop Description: " + stopsInPrefix.get(i).stopDesc);
            System.out.println("");
            }
        } 
        
        else 
        {
            System.out.println("No matching stops were found");
        }
        
	}
	public static boolean stopExists (ArrayList <busStops> stops, int stopIDGiven)
	{
		boolean found  = false;
		for (int i = 0; i < stops.size(); i ++)
		{
			if (stops.get(i).stopID == stopIDGiven)
			{
				found = true;
			}
		}
		return found;
		
	}
	public static ArrayList<busStopTimes> arrivalBusStops (ArrayList <busStopTimes> stopTimes, String arrivalsTime)
	{
		ArrayList<busStopTimes> busStopArrival = new ArrayList<busStopTimes>();
		for (int i = 0; i < stopTimes.size(); i ++)
		{
			String time = stopTimes.get(i).arrivalTime;
			if (time.equals(arrivalsTime))
			{
				busStopArrival.add(stopTimes.get(i));
			}
		}
		return busStopArrival;
	}
}
