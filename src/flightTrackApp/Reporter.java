package flightTrackApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Reporter {
	private String fileName;
	private File file;
	public Reporter(String fileName) {
		this.fileName = fileName;
	}
	
	public void createFile() {
		file = new File(fileName);
		try {			
			Boolean fileCreated = file.createNewFile();
			if (fileCreated) {				
				PrintWriter writer = new PrintWriter(file);
				String[] headers = new String[] {
						"Activity",
						"Flight Number",
						"From",
						"To",
						"Scheduled Departure",
						"Scheduled Arrival",
						"Takeoff",
						"Landing",
						"Delayed Hours",
						"Airlines",
						"Aircraft Model",
				};
				writer.write(String.join(",", headers));
				writer.append('\n');
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reportFlight(Flight fl, String activity) {
		try {			
			PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
			writer.append(activity + "," + fl.toString());
			writer.append('\n');
			writer.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
