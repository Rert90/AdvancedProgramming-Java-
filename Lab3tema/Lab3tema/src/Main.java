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

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(6);
        List<Attraction> attractions = new ArrayList<>();
        attractions.add(statue1);
        attractions.add(statue);
        attractions.add(notreDame);
        attractions.add(concert);
        Trip trip = new Trip(startDate, attractions);

        trip.displayVisitableNotPayableLocations();

        // Create a travel plan
        Map<LocalDate, List<Attraction>> plan = new HashMap<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            List<Attraction> dailyAttractions = new ArrayList<>();

            Map<LocalDate, Pair<LocalTime, LocalTime>> statueTimetableForDay = new HashMap<>();
            statueTimetableForDay.put(currentDate, new Pair<>(LocalTime.of(10, 30), LocalTime.of(18, 0)));
            Statue statueForDay = new Statue("Statue of Liberty", "Symbol of freedom", statueTimetableForDay);

            Map<LocalDate, Pair<LocalTime, LocalTime>> notreDameTimetableForDay = new HashMap<>();
            notreDameTimetableForDay.put(currentDate, new Pair<>(LocalTime.of(8, 19), LocalTime.of(19, 0)));
            Church notreDameForDay = new Church("Notre Dame Cathedral", "Gothic masterpiece", notreDameTimetableForDay, 10.0);

            Map<LocalDate, Pair<LocalTime, LocalTime>> concertTimetableForDay = new HashMap<>();
            concertTimetableForDay.put(currentDate, new Pair<>(LocalTime.of(20, 0), LocalTime.of(23, 0)));
            Concert concertForDay = new Concert("Concert Hall", "Live music performances", concertTimetableForDay, 25.0);

            dailyAttractions.add(statueForDay);
            dailyAttractions.add(notreDameForDay);
            dailyAttractions.add(concertForDay);

            // Add daily attractions to the travel plan
            plan.put(currentDate, dailyAttractions);
            currentDate = currentDate.plusDays(1);
        }

        TravelPlan travelPlan = new TravelPlan(plan);
        travelPlan.printPlan();
    }
}