package org.example;

public class Publisher {
    private int publisherId;
    private String name;
    private String location;

    public Publisher() {
    }

    public Publisher(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "publisherId=" + publisherId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
