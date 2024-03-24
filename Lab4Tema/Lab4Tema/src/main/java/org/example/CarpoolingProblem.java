package org.example;

import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.Collectors;

public class CarpoolingProblem {
    private List<Person> people;
    private List<Destination> destinations;

    public CarpoolingProblem(List<Person> people, List<Destination> destinations) {
        this.people = people;
        this.destinations = destinations;
    }

    public List<String> destinationsPassedByDrivers() {
        return people.stream()
                .filter(person -> person.getRole().equals("driver"))
                .map(Person::getDestination)
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, List<Person>> destinationToPeopleMap() {
        Map<String, List<Person>> map = new HashMap<>();
        for (Destination destination : destinations) {
            List<Person> peopleGoingToDestination = people.stream()
                    .filter(person -> person.getDestination().equals(destination.getName()))
                    .collect(Collectors.toList());
            map.put(destination.getName(), peopleGoingToDestination);
        }
        return map;
    }

    // Greedy algorithm to match drivers and passengers
    public Map<Person, List<Person>> matchDriversAndPassengers() {
        Map<Person, List<Person>> matches = new HashMap<>();
        List<Person> drivers = people.stream()
                .filter(person -> person.getRole().equals("driver")).toList();
        List<Person> passengers = people.stream()
                .filter(person -> person.getRole().equals("passenger")).toList();

        for (Person driver : drivers) {
            List<Person> matchedPassengers = new ArrayList<>();
            for (Person passenger : passengers) {
                if (passenger.getDestination().equals(driver.getDestination())) {
                    matchedPassengers.add(passenger);
                }
            }
            matches.put(driver, matchedPassengers);
        }
        return matches;
    }


    public static CarpoolingProblem generateProblem(int numPeople, int numDestinations, double driverProbability) {
        Faker faker = new Faker();
        List<Person> people = generateFakePeople(faker, numPeople, driverProbability);
        List<Destination> destinations = generateFakeDestinations(faker, numDestinations);
        return new CarpoolingProblem(people, destinations);
    }

    private static List<Person> generateFakePeople(Faker faker, int count, double driverProbability) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = faker.name().fullName();
            String destination = faker.address().city();
            String role = Math.random() < driverProbability ? "driver" : "passenger";
            people.add(new Person(name, destination, role));
        }
        return people;
    }

    private static List<Destination> generateFakeDestinations(Faker faker, int count) {
        List<Destination> destinations = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = faker.address().city();
            String location = faker.address().city();
            destinations.add(new Destination(name, location));
        }
        return destinations;
    }


}
