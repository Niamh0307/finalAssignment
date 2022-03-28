import java.time.LocalDate;
import java.util.Date;

public class busStopTimes {
	
	public int tripID ;
	public LocalDate arrivalTime;
	public LocalDate departureTime;
	public int stopID;
	public int stopSeq;
	public int stopHead;
	public int pickUpType;
	public int dropOffType;
	public double distTrav;
	
	busStopTimes(int tripID, LocalDate arrivalTime, LocalDate departureTime, int stopID, int stopSeq, int stopHead,
			int pickUpType, int dropOffType, double distTravel)
	{
		this.tripID = tripID;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stopID = stopID;
		this.stopSeq = stopSeq;
		this.stopHead = stopHead;
		this.pickUpType = pickUpType;
		this.dropOffType = dropOffType;
		this.distTrav = distTravel;
	}
	
}
