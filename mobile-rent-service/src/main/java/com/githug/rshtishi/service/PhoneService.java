package com.githug.rshtishi.service;

import com.githug.rshtishi.entity.Phone;
import com.githug.rshtishi.exception.PhoneAvailableException;
import com.githug.rshtishi.exception.PhoneNotAvailableException;
import com.githug.rshtishi.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private PhoneRepository phoneRepository;

    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }


    public Phone bookPhone(long id, String bookedBy) {
        Phone phone = phoneRepository.findById(id).orElse(null);
        if(phone.isAvailability()){
            phone.setAvailability(false);
            phone.setBookedBy(bookedBy);
            phone.setBookedAt(LocalDateTime.now());
            phoneRepository.save(phone);
            return phone;
        }
        throw new PhoneNotAvailableException("Phone with name " + phone.getName() + " is not available");
    }

    public Phone returnPhone(long id, String returnedBy) {
        Phone phone = phoneRepository.findById(id).orElse(null);
        if(!phone.isAvailability()){
            phone.setAvailability(true);
            phone.setBookedBy("");
            phone.setBookedAt(null);
            phoneRepository.save(phone);
            return phone;
        }
        throw new PhoneAvailableException("Phone with name " + phone.getName() + " is already available");
    }
}
