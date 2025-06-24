package org.ecom.utils.data;

import com.github.javafaker.Faker;

@SuppressWarnings("unused")
public class TestData {
    private final Faker faker;
    public TestData(){
        faker = new Faker();
    }
    public String getRandomEmail() {
        return faker.internet().emailAddress();
    }
    public String getRandomPassword() {
        return faker.internet().password(8, 16, true, true);
    }
    public String getRandomName() {
        return faker.name().fullName();
    }
    public String getRandomAddress() {
        return faker.address().fullAddress();
    }
    public String getRandomPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }
    public String getRandomCompanyName() {
        return faker.company().name();
    }
    public String getRandomProductName() {
        return faker.commerce().productName();
    }
    public String getRandomCategory() {
        return faker.commerce().department();
    }
}
