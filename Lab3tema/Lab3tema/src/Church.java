import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Church extends Attraction implements Visitable,Payable{
    private final Map<LocalDate,Pair<LocalTime,LocalTime>> visitingTimetable;
    private final double entryFee;

    public Church(String name, String description, Map<LocalDate, Pair<LocalTime, LocalTime>> visitingTimetable, double entryFee) {
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
        return "Church: " + getName() +
                ", entryFee=" + entryFee + " , ";
    }
}
