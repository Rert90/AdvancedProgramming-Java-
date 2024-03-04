class Depot{
 private String name;
public Depot(String name){
    this.name=name;
}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "name='" + name + '\'' +
                '}';
    }
}
class Vehicle{
    private String plateNumber;
    public Vehicle(String plateNumber){
        this.plateNumber=plateNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "plateNumber='" + plateNumber + '\'' +
                '}';
    }
}

enum ClientType{
    REGULAR,
    PREMIUM
}

class Client{
    private String name;
    private ClientType type;

    public Client(String name, ClientType type) {
        this.name = name;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                '}';
    }
}


public class Main {
    public static void main(String[] args) {
        Depot depot = new Depot("Depot");
        Vehicle vehicle = new Vehicle("is12tst");
        Client regularClient = new Client("Hatz", ClientType.REGULAR);
        Client premiumClient = new Client("gIaNi", ClientType.PREMIUM);

        // Print the objects
        System.out.println(depot);
        System.out.println(vehicle);
        System.out.println(regularClient);
        System.out.println(premiumClient);
    }
}