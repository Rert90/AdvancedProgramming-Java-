package org.example;
public class Driver extends Person {
    private String destination;

    public Driver(String name, int age, String destination) {
        super(name, age);
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    // Override toString to include destination information
    @Override
    public String toString() {
        return super.getName() + " - Age: " + super.getAge() + " - Destination: " + destination;
    }
}
