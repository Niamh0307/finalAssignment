import java.time.LocalDate;

public class busTransfers {
	public int fromStopID;
	public int toStopID;
	public int transferType;
	public LocalDate minTranTime;
	
	busTransfers(int fromTo, int toStop, int transferType, LocalDate mintranTime)
	{
		this.fromStopID = fromTo;
		this.toStopID = toStop;
		this.transferType = transferType;
		this.minTranTime = minTranTime;
		
	}
}
