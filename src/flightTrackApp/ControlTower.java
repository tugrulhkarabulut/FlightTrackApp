package flightTrackApp;
import java.awt.Component;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

public class ControlTower {
	private Capital capital;
	private Component frame;
	
	public ControlTower(Capital capital, Component frame) {
		this.capital = capital;
		this.frame = frame;
	}
	
	public Boolean cancelFlight(Flight fl) {
		if (fl.getDestination().getFrom().equals(capital)) {			
			fl.setStatus(Flight.CANCELLED);
			return true;
		}
		
		JOptionPane.showMessageDialog(frame, "This control tower can not cancel selected flight", "Cancel Error", JOptionPane.ERROR_MESSAGE);
		return false;
		
	}
	
	public Boolean delayFlight(Flight fl, int hours) {
		if (fl.getDestination().getFrom().equals(capital)) {			
			fl.setTakeOffTime(fl.getTakeOffTime().plusHours(hours));
			fl.setDelayedHours(fl.getDelayedHours() + hours);
			return true;
		}
		
		JOptionPane.showMessageDialog(frame, "This control tower can not delay selected flight", "Cancel Error", JOptionPane.ERROR_MESSAGE);
		return false;
		
	}
	
	public Boolean givePermissionToLand(Flight fl, LocalDateTime systemDateTime) {
		if (fl.getDestination().getTo().equals(capital)) {
			fl.setStatus(Flight.LANDED);
			fl.setLandingTime(systemDateTime);
			return true;
		}
		
		JOptionPane.showMessageDialog(frame, "This control tower can not give permission to selected flight", "Cancel Error", JOptionPane.ERROR_MESSAGE);
		return false;
	}
}
