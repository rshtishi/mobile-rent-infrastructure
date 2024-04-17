package com.githug.rshtishi.repository;

import com.githug.rshtishi.config.MysqlTestContainer;
import com.githug.rshtishi.entity.Phone;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = MysqlTestContainer.DataSourceInitializer.class)
class PhoneRepositoryTest implements MysqlTestContainer {

    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    @DisplayName("Should return all phones")
    void shouldReturnAllPhones() {
        List<Phone> phones = phoneRepository.findAll();
        assertThat(phones).isNotEmpty();
        assertThat(phones).hasSize(10);
    }

    @Test
    @DisplayName("Should return true if phone is not booked")
    void shouldReturnTrueIfPhoneIsNotBooked() {
        Phone phone = phoneRepository.findById(1L).get();
        assertThat(phone.isAvailability()).isTrue();
    }

    @Test
    @DisplayName("Should book the phone successfully")
    void shouldBookThePhoneSuccessfully(){
        String bookedBy = "john.doe@mail.com";
        Phone phone = phoneRepository.findById(1L).get();
        phone.setAvailability(false);
        phone.setBookedBy(bookedBy);
        phoneRepository.save(phone);
        Phone bookedPhone = phoneRepository.findById(1L).get();
        assertThat(bookedPhone.isAvailability()).isFalse();
        assertThat(bookedPhone.getBookedBy()).isEqualTo(bookedBy);
    }







}