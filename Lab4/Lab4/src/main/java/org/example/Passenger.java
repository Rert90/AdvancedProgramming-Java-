// Passenger.java
package org.example;

import java.util.Map;

public class Passenger extends Person implements Comparable<Passenger> {
    private String destination;
    private boolean hasDriverLicense;

    public Passenger(String name, int age, String destination) {
        super(name, age);
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public int compareTo(Passenger other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return super.getName() + " - Age: " + super.getAge() + " - Destination: " + destination;
    }

}
