package FMS.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import FMS.provided.Aircraft;
import FMS.provided.DateTime;
import FMS.provided.Passenger;
import FMS.provided.Staff;

/**
 * A flight in the flight management system (FMS).<br>
 * 
 * A flight stores information about place and time of departure and arrival as
 * well as the aircraft and personnel involved. Both crew and passengers can be
 * added to a flight. A flight is ready for boarding as soon as one crew member
 * is assigned (added to the flight). Passengers can be listed (added) and only
 * listed passengers can board the flight. Passengers can only board one flight
 * at a time.
 * 
 * 
 * Flights are naturally ordered by ID.
 * 
 *
 */
public abstract class Flight implements Comparable<Flight> {
	private DateTime arrival;
	private Set<Staff> crew;
	private DateTime departure;
	private String destination;
	private String flightID;
	private String origin;
	private Set<Passenger> passengers;
	private Aircraft vessel;

	public Flight(Flight f){
		this.arrival = new DateTime(arrival);
		this.crew = new HashSet<>(f.crew);
		this.departure = new DateTime(f.departure);
		this.destination = f.destination;
		this.flightID = f.flightID;
		this.origin = f.origin;
		this.passengers = new HashSet<>(f.passengers);
		this.vessel = f.vessel;
	}
	public Flight(String flightID, String origin, String destination, DateTime departure, DateTime arrival) {
		this.crew = new HashSet<>();
		this.passengers = new HashSet<>();
		setFlightID(flightID);
		setOrigin(origin);
		setDestination(destination);
		setDeparture(departure);
		setArrival(arrival);
	}
	private final String ensureNonNullNonEmpty(String str) {
		if(str != null && !str.isEmpty())
			return str;
		else
			throw new IllegalArgumentException("Flight must have a non-null & non-empty value");
	}
	public final void setFlightID(String flightID) {
		this.flightID = ensureNonNullNonEmpty(flightID);
	}
	public final void setDestination(String destination) {
		this.destination = ensureNonNullNonEmpty(destination);
	}
	public String getOrigin(){
		return this.origin;
	}
	public final void setOrigin(String origin){
		this.origin = ensureNonNullNonEmpty(origin);
	}
	public DateTime getDeparture(){
		return new DateTime(departure);
	}
	public final void setDeparture(DateTime departure){
		this.departure = new DateTime(departure);
	}
	public final void setArrival(DateTime arrival){
		this.arrival = new DateTime(arrival);
	}
	public String getFlightId(){
		return this.flightID;
	}
	public Flight setVessel(Aircraft vessel){
		this.vessel = vessel;
		return this;
	}
	public abstract int getBonusMiles();
	public int compareTo(Flight f){
		return this.flightID.compareTo(f.flightID); //natural ordering
		//return Integer.compare(Integer.parseInt(this.flightID), Integer.parseInt(f.flightID));
	}
	public boolean add(Staff staff){
		if(staff != null){
			if(!this.crew.contains(staff)){
				crew.add(staff);
				return true;
			}
		}
		return false;
	}
	public boolean add(Passenger passenger){
		if(this.vessel != null){
			if(!this.passengers.contains(passenger) && (passenger != null) && ((vessel.getCapactiy() > passengers.size()))){
				passengers.add(passenger);
				return true;
			}
		}
		return false;
	}
	public boolean readyToBoard(){
		if(!crew.isEmpty()){
			return true;
		}
		return false;
	}
	public boolean boardingCompleted(){
		if(passengers.isEmpty())
			return false;

		for (Passenger passenger : passengers) {
			if(passenger.getBoarded() != this)
				return false;
		}
		return true;
	}
	public boolean board(Passenger passenger){
		if(readyToBoard() && passengers.contains(passenger) && passenger.getBoarded() == this){
			passenger.setBoarded(this);
			return true;
		}
		return false;
	}
	/**
	 * Creates a String representation of this flight.<br>
	 * 
	 * @see Object#toString()
	 * @ProgrammingProblem.Hint provided
	 */
	@Override
	public String toString() {
		return String.format(
				"%5s from %3.3S (%s) to %3.3S (%s)" + " with a crew of %d and %d passengers "
						+ "<%s> boarding%scompleted \n%s\n%s",
				flightID, origin, departure, destination, arrival, 
				crew == null ? 0 : crew.size(),
				passengers == null ? 0 : passengers.size(),
				vessel == null ? "no vessel" : vessel.toString(),
				boardingCompleted() ? " " : " not ",
				crew,
				passengers);
	}

}
