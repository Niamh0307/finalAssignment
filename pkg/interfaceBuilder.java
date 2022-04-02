import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class interfaceBuilder {
	
	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	System.out.println("Welcome to the bus network information system. Please select option 1, 2, 3"
			+ " or type quit to exit \n");
	String answer = input.nextLine();
	boolean validInput = false;
	boolean finished = false;
	while (!finished)
	{
		if (answer.equals("1"))
		{
			System.out.println("Thank you for selecting option 1");
		}
		else if (answer.equals("2"))
		{
			System.out.println("Thank you for choosing option 2");
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
				+ "\nor type mainPage to return to the homescreen");
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
						input.next();
					}
					else correctStartTime = true;		
				}	
			}
			System.out.print("Thank you for selecting an start time. Now please select an end time");
			//String endTime = input.next();
			while (correctEndTime == false)
			{	
				//System.out.print("Please enter the end of the time interval \n");
				String endTime = input.nextLine();
				String endTimeCheck = timeCheck(endTime);
				if (endTimeCheck.equals("quit"))
				{
					finished = true;
					System.out.print("goodbye");	
				}
				else if (isValidInterval(startTime, endTime))
				{
				correctEndTime = true;
				}
				else
				{
					System.out.print("Please enter a valid start time or type quit or mainPage");
					input.next();
				}
				
			}
			
			
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
	public static boolean isValidInterval(String arrival, String departure)
	{
		LocalTime startInterval = LocalTime.parse(arrival);
		LocalTime endInterval = LocalTime.parse(departure);
		if (endInterval.isAfter(startInterval))
		{
			return true;
		}
		else 
			System.out.print("Selected end time is before the start time");
			return false;
	}
}

