import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Concert extends Attraction implements Visitable,Payable{
    private final Map<LocalDate,Pair<LocalTime,LocalTime>> visitingTimetable;
    private final double entryFee;

    public Concert(String name, String description, Map<LocalDate, Pair<LocalTime, LocalTime>> visitingTimetable, double entryFee) {
        super(name, description);
        this.visitingTimetable = visitingTimetable;
        this.entryFee = entryFee;
    }

    @Override
    public Map<LocalDate, Pair<LocalTime, LocalTime>> getVisitingTimetable() {
        return visitingTimetable;
    }

    @Override
    public double getEntryFee() {
        return entryFee;
    }

    @Override
    public String toString() {
        return "Concert: " +
                "attendingTime" + visitingTimetable +
                ", entryFee=" + entryFee +" , ";
    }
}
