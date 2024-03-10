import java.util.Objects;

class Truck extends Vehicle {
    private double capacity;
    private double usedCapacity;

    public Truck(String id, double capacity) {
        super(id);
        this.capacity = capacity;
        this.usedCapacity = 0;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getUsedCapacity() {
        return usedCapacity;
    }

    public void addLoad(double demand) {
        usedCapacity += demand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Truck truck = (Truck) o;
        return Double.compare(truck.capacity, capacity) == 0 &&
                Double.compare(truck.usedCapacity, usedCapacity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), capacity, usedCapacity);
    }

    @Override
    public double getRemainingCapacity() {
        return capacity - usedCapacity;
    }
}
