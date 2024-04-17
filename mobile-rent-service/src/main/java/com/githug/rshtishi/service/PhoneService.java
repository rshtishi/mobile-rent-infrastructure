package com.githug.rshtishi.service;

import com.githug.rshtishi.entity.Phone;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {
    private List<Phone> phones;

    {
        phones = new ArrayList<>();
        // Add Phone objects to the list
        phones.add(new Phone(1L, "Samsung Galaxy S9", true, "", null));
        phones.add(new Phone(2L, "Samsung Galaxy S8", true, "", null));
        phones.add(new Phone(3L, "Samsung Galaxy S8", true, "", null));
        phones.add(new Phone(4L, "Motorola Nexus 6", true, "", null));
        phones.add(new Phone(5L, "Oneplus 9", true, "", null));
        phones.add(new Phone(6L, "Apple iPhone 13", true, "", null));
        phones.add(new Phone(7L, "Apple iPhone 12", true, "", null));
        phones.add(new Phone(8L, "Apple iPhone 11", true, "", null));
        phones.add(new Phone(9L, "iPhone X", true, "", null));
        phones.add(new Phone(10L, "Nokia 3310", true, "", null));
    }

    public List<Phone> getAllPhones() {
        return phones;
    }

    public Phone bookPhone(long id, String bookedBy) {
        Optional<Phone> maybePhone = phones.stream().filter(phone -> phone.getId() == id).findFirst();
        maybePhone.ifPresent(phone -> {
            phone.setAvailability(false);
            phone.setBookedBy(bookedBy);
            phone.setBookedAt(LocalDateTime.now());
        });
        return maybePhone.orElse(null);
    }

    public Phone returnPhone(long id, String bookedBy) {
        Optional<Phone> maybePhone = phones.stream().filter(phone -> phone.getId() == id).findFirst();
        maybePhone.ifPresent(phone -> {
            phone.setAvailability(true);
            phone.setBookedBy("");
        });
        return maybePhone.orElse(null);
    }
}
