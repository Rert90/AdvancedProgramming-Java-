import java.util.ArrayList;
import java.util.List;
class Trip {
    private String cityName;
    private String periodOfTime;
    private List<Visitable> attractions;

    public Trip(String cityName, String periodOfTime) {
        this.cityName = cityName;
        this.periodOfTime = periodOfTime;
        this.attractions = new ArrayList<>();
    }

    public void addAttraction(Visitable attraction) {
        attractions.add(attraction);
    }

    public void visitAttractions() {
        System.out.println("Visiting attractions in " + cityName + " for " + periodOfTime + ":");
        for (Visitable attraction : attractions) {
            attraction.visit();
        }
    }
}
