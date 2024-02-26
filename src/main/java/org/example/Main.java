package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        int result = n * 3;
        int result2 = result + Integer.parseInt("10101", 2);
        int result3 = result2 + Integer.parseInt("FF", 16);
        int finalresult = result3 * 6;
        System.out.println(finalresult);
    }
}