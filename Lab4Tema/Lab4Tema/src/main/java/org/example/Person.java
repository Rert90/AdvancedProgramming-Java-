package org.example;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Person {
    private String name;
    private String destination;
    private String role;

    public Person(String name, String destination, String role) {
        this.name = name;
        this.destination = destination;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return name;
    }
}
