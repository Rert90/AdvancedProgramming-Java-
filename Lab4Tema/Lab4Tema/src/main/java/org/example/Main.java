package org.example;

import com.github.javafaker.Faker;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        List<Destination> destinations = generateFakeDestinations(faker, 3);

        List<Person> people = generateFakePeople(faker, 10, 0.3, destinations);

        System.out.println("Generated People:");
        for (Person person : people) {
            System.out.println(person.getName() + " - " + person.getRole() + " to " + person.getDestination());
        }

        CarpoolingProblem problem = new CarpoolingProblem(people, destinations);

        List<String> destinationsPassedByDrivers = problem.destinationsPassedByDrivers();
        System.out.println("\nDestinations passed by drivers: " + destinationsPassedByDrivers);

        System.out.println("\nDestination to people map: ");
        Map<String, List<Person>> destinationToPeopleMap = problem.destinationToPeopleMap();
        for (Map.Entry<String, List<Person>> entry : destinationToPeopleMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Map<Person, List<Person>> matches = problem.matchDriversAndPassengers();
        System.out.println("\nMatches: " + matches);
    }

    private static List<Destination> generateFakeDestinations(Faker faker, int count) {
        List<Destination> destinations = new ArrayList<>();
        Set<String> uniqueDestinations = new HashSet<>();
        for (int i = 0; i < count; i++) {
            String destinationName = faker.address().city();
            String destinationLocation = faker.address().city();
            String destinationKey = destinationName + "-" + destinationLocation;
            if (uniqueDestinations.add(destinationKey)) {
                destinations.add(new Destination(destinationName, destinationLocation));
            }
        }
        return destinations;
    }

    private static List<Person> generateFakePeople(Faker faker, int count, double driverProbability, List<Destination> destinations) {
        List<Person> people = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String name = faker.name().fullName();
            String destination;

            if (random.nextDouble() < 0.7 && destinations.size() > 0) {
                destination = destinations.get(random.nextInt(destinations.size())).getName();
            } else {
                destination = faker.address().city();
            }
            String role = faker.bool().bool() ? "driver" : "passenger";
            people.add(new Person(name, destination, role));
        }
        return people;
    }
}
