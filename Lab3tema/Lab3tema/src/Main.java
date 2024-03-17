import java.util.*;
import java.time.*;
public class Main {
    public static void main(String[] args) {
        // Create visiting timetable
        Map<LocalDate, Pair<LocalTime, LocalTime>> statueTimetable = new HashMap<>();
        statueTimetable.put(LocalDate.now(), new Pair<>(LocalTime.of(10, 20), LocalTime.of(18, 0)));
        Statue statue = new Statue("Statue of Liberty", "Symbol of freedom", statueTimetable);

        Map<LocalDate, Pair<LocalTime, LocalTime>> notreDameTimetable = new HashMap<>();
        notreDameTimetable.put(LocalDate.now(), new Pair<>(LocalTime.of(8, 0), LocalTime.of(19, 0)));
        Church notreDame = new Church("Notre Dame Cathedral", "Gothic masterpiece", notreDameTimetable, 10.0);

        Map<LocalDate, Pair<LocalTime, LocalTime>> concertTimetable = new HashMap<>();
        concertTimetable.put(LocalDate.now(), new Pair<>(LocalTime.of(20, 0), LocalTime.of(23, 0)));
        Concert concert = new Concert("Concert Hall", "Live music performances", concertTimetable, 25.0);

        Map<LocalDate,Pair<LocalTime,LocalTime>> statueNY = new HashMap<>();
        statueNY.put(LocalDate.now(), new Pair<>(LocalTime.of(2,2),LocalTime.of(19,2)));
        Statue statue1 = new Statue("NuMaiStiuStatue","aia e",statueNY);

        // Create a trip
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);
        List<Attraction> attractions = new ArrayList<>();
        attractions.add(statue1);
        attractions.add(statue);
        attractions.add(notreDame);
        attractions.add(concert);
        Trip trip = new Trip(startDate, attractions);

        // Display visitable locations that are not payable, sorted by opening hour
        trip.displayVisitableNotPayableLocations();

        // Create a travel plan
        Map<LocalDate, List<Attraction>> plan = new HashMap<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            List<Attraction> dailyAttractions = new ArrayList<>();
            // Create new instances of attractions for each day
            Map<LocalDate, Pair<LocalTime, LocalTime>> statueTimetableForDay = new HashMap<>();
            statueTimetableForDay.put(currentDate, new Pair<>(LocalTime.of(10, 30), LocalTime.of(18, 0))); // Example opening hours for today
            Statue statueForDay = new Statue("Statue of Liberty", "Symbol of freedom", statueTimetableForDay);

            Map<LocalDate, Pair<LocalTime, LocalTime>> notreDameTimetableForDay = new HashMap<>();
            notreDameTimetableForDay.put(currentDate, new Pair<>(LocalTime.of(8, 19), LocalTime.of(19, 0))); // Example opening hours for today
            Church notreDameForDay = new Church("Notre Dame Cathedral", "Gothic masterpiece", notreDameTimetableForDay, 10.0);

            Map<LocalDate, Pair<LocalTime, LocalTime>> concertTimetableForDay = new HashMap<>();
            concertTimetableForDay.put(currentDate, new Pair<>(LocalTime.of(20, 0), LocalTime.of(23, 0))); // Example opening hours for today
            Concert concertForDay = new Concert("Concert Hall", "Live music performances", concertTimetableForDay, 25.0);

            // Add attractions to the daily list
            dailyAttractions.add(statueForDay);
            dailyAttractions.add(notreDameForDay);
            dailyAttractions.add(concertForDay);

            // Add daily attractions to the travel plan
            plan.put(currentDate, dailyAttractions);
            currentDate = currentDate.plusDays(1);
        }


        // Print the travel plan
        TravelPlan travelPlan = new TravelPlan(plan);
        travelPlan.printPlan();
    }
}