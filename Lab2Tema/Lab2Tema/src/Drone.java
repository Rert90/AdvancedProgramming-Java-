import java.util.Objects;

public class Drone extends Vehicle{
    private int maxFlightDuration;

    public Drone(String id, int maxFlightDuration) {
        super(id);
        this.maxFlightDuration = maxFlightDuration;
    }

    public int getMaxFlightDuration() {
        return maxFlightDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Drone drone = (Drone) o;
        return maxFlightDuration == drone.maxFlightDuration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxFlightDuration);
    }
    public double getRemainingCapacity() {
        // Drones don't have a capacity, so we return a placeholder value
        return Double.POSITIVE_INFINITY;
    }
}
