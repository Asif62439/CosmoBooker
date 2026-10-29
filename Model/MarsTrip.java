package Model;

public class MarsTrip extends Destination {

    public MarsTrip() {
        super("Mars Colony", 500000, "6 Months");
    }

    
    public String getDetails() {
        return name + " | Time: " + travelTime + " | Price: $" + price;
    }
}
