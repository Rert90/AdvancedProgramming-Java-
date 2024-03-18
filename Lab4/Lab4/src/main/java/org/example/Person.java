package org.example;

public class Person {
    private String name;
    private boolean hasDriverLicense;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.hasDriverLicense = Math.random() < 0.5;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public boolean hasDriverLicense() {
        return hasDriverLicense;
    }


}
