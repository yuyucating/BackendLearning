package com.example;
import net.datafaker.Faker;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        // String name = faker.name().fullName();
        // System.out.println(name);

        for(int i=0; i<10;i++){
            String name = faker.name().fullName();
            System.out.println(name);
        }
    }
}
