package FMS.entities;

import FMS.provided.DateTime;

public class ScheduledFlight extends Flight {
    private int distance;

    public ScheduledFlight(ScheduledFlight f) {
        super(f);
    }
    public ScheduledFlight(String flightID, String origin, String destination, DateTime departureTime, DateTime arrivalTime, int distance) {
        super(flightID, origin, destination, departureTime, arrivalTime);
        if(distance > 0)
            this.distance = distance;
    }

    @Override
    public int getBonusMiles() {
        return distance / 1000;
    }
}
