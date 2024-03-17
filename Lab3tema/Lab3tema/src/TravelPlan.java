import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class TravelPlan {
    private final Map<LocalDate, List<Attraction>> plan;

    public TravelPlan(Map<LocalDate, List<Attraction>> plan) {
        this.plan = plan;
    }
    public void printPlan(){
        for(Map.Entry<LocalDate,List<Attraction>> entry : plan.entrySet()){
            System.out.println("Date " + entry.getKey());
            List<Attraction> attractions = entry.getValue();
            for(Attraction attraction : attractions){
                if (attraction instanceof Visitable visitableAttraction) {
                    LocalTime openingHour = visitableAttraction.getOpeningHour(entry.getKey());
                    LocalTime closingHour = visitableAttraction.getVisitingTimetable().getOrDefault(entry.getKey(), new Pair<>(LocalTime.MIN, LocalTime.MAX)).getValue();
                    System.out.print(" - " + attraction.getName() + " Opens at " + openingHour + ", Closes at " + closingHour);

                    if (attraction instanceof Payable payableAttraction) {
                        double entryFee = payableAttraction.getEntryFee();
                        System.out.println(", Entry Fee: $" + entryFee);
                    } else {
                        System.out.println();
                    }
                } else {
                    System.out.println(" - " + attraction.getName());
                }
            }
            System.out.println();
        }
    }



}

