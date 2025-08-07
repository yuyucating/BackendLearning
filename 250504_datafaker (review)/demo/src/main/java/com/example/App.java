package com.example;
import net.datafaker.Faker;
// import net.datafaker.providers.healthcare.Disease;
// import net.datafaker.providers.sport.Basketball;

public class App {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Faker faker = new Faker();
        for (int i=0; i<5; i++){
            System.out.println("人名"+(i+1)+": "+faker.name().fullName());
        }

        // Disease disease = faker.disease();
        System.out.println("疾病名稱: "+getRandomDisease(faker));
        
        System.out.println("球員名稱: "+getRandomPlayer(faker));
    }
    private static String getRandomDisease(Faker faker){
        return faker.disease().anyDisease();
    }
    private static String getRandomPlayer(Faker faker){
    //     // Faker faker = new Faker();
        return faker.basketball().players();
    }
}
