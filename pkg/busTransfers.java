import java.time.LocalDate;
import java.time.LocalTime;

public class busTransfers {
	public int fromStopID;
	public int toStopID;
	public int transferType;
	public int minTranTime;
	
	busTransfers(int fromTo, int toStop, int transferType, int minTranTime2)
	{
		this.fromStopID = fromTo;
		this.toStopID = toStop;
		this.transferType = transferType;
		this.minTranTime = minTranTime2;
	}
}
