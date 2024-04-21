package com.github.rshtishi;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneManagerTest {

    @Test
    @DisplayName("given a phone manager, when select phone, then the phone manger state should be updated")
    public void testSelectPhone() {
        // setup
        PhoneManager phoneManager = new PhoneManager();
        Phone phone = new Phone("1", "Samsung");
        // execute
        phoneManager.selectPhone(phone.getId());
        // verify
        assertTrue(phoneManager.isPhoneSelected());
    }

    @ParameterizedTest
    @DisplayName("given a phone manage, when select phone with invalid id, then it should throw an exception")
    @NullSource
    @ValueSource(strings = {"11", "20","30"})
    public void testSelectPhoneWithInvalidId(String phoneId) {
        // setup
        PhoneManager phoneManager = new PhoneManager();
        Long value =null;
        if(phoneId!=null){
            value = Long.parseLong(phoneId);
        }
        // execute and verify
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> phoneManager.selectPhone(null));
        assertTrue(exception.getMessage().contains("Phone not found!"));
    }

}