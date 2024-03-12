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
        return "Depot{" + "name='" + name + '\'' + '}';
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
        return "Vehicle{" + "plateNumber='" + plateNumber + '\'' + '}';
    }
}

enum ClientType{
    REGULAR,
    PREMIUM
}

class Client{
    private String name;
    private ClientType type;
    private String visitingTime;

    public Client(String name, ClientType type,String visitingTime) {
        this.name = name;
        this.type = type;
        this.visitingTime=visitingTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Client{" + "name='" + name + '\'' + ", type=" + type + ", visitingTime='" + visitingTime + '\'' + '}';
    }
}


public class Main {
    public static void main(String[] args) {
        Depot depot = new Depot("Depot");
        Vehicle vehicle = new Vehicle("is12tst");
        Client regularClient = new Client("Hatz", ClientType.REGULAR,"9-12");
        Client premiumClient = new Client("gIaNi", ClientType.PREMIUM,"8-16");
        System.out.println(depot);
        System.out.println(vehicle);
        System.out.println(regularClient);
        System.out.println(premiumClient);
    }
}