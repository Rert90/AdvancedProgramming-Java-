import java.util.*;
public class Problem {
    private List<Depot> depots;
    private List<Vehicle> vehicles;
    private List<Client> clients;

    public Problem(){
        depots=new ArrayList<>();
        vehicles=new ArrayList<>();
        clients=new ArrayList<>();
    }

    /**
     * Adds a depot to the problem.
     *
     * @param depot the depot to add
     */
    public void addDepot(Depot depot) {
        if (!depots.contains(depot)) {
            depots.add(depot);
        }
    }
    /**
     * Adds a vehicle to the problem.
     *
     * @param vehicle the vehicle to add
     */
    public void addVehicle(Vehicle vehicle){
        if(!vehicles.contains(vehicle)){
            vehicles.add(vehicle);
        }
    }
    /**
     * Adds a client to the problem.
     *
     * @param client the client to add
     */
    public void addClient(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
        }
    }
    public void allocateClients() {
        // Sort vehicles by remaining capacity in descending order
        vehicles.sort(Comparator.comparingDouble(Vehicle::getRemainingCapacity).reversed());

        // Assign clients to vehicles
        for (Client client : clients) {
            boolean assigned = false;
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getRemainingCapacity() >= client.getDemand()) {
                    System.out.println("Client " + client.getId() + " assigned to vehicle " + vehicle.getId());
                    assigned = true;
                    break;
                }
            }
            if (!assigned) {
                System.out.println("Cannot assign client " + client.getId() + " to any vehicle");
            }
        }
    }
    /**
     * Retrieves all vehicles from all depots.
     *
     * @return an array of all vehicles from all depots
     */
    public Vehicle[] getVehicles(){
        return vehicles.toArray(new Vehicle[0]);
    }

}
