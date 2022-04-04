import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class busStopTimes {
	
	public int tripID ;
	public String arrivalTime;
	public String departureTime;
	public int stopID;
	public int stopSeq;
	public int pickUpType;
	public int dropOffType;
	
	busStopTimes(int tripID, String arrivalTime2, String departureTime2, int stopID, int stopSeq, 
			int pickUpType, int dropOffType)
	{
		this.tripID = tripID;
		this.arrivalTime = arrivalTime2;
		this.departureTime = departureTime2;
		this.stopID = stopID;
		this.stopSeq = stopSeq;
		//this.stopHead = stopHead;
		this.pickUpType = pickUpType;
		this.dropOffType = dropOffType;
	}
	
}
