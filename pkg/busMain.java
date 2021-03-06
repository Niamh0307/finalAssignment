import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class busMain <Value> {

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
	            		String stopDesc = line[3];
	            		double stopLat = Double.parseDouble(line[4]);
	            		double stopLon = Double.parseDouble(line[5]);
	            		String stopZone = line[6];
	            		String stopURL = line[7];
	            		int stopLocType = Integer.parseInt(line[8]);
	            		stops.add(new busStops(stopID, stopCode, stopName, stopDesc, stopLat, stopLon, 
	            				stopZone, stopURL, stopLocType));	
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
	            	}
	            	
	            } 

	            
		}
		catch(FileNotFoundException e)
		{
			System.out.print("File not found");
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
	  			//System.out.println(timeArriv);
	    		}
	    	}
		
		catch(FileNotFoundException e)
		{
			System.out.print("File not found");
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
			System.out.print("File not found");
		}

		Scanner input = new Scanner(System.in);
		boolean finished = false;
		boolean finishedPart1 = false;
		boolean finishedPart2 = false;
		boolean finishedPart3 = false;
		while (!finished)
		{
			System.out.println("Welcome to the bus network information system. Please select option 1, 2, 3"
				+ " or type quit to exit");
			String answer = input.next();
			if (answer.equals("1"))
			{
				edgeWeightedDigraph digraph = edgeWeightedDigraph(stops, stopTimes, transfers);

				System.out.println("Thank you for selecting option 1");
				System.out.println("Please enter your starting stop or type mainpage to return to the main menu");
				
				finishedPart1 = false;
				while (!finishedPart1)
				{
					String stopLookingFor = input.next();
					if (isNumber(stopLookingFor))
					{
						int startingStop = Integer.parseInt(stopLookingFor);
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
									System.out.println("The path to this stop is " + dijkstra.pathTo(finalStop, stops));
									System.out.print("The details of the final stop are as follows \n");
									for (int i =0; i < stops.size(); i++)
									{
										if (stops.get(i).stopID == finalStop)
										{
											System.out.println("");
											System.out.println("Stop ID: " + stops.get(i).stopID);
											System.out.println("Stop Name: " + stops.get(i).stopName);
											System.out.println("Stop Description: " + stops.get(i).stopDesc);
											System.out.println("");
										}
									}
									finishedPart1 = true;
									
								}
								else
								{
									System.out.print("There is no path between those stops");
									finishedPart1 = true;
								}
							}
							else
							{
								System.out.print("invalid stop \n");
								//input.nextLine();
							}
							
						}
						else
						{
							System.out.print("invalid stop\nPlease enter a valid stop \n");
							input.nextLine();
						}
					}
					else if (stopLookingFor.equals("mainpage"))
					{
						finishedPart1 = true;
					}
				}
			}
			else if (answer.equals("2"))
			{
				finishedPart2 = false;
				while (!finishedPart2)
				{
					System.out.println("Thank you for choosing option 2");
					TST ternaryBusStops = new TST();
					for(int i = 0; i < stops.size(); i ++)
					{
						busStops currentStop = stops.get(i);
						String key = String.valueOf(stops.get(i).stopID);
						ternaryBusStops.put(currentStop.stopName, currentStop );	
					}
					System.out.println("Enter a stop to find or type mainpage to return to the main menu\n");
					String searchStop = input.next();
					if (searchStop.equals("mainpage"))
					{
						finishedPart2 = true;
					}
					else
					{	
						stopFindByPrefix(searchStop.toUpperCase(), ternaryBusStops);
						finishedPart2 = true;
					}
				}
			}
			else if (answer.equals("quit"))
			{
				System.out.print("goodbye");
				finished = true;
				input.close();
			}
			
			else if (answer.equals("3"))
			{
				finishedPart3 = false;
				while (!finishedPart3)
				{
					boolean correctStartTime = false;
					System.out.print("You have selected to find all trips within a given arrival time\n"
							+ "please input the begining of this time interval in the format hh:mm:ss \nor type mainpage to return to the main menu \n");
					
					while (!correctStartTime)
					{	
						String startTime = input.next();
						if (startTime.equals("mainpage"))
						{
							correctStartTime = true;
							finishedPart3 = true;
						}
						else
						{
							String startTimeCheck = timeCheck(startTime);
							if (startTimeCheck == "-1")
							{
								System.out.print("Please enter a valid start time or type mainpage to return to the main menu\n");
								input.nextLine();
							}
							else 
							{
								correctStartTime = true;	
								System.out.print("Thank you for selecting a start time. Below are the trips with this arrival time \n");
								ArrayList<busStopTimes> hello = arrivalBusStops(stopTimes, startTime);
								if (hello.size() == 0)
								{
									System.out.print("\nSorry there are no trips with this arrival time\n");
									finishedPart3 = true;
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
									finishedPart3 = true;
								}
							}
						}	
					}
				}
			}
			else
			{
				System.out.print("Please enter a valid option\n");
				//input.next();
			}
		
		}
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
	
	public static boolean charCompare (String time)
	{
		char ch1 = '1';
		char ch2 = '2';
		char chTime = time.charAt(0);
		if ((chTime==ch1) || (chTime==ch2))
		{
			return true;
		}
		else return false;
	}
	
	public static String timeCheck (String timeInputted)
	{
		String updatedTime;
		if (timeInputted.length() < 8 || timeInputted.length()>8)
		{
			//System.out.print("Invalid time\n");
			return "-1";
		}
		if (charCompare(timeInputted) == true)
		{
			if (isValidTime(timeInputted))
			{
				return timeInputted;
			}
			else 
				//System.out.print("Invalid time\n");
				return "-1";
		}
		else 
		{
			updatedTime = addChar(timeInputted);
			if (isValidTime(updatedTime))
			{
				return updatedTime;
			}
			else 
				//System.out.print("Invalid time\n");
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
				double weight = 1.0;
				DirectedEdge newEdge = new DirectedEdge(stopTimes.get(i-1).stopID, stopTimes.get(i).stopID, weight);
				digraph.addEdge(newEdge);
			}
		}
		for (int i = 1; i < transfers.size(); i ++)
		{
			if (transfers.get(i).toStopID == transfers.get(i-1).fromStopID)
			{
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
				insertionSortStopTimes(busStopArrival);
			}
		}
		return busStopArrival;
	}
	public static boolean isNumber (String number)
	{
		if (number == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(number);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
