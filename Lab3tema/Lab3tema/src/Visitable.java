import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface Visitable {
    Map<LocalDate, Pair<LocalTime, LocalTime>> getVisitingTimetable();
    default LocalTime getOpeningHour(LocalDate date){
        Map<LocalDate,Pair<LocalTime,LocalTime>> timetable=getVisitingTimetable();
        return timetable.getOrDefault(date,new Pair<>(LocalTime.MIN,LocalTime.MAX)).getKey();
    }
}
