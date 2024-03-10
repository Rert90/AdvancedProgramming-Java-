public class Main {
    public static void main(String[] args) {

        Depot depot1 = new Depot("D1");
        Depot depot2 = new Depot("D2");

        Truck truck1 = new Truck("T1", 5000);
        Truck truck2 = new Truck("T2", 6000);

        Drone drone1 = new Drone("D1", 120);
        Drone drone2 = new Drone("D2", 150);

        Client client1 = new Client("C1", 2000);
        Client client2 = new Client("C2", 3000);
        Client client3 = new Client("C3", 4000);

        // Creating the problem
        Problem problem = new Problem();

        // Adding depots, vehicles, and clients to the problem
        problem.addDepot(depot1);
        problem.addDepot(depot2);

        problem.addVehicle(truck1);
        problem.addVehicle(truck2);
        problem.addVehicle(drone1);
        problem.addVehicle(drone2);

        problem.addClient(client1);
        problem.addClient(client2);
        problem.addClient(client3);

        Vehicle[] allVehicles = problem.getVehicles();
        for (Vehicle vehicle : allVehicles) {
            System.out.println("Vehicle ID: " + vehicle.getId());
        }

        problem.allocateClients();
    }
}