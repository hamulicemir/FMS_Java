package FMS.util;
import FMS.entities.*;
import FMS.provided.Matcher;

public class OriginMatcher implements Matcher<Flight>{
    private String origin;

    public OriginMatcher(String origin){
        this.origin = origin;
    }
    @Override
    public boolean match(Flight flight) {
        if(flight.getOrigin().equals(this.origin)){
            return true;
        }
        return false;
    }
}
