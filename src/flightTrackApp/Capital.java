package flightTrackApp;


public class Capital {
	public static int numCapitals = 0;
	private String name;
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Capital(String name) {
		numCapitals++;
		this.id = numCapitals;
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
