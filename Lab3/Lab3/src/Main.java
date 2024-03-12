import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Statue statue1 = new Statue("David");
        Church church1 = new Church("St. Peter's Basilica", 15.0);
        Concert concert1 = new Concert("Symphony", 25.0);

        Trip myTrip = new Trip("Rome", "3 days");

        myTrip.addAttraction(statue1);
        myTrip.addAttraction(church1);
        myTrip.addAttraction(concert1);

        myTrip.visitAttractions();

        List<Visitable> attractions = new ArrayList<>();
        attractions.add(statue1);
        attractions.add(church1);
        attractions.add(concert1);

        Collections.sort(attractions, (a, b) -> {
            if (a instanceof Comparable && b instanceof Comparable && a.getClass() == b.getClass()) {
                return ((Comparable) a).compareTo(b);
            }
            return 0;
        });

        System.out.println("\nAttractions in sorted order:");
        for (Visitable attraction : attractions) {
            attraction.visit();
        }
    }
}
