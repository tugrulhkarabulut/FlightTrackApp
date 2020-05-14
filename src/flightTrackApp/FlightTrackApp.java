package flightTrackApp;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.Timer;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import javax.swing.JScrollPane;


public class FlightTrackApp {

	private JFrame frame;
	private DefaultTableModel flightsTableModel, capitalsTableModel;
	private JPanel flightsPanel, createFlightPanel, systemDatePanel, destinationsPanel;
	private JLabel lblAllFlights;
	private JButton backToListButton;
	private JLabel lblCreateANew;
	private JTextField aircraftModelField;
	private JTextField departureDateField;
	private JTextField airlinesField;
	private JComboBox<Object> destinationField, destinationFromField, destinationToField;
	private JButton btnSave;
	private JTable flightsTable, capitalsTable;
	private ArrayList<Capital> capitals;
	private ArrayList<Flight> flights;
	private ArrayList<Destination> destinations;
	private ArrayList<ControlTower> controlTowers;
	private JLabel lblDestination;
	private JLabel lblAirlines;
	private JLabel lblDeparture;
	private JLabel lblAircraftMode;
	private JTextField flightNumberField;
	private Timer timer;
	private LocalDateTime systemDateTime = LocalDateTime.now();
	private JTextField departureTimeField;
	private JLabel lblArrival;
	private JLabel lblDate;
	private JLabel lblTime;
	private JTextField arrivalDateField;
	private JTextField arrivalTimeField;
	private JLabel lblSystemDateTime;
	private Boolean systemRunning = false;
	private Button returnToFlightListButton;
	private TextField setSystemDateField, setSystemTimeField;
	private JPanel capitalsPanel;
	private JLabel lblCapitals;
	private JTextField capitalNameField;
	private JLabel lblName;
	private JLabel lblDeleteCapital;
	private Button capitalDeleteButton;
	private JButton btnAdddeleteCapitals;
	private Button capitalBackToFlightsBtn;
	private Button goToSystemDateButton;
	private Button btnDelayFlight;
	private TextField txtDelayHours;
	private Button btnGivePermissionToLand;
	private JComboBox<Object> controlTowersCombobox;
	private Reporter flightReporter;
	private JTable destinationsTable;
	private DefaultTableModel destinationsTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightTrackApp window = new FlightTrackApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FlightTrackApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initializeData();
		initalizeTimer();
		setupGUI();
	}
	
	/*
	 * Updates the system date time when system is running
	 */
	private void initalizeTimer() {
	    timer = new Timer(1000, new ActionListener(){
	       public void actionPerformed(ActionEvent e) {
	    	   if (systemRunning) {
	    		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:00");
	    		   lblSystemDateTime.setText("System Date: " + systemDateTime.format(formatter));
	    		   systemDateTime = systemDateTime.plusHours(1);
	    	   }
	       }
	    });
	    timer.start();
	}
	
	/*
	 * Provides initial data to application
	 */
	private void initializeData() {
		capitals = new ArrayList<Capital>();
		destinations = new ArrayList<Destination>();
		flights = new ArrayList<Flight>();
		controlTowers = new ArrayList<ControlTower>();
		
		capitals.add(new Capital("Istanbul"));
		capitals.add(new Capital("Paris"));
		capitals.add(new Capital("London"));
		capitals.add(new Capital("Tokio"));
		capitals.add(new Capital("Washington"));
		
		controlTowers.add(new ControlTower(capitals.get(0), frame));
		controlTowers.add(new ControlTower(capitals.get(1), frame));
		controlTowers.add(new ControlTower(capitals.get(2), frame));
		controlTowers.add(new ControlTower(capitals.get(3), frame));
		controlTowers.add(new ControlTower(capitals.get(4), frame));
		
		
		destinations.add(new Destination(capitals.get(0), capitals.get(1)));
		destinations.add(new Destination(capitals.get(0), capitals.get(2)));
		destinations.add(new Destination(capitals.get(0), capitals.get(3)));
		destinations.add(new Destination(capitals.get(2), capitals.get(1)));
		destinations.add(new Destination(capitals.get(2), capitals.get(3)));
		destinations.add(new Destination(capitals.get(2), capitals.get(4)));
		destinations.add(new Destination(capitals.get(2), capitals.get(0)));
		destinations.add(new Destination(capitals.get(3), capitals.get(1)));
		destinations.add(new Destination(capitals.get(4), capitals.get(3)));
		destinations.add(new Destination(capitals.get(1), capitals.get(3)));
		
		initializeFlight(
			new Flight(1, destinations.get(0), LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(10), "Turkish Airlines", "Boeing 747")
		);
		initializeFlight(
				new Flight(2, destinations.get(5), LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(24), "Emirates Airlines", "Boeing 777")
		);
		initializeFlight(
				new Flight(10, destinations.get(3), LocalDateTime.now().plusHours(12), LocalDateTime.now().plusHours(28), "Turkish Airlines", "Boeing 777-9")
		);
		initializeFlight(
				new Flight(23, destinations.get(6), LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(12), "Qatar Airlines", "Boeing 777-30ER")
		);
		initializeFlight(
				new Flight(12, destinations.get(2), LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(4), "Turkish Airlines", "Boeing 747")
		);
		initializeFlight(
				new Flight(11, destinations.get(7), LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(6), "Turkish Airlines", "Boeing 777")
		);
		initializeFlight(
				new Flight(131, destinations.get(4), LocalDateTime.now().plusWeeks(1), LocalDateTime.now().plusWeeks(1).plusHours(10), "Emirates Airlines", "Boeing 777-9")
		);
		initializeFlight(
				new Flight(13, destinations.get(8), LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(6), "Turkish Airlines", "Boeing 747")
		);
		initializeFlight(
				new Flight(14, destinations.get(9), LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(8), "Turkish Airlines", "Boeing 747")
		);
		initializeFlight(
				new Flight(28, destinations.get(3), LocalDateTime.now().plusHours(12), LocalDateTime.now().plusHours(20), "Qatar Airlines", "Boeing 777")
		);
		
		flightReporter = new Reporter("flights.txt");
		flightReporter.createFile();
		
	}
	
	private void initializeFlight(Flight fl) {
		final Flight newFlight = fl;
		flights.add(fl);
		Thread flightThread = new Thread(new Runnable() {
			public void run() {
				while(!newFlight.getStatus().equals(Flight.LANDED) && !newFlight.getStatus().equals(Flight.CANCELLED)) {
					Boolean statusChanged = false;
					if (newFlight.getArrival().isEqual(systemDateTime) || newFlight.getArrival().isBefore(systemDateTime)) {
						if (!newFlight.getStatus().equals(Flight.WAITING)) {										
							statusChanged = true;
							newFlight.setStatus(Flight.WAITING);
						}
					} else if (newFlight.getTakeOffTime().isEqual(systemDateTime) || newFlight.getTakeOffTime().isBefore(systemDateTime)) {
						if (newFlight.getStatus().equals(Flight.NOT_STARTED)) {
							statusChanged = true;
							newFlight.setStatus(Flight.STARTED);
							flightReporter.reportFlight(newFlight, "Takeoff");
						}
					}
					
					if (statusChanged) {
						for (int i = 0; i < flightsTable.getRowCount(); i++) {
							if (flightsTable.getValueAt(i, 0) == (Integer)newFlight.getFlightNumber()) {
								flightsTableModel.setValueAt(newFlight.getStatus(), i, 7);
							}
						}
					}
													
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
					
			}
		});
		flightThread.start();
	}
	
	/*
	 * GUI setup, interactions, event handlers, etc.
	 */
	private void setupGUI() {
		frame = new JFrame();
		frame.setBounds(100, 100, 960, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Flight Track App");
		frame.getContentPane().setLayout(null);
		
		flightsPanel = new JPanel();
		flightsPanel.setBounds(0, 25, 960, 640);
		flightsPanel.setLayout(null);
		flightsPanel.setVisible(false);
		frame.getContentPane().add(flightsPanel);
		
		JButton newFlightButton = new JButton("Create/Update Flight");
		newFlightButton.setBounds(500, 12, 194, 25);
		flightsPanel.add(newFlightButton);
		newFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createFlightPanel.setVisible(true);
				flightsPanel.setVisible(false);
			}
		});
		
		lblAllFlights = new JLabel("FLIGHTS");
		lblAllFlights.setBounds(170, 29, 109, 25);
		flightsPanel.add(lblAllFlights);
		
		flightsTableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
	            return false;
	        }
		};
		
		flightsTableModel.addColumn("Flight Number");
		flightsTableModel.addColumn("From");
		flightsTableModel.addColumn("To");
		flightsTableModel.addColumn("Airlines");
		flightsTableModel.addColumn("Aircraft Model");
		flightsTableModel.addColumn("Departure");
		flightsTableModel.addColumn("Arrival");
		flightsTableModel.addColumn("Status");
		flightsTableModel.addColumn("Takeoff");
		flightsTableModel.addColumn("Landing");
		
		flightsTable = new JTable(flightsTableModel);
		flightsTable.setFillsViewportHeight(true);
		flightsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		flightsTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		flightsTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		flightsTable.getColumnModel().getColumn(2).setPreferredWidth(60);
		flightsTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		flightsTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		flightsTable.getColumnModel().getColumn(5).setPreferredWidth(120);
		flightsTable.getColumnModel().getColumn(6).setPreferredWidth(120);
		flightsTable.getColumnModel().getColumn(7).setPreferredWidth(80);
		flightsTable.getColumnModel().getColumn(8).setPreferredWidth(120);
		flightsTable.getColumnModel().getColumn(9).setPreferredWidth(120);
		flightsTable.setRowSelectionAllowed(true);
		flightsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 125, 940, 350);
		scrollPane.setViewportView(flightsTable);
		flightsPanel.add(scrollPane);
		
		JButton btnSystemDateTime = new JButton("Update System Date");
		btnSystemDateTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flightsPanel.setVisible(false);
				systemDatePanel.setVisible(true);
			}
		});
		btnSystemDateTime.setBounds(700, 12, 194, 25);
		flightsPanel.add(btnSystemDateTime);
		
		btnAdddeleteCapitals = new JButton("Add/Delete Capitals");
		btnAdddeleteCapitals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capitalsPanel.setVisible(true);
				flightsPanel.setVisible(false);
			}
		});
		btnAdddeleteCapitals.setBounds(700, 48, 194, 25);
		flightsPanel.add(btnAdddeleteCapitals);
		
		JButton btnAddDestinations = new JButton("Add Destinations");
		btnAddDestinations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flightsPanel.setVisible(false);
				destinationsPanel.setVisible(true);
			}
		});
		btnAddDestinations.setBounds(500, 48, 194, 25);
		flightsPanel.add(btnAddDestinations);
		
		Button btnCancelFlight = new Button("Cancel Selected Flight");
		btnCancelFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowIndex = flightsTable.getSelectedRow();
				if (rowIndex == -1) {
					JOptionPane.showMessageDialog(frame, "No Flight Selected. Click a row on the table to select a flight", "Cancel Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int flightNumber = (Integer)flightsTable.getValueAt(rowIndex, 0);
				for (Flight fl : flights) {
					if (fl.getFlightNumber() == flightNumber) {
						if (fl.getStatus().equals(Flight.NOT_STARTED)) {
							ControlTower controlTower = controlTowers.get(controlTowersCombobox.getSelectedIndex());
							Boolean canceled = controlTower.cancelFlight(fl);
							if (canceled) {
								flightReporter.reportFlight(fl, "Canceled");
							}
							if (flightsTableModel.getValueAt(rowIndex, 0).equals(fl.getFlightNumber())) {
								flightsTableModel.setValueAt(fl.getStatus(), rowIndex, 7);
							}	
						} else {
							JOptionPane.showMessageDialog(frame, "Can not cancel flight. It's either cancelled or started", "Cancel Error", JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
				}
				

			}
		});
		btnCancelFlight.setBounds(10, 482, 200, 23);
		flightsPanel.add(btnCancelFlight);
		
		btnDelayFlight = new Button("Delay Selected Flight");
		btnDelayFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = flightsTable.getSelectedRow();
				if (rowIndex == -1) {
					JOptionPane.showMessageDialog(frame, "No Flight Selected. Click a row on the table to select a flight", "Cancel Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int flightNumber = (Integer)flightsTable.getValueAt(rowIndex, 0);
				int delayHours = 0;
				try {
					delayHours = Integer.parseInt(txtDelayHours.getText());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Please give a valid number", "Delay Error", JOptionPane.ERROR_MESSAGE);
					throw ex;
				}
				for (Flight fl : flights) {
					if (fl.getFlightNumber() == flightNumber) {
						if (fl.getStatus().equals(Flight.NOT_STARTED)) {							
							ControlTower controlTower = controlTowers.get(controlTowersCombobox.getSelectedIndex());
							controlTower.delayFlight(fl, delayHours);
							if (flightsTableModel.getValueAt(rowIndex, 0).equals(fl.getFlightNumber())) {
								flightsTableModel.setValueAt(fl.getTakeOffTimeFormatted(), rowIndex, 8);
							}	
						} else {
							JOptionPane.showMessageDialog(frame, "Can not delay took off or cancelled flight.", "Delay Error", JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
				}
			}
		});
		btnDelayFlight.setBounds(216, 481, 200, 23);
		flightsPanel.add(btnDelayFlight);
		
		txtDelayHours = new TextField();
		txtDelayHours.setText("Delay Time (Hours)");
		txtDelayHours.setBounds(216, 511, 200, 19);
		flightsPanel.add(txtDelayHours);
		
		btnGivePermissionToLand = new Button("Give Selected Flight Permission To Land");
		btnGivePermissionToLand.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int rowIndex = flightsTable.getSelectedRow();
			if (rowIndex == -1) {
				JOptionPane.showMessageDialog(frame, "No Flight Selected. Click a row on the table to select a flight", "Cancel Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int flightNumber = (Integer)flightsTable.getValueAt(rowIndex, 0);
			for (Flight fl : flights) {
				if (fl.getFlightNumber() == flightNumber) {
					if (fl.getArrival().isEqual(systemDateTime) || fl.getArrival().isBefore(systemDateTime)) {							
						ControlTower controlTower = controlTowers.get(controlTowersCombobox.getSelectedIndex());
						Boolean landed = controlTower.givePermissionToLand(fl, systemDateTime);
						if (landed) {							
							flightReporter.reportFlight(fl, "Landing");
						}
						if (flightsTableModel.getValueAt(rowIndex, 0).equals(fl.getFlightNumber())) {
							flightsTableModel.setValueAt(fl.getStatus(), rowIndex, 7);
							flightsTableModel.setValueAt(fl.getLandingTimeFormatted(), rowIndex, 9);
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Plane is not close enough.", "Landing Error", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
			}
		}
		});
		
		controlTowersCombobox = new JComboBox<Object>(capitals.toArray());
		Capital defaultCapital = capitals.get(0);
		flightsTableModel.setRowCount(0);
		for (Flight fl : flights) {
			Destination flDest = fl.getDestination();
			if (flDest.getFrom().getId() == defaultCapital.getId() || flDest.getTo().getId() == defaultCapital.getId()) {						
				flightsTableModel.addRow(fl.toArray());
			}
		}
		controlTowersCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Capital selectedCapital = (Capital)controlTowersCombobox.getSelectedItem();
				flightsTableModel.setRowCount(0);
				for (Flight fl : flights) {
					Destination flDest = fl.getDestination();
					if (flDest.getFrom().getId() == selectedCapital.getId() || flDest.getTo().getId() == selectedCapital.getId()) {						
						flightsTableModel.addRow(fl.toArray());
					}
				}
			}
		});
		controlTowersCombobox.setBounds(133, 93, 120, 25);
		flightsPanel.add(controlTowersCombobox);
		btnGivePermissionToLand.setActionCommand("");
		btnGivePermissionToLand.setBounds(422, 481, 300, 23);
		flightsPanel.add(btnGivePermissionToLand);
		
		JLabel lblControlTower = new JLabel("Control  Tower:");
		lblControlTower.setBounds(12, 98, 120, 15);
		flightsPanel.add(lblControlTower);
		

		
		systemDatePanel = new JPanel();
		systemDatePanel.setBounds(0, 25, 960, 640);
		systemDatePanel.setVisible(false);
		
		returnToFlightListButton = new Button("Go To Flight List");
		returnToFlightListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				systemDatePanel.setVisible(false);
				flightsPanel.setVisible(true);
			}
		});
		returnToFlightListButton.setActionCommand("");
		returnToFlightListButton.setBounds(307, 185, 225, 23);
		systemDatePanel.add(returnToFlightListButton);
		
		createFlightPanel = new JPanel();
		createFlightPanel.setBounds(0, 25, 960, 640);
		createFlightPanel.setLayout(null);
		frame.getContentPane().add(createFlightPanel);
		createFlightPanel.setVisible(false);
		
		backToListButton = new JButton("Back to flight list");
		backToListButton.setBounds(335, 12, 153, 25);
		backToListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createFlightPanel.setVisible(false);
				flightsPanel.setVisible(true);
			}
		});
		createFlightPanel.add(backToListButton);
		
		lblCreateANew = new JLabel("Create a new flight");
		lblCreateANew.setBounds(167, 0, 170, 25);
		createFlightPanel.add(lblCreateANew);
		
		aircraftModelField = new JTextField();
		aircraftModelField.setBounds(179, 340, 158, 19);
		createFlightPanel.add(aircraftModelField);
		aircraftModelField.setColumns(10);
		
		departureDateField = new JTextField();
		departureDateField.setColumns(10);
		departureDateField.setBounds(175, 282, 99, 19);
		createFlightPanel.add(departureDateField);
		
		airlinesField = new JTextField();
		airlinesField.setBounds(179, 198, 158, 19);
		createFlightPanel.add(airlinesField);
		airlinesField.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flightNumber = 0;
				int destIndex = 0;
				String airlines = null;
				LocalDate departureDate = null;
				LocalDate arrivalDate = null;
				LocalTime departureTime = null;
				LocalTime arrivalTime = null;
				String aircraftModel = null;
				try {
					airlines = airlinesField.getText();
					aircraftModel = aircraftModelField.getText();
					flightNumber = Integer.parseInt(flightNumberField.getText());
					destIndex = destinationField.getSelectedIndex();
					String[] departureDateText = departureDateField.getText().split("\\.");
					String departureTimeText = departureTimeField.getText();
					departureDate = LocalDate.of(
							Integer.parseInt(departureDateText[2]),
							Integer.parseInt(departureDateText[1]), 
							Integer.parseInt(departureDateText[0])
							);
					departureTime = LocalTime.of(Integer.parseInt(departureTimeText), 0, 0, 0);
					String[] arrivalDateText = arrivalDateField.getText().split("\\.");
					String arrivalTimeText = arrivalTimeField.getText();
					arrivalDate = LocalDate.of(
							Integer.parseInt(arrivalDateText[2]),
							Integer.parseInt(arrivalDateText[1]), 
							Integer.parseInt(arrivalDateText[0])
							);
					arrivalTime = LocalTime.of(Integer.parseInt(arrivalTimeText), 0, 0, 0);
				} catch (ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(frame, "Please give a valid date. Example: 10.5.2020");
					throw ex;
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Please give a valid flight number. Example: 14");
					throw ex;
				} catch (DateTimeException ex) {
					JOptionPane.showMessageDialog(frame, "Please give a valid date time");
					throw ex;
				}
				LocalDateTime departureDateTime = LocalDateTime.of(departureDate, departureTime);
				LocalDateTime arrivalDateTime = LocalDateTime.of(arrivalDate, arrivalTime);
				
				Flight existingFlight = null;
				int flightIndex = 0;
				for (Flight fl : flights) {
					if (fl.getFlightNumber() == flightNumber) {
						fl.setAircraftModel(aircraftModel);
						fl.setAirlines(airlines);
						fl.setDeparture(departureDateTime);
						fl.setArrival(arrivalDateTime);
						fl.setDestination(destinations.get(destIndex));
						existingFlight = fl;
						break;
					}
					flightIndex++;
				}
				
				if (existingFlight != null && (existingFlight.getDestination().getFrom() == controlTowersCombobox.getSelectedItem() || existingFlight.getDestination().getTo() == controlTowersCombobox.getSelectedItem())) {
					for (int i = 0; i < flightsTable.getRowCount(); i++) {
						if (existingFlight.getFlightNumber() == (Integer)flightsTable.getValueAt(i, 0)) {													
							if (flightsTableModel.getValueAt(i, 0).equals(existingFlight.getFlightNumber())) {
								flightsTableModel.setValueAt(flightNumber, i, 0);
								flightsTableModel.setValueAt(destinations.get(destIndex).getFrom(), i, 1);
								flightsTableModel.setValueAt(destinations.get(destIndex).getTo(), i, 2);
								flightsTableModel.setValueAt(airlines, i, 3);
								flightsTableModel.setValueAt(aircraftModel, i, 4);
								flightsTableModel.setValueAt(existingFlight.getDepartureFormatted(), i, 5);
								flightsTableModel.setValueAt(existingFlight.getArrivalFormatted(), i, 6);
								flightsTableModel.setValueAt(existingFlight.getStatus(), flightIndex, 7);
								flightsTableModel.setValueAt(existingFlight.getTakeOffTimeFormatted(), i, 8);
								flightsTableModel.setValueAt(existingFlight.getLandingTimeFormatted(), i, 9);
							}						
						}
					}
				}
				
			
				
				
				
				if (existingFlight == null) {
					final Flight newFlight = new Flight(flightNumber, destinations.get(destIndex), departureDateTime, arrivalDateTime, airlines, aircraftModel);
					initializeFlight(newFlight);
					if (newFlight.getDestination().getFrom() == controlTowersCombobox.getSelectedItem() || newFlight.getDestination().getTo() == controlTowersCombobox.getSelectedItem()) {						
						flightsTableModel.addRow(newFlight.toArray());
					}
				}
				
				airlinesField.setText("");
				departureDateField.setText("");
				departureTimeField.setText("");
				arrivalDateField.setText("");
				arrivalTimeField.setText("");
				aircraftModelField.setText("");
				flightNumberField.setText("");
				destinationField.setSelectedIndex(0);
				
				createFlightPanel.setVisible(false);
				flightsPanel.setVisible(true);
				
				
			}
		});
		btnSave.setBounds(280, 371, 80, 25);
		createFlightPanel.add(btnSave);
		
		destinationField = new JComboBox<Object>(destinations.toArray());
		destinationField.setBounds(179, 138, 158, 24);
		createFlightPanel.add(destinationField);
		
		lblDestination = new JLabel("Destination");
		lblDestination.setBounds(180, 111, 94, 15);
		createFlightPanel.add(lblDestination);
		
		lblAirlines = new JLabel("Airlines");
		lblAirlines.setBounds(179, 171, 70, 15);
		createFlightPanel.add(lblAirlines);
		
		lblDeparture = new JLabel("Departure");
		lblDeparture.setBounds(179, 239, 95, 15);
		createFlightPanel.add(lblDeparture);
		
		lblAircraftMode = new JLabel("Aircraft Model");
		lblAircraftMode.setBounds(179, 313, 114, 15);
		createFlightPanel.add(lblAircraftMode);
		
		flightNumberField = new JTextField();
		flightNumberField.setBounds(177, 80, 160, 19);
		createFlightPanel.add(flightNumberField);
		flightNumberField.setColumns(10);
		
		JLabel lblFlightNumber = new JLabel("Flight Number");
		lblFlightNumber.setBounds(177, 53, 116, 15);
		createFlightPanel.add(lblFlightNumber);
		
		departureTimeField = new JTextField();
		departureTimeField.setBounds(335, 282, 33, 19);
		createFlightPanel.add(departureTimeField);
		departureTimeField.setColumns(10);
		
		JLabel lblDepartureDate = new JLabel("Date(dd.mm.yyyy)");
		lblDepartureDate.setBounds(179, 266, 130, 15);
		createFlightPanel.add(lblDepartureDate);
		
		JLabel lblDepartureTime = new JLabel("Time(Only Hour)");
		lblDepartureTime.setBounds(335, 266, 130, 15);
		createFlightPanel.add(lblDepartureTime);
		
		lblArrival = new JLabel("Arrival");
		lblArrival.setBounds(485, 239, 70, 15);
		createFlightPanel.add(lblArrival);
		
		lblDate = new JLabel("Date");
		lblDate.setBounds(485, 266, 70, 15);
		createFlightPanel.add(lblDate);
		
		lblTime = new JLabel("Time");
		lblTime.setBounds(582, 266, 70, 15);
		createFlightPanel.add(lblTime);
		
		arrivalDateField = new JTextField();
		arrivalDateField.setBounds(485, 282, 82, 19);
		createFlightPanel.add(arrivalDateField);
		arrivalDateField.setColumns(10);
		
		arrivalTimeField = new JTextField();
		arrivalTimeField.setBounds(582, 282, 33, 19);
		createFlightPanel.add(arrivalTimeField);
		arrivalTimeField.setColumns(10);
		frame.getContentPane().add(systemDatePanel);
		
		lblSystemDateTime = new JLabel("System Date Time: Not set");
		lblSystemDateTime.setBounds(10, 12, 253, 15);
		frame.getContentPane().add(lblSystemDateTime);
		
		Button startSystemDateButton = new Button("Start System Date");
		startSystemDateButton.setBounds(307, 96, 225, 23);
		startSystemDateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				systemRunning = true;
			}
		});
		systemDatePanel.setLayout(null);
		systemDatePanel.add(startSystemDateButton);
		
		Button stopSystemDateButton = new Button("Stop System Date");
		stopSystemDateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				systemRunning = false;
			}
		});
		stopSystemDateButton.setBounds(307, 140, 225, 23);
		systemDatePanel.add(stopSystemDateButton);
		
		setSystemDateField = new TextField();
		setSystemDateField.setBounds(308, 71, 102, 19);
		systemDatePanel.add(setSystemDateField);
		
		Label lblSetSystemDate = new Label("Set System Date (Leave it blank for current date)");
		lblSetSystemDate.setBounds(307, 10, 391, 21);
		systemDatePanel.add(lblSetSystemDate);
		
		setSystemTimeField = new TextField();
		setSystemTimeField.setBounds(474, 71, 58, 19);
		systemDatePanel.add(setSystemTimeField);
		
		Label lblSystemDate = new Label("Date(dd.mm.yyyy)");
		lblSystemDate.setBounds(307, 44, 124, 21);
		systemDatePanel.add(lblSystemDate);
		
		Label lblSystemTime = new Label("Time(In Hours)");
		lblSystemTime.setBounds(477, 44, 100, 21);
		systemDatePanel.add(lblSystemTime);
		
		Button setSystemDateButton = new Button("Save");
		setSystemDateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDate systemDate = null;
				LocalTime systemTime = null;
				try {
					String[] systemDateText = setSystemDateField.getText().split("\\.");
					String systemTimeText = setSystemTimeField.getText();
					systemDate = LocalDate.of(
							Integer.parseInt(systemDateText[2]),
							Integer.parseInt(systemDateText[1]), 
							Integer.parseInt(systemDateText[0])
							);
					systemTime = LocalTime.of(Integer.parseInt(systemTimeText), 0, 0, 0);
					systemDateTime = LocalDateTime.of(systemDate, systemTime);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:00");
		    		lblSystemDateTime.setText("System Date: " + systemDateTime.format(formatter));
				} catch (ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(frame, "Please give a valid date");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frame, "Please give a valid time");
				} catch (DateTimeException ex) {
					JOptionPane.showMessageDialog(frame, "Please give a valid date time");
				}
			}
		});
		setSystemDateButton.setBounds(565, 71, 86, 23);
		systemDatePanel.add(setSystemDateButton);
		
		capitalsPanel = new JPanel();
		capitalsPanel.setBounds(0, 25, 960, 640);
		frame.getContentPane().add(capitalsPanel);
		capitalsPanel.setLayout(null);
		capitalsPanel.setVisible(false);
		
		lblCapitals = new JLabel("Capitals");
		lblCapitals.setBounds(255, 13, 58, 15);
		capitalsPanel.add(lblCapitals);

		
		capitalsTableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
	            return false;
	        }
		};
		capitalsTable = new JTable(capitalsTableModel);
		capitalsTable.setBounds(64, 40, 420, 350);
		capitalsTableModel.addColumn("#");
		capitalsTableModel.addColumn("Name");
		capitalsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		capitalsTable.setRowSelectionAllowed(true);
		JScrollPane capitalsScrollPane = new JScrollPane();
		capitalsScrollPane.setBounds(64, 40, 420, 350);
		capitalsScrollPane.setViewportView(capitalsTable);
		capitalsPanel.add(capitalsScrollPane);
		
		for (Capital cp : capitals) {
			capitalsTableModel.addRow(new Object[] { cp.getId(), cp.getName() });
		}

		
		capitalNameField = new JTextField();
		capitalNameField.setBounds(630, 57, 114, 19);
		capitalsPanel.add(capitalNameField);
		capitalNameField.setColumns(10);
		
		JLabel lblAddCapital = new JLabel("Add Capital");
		lblAddCapital.setBounds(642, 13, 114, 15);
		capitalsPanel.add(lblAddCapital);
		
		Button capitalAddButton = new Button("Add");
		capitalAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Capital newCapital = new Capital(capitalNameField.getText());
				ControlTower newControlTower = new ControlTower(newCapital, frame);
				capitals.add(newCapital);
				controlTowers.add(newControlTower);
				capitalsTableModel.addRow(new Object[] { newCapital.getId(), newCapital.getName() });
				destinationFromField.addItem(newCapital);
				destinationToField.addItem(newCapital);
				controlTowersCombobox.addItem(newCapital);
			}
		});
		capitalAddButton.setBounds(694, 82, 50, 23);
		capitalsPanel.add(capitalAddButton);
		
		lblName = new JLabel("Name");
		lblName.setBounds(633, 40, 70, 15);
		capitalsPanel.add(lblName);
		
		lblDeleteCapital = new JLabel("Delete Capital");
		lblDeleteCapital.setBounds(630, 147, 125, 15);
		capitalsPanel.add(lblDeleteCapital);
		
		capitalDeleteButton = new Button("Delete Selected Capital");
		capitalDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedCapitalIndex = capitalsTable.getSelectedRow();
				
				if (selectedCapitalIndex == -1) {
					JOptionPane.showMessageDialog(frame, "No Capital Selected. Click a row on the table to select a capital");
					return;
				}
				
				int capitalNumber = (Integer)capitalsTable.getValueAt(selectedCapitalIndex, 0);
				for (int i = 0; i < capitals.size(); i++) {
					if (capitals.get(i).getId() == capitalNumber) {
						capitals.remove(i);
						controlTowers.remove(i);
						break;
					}
				}
				
				capitalsTableModel.removeRow(selectedCapitalIndex);
				controlTowersCombobox.removeItemAt(selectedCapitalIndex);
				
				ArrayList<Destination> destinationsNew = new ArrayList<Destination>();
				for (int i = 0; i < destinations.size(); i++) {
					Destination destination = destinations.get(i);
					if (destination.getFrom().getId() == capitalNumber || destination.getTo().getId() == capitalNumber) {
						continue;
					}
					destinationsNew.add(destination);
				}
				destinations = destinationsNew;
						
				destinationField.removeAllItems();
				for (Destination dt : destinations) {
					destinationField.addItem(dt);
				}
								
				
			}
		});
		
		capitalBackToFlightsBtn = new Button("Back To Flights");
		capitalBackToFlightsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capitalsPanel.setVisible(false);
				flightsPanel.setVisible(true);
			}
		});
		capitalBackToFlightsBtn.setBounds(680, 320, 138, 23);
		capitalsPanel.add(capitalBackToFlightsBtn);
		capitalDeleteButton.setBounds(630, 180, 200, 23);
		capitalsPanel.add(capitalDeleteButton);
		
		destinationsPanel = new JPanel();
		destinationsPanel.setBounds(0, 25, 960, 640);
		frame.getContentPane().add(destinationsPanel);
		destinationsPanel.setLayout(null);
		destinationsPanel.setVisible(true);
		
		JLabel lblDestinations = new JLabel("Add New Destinations");
		lblDestinations.setBounds(350, 25, 175, 15);
		destinationsPanel.add(lblDestinations);
		
		Button destinationAddButtom = new Button("Add");
		destinationAddButtom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Capital from = (Capital)destinationFromField.getSelectedItem();
				Capital to = (Capital)destinationToField.getSelectedItem();
				if (from.getId() == to.getId()) {
					JOptionPane.showMessageDialog(frame, "Destination cannot be same.", "Destination Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				for (Destination dt : destinations) {
					if (dt.getFrom().equals(from) && dt.getTo().equals(to)) {
						JOptionPane.showMessageDialog(frame, "This destination already exists.", "Destination Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				Destination newDest = new Destination(from, to);
				destinations.add(newDest);
				destinationsTableModel.addRow(new Object[] { newDest.getId(), newDest.getFrom(), newDest.getTo() });
				destinationField.addItem(newDest);
			}
		});
		destinationAddButtom.setActionCommand("Add");
		destinationAddButtom.setBounds(500, 75, 50, 20);
		destinationsPanel.add(destinationAddButtom);
		
		Button destinationDeleteButton = new Button("Delete Selected Destination");
		destinationDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedDestIndex = destinationsTable.getSelectedRow();
				if (selectedDestIndex == -1) {
					JOptionPane.showMessageDialog(frame, "No Destination Selected. Click a row on the table to select a destination", "Delete Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int destId = (Integer)destinationsTable.getValueAt(selectedDestIndex, 0);
				destinationsTableModel.removeRow(selectedDestIndex);
				
				for (int i = 0; i < destinations.size(); i++) {
					if (destinations.get(i).getId() == destId) {
						destinations.remove(i);
						break;
					}
				}
		
				destinationField.removeAllItems();
				for (Destination dt : destinations) {
					destinationField.addItem(dt);
				}
			}
		});
		destinationDeleteButton.setBounds(10, 90, 200, 23);
		destinationsPanel.add(destinationDeleteButton);
		
		destinationFromField = new JComboBox<Object>(capitals.toArray());
		destinationFromField.setBounds(300, 50, 120, 20);
		destinationsPanel.add(destinationFromField);
		
		destinationToField = new JComboBox<Object>(capitals.toArray());
		destinationToField.setBounds(450, 50, 120, 20);
		destinationsPanel.add(destinationToField);
		
		goToSystemDateButton = new Button("Set System Date");
		goToSystemDateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				destinationsPanel.setVisible(false);
				systemDatePanel.setVisible(true);
			}
		});
		goToSystemDateButton.setBounds(700, 50, 150, 23);
		destinationsPanel.add(goToSystemDateButton);
		
		JLabel lblAvailableDestinations = new JLabel("Available Destinations");
		lblAvailableDestinations.setBounds(390, 120, 200, 15);
		destinationsPanel.add(lblAvailableDestinations);
		
		JScrollPane destinationsScrollPane = new JScrollPane();
		destinationsScrollPane.setBounds(10, 150, 940, 300);
		destinationsPanel.add(destinationsScrollPane);
		
		destinationsTableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
	            return false;
	        }
		};
		destinationsTableModel.addColumn("#");
		destinationsTableModel.addColumn("From");
		destinationsTableModel.addColumn("To");

		
		destinationsTable = new JTable(destinationsTableModel);
		for (Destination dt : destinations) {			
			destinationsTableModel.addRow(new Object[] { dt.getId() ,dt.getFrom(), dt.getTo() });
		}
		destinationsScrollPane.setViewportView(destinationsTable);
		
		
	}
}

