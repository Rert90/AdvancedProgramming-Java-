package org.example;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int groupSize = 8;
        Set<Person> persons = IntStream.range(0, groupSize)
                .mapToObj(i -> new Person("Person" + i, (int) (Math.random() * 50) + 18))
                .collect(Collectors.toSet());

        // Filter persons into drivers and passengers
        LinkedList<Driver> drivers = persons.stream()
                .filter(person-> person.hasDriverLicense() &&  Math.random() < 0.5)
                .map(person -> new Driver(person.getName(), person.getAge(), "Destination" + ((int) (Math.random() * 5) + 1)))
                .collect(Collectors.toCollection(LinkedList::new));

        TreeSet<Passenger> passengers = persons.stream()
                .filter(person -> !drivers.contains(person))
                .map(person -> new Passenger(person.getName(), person.getAge(), "Destination" + ((int) (Math.random() * 5) + 1)))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Passenger::compareTo)));


        System.out.println("Drivers (sorted by age):");
        drivers.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .forEach(System.out::println);

        System.out.println("\nPassengers (sorted by name):");
        passengers.forEach(passenger -> {
            System.out.println(passenger);
            if (passenger != null) {
                System.out.println("Has Driver's License: " + passenger.hasDriverLicense());
            }
        });
    }
}
