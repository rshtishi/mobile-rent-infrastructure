package com.github.rshtishi;

import java.util.ArrayList;
import java.util.List;

public class PhoneManager {

    private List<Phone> phones = new ArrayList<>();
    private Phone selectedPhone;

    public PhoneManager() {
        phones.add(new Phone("1", "Samsung Galaxy S9"));
        phones.add(new Phone("2", "Samsung Galaxy S8"));
        phones.add(new Phone("3", "Samsung Galaxy S8"));
        phones.add(new Phone("4", "Motorola Nexus 6"));
        phones.add(new Phone("5", "Oneplus 9"));
        phones.add(new Phone("6", "Apple iPhone 13"));
        phones.add(new Phone("7", "Apple iPhone 12"));
        phones.add(new Phone("8", "Apple iPhone 11"));
        phones.add(new Phone("9", "iPhone X"));
        phones.add(new Phone("10", "Nokia 3310"));
    }

    public List<Phone> listPhones() {
        return phones;
    }

    public void selectPhone(String phoneId) {
        selectedPhone = phones.stream()
                .filter(phone -> phone.getId().equals(phoneId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Phone not found!"));
    }

    public void bookPhone(Customer customer) {
        selectedPhone.book(customer);
    }

    public void returnPhone(Customer customer) {
        selectedPhone.returnPhone(customer);
    }

    public boolean isPhoneSelected() {
        return selectedPhone != null;
    }

    public Phone getSelectedPhone() {
        return selectedPhone;
    }

    public void setSelectedPhone(Phone selectedPhone) {
        this.selectedPhone = selectedPhone;
    }

    public void displayPhones() {
        System.out.println("Phones:");
        phones.forEach(phone -> {
            System.out.println(phone.getId() + " - " + phone.getBrand() + " - " + (phone.isBooked() ? "Booked" : "Available") + " - " + phone.getBookingDate());
        });
    }
}
