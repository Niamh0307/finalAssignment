import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class busStopTimes {
	
	public int tripID ;
	public LocalTime arrivalTime;
	public LocalTime departureTime;
	public int stopID;
	public int stopSeq;
	public int stopHead;
	public int pickUpType;
	public int dropOffType;
	public double distTrav;
	
	busStopTimes(int tripID, LocalTime arrivalTime2, LocalTime departureTime2, int stopID, int stopSeq, int stopHead,
			int pickUpType, int dropOffType, double distTravel)
	{
		this.tripID = tripID;
		this.arrivalTime = arrivalTime2;
		this.departureTime = departureTime2;
		this.stopID = stopID;
		this.stopSeq = stopSeq;
		this.stopHead = stopHead;
		this.pickUpType = pickUpType;
		this.dropOffType = dropOffType;
		this.distTrav = distTravel;
	}
	
}
