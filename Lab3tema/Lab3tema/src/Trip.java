import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Trip {
    private final LocalDate startDate;
    private final List<Attraction> attractions;

    public Trip(LocalDate startDate, List<Attraction> attractions) {
        this.startDate = startDate;
        this.attractions = attractions;
    }
    public void displayVisitableNotPayableLocations(){
        List<Visitable> visitablesNotPayableLocations=new ArrayList<>();
        for(Attraction attraction:attractions){
            if(attraction instanceof Visitable && !(attraction instanceof Payable)){
                visitablesNotPayableLocations.add((Visitable) attraction);
            }
        }
        visitablesNotPayableLocations.sort(Comparator.comparing(v -> v.getOpeningHour(startDate)));
        for (Visitable location : visitablesNotPayableLocations) {
            System.out.println(location + " - Opens at: " + location.getOpeningHour(startDate));
        }
    }
}
