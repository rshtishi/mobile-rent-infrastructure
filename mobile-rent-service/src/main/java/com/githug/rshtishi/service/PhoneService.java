package com.githug.rshtishi.service;

import com.githug.rshtishi.dto.PhoneBookedEvent;
import com.githug.rshtishi.entity.Phone;
import com.githug.rshtishi.exception.PhoneAvailableException;
import com.githug.rshtishi.exception.PhoneNotAvailableException;
import com.githug.rshtishi.publisher.MobileRentNotificationPublisher;
import com.githug.rshtishi.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private MobileRentNotificationPublisher mobileRentNotificationPublisher;

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
            mobileRentNotificationPublisher.publishNotification(new PhoneBookedEvent(phone.getId(), phone.getName(), bookedBy));
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
