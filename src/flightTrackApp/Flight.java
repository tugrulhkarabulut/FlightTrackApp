package flightTrackApp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {
	
	public static String STARTED = "Started";
	public static String CANCELLED = "Cancelled";
	public static String NOT_STARTED = "Not Started";
	public static String LANDED = "Landed";
	public static String WAITING = "Waiting";
	
	private String aircraftModel;
	private int flightNumber;
	private int delayedHours;

	private String airlines;
	private LocalDateTime departure;
	private LocalDateTime arrival;
	private LocalDateTime takeOffTime;
	private LocalDateTime landingTime;
	private Destination destination;
	private volatile String status = NOT_STARTED;

	public Flight(int flightNumber, Destination destination, LocalDateTime departure, LocalDateTime arrival, String airlines, String aircraftModel) {		
		this.flightNumber = flightNumber;
		this.destination = destination;
		this.departure = departure;
		this.takeOffTime = departure;
		this.arrival = arrival;
		this.airlines = airlines;
		this.aircraftModel = aircraftModel;
	}
	
	public String getAircraftModel() {
		return aircraftModel;
	}

	public void setAircraftModel(String aircraftModel) {
		this.aircraftModel = aircraftModel;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAirlines() {
		return airlines;
	}

	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}

	public LocalDateTime getDeparture() {
		return departure;
	}

	public void setDeparture(LocalDateTime departure) {
		this.departure = departure;
	}

	public LocalDateTime getArrival() {
		return arrival;
	}

	public void setArrival(LocalDateTime arrival) {
		this.arrival = arrival;
	}

	public LocalDateTime getTakeOffTime() {
		return takeOffTime;
	}

	public void setTakeOffTime(LocalDateTime takeOffTime) {
		this.takeOffTime = takeOffTime;
	}

	public LocalDateTime getLandingTime() {
		return landingTime;
	}

	public void setLandingTime(LocalDateTime landingTime) {
		this.landingTime = landingTime;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getDelayedHours() {
		return delayedHours;
	}

	public void setDelayedHours(int delayedHours) {
		this.delayedHours = delayedHours;
	}

	public String getDepartureFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:00");
        return departure.format(formatter);
	}
	
	public String getArrivalFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:00");
        return arrival.format(formatter);
	}
	
	public String getTakeOffTimeFormatted() {
		if (takeOffTime != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:00");
			return takeOffTime.format(formatter);			
		}
		return "";
	}
	
	public String getLandingTimeFormatted() {
        if (landingTime != null) {
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:00");
        	return landingTime.format(formatter);
        }
        return "";
	}
	
	public Object[] toArray() {
		return new Object[] { flightNumber, destination.getFrom(), destination.getTo(), airlines, aircraftModel, getDepartureFormatted(), getArrivalFormatted(), status, getTakeOffTimeFormatted(), getLandingTimeFormatted() };
	}
	
	public String toString() {
		String[] stringArray = {
			String.valueOf(flightNumber),
			destination.getFrom().toString(),
			destination.getTo().toString(),
			getDepartureFormatted(),
			getArrivalFormatted(),
			getTakeOffTimeFormatted(),
			getLandingTimeFormatted(),
			String.valueOf(delayedHours),
			airlines,
			aircraftModel,
		};
		
		return String.join(",", stringArray);
	}


}
