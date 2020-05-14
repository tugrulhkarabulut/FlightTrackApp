package flightTrackApp;


public class Destination {
	
	public static int numDestinations = 0;
	private int id;
	private Capital from;
	private Capital to;
	public Destination(Capital from, Capital to) {
		numDestinations++;
		
		this.id = numDestinations;
		this.from = from;
		this.to = to;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Capital getFrom() {
		return from;
	}

	public void setFrom(Capital from) {
		this.from = from;
	}

	public Capital getTo() {
		return to;
	}

	public void setTo(Capital to) {
		this.to = to;
	}

	public String toString() {
		return from.toString() + "-" + to.toString();
	}
}
