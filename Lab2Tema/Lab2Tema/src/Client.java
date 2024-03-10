import java.util.Objects;

class Client {
    private String id;
    private double demand;

    public Client(String id, double demand) {
        this.id = id;
        this.demand = demand;
    }

    public String getId() {
        return id;
    }

    public double getDemand() {
        return demand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}