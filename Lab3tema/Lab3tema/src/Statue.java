import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Statue extends Attraction implements Visitable{
    private final Map<LocalDate,Pair<LocalTime,LocalTime>> visitingTimetable;

    public Statue(String name, String description, Map<LocalDate, Pair<LocalTime, LocalTime>> visitingTimetable) {
        super(name, description);
        this.visitingTimetable = visitingTimetable;
    }

    @Override
    public Map<LocalDate, Pair<LocalTime, LocalTime>> getVisitingTimetable() {
        return visitingTimetable;
    }

    @Override
    public String toString() {
        return "Statue: " + getName() + ", "+ getDescription() + " , ";
    }
}
