package com.booking.api.dataProviders;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class MockDataGenerate {
    private static final Faker faker = new Faker();

    public static String randomFirstName() {
        return faker.name().firstName();
    }

    public static String randomLastname() {
        return faker.name().lastName();
    }

    public static int randomTotalPrice() {
        return faker.number().numberBetween(100, 500);
    }

    public static boolean randomDepositPaid() {
        return faker.bool().bool();
    }

    public static String randomAdditionalNeeds() {
        return faker.options().option("Breakfast", "Lunch", "Dinner");
    }

    public static String randomCheckin() {
        LocalDate checkin = faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return checkin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String randomCheckout() {
        LocalDate checkin = faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate checkout = checkin.plusDays(faker.number().numberBetween(1, 14));
        return checkout.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

//    protected static Faker faker = new Faker();
//
//    protected String randomFirstName = faker.name().firstName();
//    protected String randomLastname = faker.name().lastName();
//    protected int randomTotalPrice = faker.number().numberBetween(100, 500);
//    protected boolean randomDepositPaid = faker.bool().bool();
//    protected String randomAdditionalneeds = faker.options().option("Breakfast", "Lunch", "Dinner");
//
//
//    LocalDate checkin = faker.date().future(30, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//    LocalDate checkout = checkin.plusDays(faker.number().numberBetween(1, 14));
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    protected String randomCheckin = checkin.format(formatter);
//    protected String randomCheckout = checkout.format(formatter);

}
